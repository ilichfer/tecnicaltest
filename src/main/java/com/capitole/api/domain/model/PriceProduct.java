package com.capitole.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceProduct {

private Long brandId;
private Date StartDate;
private Date endDate;
private Long priceList;
private Long productId;
private Long priority;
private BigDecimal price;
private String curr;

}
