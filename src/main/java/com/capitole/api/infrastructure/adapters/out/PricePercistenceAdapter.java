package com.capitole.api.infrastructure.adapters.out;

import com.capitole.api.aplication.ports.out.PricePersistencePort;
import com.capitole.api.domain.model.PriceProduct;
import com.capitole.api.infrastructure.adapters.out.mapper.PriceMapper;
import com.capitole.api.infrastructure.adapters.out.repository.PriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public class PricePercistenceAdapter implements PricePersistencePort {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    public PricePercistenceAdapter(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<PriceProduct> findApplicablePrice(Date aplicationDate, Long productId, Long BrandId) {
        return priceMapper.listEntityToListPriceProduct( priceRepository.findByProductIdAndBrandIdAndDateBetween(productId,BrandId,aplicationDate));
    }
}
