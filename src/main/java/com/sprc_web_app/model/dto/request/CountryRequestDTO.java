package com.sprc_web_app.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CountryRequestDTO {
    @NotEmpty
    private String nume;

    private double lat;

    private double lon;
}
