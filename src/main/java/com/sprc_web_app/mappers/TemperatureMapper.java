package com.sprc_web_app.mappers;

import com.sprc_web_app.model.dto.request.TemperatureRequestDTO;
import com.sprc_web_app.model.dto.response.TemperatureDTO;
import com.sprc_web_app.model.dto.response.TemperatureIdResponse;
import com.sprc_web_app.model.entity.TemperatureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TemperatureMapper {

    @Mapping(target = "city", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    TemperatureEntity mapTemperatureRequestToEntity(TemperatureRequestDTO temperatureRequestDTO);

    TemperatureIdResponse mapTemperatureEntityToIdResponse(TemperatureEntity temperatureEntity);

    List<TemperatureDTO> mapTemperatureEntitiesToDTOs(List<TemperatureEntity> temperatureEntitiesList);
}
