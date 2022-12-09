package com.sprc_web_app.mappers;

import com.sprc_web_app.model.dto.request.CountryRequestDTO;
import com.sprc_web_app.model.dto.response.CountryDTO;
import com.sprc_web_app.model.entity.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cities", ignore = true)
    CountryEntity mapCountryRequestToEntity(CountryRequestDTO countryRequestDTO);

    List<CountryDTO> mapCountryEntitiesToDTOs(List<CountryEntity> countryEntityList);
}
