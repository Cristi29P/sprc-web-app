package com.sprc_web_app.repositories;

import com.sprc_web_app.model.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@SuppressWarnings("NullableProblems")
@Repository
public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Long> {
    @Query("SELECT t from TemperatureEntity t left join fetch t.city")
    List<TemperatureEntity> findAll();

    List<TemperatureEntity> findAllByCityId(Long cityId);

    @Query("SELECT t from TemperatureEntity t inner join fetch t.city c inner join fetch c.country d where d.id = ?1")
    List<TemperatureEntity> findAllByCountryId(Long countryId);
}

