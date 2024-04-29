package com.stock.market.stockconsumer.dto;

import lombok.Data;

@Data
public class StockAnalyticsDTO {
    private double priceChangePercentage;
    private double tradingRange;
    private double vwap;
}
