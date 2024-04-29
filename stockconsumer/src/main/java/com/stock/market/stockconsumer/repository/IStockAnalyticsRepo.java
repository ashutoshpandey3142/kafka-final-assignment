package com.stock.market.stockconsumer.repository;

import com.stock.market.stockconsumer.entity.StockAnalytics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IStockAnalyticsRepo extends MongoRepository<StockAnalytics, String> {
}
