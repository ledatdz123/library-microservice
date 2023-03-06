package com.capgemini.datle.borrowingservicev2.command.api.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "borrowings")
@Data
public class Borrowing {
    @Id
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
    private String status;
}
