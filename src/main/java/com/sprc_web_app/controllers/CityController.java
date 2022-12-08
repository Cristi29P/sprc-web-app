package com.sprc_web_app.controllers;

import com.sprc_web_app.model.dto.request.CityRequestDTO;
import com.sprc_web_app.model.dto.response.CityDTO;
import com.sprc_web_app.model.dto.response.CityIdResponse;
import com.sprc_web_app.services.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    @PostMapping
    public ResponseEntity<CityIdResponse> createCity(@Valid @RequestBody CityRequestDTO cityRequestDTO) {
        return new ResponseEntity<>(cityService.createCity(cityRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/country/{id_Tara}")
    public ResponseEntity<List<CityDTO>> getAllCitiesByCountry(@PathVariable Long id_Tara) {
        return ResponseEntity.ok(cityService.getAllCitiesByCountryId(id_Tara));
    }

    @PutMapping("{id}")
    public ResponseEntity<CityIdResponse> updateCity(@PathVariable Long id,@RequestBody CityRequestDTO cityRequestDTO) {
        return ResponseEntity.ok(cityService.updateCity(id, cityRequestDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
