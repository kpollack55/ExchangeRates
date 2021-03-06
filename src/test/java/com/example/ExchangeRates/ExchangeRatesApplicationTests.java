package com.example.ExchangeRates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

@SpringBootTest()
class ExchangeRatesApplicationTests {

	String jsonString = "{\"success\":true,\"timestamp\":1649812623,\"base\":\"EUR\",\"date\":\"2022-04-13\",\"rates\":{\"AED\":3.974575,\"AFN\":95.61718,\"ALL\":121.05882,\"AMD\":514.965483,\"ANG\":1.959271,\"AOA\":455.418621,\"ARS\":121.966168,\"AUD\":1.452051,\"AWG\":1.947736,\"AZN\":1.838495,\"BAM\":1.956591,\"BBD\":2.195267,\"BDT\":93.704937,\"BGN\":1.955649,\"BHD\":0.40801,\"BIF\":2231.027525,\"BMD\":1.082076,\"BND\":1.482163,\"BOB\":7.48491,\"BRL\":5.058162,\"BSD\":1.087228,\"BTC\":2.7192425e-5,\"BTN\":82.73709,\"BWP\":12.525786,\"BYN\":3.60265,\"BYR\":21208.684906,\"BZD\":2.191565,\"CAD\":1.366927,\"CDF\":2174.972347,\"CHF\":1.009858,\"CLF\":0.03161,\"CLP\":872.207291,\"CNY\":6.888597,\"COP\":4058.022159,\"CRC\":712.8639,\"CUC\":1.082076,\"CUP\":28.675008,\"CVE\":110.307383,\"CZK\":24.45686,\"DJF\":193.554044,\"DKK\":7.436241,\"DOP\":59.780969,\"DZD\":155.19674,\"EGP\":20.017645,\"ERN\":16.23114,\"ETB\":55.929431,\"EUR\":1,\"FJD\":2.2834,\"FKP\":0.832834,\"GBP\":0.832295,\"GEL\":3.310917,\"GGP\":0.832834,\"GHS\":8.262213,\"GIP\":0.832834,\"GMD\":58.486055,\"GNF\":9687.877559,\"GTQ\":8.341466,\"GYD\":227.3384,\"HKD\":8.480817,\"HNL\":26.682129,\"HRK\":7.55137,\"HTG\":118.496529,\"HUF\":377.882284,\"IDR\":15546.182451,\"ILS\":3.465569,\"IMP\":0.832834,\"INR\":82.399474,\"IQD\":1586.681638,\"IRR\":45771.804407,\"ISK\":139.598176,\"JEP\":0.832834,\"JMD\":168.073802,\"JOD\":0.76716,\"JPY\":135.884927,\"KES\":125.509843,\"KGS\":93.079882,\"KHR\":4397.36926,\"KMF\":491.668162,\"KPW\":973.868204,\"KRW\":1330.406744,\"KWD\":0.329892,\"KYD\":0.905952,\"KZT\":487.749659,\"LAK\":12916.375827,\"LBP\":1644.098837,\"LKR\":353.350987,\"LRD\":164.746171,\"LSL\":15.787574,\"LTL\":3.195089,\"LVL\":0.654537,\"LYD\":5.105281,\"MAD\":10.676151,\"MDL\":20.021428,\"MGA\":4394.739182,\"MKD\":61.638968,\"MMK\":2013.012385,\"MNT\":3198.796001,\"MOP\":8.774893,\"MRO\":386.30086,\"MUR\":46.802196,\"MVR\":16.718077,\"MWK\":880.469435,\"MXN\":21.426025,\"MYR\":4.577721,\"MZN\":69.068785,\"NAD\":15.787286,\"NGN\":448.617489,\"NIO\":38.923416,\"NOK\":9.498134,\"NPR\":132.378251,\"NZD\":1.57821,\"OMR\":0.416628,\"PAB\":1.087123,\"PEN\":4.03747,\"PGK\":3.830554,\"PHP\":56.228958,\"PKR\":197.740578,\"PLN\":4.640182,\"PYG\":7416.003298,\"QAR\":3.939814,\"RON\":4.939896,\"RSD\":117.751921,\"RUB\":92.382229,\"RWF\":1106.145231,\"SAR\":4.057915,\"SBD\":8.660138,\"SCR\":15.599732,\"SDG\":483.687792,\"SEK\":10.307627,\"SGD\":1.476097,\"SHP\":1.490451,\"SLL\":13152.630763,\"SOS\":625.980698,\"SRD\":22.424401,\"STD\":22396.78354,\"SVC\":9.512699,\"SYP\":2718.661087,\"SZL\":15.843698,\"THB\":36.244663,\"TJS\":13.589021,\"TMT\":3.787265,\"TND\":3.244608,\"TOP\":2.450688,\"TRY\":15.78554,\"TTD\":7.385172,\"TWD\":31.524655,\"TZS\":2512.858034,\"UAH\":32.097629,\"UGX\":3826.687991,\"USD\":1.082076,\"UYU\":45.277769,\"UZS\":12300.966857,\"VEF\":231380500453.79294,\"VND\":24768.714158,\"VUV\":122.375521,\"WST\":2.810847,\"XAF\":656.215199,\"XAG\":0.042585,\"XAU\":0.000551,\"XCD\":2.924364,\"XDR\":0.792912,\"XOF\":656.275872,\"XPF\":119.514963,\"YER\":270.789382,\"ZAR\":15.720451,\"ZMK\":9739.972404,\"ZMW\":18.945005,\"ZWL\":348.427953}}";

