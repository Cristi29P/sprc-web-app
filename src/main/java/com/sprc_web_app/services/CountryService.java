package com.sprc_web_app.services;

import com.sprc_web_app.mappers.CountryMapper;
import com.sprc_web_app.model.dto.request.CountryRequestDTO;
import com.sprc_web_app.model.dto.response.CountryDTO;
import com.sprc_web_app.model.dto.response.CountryIdResponse;
import com.sprc_web_app.model.entity.CountryEntity;
import com.sprc_web_app.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    public CountryIdResponse createCountry(CountryRequestDTO countryRequestDTO) {
        CountryEntity countryEntity = countryRepository.save(countryMapper.mapCountryRequestToEntity(countryRequestDTO));
        return countryMapper.mapCountryEntityToIdResponse(countryEntity);
    }

    public List<CountryDTO> getAllCountries() {
        return countryMapper.mapCountryEntitiesToDTOs(countryRepository.findAll());
    }
}
