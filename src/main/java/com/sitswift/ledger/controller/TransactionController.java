package com.sitswift.ledger.controller;

import com.sitswift.ledger.dto.TransactionDTO;
import com.sitswift.ledger.model.Transaction;
import com.sitswift.ledger.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/detail/{id}")
    public TransactionDTO getTransactionDetailsById(@PathVariable String id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/all")
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public HttpStatus saveTransaction(@RequestBody @Validated TransactionDTO transactionDTO) {
        return transactionService.saveTransaction(transactionDTO);
    }

    @PostMapping("/upload-by-csv")
    public HttpStatus uploadByCsv(@RequestParam("file") MultipartFile file) {
        return transactionService.saveTransactionFromCsv(file);
    }

}
