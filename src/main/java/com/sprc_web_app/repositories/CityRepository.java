package com.sprc_web_app.repositories;

import com.sprc_web_app.model.entity.CityEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
    @Query("SELECT c FROM CityEntity c left join fetch c.country")
    List<CityEntity> findAll();

    List<CityEntity> findAllByCountryId(Long idTara);
}

