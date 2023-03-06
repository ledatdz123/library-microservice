package com.capgemini.datle.bookservicev2.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookAddedEvent {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
