package com.capgemini.datle.bookservicev2.query.api.handler;

import com.capgemini.datle.bookservicev2.command.api.data.Book;
import com.capgemini.datle.bookservicev2.command.api.data.BookRepository;
import com.capgemini.datle.bookservicev2.query.api.model.BookRestModel;
import com.capgemini.datle.bookservicev2.query.api.queries.QueryAllBook;
import com.capgemini.datle.bookservicev2.query.api.queries.QueryBookDetail;
import com.capgemini.datle.commonservice.model.BookCommonModel;
import com.capgemini.datle.commonservice.queries.GetAllBook;
import com.capgemini.datle.commonservice.queries.GetBookDetail;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookQueryHandler {
    private BookRepository bookRepository;

    public BookQueryHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @QueryHandler
    public BookRestModel getBookDetails(QueryBookDetail queryBookDetail) {
        var bookOptional = bookRepository.findById(queryBookDetail.getBookId());
        BookRestModel bookModel = new BookRestModel();
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            BeanUtils.copyProperties(book, bookModel);
        }
        return bookModel;
    }

    @QueryHandler
    public List<BookRestModel> getAllBook(QueryAllBook queryAllBook){
        List<Book> bookList=bookRepository.findAll();
        List<BookRestModel> books=new ArrayList<>();
        BookRestModel bookRestModel=null;
        for (Book bookEntity: bookList
             ) {
            bookRestModel=new BookRestModel();
            BeanUtils.copyProperties(bookEntity, bookRestModel);
            books.add(bookRestModel);
        }
        return books;
    }
    @QueryHandler
    public List<BookCommonModel> getAllBook(GetAllBook queryAllBook){
        List<Book> bookList=bookRepository.findAll();
        List<BookCommonModel> books=new ArrayList<>();
        BookCommonModel bookCommonModel=null;
        for (Book bookEntity: bookList
        ) {
            bookCommonModel=new BookCommonModel();
            BeanUtils.copyProperties(bookEntity, bookCommonModel);
            books.add(bookCommonModel);
        }
        return books;
    }
    @QueryHandler
    public BookCommonModel getBookCommandDetail(GetBookDetail getBookDetail){
        var bookOptional=bookRepository.findById(getBookDetail.getBookId());
        BookCommonModel bookCommonModel=new BookCommonModel();
        if (bookOptional.isPresent()){
            Book book=bookOptional.get();
            BeanUtils.copyProperties(book, bookCommonModel);
        }
        return bookCommonModel;
    }
}
