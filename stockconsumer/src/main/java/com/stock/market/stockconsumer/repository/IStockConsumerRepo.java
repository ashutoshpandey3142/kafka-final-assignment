package com.stock.market.stockconsumer.repository;

import com.stock.market.stockconsumer.entity.StockData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IStockConsumerRepo extends MongoRepository<StockData, String> {
}
