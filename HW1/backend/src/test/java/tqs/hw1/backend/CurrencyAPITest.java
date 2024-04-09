package tqs.hw1.backend;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.gson.JsonSyntaxException;

import tqs.hw1.backend.cache.Currency;
import tqs.hw1.backend.cache.CurrencyAPI;

public class CurrencyAPITest {

    /*
     * TODO: REFACTOR WITH MOCK
     */

    private CurrencyAPI currencyAPI;
    private String apiKey;

    @BeforeEach
    public void setUp() {
        apiKey = "fca_live_3hZibvJW9Scmg7giaauLj5XmhaotgLWrBDLtUDAN";
        currencyAPI = new CurrencyAPI(apiKey, "https://api.freecurrencyapi.com/v1/");
    }

    @Test
    public void testGetExchangeRate_Success() throws IOException {
        Currency fromCurrency = Currency.EUR;
        Currency toCurrency = Currency.USD;
        assertThat(String.format("%.6f", 1.0862243414f), is(equalTo(String.format("%.6f", currencyAPI.getExchangeRate(fromCurrency, toCurrency)))));
    }

    @Test
    public void testIncorrectAPIKey() throws IOException {
        apiKey = "ajodhidad";
        currencyAPI = new CurrencyAPI(apiKey, "https://api.freecurrencyapi.com/v1/");
        Currency fromCurrency = Currency.EUR;
        Currency toCurrency = Currency.USD;
        assertThrows(JsonSyntaxException.class, () -> currencyAPI.getExchangeRate(fromCurrency, toCurrency));
    }
}
