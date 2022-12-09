package com.sprc_web_app.controllers;

import com.sprc_web_app.model.dto.request.TemperatureRequestDTO;
import com.sprc_web_app.model.dto.response.ObjectResponse;
import com.sprc_web_app.model.dto.response.TemperatureDTO;
import com.sprc_web_app.services.TemperatureService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/temperatures")
public class TemperatureController {
    private final TemperatureService temperatureService;

    @PostMapping
    public ResponseEntity<ObjectResponse<Long>> createTemperature(@Valid @RequestBody TemperatureRequestDTO temperatureRequestDTO) {
        return new ResponseEntity<>(new ObjectResponse<>(temperatureService.createTemperature(temperatureRequestDTO)),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TemperatureDTO>> getTemperaturesByCriteria(@RequestParam(required = false) Double lat,
                                                                          @RequestParam(required = false) Double lon,
                                                                          @RequestParam(required = false) String from,
                                                                          @RequestParam(required = false) String until) {
        return new ResponseEntity<>(temperatureService.getTemperaturesByCriteria(lat, lon, from, until), HttpStatus.OK);
    }

    @GetMapping("/cities/{id_oras}")
    public ResponseEntity<List<TemperatureDTO>> getTemperaturesByCity(@PathVariable(required = false, name = "id_oras") Long idOras,
                                                                      @RequestParam(required = false) String from,
                                                                      @RequestParam(required = false) String until) {
        return new ResponseEntity<>(temperatureService.getTemperaturesByCity(idOras, from, until), HttpStatus.OK);
    }

    @GetMapping("/countries/{id_tara}")
    public ResponseEntity<List<TemperatureDTO>> getTemperaturesByCountry(@PathVariable(name = "id_tara") Long idTara,
                                                                         @RequestParam(required = false) String from,
                                                                         @RequestParam(required = false) String until) {
        return new ResponseEntity<>(temperatureService.getTemperaturesByCountry(idTara, from, until), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectResponse<Long>> updateTemperature(@PathVariable Long id,
                                                                  @Valid @RequestBody TemperatureRequestDTO temperatureRequestDTO) {
        if (!id.equals(temperatureRequestDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ObjectResponse<>(temperatureService.updateTemperature(id, temperatureRequestDTO)),
                HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTemperature(@PathVariable Long id) {
        temperatureService.deleteTemperature(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
