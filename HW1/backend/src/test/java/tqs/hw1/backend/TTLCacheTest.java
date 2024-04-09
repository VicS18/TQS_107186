package tqs.hw1.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.hw1.backend.cache.Currency;
import tqs.hw1.backend.cache.CurrencyAPI;
import tqs.hw1.backend.cache.TTLCache;

@ExtendWith(MockitoExtension.class)
public class TTLCacheTest {

    @Mock
    private CurrencyAPI currencyAPI;

    @InjectMocks
    private TTLCache ttlCache;

    @BeforeEach
    public void setUp() {
        ttlCache = new TTLCache(currencyAPI, Duration.ofSeconds(5)); // 30 sec TTL 
    }

    @Test
    public void testGetExchangeRate_Cached() throws IOException {
        // Mock API
        when(currencyAPI.getExchangeRate(Currency.EUR, Currency.USD)).thenReturn(1.0);

        ttlCache.getExchangeRate(Currency.USD); // Cache it 
        
        // Check for caching
        assertEquals(1.0, ttlCache.getExchangeRate(Currency.USD));
        verify(currencyAPI, times(1)).getExchangeRate(any(), any()); // Ensure API is called only once
    }

    @Test
    public void testGetExchangeRate_Expired() throws IOException, InterruptedException {
        // Mock API
        when(currencyAPI.getExchangeRate(Currency.EUR, Currency.USD)).thenReturn(1.0);

        double rate = ttlCache.getExchangeRate(Currency.USD);
        assertEquals(1.0, rate);

        // In the mean time, the stock exchange has changed...
        when(currencyAPI.getExchangeRate(Currency.EUR, Currency.USD)).thenReturn(2.0);

        // Check for caching again
        rate = ttlCache.getExchangeRate(Currency.USD);
        assertEquals(1.0, rate);

        Thread.sleep(5500);

        // Expiration check
        rate = ttlCache.getExchangeRate(Currency.USD);
        assertEquals(2.0, rate);
        verify(currencyAPI, times(2)).getExchangeRate(Currency.EUR, Currency.USD); // Ensure API is called twice
    }
}

