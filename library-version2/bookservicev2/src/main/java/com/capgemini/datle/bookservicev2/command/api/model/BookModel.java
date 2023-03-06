package com.capgemini.datle.bookservicev2.command.api.model;

import lombok.Data;

@Data
public class BookModel {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
