package com.capgemini.datle.borrowingservicev2.command.api.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;
@Data
@Builder
public class AddBorrowingCommand {
    @TargetAggregateIdentifier
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private String status;
}
