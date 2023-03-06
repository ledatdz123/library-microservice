package com.capgemini.datle.CGlibraryBookServiceVS1.service;

import com.capgemini.datle.CGlibraryBookServiceVS1.payloads.BookDTO;

import java.util.List;

public interface IBookService {

    BookDTO addBook(BookDTO bookDTO);
    BookDTO updateBook(BookDTO bookDTO, String bookId);
    Boolean deleteBook(String bookId);
    BookDTO getBookDetails(String bookId);
    List<BookDTO> getAllBooks();
    Boolean updateBookReady(BookDTO bookDTO);
}
