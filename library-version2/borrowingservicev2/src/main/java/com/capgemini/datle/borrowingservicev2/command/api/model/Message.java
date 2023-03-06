package com.capgemini.datle.borrowingservicev2.command.api.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private String borrowingId;
    private String status;
    private String message;
}
