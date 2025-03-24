package com.capitole.api.aplication.usecase;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.capitole.api.aplication.ports.out.PricePersistencePort;
import com.capitole.api.domain.model.PriceProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PricingUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PricingUseCaseTest {

    public static final Long PRODUCT_ID = 35455L;
    public static final Long BRAND_ID = 1L;

    @MockBean
    private PricePersistencePort pricePersistencePort;

    @Autowired
    private PricingUseCase pricingUseCase;

    /**
     * Method under test: {@link PricingUseCase#findApplicablePrice(LocalDateTime, Long, Long)}
     */
    @Test
    void test1_FindApplicablePrice_givenArrayListAddPriceProduct_thenReturnPriceProduct() {
        // Arrange
        ArrayList<PriceProduct> priceProductList = new ArrayList<>();
        PriceProduct priceProduct = new PriceProduct();
        priceProductList.add(priceProduct);
        when(pricePersistencePort.findApplicablePrice(Mockito.<LocalDateTime>any(), Mockito.<Long>any(),
                Mockito.<Long>any())).thenReturn(priceProductList);

        // Act
        PriceProduct actualFindApplicablePriceResult = pricingUseCase
                .findApplicablePrice(LocalDateTime.of(2024, 6, 14, 10, 0), PRODUCT_ID, BRAND_ID);

        // Assert
        verify(pricePersistencePort).findApplicablePrice(isA(LocalDateTime.class), eq(PRODUCT_ID), eq(BRAND_ID));
        assertSame(priceProduct, actualFindApplicablePriceResult);
    }

    @Test
    void test2_FindApplicablePrice_givenArrayListAddPriceProduct_thenReturnPriceProduct() {
        // Arrange
        ArrayList<PriceProduct> priceProductList = new ArrayList<>();
        PriceProduct priceProduct = new PriceProduct();
        priceProductList.add(priceProduct);
        when(pricePersistencePort.findApplicablePrice(Mockito.<LocalDateTime>any(), Mockito.<Long>any(),
                Mockito.<Long>any())).thenReturn(priceProductList);

        // Act
        PriceProduct actualFindApplicablePriceResult = pricingUseCase
                .findApplicablePrice(LocalDateTime.of(2024, 6, 14, 16, 0), PRODUCT_ID, BRAND_ID);

        // Assert
        verify(pricePersistencePort).findApplicablePrice(isA(LocalDateTime.class), eq(PRODUCT_ID), eq(BRAND_ID));
        assertSame(priceProduct, actualFindApplicablePriceResult);
    }

    @Test
    void test3_FindApplicablePrice_givenArrayListAddPriceProduct_thenReturnPriceProduct() {
        // Arrange
        ArrayList<PriceProduct> priceProductList = new ArrayList<>();
        PriceProduct priceProduct = new PriceProduct();
        priceProductList.add(priceProduct);
        when(pricePersistencePort.findApplicablePrice(Mockito.<LocalDateTime>any(), Mockito.<Long>any(),
                Mockito.<Long>any())).thenReturn(priceProductList);

        // Act
        PriceProduct actualFindApplicablePriceResult = pricingUseCase
                .findApplicablePrice(LocalDateTime.of(2024, 6, 14, 21, 0), PRODUCT_ID, BRAND_ID);

        // Assert
        verify(pricePersistencePort).findApplicablePrice(isA(LocalDateTime.class), eq(PRODUCT_ID), eq(BRAND_ID));
        assertSame(priceProduct, actualFindApplicablePriceResult);
    }

    @Test
    void test4_FindApplicablePrice_givenArrayListAddPriceProduct_thenReturnPriceProduct() {
        // Arrange
        ArrayList<PriceProduct> priceProductList = new ArrayList<>();
        PriceProduct priceProduct = new PriceProduct();
        priceProductList.add(priceProduct);
        when(pricePersistencePort.findApplicablePrice(Mockito.<LocalDateTime>any(), Mockito.<Long>any(),
                Mockito.<Long>any())).thenReturn(priceProductList);

        // Act
        PriceProduct actualFindApplicablePriceResult = pricingUseCase
                .findApplicablePrice(LocalDateTime.of(2024, 6, 15, 10, 0), PRODUCT_ID, BRAND_ID);

        // Assert
        verify(pricePersistencePort).findApplicablePrice(isA(LocalDateTime.class), eq(PRODUCT_ID), eq(BRAND_ID));
        assertSame(priceProduct, actualFindApplicablePriceResult);
    }

    @Test
    void test5_FindApplicablePrice_givenArrayListAddPriceProduct_thenReturnPriceProduct() {
        // Arrange
        ArrayList<PriceProduct> priceProductList = new ArrayList<>();
        PriceProduct priceProduct = new PriceProduct();
        priceProductList.add(priceProduct);
        when(pricePersistencePort.findApplicablePrice(Mockito.<LocalDateTime>any(), Mockito.<Long>any(),
                Mockito.<Long>any())).thenReturn(priceProductList);

        // Act
        PriceProduct actualFindApplicablePriceResult = pricingUseCase
                .findApplicablePrice(LocalDateTime.of(2024, 6, 16, 21, 0), PRODUCT_ID, BRAND_ID);

        // Assert
        verify(pricePersistencePort).findApplicablePrice(isA(LocalDateTime.class), eq(PRODUCT_ID), eq(BRAND_ID));
        assertSame(priceProduct, actualFindApplicablePriceResult);
    }

}
