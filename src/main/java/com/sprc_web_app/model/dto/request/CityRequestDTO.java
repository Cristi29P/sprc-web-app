package com.sprc_web_app.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CityRequestDTO {

    private Long idTara;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Country name must contain only letters")
    private String nume;
    private Double lat;
    private Double lon;
}
