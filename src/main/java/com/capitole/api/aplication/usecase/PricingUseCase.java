package com.capitole.api.aplication.usecase;

import com.capitole.api.aplication.ports.in.PriceServicePort;
import com.capitole.api.aplication.ports.out.PricePersistencePort;
import com.capitole.api.domain.exception.PriceException;
import com.capitole.api.domain.model.PriceProduct;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class PricingUseCase implements PriceServicePort{

    private final PricePersistencePort pricePersistencePort;

    public PricingUseCase(PricePersistencePort pricePersistencePort) {
        this.pricePersistencePort = pricePersistencePort;
    }

    @Override
    public PriceProduct findApplicablePrice(LocalDateTime aplicationDate, Long productId, Long brandID) {
        List<PriceProduct> listPrices=  pricePersistencePort.findApplicablePrice(aplicationDate,productId,brandID);
        PriceProduct priorityPrice = listPrices.stream()
                .max(Comparator.comparing(PriceProduct::getPriceList))
                .orElseThrow(PriceException::new);
        return priorityPrice;
    }
}
