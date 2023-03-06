package com.capgemini.datle.bookservicev2.command.api.events;

import com.capgemini.datle.bookservicev2.command.api.data.Book;
import com.capgemini.datle.bookservicev2.command.api.data.BookRepository;
import com.capgemini.datle.commonservice.events.BookRollBackedStatusEvent;
import com.capgemini.datle.commonservice.events.BookUpdatedStatusEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookEventHandler {
    private BookRepository bookRepository;

    public BookEventHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @EventHandler
    public void on(BookAddedEvent bookAddedEvent){
        Book book=new Book();
        BeanUtils.copyProperties(bookAddedEvent, book);
        bookRepository.save(book);
    }
    @EventHandler
    public void on(BookUpdatedEvent bookUpdatedEvent){
        Book book=new Book();
        BeanUtils.copyProperties(bookUpdatedEvent, book);
        bookRepository.save(book);
    }
    @EventHandler
    public void on(BookDeletedEvent bookDeletedEvent){
        Book book=new Book();
        BeanUtils.copyProperties(bookDeletedEvent, book);
        bookRepository.delete(book);
    }
    @EventHandler
    public void on(BookUpdatedStatusEvent event){
        Book book=bookRepository.findById(event.getBookId()).get();
        book.setIsReady(event.getIsReady());
        bookRepository.save(book);
    }
    @EventHandler
    public void on(BookRollBackedStatusEvent event){
        Book book=bookRepository.findById(event.getBookId()).get();
        book.setIsReady(event.getIsReady());
        bookRepository.save(book);
    }
}
