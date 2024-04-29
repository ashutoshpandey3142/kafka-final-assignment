package com.stock.market.producer.exception;

import lombok.Data;


@Data
public class ErrorResponse {
    private String message;
    private int status;
}
