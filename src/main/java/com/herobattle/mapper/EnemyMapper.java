package com.herobattle.mapper;

import com.herobattle.controller.dto.EnemyDto;
import com.herobattle.repository.entity.EnemyEntity;
import com.herobattle.service.model.Enemy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnemyMapper {

    EnemyEntity mapToEntity(Enemy model);

    Enemy mapToModel(EnemyEntity entity);

    EnemyDto mapToDto(Enemy model);
}
