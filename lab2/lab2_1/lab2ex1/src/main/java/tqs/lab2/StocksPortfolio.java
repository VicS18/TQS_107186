package tqs.lab2;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio{
    private List<Stock> stocks = new ArrayList<Stock>();
    private IStockmarketService stockMarket;

    public StocksPortfolio(IStockmarketService stockMarket) {
        this.stockMarket = stockMarket;
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public double totalValue(){
        double sum = 0.0;
        for(Stock stock : stocks)
            sum += stock.getQuantity() * stockMarket.lookUpPrice(stock.getLabel());
        return sum;
    }
}