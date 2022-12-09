package com.sprc_web_app.controllers;

import com.sprc_web_app.model.dto.request.CityRequestDTO;
import com.sprc_web_app.model.dto.response.CityDTO;
import com.sprc_web_app.model.dto.response.ObjectResponse;
import com.sprc_web_app.services.CityService;
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
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    @PostMapping
    public ResponseEntity<ObjectResponse<Long>> createCity(@Valid @RequestBody CityRequestDTO cityRequestDTO) {
        return new ResponseEntity<>(new ObjectResponse<>(cityService.createCity(cityRequestDTO)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/country/{id_Tara}")
    public ResponseEntity<List<CityDTO>> getAllCitiesByCountry(@PathVariable(name = "id_Tara") Long idTara) {
        return ResponseEntity.ok(cityService.getAllCitiesByCountryId(idTara));
    }

    @PutMapping("{id}")
    public ResponseEntity<ObjectResponse<Long>> updateCity(@PathVariable Long id, @Valid @RequestBody CityRequestDTO cityRequestDTO) {
        if (!id.equals(cityRequestDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ObjectResponse<>(cityService.updateCity(id, cityRequestDTO)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
