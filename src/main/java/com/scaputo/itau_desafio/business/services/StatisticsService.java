package com.scaputo.itau_desafio.business.services;

import com.scaputo.itau_desafio.controller.dtos.StatisticsResponseDTO;
import com.scaputo.itau_desafio.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    public final TransactionService transactionService;

    public StatisticsResponseDTO getStatistics(Integer searchInterval) {

        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(searchInterval);

        DoubleSummaryStatistics statistics = transactions.stream().mapToDouble(TransactionRequestDTO::valor).summaryStatistics();

        return new StatisticsResponseDTO(statistics.getCount(),
                statistics.getSum(),
                statistics.getAverage(),
                statistics.getMin(),
                statistics.getMax());
    }
}
