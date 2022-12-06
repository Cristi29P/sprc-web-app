package com.sprc_web_app.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class CityEntity {
    @Id
    @GeneratedValue(generator = "cities_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tara", unique = true)
    private CountryEntity country;

    @Column(name = "nume_oras", unique = true)
    private String nume;

    @Column(name = "latitudine")
    private Double lat;

    @Column(name = "longitudine")
    private Double lon;

    @OneToMany(mappedBy = "city")
    private List<TemperatureEntity> temperatures;
}
