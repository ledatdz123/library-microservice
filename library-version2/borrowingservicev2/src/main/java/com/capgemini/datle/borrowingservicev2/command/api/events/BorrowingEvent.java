package com.capgemini.datle.borrowingservicev2.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingEvent {
    private String employeeId;
    private String bookId;
    private String message;
}
