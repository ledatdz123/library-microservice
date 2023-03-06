package com.capgemini.datle.commonservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
@Builder
public class CompleteBorrowingCommand {
    @TargetAggregateIdentifier
    private String borrowingId;
    private String status;
}
