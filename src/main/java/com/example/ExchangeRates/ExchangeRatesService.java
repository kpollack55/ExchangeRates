package com.example.ExchangeRates;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class ExchangeRatesService {

    protected BigDecimal exchangeRates(String currencyAmount, String sourceCurrency, String targetCurrency) {
        if(validateCurrencyType(sourceCurrency) && validateCurrencyType(targetCurrency) && validateCurrencyAmount(currencyAmount)) {
            JsonNode rootNode = getExchangeRateData();
            BigDecimal sourceExchangeRate = parseTargetCurrencyRate(rootNode, sourceCurrency);
            BigDecimal targetExchangeRate = parseTargetCurrencyRate(rootNode, targetCurrency);
            BigDecimal exchangedAmount = getTargetCurrencyAmount(new BigDecimal(currencyAmount),sourceExchangeRate,targetExchangeRate);

            return exchangedAmount;
        } else {
            throw new RuntimeException("Failed to get exchange rate");
        }

    }

    protected JsonNode getExchangeRateData() {
        JsonNode rootNode = null;
        String baseUrl = "http://api.exchangeratesapi.io/v1/latest?access_key=2ee7f8671988a17eb695029130598be5";
        try {
            URL url = new URL(baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            url.openConnection();
            conn.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(conn.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootNode;
    }

    protected BigDecimal parseTargetCurrencyRate(JsonNode jsonNode, String currencyDenomination) {
        String exchangeRate = String.valueOf(jsonNode.get("rates").get(currencyDenomination));
        BigDecimal exchangedCurrency = new BigDecimal(Double.parseDouble(exchangeRate));

        return exchangedCurrency;
    }

    protected BigDecimal getTargetCurrencyAmount(BigDecimal currencyAmount, BigDecimal sourceExchangeRate, BigDecimal targetExchangeRate ) {
        BigDecimal exchangeAmount = currencyAmount.divide(sourceExchangeRate, 5, BigDecimal.ROUND_FLOOR);
        exchangeAmount = exchangeAmount.multiply(targetExchangeRate);

        return exchangeAmount;
    }

    protected boolean validateCurrencyType(String currencyType) {
        try {
            CurrencyDenominationsEnum.valueOf(currencyType);
        } catch(IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    protected boolean validateCurrencyAmount(String currencyAmount) {
        try {
            Double.parseDouble(currencyAmount);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

}
