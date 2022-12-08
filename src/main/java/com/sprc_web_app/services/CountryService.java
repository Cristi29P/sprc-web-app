package com.sprc_web_app.services;

import com.sprc_web_app.mappers.CountryMapper;
import com.sprc_web_app.model.dto.request.CountryRequestDTO;
import com.sprc_web_app.model.dto.response.CountryDTO;
import com.sprc_web_app.model.dto.response.CountryIdResponse;
import com.sprc_web_app.model.entity.CountryEntity;
import com.sprc_web_app.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    @Transactional
    public CountryIdResponse createCountry(CountryRequestDTO countryRequestDTO) {
        boolean exists = countryRepository.existsByNume(countryRequestDTO.getNume());

        if (!exists) {
            CountryEntity countryEntity = countryRepository.save(countryMapper.mapCountryRequestToEntity(countryRequestDTO));
            return countryMapper.mapCountryEntityToIdResponse(countryEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country already exists");
        }
    }

    public List<CountryDTO> getAllCountries() {
        return countryMapper.mapCountryEntitiesToDTOs(countryRepository.findAll());
    }

    public CountryIdResponse updateCountry(Long id, CountryRequestDTO countryRequestDTO) {
        CountryEntity countryEntity = countryRepository.findById(id).orElseThrow();
        countryEntity.setNume(countryRequestDTO.getNume());
        countryEntity.setLat(countryRequestDTO.getLat());
        countryEntity.setLon(countryRequestDTO.getLon());

        countryEntity = countryRepository.save(countryEntity);

        return countryMapper.mapCountryEntityToIdResponse(countryEntity);
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
