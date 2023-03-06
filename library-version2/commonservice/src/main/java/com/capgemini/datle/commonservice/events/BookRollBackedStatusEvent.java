package com.capgemini.datle.commonservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRollBackedStatusEvent {
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Boolean isReady;
}
