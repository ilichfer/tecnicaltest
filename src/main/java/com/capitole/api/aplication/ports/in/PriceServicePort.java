package com.capitole.api.aplication.ports.in;

import com.capitole.api.domain.model.PriceProduct;

import java.time.LocalDateTime;

public interface PriceServicePort {
    PriceProduct findApplicablePrice(LocalDateTime aplicationDate,Long productId, Long brandID);
}
