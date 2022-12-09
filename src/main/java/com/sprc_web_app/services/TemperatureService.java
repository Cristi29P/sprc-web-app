package com.sprc_web_app.services;

import com.sprc_web_app.mappers.TemperatureMapper;
import com.sprc_web_app.model.dto.request.TemperatureRequestDTO;
import com.sprc_web_app.model.dto.response.TemperatureDTO;
import com.sprc_web_app.model.entity.CityEntity;
import com.sprc_web_app.model.entity.TemperatureEntity;
import com.sprc_web_app.repositories.CityRepository;
import com.sprc_web_app.repositories.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;

    private final CityRepository cityRepository;

    private final TemperatureMapper temperatureMapper;

    public Long createTemperature(TemperatureRequestDTO temperatureRequestDTO) {
        Optional<CityEntity> cityEntityOptional = cityRepository.findById(temperatureRequestDTO.getIdOras());
        if (cityEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
        }

        TemperatureEntity temperatureEntity = temperatureMapper.mapTemperatureRequestToEntity(temperatureRequestDTO);
        temperatureEntity.setCity(cityEntityOptional.get());

        return temperatureRepository.save(temperatureEntity).getId();
    }

    private List<TemperatureEntity> sortByDates(List<TemperatureEntity> temperatureEntities, String from, String until) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (from != null) {
            temperatureEntities = temperatureEntities
                    .stream()
                    .filter(temperature -> temperature.getTimestamp()
                            .isAfter(LocalDate.parse(from, formatter).atStartOfDay()))
                    .toList();
        }

        if (until != null) {
            temperatureEntities = temperatureEntities
                    .stream()
                    .filter(temperature -> temperature.getTimestamp()
                            .isBefore(LocalDate.parse(until, formatter).atStartOfDay()))
                    .toList();
        }

        return temperatureEntities;
    }

    public List<TemperatureDTO> getTemperaturesByCriteria(Double lat, Double lon, String from, String until) {
        List<TemperatureEntity> temperatureEntities = temperatureRepository.findAll();

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

        return temperatureMapper.mapTemperatureEntitiesToDTOs(sortByDates(temperatureEntities, from, until));
    }

    public List<TemperatureDTO> getTemperaturesByCity(Long idOras, String from, String until) {
        List<TemperatureEntity> temperatureEntities = temperatureRepository.findAllByCityId(idOras);

        return temperatureMapper.mapTemperatureEntitiesToDTOs(sortByDates(temperatureEntities, from, until));
    }

    public List<TemperatureDTO> getTemperaturesByCountry(Long idTara, String from, String until) {
        List<TemperatureEntity> temperatureEntities = temperatureRepository.findAllByCountryId(idTara);

        return temperatureMapper.mapTemperatureEntitiesToDTOs(sortByDates(temperatureEntities, from, until));
    }

    public Long updateTemperature(Long id, TemperatureRequestDTO temperatureRequestDTO) {
        Optional<TemperatureEntity> temperatureEntityOptional = temperatureRepository.findById(id);
        if (temperatureEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Temperature does not exist");
        }

        Optional<CityEntity> cityEntityOptional = cityRepository.findById(temperatureRequestDTO.getIdOras());
        if (cityEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
        }

        TemperatureEntity temperatureEntity = temperatureEntityOptional.get();
        temperatureEntity.setCity(cityEntityOptional.get());
        temperatureEntity.setValoare(temperatureRequestDTO.getValoare());

        return temperatureRepository.save(temperatureEntity).getId();
    }

    public void deleteTemperature(Long id) {
        if (!temperatureRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Temperature not found");
        }

        temperatureRepository.deleteById(id);
    }
}
