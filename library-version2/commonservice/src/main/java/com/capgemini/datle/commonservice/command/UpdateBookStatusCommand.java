package com.capgemini.datle.commonservice.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
@Builder
public class UpdateBookStatusCommand {
    @TargetAggregateIdentifier
    private String bookId;
    private String employeeId;
    private Boolean isReady;
    private String borrowingId;
}
