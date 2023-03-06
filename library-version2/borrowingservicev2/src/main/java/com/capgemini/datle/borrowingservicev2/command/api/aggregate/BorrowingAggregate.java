package com.capgemini.datle.borrowingservicev2.command.api.aggregate;

import com.capgemini.datle.borrowingservicev2.command.api.command.AddBorrowingCommand;
import com.capgemini.datle.borrowingservicev2.command.api.command.SendMessageCommand;
import com.capgemini.datle.borrowingservicev2.command.api.events.BorrowingAddedEvent;
import com.capgemini.datle.borrowingservicev2.command.api.events.MessageSendEvent;
import com.capgemini.datle.commonservice.command.CancelBorrowingCommand;
import com.capgemini.datle.commonservice.command.CompleteBorrowingCommand;
import com.capgemini.datle.commonservice.events.BorrowingCanceledEvent;
import com.capgemini.datle.commonservice.events.BorrowingCompletedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import java.util.Date;

@Aggregate
public class BorrowingAggregate {
    @AggregateIdentifier
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
    private String status;
    private String message;
    public BorrowingAggregate() {
    }

    @CommandHandler
    public BorrowingAggregate(AddBorrowingCommand command){
        BorrowingAddedEvent event=new BorrowingAddedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(BorrowingAddedEvent event){
        this.borrowingId=event.getBorrowingId();
        this.bookId=event.getBookId();
        this.employeeId=event.getEmployeeId();
        this.borrowingDate=event.getBorrowingDate();
        this.status=event.getStatus();
    }

    @CommandHandler
    public void on(CompleteBorrowingCommand command){
        BorrowingCompletedEvent event=BorrowingCompletedEvent
                .builder()
                .borrowingId(command.getBorrowingId())
                .status(command.getStatus())
                .build();
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(BorrowingCompletedEvent event){
        this.status= event.getStatus();
    }

    @CommandHandler
    public void on(CancelBorrowingCommand command){
        BorrowingCanceledEvent event= new BorrowingCanceledEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(BorrowingCanceledEvent event){
        this.status= event.getStatus();
    }
    @CommandHandler
    public void on(SendMessageCommand sendMessageCommand){
        MessageSendEvent event=new MessageSendEvent();
        BeanUtils.copyProperties(sendMessageCommand, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(MessageSendEvent event){
        this.borrowingId=event.getBorrowingId();
        this.status=event.getStatus();
        this.message=event.getMessage();
    }
}
