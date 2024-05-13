package com.sitswift.ledger.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transaction_file")
public class Transaction {
    @Id
    @Column(name = "id")
    @CsvBindByName(column = "ID")
    private String id;
    @Column(name="date")
    @CsvBindByName(column = "DATE")
    @CsvDate("dd/MM/yyyy")
    private LocalDate date;
    @Column(name="amount")
    @CsvBindByName(column = "Amt")
    private Double amount;
    @Column(name="voucher_type")
    @CsvBindByName(column = "VType")
    private String voucherType;
    @Column(name="voucher_number")
    @CsvBindByName(column = "V No")
    private String voucherNumber;
    @Column(name="ref_head")
    @CsvBindByName(column = "REF")
    private String refHead;
}
