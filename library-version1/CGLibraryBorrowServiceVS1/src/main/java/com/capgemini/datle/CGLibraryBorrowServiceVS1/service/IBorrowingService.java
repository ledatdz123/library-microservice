package com.capgemini.datle.CGLibraryBorrowServiceVS1.service;

import com.capgemini.datle.CGLibraryBorrowServiceVS1.payloads.BorrowingDTO;

import java.util.List;

public interface IBorrowingService {
    BorrowingDTO addBookBorrowing(BorrowingDTO borrowingDTO);
    BorrowingDTO updateBookReturn(String employeeId, String bookId);
    List<BorrowingDTO> getBorrowingByEmployee(String employeeId);
}
