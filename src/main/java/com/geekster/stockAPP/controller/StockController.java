package com.geekster.stockAPP.controller;

import com.geekster.stockAPP.model.Stock;
import com.geekster.stockAPP.model.Type;
import com.geekster.stockAPP.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class StockController {

    @Autowired
    StockService stockService;

    @PostMapping("stocks")
    public String addStocks(@RequestBody List<Stock> newStocks){
        return stockService.addStocks(newStocks);
    }

    @GetMapping("stocks")
    public List<Stock> getStocks(){
        return stockService.getStocks();
    }

    @GetMapping("stocks/id/{stockId}")
    public Optional<Stock> getStock(@PathVariable Long stockId){
        return stockService.getStock(stockId);
    }

    @DeleteMapping("stocks/id/{stockId}")
    public String deleteById(@PathVariable Long stockId){
        return stockService.deleteById(stockId);
    }

    @PutMapping("stocks/id")
    public String updateStockById(@RequestParam Long stockId, @RequestParam double stockPrice){
        return stockService.updateStockById(stockId, stockPrice);
    }

    @PutMapping("stock/price/type")
    public String updateStockByType(@RequestParam float hike, @RequestParam Type stockType){
        return stockService.updateStockByType(hike, stockType);
    }

    @GetMapping("stocks/type/{stockType}/price/{stockPrice}")
    public List<Stock> getStocksByTypeLessEqualPrice(@PathVariable Type stockType, @PathVariable double stockPrice){
        return stockService.getStocksByTypeLessEqualPrice(stockType, stockPrice);
    }

    @GetMapping("stocks/less/price/{stockPrice}/less/year/{time}")
    public List<Stock> getAllStocksLessPriceLessTime(@PathVariable double stockPrice, @PathVariable LocalDateTime time){
        return stockService.getAllStocksLessPriceLessTime(stockPrice, time);
    }

}
