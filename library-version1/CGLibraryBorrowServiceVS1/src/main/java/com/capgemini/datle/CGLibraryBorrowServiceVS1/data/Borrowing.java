package com.capgemini.datle.CGLibraryBorrowServiceVS1.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@Entity
@IdClass(value = CompositeKey.class)
@Table(name = "borrowings")
public class Borrowing{
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
