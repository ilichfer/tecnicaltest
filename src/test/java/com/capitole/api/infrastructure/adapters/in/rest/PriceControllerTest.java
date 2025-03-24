package com.capitole.api.infrastructure.adapters.in.rest;


import com.capitole.api.aplication.ports.in.PriceServicePort;
import com.capitole.api.domain.model.PriceProduct;
import com.capitole.api.infrastructure.adapters.in.rest.mapper.PriceRestMapper;
import com.capitole.api.infrastructure.adapters.model.PriceResponse;
import com.capitole.api.infrastructure.adapters.out.mapper.PriceMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    public static final String PRODUCT_ID = "35455";
    public static final String BRAND_ID = "1";
    public static final String PATH = "/price/v1/api";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    PriceServicePort priceServicePort = mock(PriceServicePort.class);

    @Mock
    PriceMapper priceMapper;

    @InjectMocks
    private PriceController priceController;


    @Mock
    private PriceRestMapper priceRestMapper;

    @Autowired
    private PriceRestAdvice priceRestAdvice;

    /**
     * Method under test: {@link PriceController#getApplicablePrice(LocalDateTime, Long, Long)} ()}
     */
    @Test
    void testGetApplicablePriceByParameters() {
        (new PriceController(priceServicePort, mock(PriceRestMapper.class))).getApplicablePrice(LocalDateTime.now(), 35455L, 1L);
    }


    @Test
    void test1_ApplicablePrice() throws Exception {
        String aplicationDateRequest = "2024-06-14 10:00:00";
        LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 14, 10, 0);
        mockPriceResponse(applicationDate, 35455L, 1L, BigDecimal.valueOf(35.50));

        mockMvc.perform(get(PATH)
                        .param("applicationDate", aplicationDateRequest)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void test2_ApplicablePrice() throws Exception {
        String aplicationDateRequest = "2024-06-14 16:00:00";
        LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 14, 16, 0);
        mockPriceResponse(applicationDate, 35455L, 1L,  BigDecimal.valueOf(25.45));

        mockMvc.perform(get(PATH)
                        .param("applicationDate", aplicationDateRequest)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void test3_ApplicablePrice() throws Exception {
        String aplicationDateRequest = "2024-06-14 21:00:00";
        LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 14, 21, 0);
        mockPriceResponse(applicationDate, 35455L, 1L,  BigDecimal.valueOf(35.50));

        mockMvc.perform(get(PATH)
                        .param("applicationDate", aplicationDateRequest)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void test4_ApplicablePrice() throws Exception {
        String aplicationDateRequest = "2024-06-15 10:00:00";
        LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 15, 10, 0);
        mockPriceResponse(applicationDate, 35455L, 1L,  BigDecimal.valueOf(30.50));

        mockMvc.perform(get(PATH)
                        .param("applicationDate", aplicationDateRequest)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    void test5_ApplicablePrice() throws Exception {
        String aplicationDateRequest = "2024-06-16 21:00:00";
        LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 16, 21, 0);
        mockPriceResponse(applicationDate, 35455L, 1L,  BigDecimal.valueOf(38.95));

        mockMvc.perform(get(PATH)
                        .param("applicationDate", aplicationDateRequest)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));
    }

    private void mockPriceResponse(LocalDateTime applicationDate, Long productId, Long brandId, BigDecimal priceValue) {

        PriceProduct price = new PriceProduct(productId, brandId,1L, applicationDate, applicationDate.plusDays(1),priceValue,1L);
        PriceResponse priceResponse = new PriceResponse(productId, brandId,1L, applicationDate, applicationDate.plusDays(1),priceValue);

        when(priceServicePort.findApplicablePrice(any(), anyLong(), anyLong())).thenReturn(price);
        when( mock(PriceRestMapper.class).priceToPriceResponse(price)).thenReturn(priceResponse);
    }

}
