package com.sprc_web_app.services;

import com.sprc_web_app.mappers.CountryMapper;
import com.sprc_web_app.model.dto.request.CountryRequestDTO;
import com.sprc_web_app.model.dto.response.CountryDTO;
import com.sprc_web_app.model.entity.CountryEntity;
import com.sprc_web_app.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public Long createCountry(CountryRequestDTO countryRequestDTO) {
        if (countryRepository.existsByNume(countryRequestDTO.getNume())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name taken!");
        }
        return countryRepository.save(countryMapper.mapCountryRequestToEntity(countryRequestDTO)).getId();
    }

    public List<CountryDTO> getAllCountries() {
        return countryMapper.mapCountryEntitiesToDTOs(countryRepository.findAll());
    }

    public Long updateCountry(Long id, CountryRequestDTO countryRequestDTO) {
        if (countryRepository.existsByNume(countryRequestDTO.getNume())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name taken!");
        }

        Optional<CountryEntity> countryEntityOptional = countryRepository.findById(id);
        if (countryEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country non existent");
        }

        CountryEntity countryEntity = countryEntityOptional.get();
        countryEntity.setNume(countryRequestDTO.getNume());
        countryEntity.setLat(countryRequestDTO.getLat());
        countryEntity.setLon(countryRequestDTO.getLon());

        return countryRepository.save(countryEntity).getId();
    }

    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }

        countryRepository.deleteById(id);
    }
}
