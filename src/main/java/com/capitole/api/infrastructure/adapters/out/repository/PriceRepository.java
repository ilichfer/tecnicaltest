package com.capitole.api.infrastructure.adapters.out.repository;

import com.capitole.api.infrastructure.adapters.out.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {


    @Query(value = "SELECT p FROM PriceEntity p " +
            "    WHERE p.brandId = :brandId " +
            "    AND p.productId = :productId " +
            "    AND :aplicationDate BETWEEN p.startDate AND p.endDate")
List<PriceEntity> findByProductIdAndBrandIdAndDateBetween(
    @Param("productId") Long productId,
    @Param("brandId") Long brandId,
    @Param("aplicationDate") LocalDateTime aplicationDate
);

}
