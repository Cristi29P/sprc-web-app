package com.sprc_web_app.repositories;

import com.sprc_web_app.model.entity.CityEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
}

