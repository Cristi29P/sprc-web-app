package com.sprc_web_app.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class CountryEntity {
    @Id
    @GeneratedValue(generator = "countries_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nume_tara", unique = true)
    private String nume;

    @Column(name = "latitudine")
    private Double lat;

    @Column(name = "longitudine")
    private Double lon;

    @OneToMany(mappedBy = "country")
    private List<CityEntity> cities;
}
