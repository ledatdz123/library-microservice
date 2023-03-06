package com.capgemini.datle.borrowingservicev2.command.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, String> {
    List<Borrowing> findBorrowingByEmployeeIdAndReturnDateIsNull(String employeeId);
}
