package com.capgemini.datle.borrowingservicev2.query.api.queries;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetBorrowingByEmployee {
    private String emplyeeId;
}
