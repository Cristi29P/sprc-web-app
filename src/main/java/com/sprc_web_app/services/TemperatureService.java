package com.sprc_web_app.services;

import com.sprc_web_app.mappers.TemperatureMapper;
import com.sprc_web_app.model.dto.request.TemperatureRequestDTO;
import com.sprc_web_app.model.dto.response.TemperatureDTO;
import com.sprc_web_app.model.entity.CityEntity;
import com.sprc_web_app.model.entity.TemperatureEntity;
import com.sprc_web_app.repositories.CityRepository;
import com.sprc_web_app.repositories.CountryRepository;
import com.sprc_web_app.repositories.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;

    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;

    private final TemperatureMapper temperatureMapper;

    public Long createTemperature(TemperatureRequestDTO temperatureRequestDTO) {
        TemperatureEntity temperatureEntity = temperatureMapper.mapTemperatureRequestToEntity(temperatureRequestDTO);
        Optional<CityEntity> cityEntityOptional = cityRepository.findById(temperatureRequestDTO.getIdOras());

        if (cityEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
        }

        temperatureEntity.setCity(cityEntityOptional.get());
        temperatureEntity = temperatureRepository.saveAndFlush(temperatureEntity);

        return temperatureEntity.getId();
    }

    public List<TemperatureDTO> getTemperaturesByCriteria(Double lat, Double lon, String from, String until) {
        List<TemperatureEntity> temperatureEntities = temperatureRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (lat != null) {
            temperatureEntities = temperatureEntities
                    .stream()
                    .filter(temperature -> Objects.equals(temperature.getCity().getLat(), lat))
                    .toList();
        }

        if (lon != null) {
            temperatureEntities = temperatureEntities
                    .stream()
                    .filter(temperature -> Objects.equals(temperature.getCity().getLon(), lon))
                    .toList();
        }

        if (from != null) {
            LocalDateTime fromDate = LocalDate.parse(from, formatter).atStartOfDay();

            temperatureEntities = temperatureEntities
                    .stream()
                    .filter(temperature -> temperature.getTimestamp().isAfter(fromDate))
                    .toList();
        }

        if (until != null) {
            LocalDateTime untilDate = LocalDate.parse(until, formatter).atStartOfDay();

            temperatureEntities = temperatureEntities
                    .stream()
                    .filter(temperature -> temperature.getTimestamp().isBefore(untilDate))
                    .toList();
        }
        return temperatureMapper.mapTemperatureEntitiesToDTOs(temperatureEntities);
    }

    public List<TemperatureDTO> getTemperaturesByCity(Long id_oras, String from, String until) {
        List<TemperatureEntity> temperatureEntities = temperatureRepository.findAllByCityId(id_oras);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (from != null) {
            LocalDateTime fromDate = LocalDate.parse(from, formatter).atStartOfDay();

            temperatureEntities = temperatureEntities
                    .stream()
                    .filter(temperature -> temperature.getTimestamp().isAfter(fromDate))
                    .toList();
        }

        if (until != null) {
            LocalDateTime untilDate = LocalDate.parse(until, formatter).atStartOfDay();

            temperatureEntities = temperatureEntities
                    .stream()
                    .filter(temperature -> temperature.getTimestamp().isBefore(untilDate))
                    .toList();
        }
        return temperatureMapper.mapTemperatureEntitiesToDTOs(temperatureEntities);
    }

    public List<TemperatureDTO> getTemperaturesByCountry(Long id_tara, String from, String until) {
        List<TemperatureEntity> temperatureEntities = temperatureRepository.findAllByCountryId(id_tara);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (from != null) {
            LocalDateTime fromDate = LocalDate.parse(from, formatter).atStartOfDay();

            temperatureEntities = temperatureEntities
                    .stream()
                    .filter(temperature -> temperature.getTimestamp().isAfter(fromDate))
                    .toList();
        }

        if (until != null) {
            LocalDateTime untilDate = LocalDate.parse(until, formatter).atStartOfDay();

            temperatureEntities = temperatureEntities
                    .stream()
                    .filter(temperature -> temperature.getTimestamp().isBefore(untilDate))
                    .toList();
        }
        return temperatureMapper.mapTemperatureEntitiesToDTOs(temperatureEntities);
    }

    public Long updateTemperature(Long id, TemperatureRequestDTO temperatureRequestDTO) {
        TemperatureEntity temperatureEntity = temperatureRepository.findById(id).orElseThrow();
        Optional<CityEntity> cityEntityOptional = cityRepository.findById(temperatureRequestDTO.getIdOras());

        if (cityEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
        }

        temperatureEntity.setCity(cityEntityOptional.get());
        temperatureEntity.setValoare(temperatureRequestDTO.getValoare());

        return temperatureRepository.saveAndFlush(temperatureEntity).getId();
    }

    public void deleteTemperature(Long id) {
        if (!temperatureRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Temperature not found");
        }

        temperatureRepository.deleteById(id);
    }

}
