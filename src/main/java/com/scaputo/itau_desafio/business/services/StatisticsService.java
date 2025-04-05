package com.scaputo.itau_desafio.business.services;

import com.scaputo.itau_desafio.controller.dtos.StatisticsResponseDTO;
import com.scaputo.itau_desafio.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    public final TransactionService transactionService;

    public StatisticsResponseDTO getStatistics(Integer searchInterval) {

        log.info("-----> Iniciando o processamento - Busca de transações <-----");
        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(searchInterval);

        if (transactions.isEmpty()) {

            return new StatisticsResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics statistics = transactions.stream().mapToDouble(TransactionRequestDTO::valor).summaryStatistics();

        log.info("-----> Finalizando o processamento - Estatisticas retornadas com sucesso <-----");
        return new StatisticsResponseDTO(statistics.getCount(),
                statistics.getSum(),
                statistics.getAverage(),
                statistics.getMin(),
                statistics.getMax());
    }
}
