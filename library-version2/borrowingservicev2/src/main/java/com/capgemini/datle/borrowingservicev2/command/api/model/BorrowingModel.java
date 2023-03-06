package com.capgemini.datle.borrowingservicev2.command.api.model;

import lombok.Data;

import java.util.Date;
@Data
public class BorrowingModel {
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
    private String status;
}
