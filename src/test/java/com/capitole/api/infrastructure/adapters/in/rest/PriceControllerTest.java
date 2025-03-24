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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

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
    void test1PriceAt14th10AM() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 14, 10, 0);
        mockPriceResponse(applicationDate, 35455L, 1L, BigDecimal.valueOf(35.50));

        mockMvc.perform(get("/price/v1/api")
                        .param("applicationDate", "2024-06-14 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void test2PriceAt16PMOn14th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 14, 16, 0);
        mockPriceResponse(applicationDate, 35455L, 1L,  BigDecimal.valueOf(25.45));

        mockMvc.perform(get("/price/v1/api")
                        .param("applicationDate", "2024-06-14 16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void test3PriceAt21PMOn14th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 14, 21, 0);
        mockPriceResponse(applicationDate, 35455L, 1L,  BigDecimal.valueOf(35.50));

        mockMvc.perform(get("/price/v1/api")
                        .param("applicationDate", "2024-06-14 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void test4PriceAt10AMOn15th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 15, 10, 0);
        mockPriceResponse(applicationDate, 35455L, 1L,  BigDecimal.valueOf(30.50));

        mockMvc.perform(get("/price/v1/api")
                        .param("applicationDate", "2024-06-15 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    void test5PriceAt21PMOn16th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 16, 21, 0);
        mockPriceResponse(applicationDate, 35455L, 1L,  BigDecimal.valueOf(38.95));

        mockMvc.perform(get("/price/v1/api")
                        .param("applicationDate", "2024-06-16 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
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

    /**
     * Test {@link PriceController#getApplicablePrice(LocalDateTime, Long, Long)}.
     * <p>
     * Method under test: {@link PriceController#getApplicablePrice(LocalDateTime, Long, Long)}
     */
    @Test
    @DisplayName("Test getApplicablePrice(LocalDateTime, Long, Long)")
    @Tag("MaintainedByDiffblue")
    void testGetApplicablePrice() throws Exception {
        // Arrange
        when(priceServicePort.findApplicablePrice(Mockito.<LocalDateTime>any(), Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(new PriceProduct());
        when(priceRestMapper.priceToPriceResponse(Mockito.<PriceProduct>any())).thenReturn(new PriceResponse());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/price/v1/api");
        MockHttpServletRequestBuilder paramResult = getResult.param("applicationDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("brandId", String.valueOf(1L));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("productId", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(priceController)
                .setControllerAdvice(priceRestAdvice)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"productId\":null,\"brandId\":null,\"priceList\":null,\"startDate\":null,\"endDate\":null,\"price\":null}"));
    }








}
