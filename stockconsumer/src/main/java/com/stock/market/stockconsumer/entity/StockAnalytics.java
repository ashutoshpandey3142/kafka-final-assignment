package com.stock.market.stockconsumer.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("stock_analytics")
@Data
public class StockAnalytics {
    @Id
    private String id;
    private double priceChangePercentage;
    private double tradingRange;
    private double vwap; //Volume Weighted Average Price
}