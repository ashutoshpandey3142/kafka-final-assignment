package com.stock.market.producer.controller;

import com.stock.market.producer.service.IStockProducerService;
import com.stock.market.producer.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(Constant.API_BASE_PATH)
public class StockController {

    private final IStockProducerService stockProducerService;

    @Autowired
    public StockController(IStockProducerService stockProducerService) {
        this.stockProducerService = stockProducerService;
    }

    @PostMapping(Constant.PRODUCE_PATH)
    public String produceStocks() throws IOException {
        stockProducerService.sendStockDetails();
        return Constant.SUCCESS_MESSAGE;
    }
}
