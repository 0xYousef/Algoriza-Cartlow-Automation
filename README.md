Here’s how you can isolate the Java utility into clean, modular classes for currency conversion and price comparison:

---

### 🧱 1. `ExchangeRateProvider.java`

```java
import java.util.Map;

public class ExchangeRateProvider {
    private final Map<String, Double> exchangeRates;

    public ExchangeRateProvider(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public double getRate(String currency) {
        return exchangeRates.getOrDefault(currency, 1.0);
    }
}
```

---

### 💱 2. `CurrencyConverter.java`

```java
public class CurrencyConverter {
    private final ExchangeRateProvider rateProvider;

    public CurrencyConverter(ExchangeRateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        double fromRate = rateProvider.getRate(fromCurrency);
        double toRate = rateProvider.getRate(toCurrency);
        return amount * (fromRate / toRate);
    }
}
```

---

### 📦 3. `ProductPrice.java`

```java
public class ProductPrice {
    public double amount;
    public String currency;
    public double converted;

    public ProductPrice(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s (%.2f USD)", amount, currency, converted);
    }
}
```

---

### 📊 4. `PriceComparator.java`

```java
import java.util.*;

public class PriceComparator {
    private final CurrencyConverter converter;

    public PriceComparator(CurrencyConverter converter) {
        this.converter = converter;
    }

    public List<ProductPrice> compare(List<ProductPrice> prices, String baseCurrency) {
        for (ProductPrice price : prices) {
            price.converted = converter.convert(price.amount, price.currency, baseCurrency);
        }
        prices.sort(Comparator.comparingDouble(p -> p.converted));
        return prices;
    }
}
```

---

### 🚀 5. `Main.java` (Usage Example)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Double> rates = Map.of("SAR", 0.27, "AED", 0.27, "USD", 1.0);
        ExchangeRateProvider provider = new ExchangeRateProvider(rates);
        CurrencyConverter converter = new CurrencyConverter(provider);
        PriceComparator comparator = new PriceComparator(converter);

        List<ProductPrice> prices = List.of(
            new ProductPrice(97, "SAR"),
            new ProductPrice(102, "AED"),
            new ProductPrice(26.22, "USD")
        );

        List<ProductPrice> sorted = comparator.compare(new ArrayList<>(prices), "USD");
        sorted.forEach(System.out::println);
    }
}
```

---

This structure makes your code:
- **Modular**: Each class has a single responsibility.
- **Testable**: Easy to write unit tests for each class.
- **Extendable**: You can plug in live exchange rates or add caching.

Would you like me to help you add support for live exchange rates or build a REST API around this?
