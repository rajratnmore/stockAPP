package com.geekster.stockAPP.service;

import com.geekster.stockAPP.model.Stock;
import com.geekster.stockAPP.model.Type;
import com.geekster.stockAPP.repo.IStockRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    IStockRepo iStockRepo;


    public String addStocks(List<Stock> newStocks) {
        iStockRepo.saveAll(newStocks);
        return newStocks.size()+" new stock added";
    }

    public List<Stock> getStocks() {
        return (List<Stock>) iStockRepo.findAll();
    }

    public String deleteById(Long stockId) {
        iStockRepo.deleteById(stockId);
        return "stock has been deleted";
    }

    public Optional<Stock> getStock(Long stockId) {
        return iStockRepo.findById(stockId);
    }

    public String updateStockById(Long stockId, double stockPrice) {
        Stock  stock = iStockRepo.findById(stockId).orElse(null);
        if(stock!= null){
            stock.setStockPrice(stockPrice);
            iStockRepo.save(stock);
            return "Stock updated.";
        }
        return "Stock not found !";
    }

    public List<Stock> getStocksByTypeLessEqualPrice(Type stockType, double stockPrice) {
        return iStockRepo.findByStockTypeAndStockPriceLessThanEqualOrderByStockPriceDesc(stockType, stockPrice);
    }

    public List<Stock> getAllStocksLessPriceLessTime(double stockPrice, LocalDateTime time) {
        return iStockRepo.findByStockPriceLessThanAndStockCreationTimeStampLessThanOrderByStockOwnerCountDesc(stockPrice, time);
    }

    @Transactional
    public String updateStockByType(float hike, Type stockType) {
        iStockRepo.updateStockByType(hike, stockType.name());
        return "updated";
    }

}
