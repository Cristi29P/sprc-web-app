package com.sprc_web_app.services;

import com.sprc_web_app.mappers.CityMapper;
import com.sprc_web_app.model.dto.request.CityRequestDTO;
import com.sprc_web_app.model.dto.response.CityDTO;
import com.sprc_web_app.model.dto.response.CityIdResponse;
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

    public CityIdResponse createCity(CityRequestDTO cityRequestDTO) {
        boolean existsById = countryRepository.existsById(cityRequestDTO.getIdTara());
        if (!existsById) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }

        Optional<CityEntity> cityAux = cityRepository.findByIdTaraAndNume(cityRequestDTO.getIdTara(), cityRequestDTO.getNume());

        if (cityAux.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Existing city name and country id");
        }


        CityEntity cityEntity = cityMapper.mapCityRequestToEntity(cityRequestDTO);
        CountryEntity countryEntity = countryRepository.findById(cityRequestDTO.getIdTara()).orElseThrow();
        cityEntity.setCountry(countryEntity);
        cityEntity = cityRepository.saveAndFlush(cityEntity);

        return cityMapper.mapCityEntityToIdResponse(cityEntity);
    }

    public List<CityDTO> getAllCities() {
        return cityMapper.mapCityEntitiesToDTOs(cityRepository.findAll());
    }

    public List<CityDTO> getAllCitiesByCountryId(Long idTara) {
        return cityMapper.mapCityEntitiesToDTOs(cityRepository.findAllByCountryId(idTara));
    }

    // TODO ADaugat conditie ca daca tara nu exista, sa dea fail
    // Sau daca nu se respecta conditiile de unicitate
    public CityIdResponse updateCity(Long id, CityRequestDTO cityRequestDTO) {
        Optional<CityEntity> cityAux = cityRepository.findByIdTaraAndNume(cityRequestDTO.getIdTara(), cityRequestDTO.getNume());
        if (cityAux.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Violates unique constraint");
        }

        CityEntity cityEntity = cityRepository.findById(id).orElseThrow();
        cityEntity.setNume(cityRequestDTO.getNume());

        Optional<CountryEntity> countryEntityOptional = countryRepository.findById(cityRequestDTO.getIdTara());

        if (countryEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country does not exist");
        }

        cityEntity.setCountry(countryEntityOptional.get());
        cityEntity.setLat(cityRequestDTO.getLat());
        cityEntity.setLon(cityEntity.getLon());
        cityEntity = cityRepository.saveAndFlush(cityEntity);

        return cityMapper.mapCityEntityToIdResponse(cityEntity);
    }

    public void deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
        }

        cityRepository.deleteById(id);
    }
}
