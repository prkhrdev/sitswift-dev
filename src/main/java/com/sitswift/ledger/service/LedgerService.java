package com.sitswift.ledger.service;

import com.sitswift.ledger.dto.LedgerDTO;
import com.sitswift.ledger.model.Ledger;
import com.sitswift.ledger.repository.LedgerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class LedgerService {
    @Autowired
    private LedgerRepository ledgerRepository;
    private final ModelMapper modelMapper;

    public LedgerService() {
        this.modelMapper = new ModelMapper();
    }

    public LedgerDTO getLedgerById(String id) {
         Optional<Ledger> ledger = ledgerRepository.findById(id);
         if(ledger.isEmpty()) {
             log.error("No ledger by Id : {} exists", id);
             return null;
         }
         return modelMapper.map(ledger, LedgerDTO.class);
    }

    public HttpStatus saveLedger(LedgerDTO ledgerDTO) {
        try {
            Ledger ledger = modelMapper.map(ledgerDTO, Ledger.class);
            ledger.setId(UUID.randomUUID().toString());
            ledgerRepository.save(ledger);
        }
        catch (Exception e) {
            log.error("failed to save ledger", e);
            return HttpStatus.EXPECTATION_FAILED;
        }
        return HttpStatus.CREATED;
    }
}
