package com.sprc_web_app.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CityRequestDTO {
    @Min(0)
    private long idTara;
    @NotEmpty
    private String nume;
    private double lat;
    private double lon;
}
