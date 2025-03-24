package com.capitole.api.infrastructure.adapters.in.rest.mapper;

import com.capitole.api.domain.model.PriceProduct;
import com.capitole.api.infrastructure.adapters.model.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceRestMapper {

    PriceRestMapper INSTANCE = Mappers.getMapper(PriceRestMapper.class);

    PriceResponse priceToPriceResponse(PriceProduct priceBo);
}
