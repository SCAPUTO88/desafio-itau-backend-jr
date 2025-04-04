package com.scaputo.itau_desafio.controller;

import com.scaputo.itau_desafio.business.services.StatisticsService;
import com.scaputo.itau_desafio.controller.dtos.StatisticsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping
    public ResponseEntity<StatisticsResponseDTO> getStatistics(
            @RequestParam(value = "searchInterval", required = false, defaultValue = "60") Integer searchInterval) {
        statisticsService.getStatistics(searchInterval);
        return ResponseEntity.ok(statisticsService.getStatistics(searchInterval));
    }
}