	@Autowired
	private ExchangeRatesService exchangeRatesService;

	@Test
	void contextLoads() {
	}

	@Test
	void testExchangeRates() {
		String currencyAmount = "30";
		String sourceCurrency = "USD";
		String targetCurrency = "AUD";
		BigDecimal expectedValue = new BigDecimal("40.230655785900003025390425648311065742745995521545410156250");

		assertEquals(expectedValue,exchangeRatesService.exchangeRates(currencyAmount, sourceCurrency, targetCurrency));
	}

	@Test
	void testExchangeRatesRunTimeFail() {
		String currencyAmount = "30";
		String sourceCurrency = "USD";
		String targetCurrency = "USA";

		assertThrows(RuntimeException.class, () -> exchangeRatesService.exchangeRates(currencyAmount, sourceCurrency, targetCurrency));
	}

	@Test
	void testParseTargetCurrencyRate() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonString);
		BigDecimal expectedValue = new BigDecimal(1.452051);

		BigDecimal exchangeRate = exchangeRatesService.parseTargetCurrencyRate(node,"AUD");
		assertTrue(expectedValue.compareTo(exchangeRate)==0);
	}

	@Test
	void testParseTargetCurrencyRateNumberFormatException() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonString);

		assertThrows(NumberFormatException.class, () -> exchangeRatesService.parseTargetCurrencyRate(node,"AU9D"));
	}

	@Test
	void testGetTargetCurrencyAmount() {
		BigDecimal currencyAmount = new BigDecimal("30");
		BigDecimal usd = new BigDecimal("1.088673");
		BigDecimal aud = new BigDecimal("1.461355");
		BigDecimal expectedValue = new BigDecimal("40.26979983040");

		BigDecimal returnValue = exchangeRatesService.getTargetCurrencyAmount(currencyAmount,usd,aud);

		assertEquals(expectedValue,returnValue);
	}

	@Test
	void testGetTargetCurrencyAmountWrong() {
		BigDecimal currencyAmount = new BigDecimal("30");
		BigDecimal usd = new BigDecimal("1.088673");
		BigDecimal aud = new BigDecimal("1.461355");
		BigDecimal expectedValue = new BigDecimal("36.26");

		BigDecimal returnValue = exchangeRatesService.getTargetCurrencyAmount(currencyAmount,usd,aud);

		assertNotEquals(expectedValue,returnValue);
	}

	@Test
	void testValidateCurrencyTypeTrue() {
		assertTrue(exchangeRatesService.validateCurrencyType("HRK"));
	}

	@Test
	void testValidateCurrencyTypeFalse() {
		assertFalse(exchangeRatesService.validateCurrencyType("Dollar"));
	}

	@Test
	void testValidateCurrencyAmount() {
		assertTrue(exchangeRatesService.validateCurrencyAmount("2.85"));
	}

	@Test
	void testValidateCurrencyAmountNumberFormatException() {
		assertTrue(!exchangeRatesService.validateCurrencyAmount("2.P85"));
	}

	@Test
	void testExchangeRatesService() {
		JsonNode rootNode = exchangeRatesService.getExchangeRateData();
		assertTrue(rootNode!=null);
	}

}
