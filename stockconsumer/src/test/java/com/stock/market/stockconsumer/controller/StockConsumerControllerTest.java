package com.stock.market.stockconsumer.controller;

import com.stock.market.stockconsumer.dto.StockAnalyticsDTO;
import com.stock.market.stockconsumer.dto.StockDataDTO;
import com.stock.market.stockconsumer.service.IStockConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StockConsumerControllerTest {

    @Mock
    private IStockConsumerService stockConsumerService;

    @InjectMocks
    private StockConsumerController stockConsumerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllStockData() {
        // Mock data
        List<StockDataDTO> stockDataList = new ArrayList<>();
        when(stockConsumerService.getAllStockData()).thenReturn(stockDataList);

        // Call the method under test
        ResponseEntity<List<StockDataDTO>> response = stockConsumerController.getAllStockData();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stockDataList, response.getBody());
    }

    @Test
    public void testGetAllStockAnalytics() {
        // Mock data
        List<StockAnalyticsDTO> stockAnalyticsList = new ArrayList<>();
        when(stockConsumerService.getAllStockAnalytics()).thenReturn(stockAnalyticsList);

        // Call the method under test
        ResponseEntity<List<StockAnalyticsDTO>> response = stockConsumerController.getAllStockAnalytics();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stockAnalyticsList, response.getBody());
    }
}
