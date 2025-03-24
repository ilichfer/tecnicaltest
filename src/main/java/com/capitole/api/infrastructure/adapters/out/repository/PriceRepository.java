package com.capitole.api.infrastructure.adapters.out.repository;

import com.capitole.api.infrastructure.adapters.out.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {



}
