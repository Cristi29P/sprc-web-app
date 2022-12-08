package com.sprc_web_app.model.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class TemperatureRequestDTO {
    @Min(0)
    private long idOras;

    private double valoare;
}
