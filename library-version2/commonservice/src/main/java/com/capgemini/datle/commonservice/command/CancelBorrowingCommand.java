package com.capgemini.datle.commonservice.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Value
public class CancelBorrowingCommand {
    @TargetAggregateIdentifier
    private String borrowingId;
    private String status="CANCELED";
}
