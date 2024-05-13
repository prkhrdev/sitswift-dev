package com.sitswift.ledger.repository;

import com.sitswift.ledger.model.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, String> {
    public Ledger getLedgerById(String id);
}
