package com.sprc_web_app.model.dto.response;

import lombok.Data;

@Data
public class CountryDTO {
    private Long id;
    private String nume_tara;
    private Double latitudine;
    private Double longitudine;
}
