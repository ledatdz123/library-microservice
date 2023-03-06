package com.capgemini.datle.commonservice.model;

import lombok.Data;

@Data
public class BookCommonModel {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
