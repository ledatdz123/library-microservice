package com.capgemini.datle.borrowingservicev2.command.api.events;

import com.capgemini.datle.borrowingservicev2.command.api.data.Borrowing;
import com.capgemini.datle.borrowingservicev2.command.api.data.BorrowingRepository;
import com.capgemini.datle.borrowingservicev2.command.api.model.Message;
import com.capgemini.datle.borrowingservicev2.command.api.producer.BorrowingProducer;
import com.capgemini.datle.commonservice.events.BorrowingCanceledEvent;
import com.capgemini.datle.commonservice.events.BorrowingCompletedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BorrowingEventHandler {
    private final BorrowingRepository borrowingRepository;
    private final BorrowingProducer borrowingProducer;
    @EventHandler
    public void on(BorrowingAddedEvent event){
        Borrowing borrowing=new Borrowing();
        BeanUtils.copyProperties(event, borrowing);
        borrowingRepository.save(borrowing);
    }

    @EventHandler
    public void on(BorrowingCompletedEvent event){
        Borrowing borrowing=borrowingRepository.findById(event.getBorrowingId()).get();
        borrowing.setStatus(event.getStatus());
        borrowingRepository.save(borrowing);
    }

    @EventHandler
    public void on(BorrowingCanceledEvent event){
        Borrowing borrowing=borrowingRepository
                .findById(event.getBorrowingId()).get();
        borrowing.setStatus(event.getStatus());
        borrowingRepository.save(borrowing);
    }
    @EventHandler
    public void on(MessageSendEvent event) throws JsonProcessingException {
        var message= Message
                .builder()
                .borrowingId(event.getBorrowingId())
                .status(event.getStatus())
                .message(event.getMessage())
                .build();
        borrowingProducer.sendMessage(message);
    }
}