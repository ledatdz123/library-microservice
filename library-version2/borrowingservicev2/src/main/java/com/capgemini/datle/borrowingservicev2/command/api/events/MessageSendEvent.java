package com.capgemini.datle.borrowingservicev2.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageSendEvent {
    private String borrowingId;
    private String status;
    private String message;
}
