package com.sitswift.ledger.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sitswift.ledger.dto.TransactionDTO;
import com.sitswift.ledger.model.Transaction;
import com.sitswift.ledger.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    private final ModelMapper modelMapper;

    public TransactionService() {
        this.modelMapper = new ModelMapper();
    }

    public HttpStatus saveTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        transaction.setId(UUID.randomUUID().toString());
        try {
            transactionRepository.save(transaction);
        }
        catch (Exception e) {
            log.error("Failed to save Transaction", e);
            return HttpStatus.EXPECTATION_FAILED;
        }
        return HttpStatus.CREATED;
    }

    public TransactionDTO getTransactionById(String id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isEmpty()) {
            log.error("No Transaction with id : {} found", id);
            return null;
        }
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    public HttpStatus saveTransactionFromCsv(MultipartFile file) {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CsvToBean<Transaction> csvToTransactions = new CsvToBeanBuilder<Transaction>(reader)
                    .withType(Transaction.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Transaction> transactionList = csvToTransactions.parse();
            transactionRepository.saveAll(transactionList);
            return HttpStatus.CREATED;
        } catch (IOException e) {
            log.error("Failed to save Transactions from CSV");
            throw new RuntimeException(e);
        }
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactionList = transactionRepository.findAll();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionList.forEach(t -> transactionDTOList.add(modelMapper.map(t, TransactionDTO.class)));
        return transactionDTOList;
    }
}
