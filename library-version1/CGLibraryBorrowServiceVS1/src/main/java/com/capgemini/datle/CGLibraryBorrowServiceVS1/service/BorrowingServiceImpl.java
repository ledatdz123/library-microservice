package com.capgemini.datle.CGLibraryBorrowServiceVS1.service;

import com.capgemini.datle.CGLibraryBorrowServiceVS1.data.Borrowing;
import com.capgemini.datle.CGLibraryBorrowServiceVS1.data.BorrowingRepository;
import com.capgemini.datle.CGLibraryBorrowServiceVS1.payloads.BookDTO;
import com.capgemini.datle.CGLibraryBorrowServiceVS1.payloads.BorrowingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingServiceImpl implements IBorrowingService {
    @Autowired
    private BorrowingRepository borrowingRepository;
    @Autowired
    private WebClient.Builder webClient;

    @Override
    public BorrowingDTO addBookBorrowing(BorrowingDTO borrowingDTO) {
        Borrowing borrowing = new Borrowing();
        borrowing.setEmployeeId(borrowingDTO.getEmployeeId());
        borrowing.setBookId(borrowingDTO.getBookId());
        borrowing.setBorrowingDate(new Date());
        Borrowing saveBorrowing=borrowingRepository.save(borrowing);
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(borrowingDTO.getBookId());
        bookDTO.setReady(false);
        Boolean updateReady = webClient
                .build()
                .put()
                .uri("http://localhost:8090/api/v1/books/updateBookReady")
                .body(Mono.just(bookDTO), BookDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return borrowingToDTO(saveBorrowing);
    }

    @Override
    public BorrowingDTO updateBookReturn(String employeeId, String bookId) {
        Borrowing borrowing = borrowingRepository.findBorrowingByEmployeeIdAndBookId(employeeId, bookId);
        borrowing.setReturnDate(new Date());
        Borrowing saveBorrowing = borrowingRepository.save(borrowing);
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(bookId);
        bookDTO.setReady(true);
        Boolean updateReady = webClient
                .build()
                .put()
                .uri("http://localhost:8090/api/v1/books/updateBookReady")
                .body(Mono.just(bookDTO), BookDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return borrowingToDTO(saveBorrowing);
    }

    @Override
    public List<BorrowingDTO> getBorrowingByEmployee(String employeeId) {
        List<Borrowing> list = borrowingRepository.findBorrowingByEmployeeId(employeeId);
        List<BorrowingDTO> listDTO = list
                .stream()
                .map(borrowing -> borrowingToDTO(borrowing))
                .collect(Collectors.toList());
        return listDTO;
    }

    public BorrowingDTO borrowingToDTO(Borrowing borrowing) {
        BorrowingDTO borrowingDTO = new BorrowingDTO();
        borrowingDTO.setEmployeeId(borrowing.getEmployeeId());
        borrowingDTO.setBookId(borrowing.getBookId());
        borrowingDTO.setBorrowingDate(borrowing.getBorrowingDate());
        borrowingDTO.setReturnDate(borrowing.getReturnDate());
        return borrowingDTO;
    }
}
