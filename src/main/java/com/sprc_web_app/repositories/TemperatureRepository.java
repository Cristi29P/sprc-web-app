package com.sprc_web_app.repositories;

import com.sprc_web_app.model.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Long> {
}

