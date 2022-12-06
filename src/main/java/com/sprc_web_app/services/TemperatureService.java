package com.sprc_web_app.services;

import com.sprc_web_app.mappers.TemperatureMapper;
import com.sprc_web_app.model.dto.request.TemperatureRequestDTO;
import com.sprc_web_app.model.dto.response.TemperatureIdResponse;
import com.sprc_web_app.model.entity.CityEntity;
import com.sprc_web_app.model.entity.TemperatureEntity;
import com.sprc_web_app.repositories.CityRepository;
import com.sprc_web_app.repositories.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;

    private final CityRepository cityRepository;

    private final TemperatureMapper temperatureMapper;

    public TemperatureIdResponse createTemperature(TemperatureRequestDTO temperatureRequestDTO) {
        TemperatureEntity temperatureEntity = temperatureMapper.mapTemperatureRequestToEntity(temperatureRequestDTO);
        CityEntity cityEntity = cityRepository.findById(temperatureRequestDTO.getIdOras()).orElseThrow();
        temperatureEntity.setCity(cityEntity);
        temperatureEntity = temperatureRepository.save(temperatureEntity);

        return temperatureMapper.mapTemperatureEntityToIdResponse(temperatureEntity);
    }
}
