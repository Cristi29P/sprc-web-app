package com.sprc_web_app.controllers;

import com.sprc_web_app.model.dto.request.CountryRequestDTO;
import com.sprc_web_app.model.dto.response.CountryDTO;
import com.sprc_web_app.model.dto.response.CountryIdResponse;
import com.sprc_web_app.services.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<CountryIdResponse> createCountry(@Valid @RequestBody CountryRequestDTO countryRequestDTO) {
        return new ResponseEntity<>(countryService.createCountry(countryRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @PutMapping("{id}")
    public ResponseEntity<CountryIdResponse> updateCountry(@PathVariable Long id, @Valid @RequestBody CountryRequestDTO countryRequestDTO) {
        return ResponseEntity.ok(countryService.updateCountry(id, countryRequestDTO));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
