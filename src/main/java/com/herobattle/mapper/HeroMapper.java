package com.herobattle.mapper;

import com.herobattle.controller.dto.HeroDto;
import com.herobattle.repository.entity.HeroEntity;
import com.herobattle.service.model.Hero;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HeroMapper {

    HeroEntity mapToEntity(Hero model);

    Hero mapToModel(HeroEntity entity);

    HeroDto mapToDto(Hero model);
}
