package com.stock.market.stockconsumer.service.impl;

import com.stock.market.stockconsumer.dto.StockAnalyticsDTO;
import com.stock.market.stockconsumer.dto.StockDataDTO;
import com.stock.market.stockconsumer.entity.StockAnalytics;
import com.stock.market.stockconsumer.entity.StockData;
import com.stock.market.stockconsumer.exception.StockException;
import com.stock.market.stockconsumer.repository.IStockAnalyticsRepo;
import com.stock.market.stockconsumer.repository.IStockConsumerRepo;
import com.stock.market.stockconsumer.service.IStockConsumerService;
import com.stock.market.stockconsumer.utils.Constant;
import com.stock.market.stockconsumer.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StockConsumerService implements IStockConsumerService {
    private static final Logger logger = Logger.getLogger(StockConsumerService.class.getName());

    private final Converter converter;
    private final IStockConsumerRepo stockConsumerRepo;
    private final IStockAnalyticsRepo stockAnalyticsRepo;

    @Autowired
    public StockConsumerService(Converter converter, IStockConsumerRepo stockConsumerRepo, IStockAnalyticsRepo stockAnalyticsRepo) {
        this.converter = converter;
        this.stockConsumerRepo = stockConsumerRepo;
        this.stockAnalyticsRepo = stockAnalyticsRepo;
    }

    @Override
    @KafkaListener(
            topics = Constant.STOCK_SIMULATION_TOPIC,
            groupId = Constant.STOCK_DETAILS_GROUP_ID
    )
    public void consumeStockDetails(StockDataDTO message) {
        logger.info("Received stock data: " + message);
        try {
            StockData stockData = converter.convertToEntity(message);
            StockData savedStock = stockConsumerRepo.save(stockData);
            System.out.println("Saved stock :" + savedStock);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error consuming stock details: " + e.getMessage(), e);
            throw new StockException(e.getMessage());
        }
    }

    @Override
    public List<StockDataDTO> getAllStockData() {
        logger.info("Fetching all stock data...");
        try {
            List<StockData> stockDataList = stockConsumerRepo.findAll();
            return stockDataList.stream().map(converter::convertToDTO).toList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all stock data: " + e.getMessage(), e);
            throw new StockException(e.getMessage());
        }
    }

    @Override
    @KafkaListener(
            topics = Constant.STOCK_SIMULATION_TOPIC,
            groupId = Constant.STOCK_ANALYSIS_GROUP_ID
    )
    public void calculateStockAnalytics(StockDataDTO stockDataDTO) {
        logger.info("Calculating stock analytics for: " + stockDataDTO);
        try {
            double priceChangePercentage = ((stockDataDTO.getCloseUSD() - stockDataDTO.getClose()) / stockDataDTO.getClose()) * 100;

            StockAnalytics stockAnalytics = getStockAnalytics(stockDataDTO, priceChangePercentage);
            stockAnalyticsRepo.save(stockAnalytics);
            System.out.println("Stock Analytics" + stockAnalytics);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error calculating stock analytics:" + e.getMessage(), e);
            throw new StockException(e.getMessage());
        }
    }

    private static StockAnalytics getStockAnalytics(StockDataDTO stockDataDTO, double priceChangePercentage) {
        double tradingRange = stockDataDTO.getHigh() - stockDataDTO.getLow();

        double sumCloseVolume = stockDataDTO.getClose() * (stockDataDTO.getVolume() + 1);
        double sumVolume = stockDataDTO.getVolume() + 1;

        double vwap = sumCloseVolume / sumVolume;

        StockAnalytics stockAnalytics = new StockAnalytics();
        stockAnalytics.setPriceChangePercentage(priceChangePercentage);
        stockAnalytics.setTradingRange(tradingRange);
        stockAnalytics.setVwap(vwap);
        return stockAnalytics;
    }

    @Override
    public List<StockAnalyticsDTO> getAllStockAnalytics() {
        logger.info("Fetching all stock analytics...");
        try {
            List<StockAnalytics> stockAnalyticsList = stockAnalyticsRepo.findAll();
            return stockAnalyticsList.stream().map(converter::convertToDTO).toList();
        } catch (Exception e) {
            throw new StockException(e.getMessage());
        }
    }
}
