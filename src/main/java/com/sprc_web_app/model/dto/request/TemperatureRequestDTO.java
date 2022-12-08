package com.sprc_web_app.model.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TemperatureRequestDTO {

    private long idOras;

    @NotNull
    private Double valoare;
}
