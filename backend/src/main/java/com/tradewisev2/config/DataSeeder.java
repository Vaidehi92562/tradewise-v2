package com.tradewisev2.config;

import com.tradewisev2.model.Stock;
import com.tradewisev2.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedStocks(StockRepository stockRepository) {
        return args -> {
            if (stockRepository.count() == 0) {
                List<Stock> stocks = List.of(
                        Stock.builder().symbol("RELIANCE").companyName("Reliance Industries").currentPrice(2948.40).previousPrice(2940.00).previousClose(2926.85).dayHigh(2960.00).dayLow(2910.00).updatedAt(LocalDateTime.now()).build(),
                        Stock.builder().symbol("TCS").companyName("Tata Consultancy Services").currentPrice(4012.85).previousPrice(4015.00).previousClose(4025.30).dayHigh(4040.00).dayLow(3998.00).updatedAt(LocalDateTime.now()).build(),
                        Stock.builder().symbol("INFY").companyName("Infosys").currentPrice(1629.20).previousPrice(1624.00).previousClose(1619.35).dayHigh(1635.00).dayLow(1605.00).updatedAt(LocalDateTime.now()).build(),
                        Stock.builder().symbol("HDFCBANK").companyName("HDFC Bank").currentPrice(1687.45).previousPrice(1685.00).previousClose(1681.45).dayHigh(1694.00).dayLow(1673.00).updatedAt(LocalDateTime.now()).build(),
                        Stock.builder().symbol("ICICIBANK").companyName("ICICI Bank").currentPrice(1098.25).previousPrice(1095.00).previousClose(1090.45).dayHigh(1105.00).dayLow(1084.00).updatedAt(LocalDateTime.now()).build(),
                        Stock.builder().symbol("SBIN").companyName("State Bank of India").currentPrice(822.90).previousPrice(826.00).previousClose(832.45).dayHigh(835.00).dayLow(818.00).updatedAt(LocalDateTime.now()).build(),
                        Stock.builder().symbol("ITC").companyName("ITC Limited").currentPrice(469.30).previousPrice(468.00).previousClose(466.90).dayHigh(472.00).dayLow(464.00).updatedAt(LocalDateTime.now()).build(),
                        Stock.builder().symbol("AXISBANK").companyName("Axis Bank").currentPrice(1182.75).previousPrice(1184.00).previousClose(1186.30).dayHigh(1193.00).dayLow(1175.00).updatedAt(LocalDateTime.now()).build(),
                        Stock.builder().symbol("LT").companyName("Larsen & Toubro").currentPrice(3624.10).previousPrice(3620.00).previousClose(3608.50).dayHigh(3638.00).dayLow(3599.00).updatedAt(LocalDateTime.now()).build(),
                        Stock.builder().symbol("WIPRO").companyName("Wipro Limited").currentPrice(548.20).previousPrice(546.00).previousClose(542.30).dayHigh(551.00).dayLow(539.00).updatedAt(LocalDateTime.now()).build()
                );

                stockRepository.saveAll(stocks);
            }
        };
    }
}
