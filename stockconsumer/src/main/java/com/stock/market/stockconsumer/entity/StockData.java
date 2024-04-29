package com.stock.market.stockconsumer.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stock_data")
@Data
public class StockData {

    @Id
    private String id;

    private String symbol;
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double adjClose;
    private double volume;
    private double closeUSD;
}
