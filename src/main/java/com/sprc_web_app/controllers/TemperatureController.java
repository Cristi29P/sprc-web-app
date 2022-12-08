package com.sprc_web_app.controllers;

import com.sprc_web_app.model.dto.request.TemperatureRequestDTO;
import com.sprc_web_app.model.dto.response.TemperatureDTO;
import com.sprc_web_app.model.dto.response.TemperatureIdResponse;
import com.sprc_web_app.services.TemperatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/temperatures")
public class TemperatureController {
    private final TemperatureService temperatureService;

    @PostMapping
    public ResponseEntity<TemperatureIdResponse> createTemperature(@RequestBody TemperatureRequestDTO temperatureRequestDTO) {
        return new ResponseEntity<>(temperatureService.createTemperature(temperatureRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TemperatureDTO>> getTemperaturesByCriteria(@RequestParam(required = false) Double lat,
                                                                          @RequestParam(required = false) Double lon,
                                                                          @RequestParam(required = false) String from,
                                                                          @RequestParam(required = false) String until) {
        return new ResponseEntity<>(temperatureService.getTemperaturesByCriteria(lat, lon, from, until), HttpStatus.OK);
    }

    @GetMapping("/cities/{id_oras}")
    public ResponseEntity<List<TemperatureDTO>> getTemperaturesByCity(@PathVariable(required = false) Long id_oras,
                                                                      @RequestParam(required = false) String from,
                                                                      @RequestParam(required = false) String until) {
        return new ResponseEntity<>(temperatureService.getTemperaturesByCity(id_oras, from, until), HttpStatus.OK);
    }

    @GetMapping("/cities/")
    public ResponseEntity<List<TemperatureDTO>> getTemperaturesByCity() {
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }

    @GetMapping("/countries/{id_tara}")
    public ResponseEntity<List<TemperatureDTO>> getTemperaturesByCountry(@PathVariable Long id_tara,
                                                                         @RequestParam(required = false) String from,
                                                                         @RequestParam(required = false) String until) {
        return new ResponseEntity<>(temperatureService.getTemperaturesByCountry(id_tara, from, until), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemperatureIdResponse> updateTemperature(@PathVariable Long id, @RequestBody TemperatureRequestDTO temperatureRequestDTO) {
        return new ResponseEntity<>(temperatureService.updateTemperature(id, temperatureRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTemperature(@PathVariable Long id) {
        temperatureService.deleteTemperature(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
