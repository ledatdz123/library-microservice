package com.capgemini.datle.CGLibraryBorrowServiceVS1.controller;

import com.capgemini.datle.CGLibraryBorrowServiceVS1.payloads.BorrowingDTO;
import com.capgemini.datle.CGLibraryBorrowServiceVS1.service.IBorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrowing")
public class BorrowingController {
    @Autowired
    private IBorrowingService iBorrowingService;

    @GetMapping("/test")
    public String getBorrowing() {
        return "hello borrowing";
    }

    @PostMapping
    public ResponseEntity<BorrowingDTO> addBookBorrowing(@RequestBody BorrowingDTO borrowingDTO) {
        BorrowingDTO create = iBorrowingService.addBookBorrowing(borrowingDTO);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}/{bookId}")
    public ResponseEntity<BorrowingDTO> updateBookReturn(@PathVariable("employeeId") String employeeId,
                                                         @PathVariable("bookId") String bookId) {
        BorrowingDTO update = iBorrowingService.updateBookReturn(employeeId, bookId);
        return ResponseEntity.ok(update);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<BorrowingDTO>> getBorrowingByEmployee(@PathVariable("employeeId") String employeeId) {
        List<BorrowingDTO> list = iBorrowingService.getBorrowingByEmployee(employeeId);
        return ResponseEntity.ok(list);
    }
}
