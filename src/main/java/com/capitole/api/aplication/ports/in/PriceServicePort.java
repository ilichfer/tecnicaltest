package com.capitole.api.aplication.ports.in;

import com.capitole.api.domain.model.PriceProduct;

import java.time.LocalDateTime;
import java.util.Date;

public interface PriceServicePort {
    PriceProduct findApplicablePrice(Date aplicationDate, Long productId, Long brandID);
}
