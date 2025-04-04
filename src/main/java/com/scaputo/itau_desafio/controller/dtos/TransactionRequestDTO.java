package com.scaputo.itau_desafio.controller.dtos;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(Double valor, OffsetDateTime dataHora) {


}
