package com.sprc_web_app.services;

import com.sprc_web_app.mappers.CountryMapper;
import com.sprc_web_app.model.dto.request.CountryRequestDTO;
import com.sprc_web_app.model.dto.response.CountryDTO;
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
    public Long createCountry(CountryRequestDTO countryRequestDTO) {
        boolean nameConflict = countryRepository.existsByNume(countryRequestDTO.getNume());

        if (!nameConflict) {
            CountryEntity countryEntity = countryRepository.saveAndFlush(countryMapper.mapCountryRequestToEntity(countryRequestDTO));
            return countryEntity.getId();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country already nameConflict");
        }
    }

    public List<CountryDTO> getAllCountries() {
        return countryMapper.mapCountryEntitiesToDTOs(countryRepository.findAll());
    }

    public Long updateCountry(Long id, CountryRequestDTO countryRequestDTO) {
        boolean nameConflict = countryRepository.existsByNume(countryRequestDTO.getNume());
        if (nameConflict) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name taken");
        }

        CountryEntity countryEntity = countryRepository.findById(id).orElseThrow();
        countryEntity.setNume(countryRequestDTO.getNume());
        countryEntity.setLat(countryRequestDTO.getLat());
        countryEntity.setLon(countryRequestDTO.getLon());

        countryEntity = countryRepository.saveAndFlush(countryEntity);

        return countryEntity.getId();
    }

    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }

        countryRepository.deleteById(id);
    }
}
