package com.stock.market.stockconsumer.service;

import com.stock.market.stockconsumer.dto.StockAnalyticsDTO;
import com.stock.market.stockconsumer.dto.StockDataDTO;
import com.stock.market.stockconsumer.entity.StockAnalytics;
import com.stock.market.stockconsumer.entity.StockData;
import com.stock.market.stockconsumer.repository.IStockAnalyticsRepo;
import com.stock.market.stockconsumer.repository.IStockConsumerRepo;
import com.stock.market.stockconsumer.service.impl.StockConsumerService;
import com.stock.market.stockconsumer.utils.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StockConsumerServiceTest {

    @Mock
    private IStockConsumerRepo stockConsumerRepo;

    @Mock
    private IStockAnalyticsRepo stockAnalyticsRepo;

    @Mock
    private Converter converter;

    @InjectMocks
    private StockConsumerService stockConsumerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConsumeStockDetails() {
        StockDataDTO stockDataDTO = new StockDataDTO();
        StockData stockData = new StockData(); // create expected StockData
        when(converter.convertToEntity(stockDataDTO)).thenReturn(stockData);
        when(stockConsumerRepo.save(stockData)).thenReturn(stockData);
        when(converter.convertToDTO(stockData)).thenReturn(stockDataDTO);
        assertDoesNotThrow(() -> stockConsumerService.consumeStockDetails(stockDataDTO));

    }

    @Test
    public void testCalculateStockAnalytics() {
        // Mock data
        StockDataDTO stockDataDTO = new StockDataDTO();
        stockDataDTO.setCloseUSD(100.0);
        stockDataDTO.setClose(90.0);
        stockDataDTO.setHigh(110.0);
        stockDataDTO.setLow(80.0);

        assertDoesNotThrow(() -> stockConsumerService.calculateStockAnalytics(stockDataDTO));


    }

    @Test
    public void testGetAllStockData() {
        List<StockData> stockDataList = new ArrayList<>();
        when(stockConsumerRepo.findAll()).thenReturn(stockDataList);
        when(converter.convertToDTO(any(StockData.class))).thenReturn(new StockDataDTO());

        List<StockDataDTO> result = stockConsumerService.getAllStockData();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetAllStockAnalytics() {
        StockAnalyticsDTO stockAnalyticsDTO  = new StockAnalyticsDTO();
        stockAnalyticsDTO.setPriceChangePercentage(2.3);
        stockAnalyticsDTO.setVwap(2.3);
        stockAnalyticsDTO.setTradingRange(2.3);
        List<StockAnalytics> stockAnalyticsList = new ArrayList<>();
        when(stockAnalyticsRepo.findAll()).thenReturn(stockAnalyticsList);
        when(converter.convertToDTO(any(StockAnalytics.class))).thenReturn(stockAnalyticsDTO);

        List<StockAnalyticsDTO> result = stockConsumerService.getAllStockAnalytics();

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
