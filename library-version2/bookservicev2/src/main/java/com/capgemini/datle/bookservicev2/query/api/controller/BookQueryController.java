package com.capgemini.datle.bookservicev2.query.api.controller;

import com.capgemini.datle.bookservicev2.query.api.model.BookRestModel;
import com.capgemini.datle.bookservicev2.query.api.queries.QueryAllBook;
import com.capgemini.datle.bookservicev2.query.api.queries.QueryBookDetail;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v2/books")
public class BookQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{bookId}")
    public BookRestModel getBookDetails(@PathVariable("bookId") String bookId) {
        QueryBookDetail queryBook = new QueryBookDetail(bookId);

        return queryGateway
                .query(queryBook, ResponseTypes.instanceOf(BookRestModel.class))
                .join();
    }
    @GetMapping
    public List<BookRestModel> getAllBook(){
        QueryAllBook queryAllBook=new QueryAllBook();
        List<BookRestModel> list=queryGateway
                .query(queryAllBook, ResponseTypes.multipleInstancesOf(BookRestModel.class))
                .join();
        return list;
    }
}
