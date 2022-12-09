package com.sprc_web_app.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonIgnoreProperties()
public class CountryRequestDTO {
    private Long id;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Country name must only contain letters")
    private String nume;
    private Double lat;
    private Double lon;
}
