package com.capgemini.datle.borrowingservicev2.command.api.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
@Builder
public class SendMessageCommand {
    @TargetAggregateIdentifier
    private String borrowingId;
    private String status;
    private String message;
}
