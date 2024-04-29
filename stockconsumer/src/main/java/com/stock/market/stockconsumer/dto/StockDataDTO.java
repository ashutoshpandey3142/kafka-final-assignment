package com.stock.market.stockconsumer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockDataDTO {

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
