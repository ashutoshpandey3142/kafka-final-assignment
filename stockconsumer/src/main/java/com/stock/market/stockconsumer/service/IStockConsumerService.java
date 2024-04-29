package com.stock.market.stockconsumer.service;

import com.stock.market.stockconsumer.dto.StockAnalyticsDTO;
import com.stock.market.stockconsumer.dto.StockDataDTO;

import java.util.List;

public interface IStockConsumerService {

    void consumeStockDetails(StockDataDTO message);
    List<StockDataDTO> getAllStockData();

    void calculateStockAnalytics(StockDataDTO stockDataDTO);
    List<StockAnalyticsDTO> getAllStockAnalytics();
}
