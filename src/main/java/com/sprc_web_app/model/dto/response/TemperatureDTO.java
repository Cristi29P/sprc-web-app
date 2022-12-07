package com.sprc_web_app.model.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TemperatureDTO {
    private Long id;

    private Double valoare;

    private LocalDateTime timestamp;
}
