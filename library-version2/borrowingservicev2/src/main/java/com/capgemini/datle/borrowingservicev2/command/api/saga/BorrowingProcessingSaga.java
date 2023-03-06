package com.capgemini.datle.borrowingservicev2.command.api.saga;

import com.capgemini.datle.borrowingservicev2.command.api.command.SendMessageCommand;
import com.capgemini.datle.borrowingservicev2.command.api.events.BorrowingAddedEvent;
import com.capgemini.datle.borrowingservicev2.command.api.events.BorrowingEvent;
import com.capgemini.datle.commonservice.command.CancelBorrowingCommand;
import com.capgemini.datle.commonservice.command.CompleteBorrowingCommand;
import com.capgemini.datle.commonservice.command.RollBackBookStatusCommand;
import com.capgemini.datle.commonservice.command.UpdateBookStatusCommand;
import com.capgemini.datle.commonservice.events.BookUpdatedStatusEvent;
import com.capgemini.datle.commonservice.model.BookCommonModel;
import com.capgemini.datle.commonservice.model.EmployeeCommonModel;
import com.capgemini.datle.commonservice.queries.GetBookDetail;
import com.capgemini.datle.commonservice.queries.GetEmployeeDetail;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Saga
@NoArgsConstructor
@Slf4j
public class BorrowingProcessingSaga {
    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;
    @StartSaga
    @SagaEventHandler(associationProperty = "borrowingId")
    public void handle(BorrowingAddedEvent event) {
        log.info("BorrowingAddedEvent in Saga for Borrowing Id : {}, BookId : {}, EmployeeId : {}",
                event.getBorrowingId(), event.getBookId(), event.getEmployeeId());
        try {
            SagaLifecycle.associateWith("bookId", event.getBookId());
            BookCommonModel bookDetailCheck = null;
            GetBookDetail getBookDetail = new GetBookDetail(event.getBookId());
            System.out.println(getBookDetail);

            bookDetailCheck = queryGateway
                    .query(
                            getBookDetail,
                            ResponseTypes.instanceOf(BookCommonModel.class)
                    ).join();
            log.info("Fetch Book from BookService with bookId: {}, bookName: {} ",
                    bookDetailCheck.getBookId(), bookDetailCheck.getName());
            if(bookDetailCheck.getIsReady()==true){
                UpdateBookStatusCommand bookUpdateStatus = UpdateBookStatusCommand
                        .builder()
                        .bookId(event.getBookId())
                        .borrowingId(event.getBorrowingId())
                        .employeeId(event.getEmployeeId())
                        .isReady(false)
                        .build();
                commandGateway.sendAndWait(bookUpdateStatus);
            }
            else{
                SendMessageCommand sendMessageCommand=SendMessageCommand
                        .builder()
                        .borrowingId(event.getBorrowingId())
                        .status("CANCEL")
                        .message("sach da co nguoi muon")
                        .build();
                commandGateway.sendAndWait(sendMessageCommand);
                throw new Exception("Book Id:"+event.getBookId()+" da duoc muon ");
            }
        } catch (Exception e) {
            this.cancelBorrowingCommand(event.getBorrowingId());
            log.error(e.getMessage());
        }
    }

    @SagaEventHandler(associationProperty = "bookId")
    public void handle(BookUpdatedStatusEvent event) {
        log.info("BorrowingAddedEvent in Saga for BookId : {}", event.getBookId());
        try {
            GetEmployeeDetail getEmployeeDetail = new GetEmployeeDetail(event.getEmployeeId());
            EmployeeCommonModel employeeDetail = null;
            try {
                employeeDetail = queryGateway
                        .query(
                                getEmployeeDetail,
                                ResponseTypes.instanceOf(EmployeeCommonModel.class))
                        .join();
                log.info("Fetch Employee from EmployeeService in Saga with EmployeeId" +
                                ": {}, employeeFirstName: {}, employeeLastName: {}",
                        employeeDetail.getEmployeeId(), employeeDetail.getFirstName(), employeeDetail.getLastName());

            } catch (Exception e) {
                this.cancelBorrowingCommand(event.getBorrowingId());
                SendMessageCommand sendMessageCommand=SendMessageCommand
                        .builder()
                        .borrowingId(event.getBorrowingId())
                        .status("CANCEL")
                        .message("no success")
                        .build();
                commandGateway.sendAndWait(sendMessageCommand);
                log.info(e.getMessage());
            }
            if (employeeDetail.isDisciplined==true) {
                log.error("Employee with Id" + employeeDetail.getEmployeeId() + "has been disciplined");
                this.cancelBorrowingCommand(event.getBorrowingId());
            }
            else{
                CompleteBorrowingCommand completeBorrowingCommand=CompleteBorrowingCommand
                        .builder()
                        .borrowingId(event.getBorrowingId())
                        .status("BORROWED")
                        .build();
                commandGateway.sendAndWait(SendMessageCommand
                        .builder()
                        .borrowingId(completeBorrowingCommand.getBorrowingId())
                        .status(completeBorrowingCommand.getStatus())
                        .message("success")
                        .build()
                );
                commandGateway.sendAndWait(completeBorrowingCommand);
                SagaLifecycle.end();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            rollBackBookStatus(event.getBorrowingId(), event.getBookId(), event.getEmployeeId());
        }
    }

    public void cancelBorrowingCommand(String borrowingId) {
        CancelBorrowingCommand cancelBorrowingCommand
                = new CancelBorrowingCommand(borrowingId);
        commandGateway.send(cancelBorrowingCommand);
    }

    public void rollBackBookStatus(String borrowingId, String bookId, String employeeId) {
        RollBackBookStatusCommand rollBackBookStatusCommand
                = RollBackBookStatusCommand
                .builder()
                .borrowingId(borrowingId)
                .bookId(bookId)
                .employeeId(employeeId)
                .isReady(true)
                .build();
        commandGateway.sendAndWait(rollBackBookStatusCommand);
    }
}
