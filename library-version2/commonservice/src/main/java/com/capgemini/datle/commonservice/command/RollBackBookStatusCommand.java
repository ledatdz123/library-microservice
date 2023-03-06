package com.capgemini.datle.commonservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
@Builder
public class RollBackBookStatusCommand {
    @TargetAggregateIdentifier
    private String bookId;
    private String employeeId;
    private Boolean isReady;
    private String borrowingId;
}

