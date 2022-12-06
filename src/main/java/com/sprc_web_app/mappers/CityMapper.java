package com.sprc_web_app.mappers;

import com.sprc_web_app.model.dto.request.CityRequestDTO;
import com.sprc_web_app.model.dto.response.CityDTO;
import com.sprc_web_app.model.dto.response.CityIdResponse;
import com.sprc_web_app.model.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {

    @Mapping(target = "temperatures", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", ignore = true)
    CityEntity mapCityRequestToEntity(CityRequestDTO cityRequestDTO);

    CityIdResponse mapCityEntityToIdResponse(CityEntity cityEntity);

    List<CityDTO> mapCityEntitiesToDTOs(List<CityEntity> cityEntityList);

    @Mapping(target = "idTara", source = "cityEntity.country.id")
    CityDTO mapCityEntityToDTO(CityEntity cityEntity);
}
