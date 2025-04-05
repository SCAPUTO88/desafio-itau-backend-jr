package com.scaputo.itau_desafio.controller;

import com.scaputo.itau_desafio.business.services.StatisticsService;
import com.scaputo.itau_desafio.controller.dtos.StatisticsResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar as estatisticas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estatisticas retornadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição")
    })
    public ResponseEntity<StatisticsResponseDTO> getStatistics(
            @RequestParam(value = "searchInterval", required = false, defaultValue = "60") Integer searchInterval) {
        statisticsService.getStatistics(searchInterval);
        return ResponseEntity.ok(statisticsService.getStatistics(searchInterval));
    }
}
