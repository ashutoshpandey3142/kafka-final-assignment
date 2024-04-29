package com.stock.market.stockconsumer.controller;

import com.stock.market.stockconsumer.dto.StockAnalyticsDTO;
import com.stock.market.stockconsumer.dto.StockDataDTO;
import com.stock.market.stockconsumer.service.IStockConsumerService;
import com.stock.market.stockconsumer.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constant.API_BASE_PATH)
public class StockConsumerController {

    private final IStockConsumerService stockConsumerService;

    @Autowired
    public StockConsumerController(IStockConsumerService stockConsumerService) {
        this.stockConsumerService = stockConsumerService;
    }

    @GetMapping(Constant.ALL_STOCKS_PATH)
    public ResponseEntity<List<StockDataDTO>> getAllStockData() {
        return ResponseEntity.ok(stockConsumerService.getAllStockData());
    }
    @GetMapping(Constant.ANALYTICS_PATH)
    public ResponseEntity<List<StockAnalyticsDTO>> getAllStockAnalytics() {
        return ResponseEntity.ok(stockConsumerService.getAllStockAnalytics());
    }
}
