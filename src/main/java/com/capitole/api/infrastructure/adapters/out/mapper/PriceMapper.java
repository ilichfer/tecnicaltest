package com.capitole.api.infrastructure.adapters.out.mapper;

import com.capitole.api.domain.model.PriceProduct;
import com.capitole.api.infrastructure.adapters.out.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    List<PriceProduct> listEntityToListPriceProduct (List<PriceEntity> entityList);

    PriceProduct entityToPriceProduct (PriceEntity entity);
}
