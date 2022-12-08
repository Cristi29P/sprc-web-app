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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;

    private final CityMapper cityMapper;

    public CityIdResponse createCity(CityRequestDTO cityRequestDTO) {


        CityEntity cityEntity = cityMapper.mapCityRequestToEntity(cityRequestDTO);
        CountryEntity countryEntity = countryRepository.findById(cityRequestDTO.getIdTara()).orElseThrow();
        cityEntity.setCountry(countryEntity);
        cityEntity = cityRepository.save(cityEntity);

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
        CityEntity cityEntity = cityRepository.findById(id).orElseThrow();
        cityEntity.setNume(cityRequestDTO.getNume());
        cityEntity.setCountry(countryRepository.findById(cityRequestDTO.getIdTara()).orElseThrow());
        cityEntity = cityRepository.save(cityEntity);

        return cityMapper.mapCityEntityToIdResponse(cityEntity);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
