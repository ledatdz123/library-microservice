package com.capgemini.datle.borrowingservicev2.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingAddedEvent {
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private String status;
}
