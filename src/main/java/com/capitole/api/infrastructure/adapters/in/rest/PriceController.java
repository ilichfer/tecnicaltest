package com.capitole.api.infrastructure.adapters.in.rest;

import com.capitole.api.aplication.ports.in.PriceServicePort;
import com.capitole.api.infrastructure.adapters.in.rest.mapper.PriceRestMapper;
import com.capitole.api.infrastructure.adapters.model.PriceResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/price")
public class PriceController {

    private final PriceServicePort priceServicePort;
    private final PriceRestMapper priceRestMapper;

    public PriceController(PriceServicePort priceServicePort, PriceRestMapper priceRestMapper) {
        this.priceServicePort = priceServicePort;
        this.priceRestMapper = priceRestMapper;
    }

    @GetMapping("/v1/api")
    public ResponseEntity<PriceResponse> getApplicablePrice(@RequestParam("applicationDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate,
                                                            @RequestParam("productId") Long productId,
                                                            @RequestParam("brandId") Long brandId) {
        return ResponseEntity.ok(priceRestMapper.priceToPriceResponse(priceServicePort.findApplicablePrice(applicationDate,productId,brandId)));
    }

}
