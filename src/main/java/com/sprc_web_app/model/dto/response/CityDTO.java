package com.sprc_web_app.model.dto.response;

import lombok.Data;

@Data
public class CityDTO {
    private Long id;

    private Long idTara;

    private String nume;

    private Double lat;

    private Double lon;

}
