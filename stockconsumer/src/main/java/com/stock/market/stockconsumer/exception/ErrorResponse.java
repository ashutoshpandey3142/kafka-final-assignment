package com.stock.market.stockconsumer.exception;

import lombok.Data;


@Data
public class ErrorResponse {
    private String message;
    private int status;
}
