package com.capgemini.datle.bookservicev2.command.api.aggregate;

import com.capgemini.datle.bookservicev2.command.api.commands.AddBookCommand;
import com.capgemini.datle.bookservicev2.command.api.commands.DeleteBookCommand;
import com.capgemini.datle.bookservicev2.command.api.commands.UpdateBookCommand;
import com.capgemini.datle.bookservicev2.command.api.events.BookAddedEvent;
import com.capgemini.datle.bookservicev2.command.api.events.BookDeletedEvent;
import com.capgemini.datle.bookservicev2.command.api.events.BookUpdatedEvent;
import com.capgemini.datle.commonservice.command.UpdateBookStatusCommand;
import com.capgemini.datle.commonservice.command.RollBackBookStatusCommand;
import com.capgemini.datle.commonservice.events.BookRollBackedStatusEvent;
import com.capgemini.datle.commonservice.events.BookUpdatedStatusEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
@Slf4j
public class BookAggregate {
    @AggregateIdentifier
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;

    @CommandHandler
    public BookAggregate(AddBookCommand addBookCommand){
        BookAddedEvent bookAddedEvent=new BookAddedEvent();
        BeanUtils.copyProperties(addBookCommand, bookAddedEvent);
        AggregateLifecycle.apply(bookAddedEvent);
    }
    @EventSourcingHandler
    public void on(BookAddedEvent bookAddedEvent){
        this.bookId=bookAddedEvent.getBookId();
        this.name=bookAddedEvent.getName();
        this.author=bookAddedEvent.getAuthor();
        this.isReady=bookAddedEvent.getIsReady();
    }

    @CommandHandler
    public void handle(UpdateBookCommand updateBookCommand){
        BookUpdatedEvent bookUpdatedEvent=new BookUpdatedEvent();
        BeanUtils.copyProperties(updateBookCommand, bookUpdatedEvent);
        AggregateLifecycle.apply(bookUpdatedEvent);
    }
    @EventSourcingHandler
    public void on(BookUpdatedEvent bookUpdatedEvent){
        this.bookId=bookUpdatedEvent.getBookId();
        this.name=bookUpdatedEvent.getName();
        this.author=bookUpdatedEvent.getAuthor();
    }

    @CommandHandler
    public void handle(DeleteBookCommand deleteBookCommand){
        BookDeletedEvent bookDeletedEvent=new BookDeletedEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeletedEvent);
        AggregateLifecycle.apply(bookDeletedEvent);
    }
    @EventSourcingHandler
    public void on(BookDeletedEvent bookDeletedEvent){
        this.bookId=bookDeletedEvent.getBookId();
    }

    @CommandHandler
    public void on(UpdateBookStatusCommand updateBookStatusCommand){
        BookUpdatedStatusEvent eventStatus=new BookUpdatedStatusEvent();
        BeanUtils.copyProperties(updateBookStatusCommand, eventStatus);
        AggregateLifecycle.apply(eventStatus);
    }
    @EventSourcingHandler
    public void on(BookUpdatedStatusEvent event){
        this.bookId=event.getBookId();
        this.isReady=event.getIsReady();
    }
    @CommandHandler
    public void on(RollBackBookStatusCommand rollBackBookStatusCommand){
        BookRollBackedStatusEvent eventRollBack=new BookRollBackedStatusEvent();
        BeanUtils.copyProperties(rollBackBookStatusCommand, eventRollBack);
        AggregateLifecycle.apply(eventRollBack);
    }
    @EventSourcingHandler
    public void on(BookRollBackedStatusEvent event){
        this.bookId=event.getBookId();
        this.isReady=event.getIsReady();
    }
}
