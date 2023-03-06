package com.capgemini.datle.bookservicev2.command.api.controller;

import com.capgemini.datle.bookservicev2.command.api.commands.AddBookCommand;
import com.capgemini.datle.bookservicev2.command.api.commands.DeleteBookCommand;
import com.capgemini.datle.bookservicev2.command.api.commands.UpdateBookCommand;
import com.capgemini.datle.bookservicev2.command.api.model.BookModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v2/books")
public class BookController {
    @Autowired
    private CommandGateway commandGateway;

    public BookController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addBook(@RequestBody BookModel bookModel){
        AddBookCommand addBookCommand= AddBookCommand
                .builder()
                .bookId(UUID.randomUUID().toString())
                .name(bookModel.getName())
                .author(bookModel.getAuthor())
                .isReady(true)
                .build();
        commandGateway.sendAndWait(addBookCommand);
        return "add book success";
    }
    @PutMapping
    public String updateBook(@RequestBody BookModel bookModel){
        UpdateBookCommand updateBookCommand=UpdateBookCommand
                .builder()
                .bookId(bookModel.getBookId())
                .name(bookModel.getName())
                .author(bookModel.getAuthor())
                .isReady(bookModel.getIsReady())
                .build();
        commandGateway.sendAndWait(updateBookCommand);
        return "update book success";
    }
    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable("bookId") String Id){
        DeleteBookCommand deleteBookCommand=DeleteBookCommand
                .builder()
                .bookId(Id)
                .build();
        commandGateway.sendAndWait(deleteBookCommand);
        return "delete book success";
    }
}
