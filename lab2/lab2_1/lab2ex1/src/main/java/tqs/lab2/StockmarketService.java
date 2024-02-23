package tqs.lab2;

// Pseudo-random virtual stock market.
public class StockmarketService implements IStockmarketService{
    public static final double UPPER_BOUND = 10000;
    public static final double LOWER_BOUND = 1.083;

    // Accepts any label and returns a random value.
    public double lookUpPrice(String label){
        return Math.random()*(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND;
    }
}
