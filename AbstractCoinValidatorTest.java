package com.thelocalmarketplace.hardware.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.tdc.Sink;
import com.tdc.coin.Coin;
import com.tdc.coin.CoinValidator;

import ca.ucalgary.seng300.simulation.InvalidArgumentSimulationException;
import ca.ucalgary.seng300.simulation.NullPointerSimulationException;
import powerutility.PowerGrid;

public class AbstractCoinValidatorTest {
	private Currency currency;
	private List<BigDecimal> coinDenominations; 
	private CoinValidator coinValidator; 
	private Map<BigDecimal, Sink<Coin>> standardSinks;
	private Sink<Coin> rejectionSink, overflowSink ;
	
	@Before
	public void setUp() {
		currency = Currency.getInstance("CAD");
		coinDenominations = Arrays.asList(new BigDecimal(0.05));
		coinValidator = new CoinValidator(currency, coinDenominations);
		rejectionSink = new SinkStub<Coin>();
		overflowSink = new SinkStub<Coin>();
		standardSinks = new HashMap<>();
        coinValidator.connect(PowerGrid.instance());
        coinValidator.activate();
        standardSinks.put(new BigDecimal("0.05"), new SinkStub<Coin>());
        coinValidator.setup(rejectionSink,standardSinks, overflowSink);   
     }
	
	 @Test(expected = NullPointerSimulationException.class)
	 public void testSetupWithNullRejectionSink() {
        CoinValidator coinValidator = new CoinValidator (currency,coinDenominations);   
        coinValidator.setup(null, standardSinks, overflowSink);
	 }
	 
	 @Test(expected = NullPointerSimulationException.class)
	 public void testSetupWithNullOverflowSink() {
	        CoinValidator coinValidator = new CoinValidator (currency,coinDenominations);   
	        coinValidator.setup(rejectionSink, standardSinks, null);
		 }
	 @Test(expected = NullPointerSimulationException.class)
	 public void testSetupWithNullStandardSinks() {
	        CoinValidator coinValidator = new CoinValidator (currency,coinDenominations);    
	        coinValidator.setup(rejectionSink, null, overflowSink);
		 }	
	 
	 @Test(expected = InvalidArgumentSimulationException.class)
	 public void testEqualStdSinksAndDenom() {
	 	standardSinks.put(new BigDecimal(0.10), new SinkStub<Coin>());
	 	standardSinks.put(new BigDecimal(0.05), new SinkStub<Coin>());
        coinValidator.setup(rejectionSink, standardSinks, overflowSink);  
	 }
	 
	 @Test(expected = NullPointerSimulationException.class)
	 public void testNullSinkDenom() {
		 SinkStub<Coin> nullStub = null;
		 standardSinks.put(new BigDecimal(0.05), new SinkStub<Coin>());
		 standardSinks.put(new BigDecimal(0.10), nullStub); 
	 }
	  
}  
