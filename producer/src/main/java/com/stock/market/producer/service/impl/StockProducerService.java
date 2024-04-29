package com.stock.market.producer.service.impl;

import com.stock.market.producer.service.IStockProducerService;
import com.stock.market.producer.utils.CSVReaderUtil;
import com.stock.market.producer.utils.Constant;
import com.stock.market.stockconsumer.dto.StockDataDTO;
import com.stock.market.stockconsumer.exception.StockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class StockProducerService implements IStockProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final CSVReaderUtil csvReaderUtil;

    @Autowired
    public StockProducerService(KafkaTemplate<String, Object> kafkaTemplate, CSVReaderUtil csvReaderUtil) {
        this.kafkaTemplate = kafkaTemplate;
        this.csvReaderUtil = csvReaderUtil;
    }


    @Override
    public void sendStockDetails() throws IOException {
        try {
            List<StockDataDTO> csvData = csvReaderUtil.readCSV(Constant.CSV_FILE_PATH);

            Random random = new Random();

            while (true) {
                StockDataDTO randomRecord = csvData.get(random.nextInt(csvData.size()));

                kafkaTemplate.send(Constant.STOCK_SIMULATION_TOPIC, randomRecord);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread interrupted while sleeping");
                }
            }
        }catch (Exception e) {
            throw new StockException(e.getMessage());
        }

    }

}
