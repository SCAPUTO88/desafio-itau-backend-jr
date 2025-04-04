package com.scaputo.itau_desafio.controller;

import com.scaputo.itau_desafio.business.services.TransactionService;
import com.scaputo.itau_desafio.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {

        transactionService.addTransaction(transactionRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransactions() {
        transactionService.cleanTransactions();
        return ResponseEntity.ok().build();
    }
}
