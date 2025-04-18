package com.scaputo.itau_desafio.business.services;

import com.scaputo.itau_desafio.controller.dtos.TransactionRequestDTO;
import com.scaputo.itau_desafio.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private List<TransactionRequestDTO> transactionList = new ArrayList<>();

    public void addTransaction(TransactionRequestDTO transactionRequestDTO) {

        log.info("-----> Iniciando o processamento - Gravação de transação <-----" + transactionRequestDTO);

        if (transactionRequestDTO.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("A data da transação não pode ser futura");
            throw new UnprocessableEntity("A data da transação não pode ser futura");
        }
        if(transactionRequestDTO.valor() < 0) {
            log.error("O valor da transação deve ser maior que zero");
            throw new UnprocessableEntity("O valor da transação deve ser maior que zero");
        }

        transactionList.add(transactionRequestDTO);

        log.info("-----> Finalizando o processamento - Transações adicionadas com sucesso <-----" + transactionRequestDTO);
    }


    public void cleanTransactions() {
        log.info("-----> Iniciando o processamento - Deleção de transações <-----");
        transactionList.clear();
        log.info("-----> Finalizando o processamento - Transações deletadas com sucesso <-----");
    }


    public List<TransactionRequestDTO> searchTransactions(Integer searchInterval) {
        log.info("-----> Iniciando o processamento - Busca de transações <-----");

        OffsetDateTime dateTimeInterval = OffsetDateTime.now().minusSeconds(searchInterval);

        log.info("-----> Finalizando o processamento - Transações retornadas com sucesso <-----");
        return transactionList.stream()
                .filter(transaction -> transaction.dataHora().isAfter(dateTimeInterval))
                .toList();

    }

    public void setTransactionList(List<TransactionRequestDTO> transactionList) {
        this.transactionList = transactionList;
    }

}
