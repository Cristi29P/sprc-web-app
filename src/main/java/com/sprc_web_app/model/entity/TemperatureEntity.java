package com.sprc_web_app.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "temperatures")
public class TemperatureEntity {
    @Id
    @GeneratedValue(generator = "temperatures_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "valoare")
    private Double valoare;

    @Column(name = "timestamp", unique = true)
    @CreationTimestamp
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oras", unique = true)
    private CityEntity city;
}
