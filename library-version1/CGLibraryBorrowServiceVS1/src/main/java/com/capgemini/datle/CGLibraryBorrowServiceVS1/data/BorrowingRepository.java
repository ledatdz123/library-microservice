package com.capgemini.datle.CGLibraryBorrowServiceVS1.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, String> {
    Borrowing findBorrowingByEmployeeIdAndBookId(String employeeId, String bookId);
    List<Borrowing> findBorrowingByEmployeeId(String employeeId);
}
