package com.capgemini.datle.CGLibraryBorrowServiceVS1.data;

import java.io.Serializable;
import java.util.Date;

public class CompositeKey implements Serializable {
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
}
