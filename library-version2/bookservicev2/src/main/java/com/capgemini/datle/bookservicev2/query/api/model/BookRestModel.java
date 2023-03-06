package com.capgemini.datle.bookservicev2.query.api.model;

import lombok.Data;

@Data
public class BookRestModel {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
