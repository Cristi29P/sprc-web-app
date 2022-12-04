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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nume_tara", unique = true)
    private String nume_tara;

    @Column(name = "latitudine")
    private Double latitudine;

    @Column(name = "longitudine")
    private Double longitudine;

    @OneToMany(mappedBy = "country")
    private List<CityEntity> cities;
}
