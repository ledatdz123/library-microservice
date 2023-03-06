package com.capgemini.datle.CGlibraryBookServiceVS1.controller;

import com.capgemini.datle.CGlibraryBookServiceVS1.payloads.BookDTO;
import com.capgemini.datle.CGlibraryBookServiceVS1.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private IBookService iBookService;
    @GetMapping("/test")
    public String getBook(){
        return "Book Service";
    }
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        BookDTO createBook= iBookService.addBook(bookDTO);
        return new ResponseEntity<>(createBook, HttpStatus.CREATED);
    }
    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO,
                                              @PathVariable("bookId") String bookId){
        BookDTO updateBook= iBookService.updateBook(bookDTO, bookId);
        return ResponseEntity.ok(updateBook);
    }
    @DeleteMapping("/{bookId}")
    public Boolean deleteBook(@PathVariable("bookId") String bookId){
        return iBookService.deleteBook(bookId);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookDetails(@PathVariable("bookId") String bookId){
        return ResponseEntity.ok(iBookService.getBookDetails(bookId));
    }
    @PutMapping("/updateBookReady")
    public Boolean updateBookReady(@RequestBody BookDTO bookDTO){
        return iBookService.updateBookReady(bookDTO);
    }
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(iBookService.getAllBooks());
    }
}
