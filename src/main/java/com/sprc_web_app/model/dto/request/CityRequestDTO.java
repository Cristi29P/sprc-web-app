package com.sprc_web_app.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CityRequestDTO {
    private Long idTara;
    @NotEmpty
    private String nume;
    private Double lat;
    private Double lon;
}
