package com.stock.market.producer.utils;

import com.stock.market.stockconsumer.dto.StockDataDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVReaderUtil {

    private final ResourceLoader resourceLoader;

    public CSVReaderUtil(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<StockDataDTO> readCSV(String filePath) throws IOException {
        List<StockDataDTO> records = new ArrayList<>();

        Resource resource = resourceLoader.getResource("classpath:" + filePath);

        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVParser csvParser = new CSVParser(reader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                StockDataDTO stockDataDTO = new StockDataDTO();
                stockDataDTO.setDate(csvRecord.get("Date"));
                stockDataDTO.setSymbol(csvRecord.get("Index"));
                stockDataDTO.setOpen(Double.parseDouble(csvRecord.get("Open")));
                stockDataDTO.setLow(Double.parseDouble(csvRecord.get("Low")));
                stockDataDTO.setClose(Double.parseDouble(csvRecord.get("Close")));
                stockDataDTO.setHigh(Double.parseDouble(csvRecord.get("High")));
                stockDataDTO.setAdjClose(Double.parseDouble(csvRecord.get("Adj Close")));
                stockDataDTO.setVolume(Double.parseDouble(csvRecord.get("Volume")));
                stockDataDTO.setCloseUSD(Double.parseDouble(csvRecord.get("CloseUSD")));
                records.add(stockDataDTO);
            }
        }

        return records;
    }
}
