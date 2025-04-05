package com.scaputo.itau_desafio.controller;

import com.scaputo.itau_desafio.business.services.TransactionService;
import com.scaputo.itau_desafio.controller.dtos.TransactionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint responsavel por adicionar transaçoes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação adicionada com sucesso"),
            @ApiResponse(responseCode = "422", description = "A data da transação nao pode ser futura"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {

        transactionService.addTransaction(transactionRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsavel por deletar todas as transacoes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição")
    })
    public ResponseEntity<Void> deleteTransactions() {
        transactionService.cleanTransactions();
        return ResponseEntity.ok().build();
    }
}
