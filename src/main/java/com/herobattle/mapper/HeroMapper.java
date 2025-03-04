package com.herobattle.mapper;

import com.herobattle.controller.dto.HeroDto;
import com.herobattle.controller.request.CreateHeroRequest;
import com.herobattle.repository.entity.HeroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HeroMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "hp", source = "hp")
    @Mapping(target = "baseDamage")
    HeroEntity mapToEntity(CreateHeroRequest request);

    HeroDto mapToDto(HeroEntity entity);
}
