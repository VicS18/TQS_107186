package tqs.lab2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StockMarketTest 
{
    @Mock
    IStockmarketService stockmarketService;

    @InjectMocks
    StocksPortfolio stocksPortfolio;

    List<Stock> stocks;

    @BeforeEach
    public void setupStocks(){
        stocks = Arrays.asList( new Stock("TST", 10), new Stock("POE", 102), new Stock("SFM", 1) );
        stocks.forEach((s) -> stocksPortfolio.addStock(s));
    }


    @Test
    public void totalValue(){        
        when(stockmarketService.lookUpPrice("TST")).thenReturn(2928.129);
        when(stockmarketService.lookUpPrice("POE")).thenReturn(213.129);
        when(stockmarketService.lookUpPrice("SFM")).thenReturn(13.2765);

        assertEquals("51033,7245", String.format("%.4f", stocksPortfolio.totalValue()));

        verify(stockmarketService, atLeastOnce()).lookUpPrice("TST");
        verify(stockmarketService, atLeastOnce()).lookUpPrice("POE");
        verify(stockmarketService, atLeastOnce()).lookUpPrice("SFM");
    }
}
