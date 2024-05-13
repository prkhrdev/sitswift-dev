package com.sitswift.ledger.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {
    @JsonFormat(pattern = "dd/MM/yyyy")
    // TODO: Check if we also need to store the time info
    private LocalDate date;
    private Double amount;
    // TODO : Check if this needs to be an enum
    private String voucherType;
    private String voucherNumber;
    private String refHead;
}
