package com.capgemini.datle.commonservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingCommonModel {
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
    private String status;
}
