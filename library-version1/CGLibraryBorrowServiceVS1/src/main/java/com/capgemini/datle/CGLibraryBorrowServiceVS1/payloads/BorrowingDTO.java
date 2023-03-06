package com.capgemini.datle.CGLibraryBorrowServiceVS1.payloads;

import javax.persistence.Id;
import java.util.Date;

public class BorrowingDTO {
    @Id
    private String bookId;
    @Id
    private String employeeId;
    @Id
    private Date borrowingDate;


    private Date returnDate;



    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public Date getBorrowingDate() {
        return borrowingDate;
    }
    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
