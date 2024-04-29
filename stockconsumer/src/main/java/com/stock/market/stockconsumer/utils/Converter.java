package com.stock.market.stockconsumer.utils;

import com.stock.market.stockconsumer.dto.StockAnalyticsDTO;
import com.stock.market.stockconsumer.dto.StockDataDTO;
import com.stock.market.stockconsumer.entity.StockAnalytics;
import com.stock.market.stockconsumer.entity.StockData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    private final ModelMapper modelMapper;

    @Autowired
    public Converter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StockData convertToEntity(StockDataDTO stockDataDTO){
        return modelMapper.map(stockDataDTO, StockData.class);
    }
    public StockDataDTO convertToDTO(StockData stockData){
        return modelMapper.map(stockData, StockDataDTO.class);
    }
    public StockAnalytics convertToEntity(StockAnalyticsDTO stockAnalyticsDTO){
        return modelMapper.map(stockAnalyticsDTO, StockAnalytics.class);
    }
    public StockAnalyticsDTO convertToDTO(StockAnalytics stockAnalytics){
        return modelMapper.map(stockAnalytics, StockAnalyticsDTO.class);
    }
}
