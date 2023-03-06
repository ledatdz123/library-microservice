package com.capgemini.datle.CGlibraryBookServiceVS1.service;

import com.capgemini.datle.CGlibraryBookServiceVS1.data.Book;
import com.capgemini.datle.CGlibraryBookServiceVS1.data.BookRepository;
import com.capgemini.datle.CGlibraryBookServiceVS1.exceptions.ResourceNotFoundException;
import com.capgemini.datle.CGlibraryBookServiceVS1.payloads.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class BookServiceImpl implements  IBookService{
    @Autowired
    private BookRepository bookRepo;
    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book=new Book();
        book.setBookId(UUID.randomUUID().toString());
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        book.setReady(true);
        Book saveBook=bookRepo.save(book);
        return bookToBookDTO(saveBook);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, String bookId) {
        Book book=bookRepo.findById(bookId)
                .orElseThrow(()->new ResourceNotFoundException("Book", "Id", bookId));
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        Book updateBook=bookRepo.save(book);
        return bookToBookDTO(updateBook);
    }

    @Override
    public Boolean deleteBook(String bookId) {
        Book book=bookRepo.findById(bookId)
                .orElseThrow(()->new ResourceNotFoundException("User", "Id", bookId));
        try {
            bookRepo.delete(book);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public BookDTO getBookDetails(String bookId) {
        Book book=bookRepo.findById(bookId)
                .orElseThrow(()->new ResourceNotFoundException("User", "Id", bookId));
        return bookToBookDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books= bookRepo.findAll();
        List<BookDTO> bookDTOs=books.stream().map(book->bookToBookDTO(book)).collect(Collectors.toList());
        return bookDTOs;
    }

    @Override
    public Boolean updateBookReady(BookDTO bookDTO) {
       Optional<Book> book=bookRepo.findById(bookDTO.getBookId());
       if(book.isPresent()){
           book.get().setReady(bookDTO.getReady());
           bookRepo.save(book.get());
           return true;
       }
       else{
           return false;
       }
    }

    public BookDTO bookToBookDTO(Book book){
        BookDTO bookDTO=new BookDTO();
        bookDTO.setBookId(book.getBookId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setReady(book.getReady());
        return bookDTO;
    }
}
