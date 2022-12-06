package com.sprc_web_app.controllers;

import com.sprc_web_app.model.dto.request.TemperatureRequestDTO;
import com.sprc_web_app.model.dto.response.TemperatureIdResponse;
import com.sprc_web_app.services.TemperatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/temperatures")
public class TemperatureController {
    private final TemperatureService temperatureService;

    @PostMapping
    public ResponseEntity<TemperatureIdResponse> createTemperature(@Valid @RequestBody TemperatureRequestDTO temperatureRequestDTO) {
        return new ResponseEntity<>(temperatureService.createTemperature(temperatureRequestDTO), HttpStatus.CREATED);
    }
}
