package com.sprc_web_app.services;

import com.sprc_web_app.mappers.CityMapper;
import com.sprc_web_app.model.dto.request.CityRequestDTO;
import com.sprc_web_app.model.dto.response.CityDTO;
import com.sprc_web_app.model.entity.CityEntity;
import com.sprc_web_app.model.entity.CountryEntity;
import com.sprc_web_app.repositories.CityRepository;
import com.sprc_web_app.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;

    private final CityMapper cityMapper;

    public Long createCity(CityRequestDTO cityRequestDTO) {
        if (cityRepository.findByIdTaraAndNume(cityRequestDTO.getIdTara(), cityRequestDTO.getNume()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Existing city name and country id");
        }

        Optional<CountryEntity> countryEntityOptional = countryRepository.findById(cityRequestDTO.getIdTara());
        if (countryEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }

        CityEntity cityEntity = cityMapper.mapCityRequestToEntity(cityRequestDTO);
        cityEntity.setCountry(countryEntityOptional.get());

        return cityRepository.save(cityEntity).getId();
    }

    public List<CityDTO> getAllCities() {
        return cityMapper.mapCityEntitiesToDTOs(cityRepository.findAll());
    }

    public List<CityDTO> getAllCitiesByCountryId(Long idTara) {
        return cityMapper.mapCityEntitiesToDTOs(cityRepository.findAllByCountryId(idTara));
    }

    public Long updateCity(Long id, CityRequestDTO cityRequestDTO) {
        if (cityRepository.findByIdTaraAndNume(cityRequestDTO.getIdTara(), cityRequestDTO.getNume()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Existing city name and country id");
        }

        Optional<CityEntity> cityEntityOptional = cityRepository.findById(id);
        if (cityEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City does not exist");
        }

        Optional<CountryEntity> countryEntityOptional = countryRepository.findById(cityRequestDTO.getIdTara());
        if (countryEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country does not exist");
        }

        CityEntity cityEntity = cityEntityOptional.get();
        cityEntity.setCountry(countryEntityOptional.get());
        cityEntity.setNume(cityRequestDTO.getNume());
        cityEntity.setLat(cityRequestDTO.getLat());
        cityEntity.setLon(cityRequestDTO.getLon());

        return cityRepository.save(cityEntity).getId();
    }

    public void deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
        }

        cityRepository.deleteById(id);
    }
}
