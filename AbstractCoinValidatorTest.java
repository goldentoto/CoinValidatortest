package com.thelocalmarketplace.hardware.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tdc.coin.CoinValidator;

public class AbstractCoinValidatorTest {
	private CoinValidator coinValidator;
	
	@Before
	public void setup() {
	List<BigDecimal> coinDenominations = Arrays.asList(new BigDecimal(0.05), new BigDecimal(0.1), new BigDecimal(0.25),
								new BigDecimal(1.0), new BigDecimal(2.0));
	Currency currency = Currency.getInstance("CAD");
	}
	
	@Test
	public void testNullRejectSink() {
		
	}
}
