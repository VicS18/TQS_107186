package tqs.hw1.backend.cache;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class TTLCache {
    private final Currency baseCurrency = Currency.EUR;
    private Map<Currency, Double> exchangeRates = new HashMap<>();
    private Map<Currency, Instant> expiryTimes = new HashMap<>();
    private final CurrencyAPI currencyAPI;
    private final Duration TTL;

    public TTLCache(CurrencyAPI currencyAPI, Duration TTL, Currency baseCurrency) {
        this.currencyAPI = currencyAPI;
        this.TTL = TTL;
    }

    public TTLCache(CurrencyAPI currencyAPI, Duration TTL) {
        this.currencyAPI = currencyAPI;
        this.TTL = TTL;
    }

    public Double getExchangeRate(Currency currency) throws IOException {
        if (exchangeRates.containsKey(currency) && !isExpired(currency)) {
            return exchangeRates.get(currency);
        }

        Double exchangeRate = currencyAPI.getExchangeRate(baseCurrency, currency);

        exchangeRates.put(currency, exchangeRate);
        expiryTimes.put(currency, Instant.now().plus(TTL));

        return exchangeRate;
    }

    private boolean isExpired(Currency currency) {
        return Instant.now().isAfter(expiryTimes.get(currency));
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }
}
