package com.stock.market.producer.controller;

import com.stock.market.producer.controller.StockController;
import com.stock.market.producer.service.IStockProducerService;
import com.stock.market.producer.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StockControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IStockProducerService stockProducerService;

    @InjectMocks
    private StockController stockController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    public void testProduceStocks() throws Exception {
        mockMvc.perform(post(Constant.API_BASE_PATH + Constant.PRODUCE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(stockProducerService, times(1)).sendStockDetails();
    }
}
