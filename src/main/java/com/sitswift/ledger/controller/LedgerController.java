package com.sitswift.ledger.controller;

import com.sitswift.ledger.dto.LedgerDTO;
import com.sitswift.ledger.service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ledger")
public class LedgerController {
    @Autowired
    private LedgerService ledgerService;
    @GetMapping("/detail/{id}")
    public LedgerDTO getLedgerById(@PathVariable String id) {
        return ledgerService.getLedgerById(id);
    }

    // For testing, remove later
    @GetMapping("/hello")
    public String test(){
        return "Hello World";
    }

    @PostMapping
    public HttpStatus saveLedger(@RequestBody @Validated LedgerDTO ledgerDTO) {
        return ledgerService.saveLedger(ledgerDTO);
    }
}
