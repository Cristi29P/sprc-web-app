package com.sprc_web_app.model.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties()
public class TemperatureRequestDTO {
    private Long id;
    private Long idOras;
    @NotNull
    private Double valoare;
}
