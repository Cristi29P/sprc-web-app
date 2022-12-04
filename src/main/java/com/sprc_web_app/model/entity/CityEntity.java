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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tara", unique = true)
    private CountryEntity country;

    @Column(name = "nume_oras", unique = true)
    private String nume_oras;

    @Column(name = "latitudine")
    private Double latitudine;

    @Column(name = "longitudine")
    private Double longitudine;

    @OneToMany(mappedBy = "city")
    private List<TemperatureEntity> temperatures;
}
