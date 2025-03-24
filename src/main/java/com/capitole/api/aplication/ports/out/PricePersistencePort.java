package com.capitole.api.aplication.ports.out;

import com.capitole.api.domain.model.PriceProduct;

import java.time.LocalDateTime;
import java.util.List;

public interface PricePersistencePort {
    List<PriceProduct> findApplicablePrice(LocalDateTime aplicationDate, Long productId, Long brandId);
}
