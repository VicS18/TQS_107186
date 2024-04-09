package tqs.hw1.backend.cache;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CurrencyAPI {
    private final String KEY;
    private final String URL;

    private static Logger logger = LogManager.getLogger(CurrencyAPI.class);

    public CurrencyAPI(final String key, final String url) {
        this.KEY = key;
        this.URL = url;
    }

    public Double getExchangeRate(Currency fromCurrency, Currency toCurrency) throws IOException{
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
        .url(URL + "latest?apikey=" + KEY + "&base_currency=" + fromCurrency.toString() + "&currencies=" + toCurrency.toString())
        .build();

        String jsonResponse = client.newCall(request).execute().body().string();

        logger.info(jsonResponse);

        Gson gson = new Gson();
        TypeToken<Map<String, Map<String, Double>>> mapType = new TypeToken<Map<String, Map<String, Double>>>(){};
        Map<String, Map<String, Double>> stringMap = gson.fromJson(jsonResponse, mapType);
        // TODO: Handle key not found
        return stringMap.get("data").get(toCurrency.toString());
    }   
}
