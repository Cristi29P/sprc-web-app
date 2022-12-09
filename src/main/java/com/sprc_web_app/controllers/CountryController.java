package com.sprc_web_app.controllers;

import com.sprc_web_app.model.dto.request.CountryRequestDTO;
import com.sprc_web_app.model.dto.response.CountryDTO;
import com.sprc_web_app.model.dto.response.ObjectResponse;
import com.sprc_web_app.services.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<ObjectResponse<Long>> createCountry(@Valid @RequestBody CountryRequestDTO countryRequestDTO) {
        return new ResponseEntity<>(new ObjectResponse<>(countryService.createCountry(countryRequestDTO)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @PutMapping("{id}")
    public ResponseEntity<ObjectResponse<Long>> updateCountry(@PathVariable Long id,
                                                              @Valid @RequestBody CountryRequestDTO countryRequestDTO) {
        if (!id.equals(countryRequestDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ObjectResponse<>(countryService.updateCountry(id, countryRequestDTO)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
