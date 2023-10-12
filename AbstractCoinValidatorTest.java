package com.thelocalmarketplace.hardware.test;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import ca.ucalgary.seng300.simulation.NullPointerSimulationException;
import powerutility.PowerGrid;

public class AbstractCVTest {
	private Currency currency = Currency.getInstance("CAD");
	private List<BigDecimal> coinDenominations = Arrays.asList(new BigDecimal(0.05));
	private CoinValidator coinValidator = new CoinValidator(currency,coinDenominations );
	private Map<BigDecimal, Sink<Coin>> standardSinks;
	private Sink<Coin> rejectionSink,overflowSink ;
	
	@Before
	public void setUp() {
		Sink<Coin> rejectionSink = new SinkStub<Coin>();
		Sink<Coin> overflowSink = new SinkStub<Coin>();
		Map<BigDecimal, Sink<Coin>> standardSinks = new HashMap<>();
        coinValidator.connect(PowerGrid.instance());
        coinValidator.activate();
        standardSinks.put(new BigDecimal("0.05"), new SinkStub<Coin>());
        coinValidator.setup(rejectionSink,standardSinks, overflowSink);   
            }
	
	 @Test(expected = NullPointerSimulationException.class)
	 public void testSetupWithNullRejectionSink() {
        CoinValidator coinValidator1 = new CoinValidator (currency,coinDenominations); // Create a concrete implementation    
        coinValidator1.setup(null, standardSinks, overflowSink);
	 }
	 
	 @Test(expected = NullPointerSimulationException.class)
	 public void testSetupWithNullOverflowSink() {
	        CoinValidator coinValidator1 = new CoinValidator (currency,coinDenominations); // Create a concrete implementation    
	        coinValidator1.setup(rejectionSink, standardSinks, null);
		 }
	 @Test(expected = NullPointerSimulationException.class)
	 public void testSetupWithNullStandardSinks() {
	        CoinValidator coinValidator1 = new CoinValidator (currency,coinDenominations); // Create a concrete implementation    
	        coinValidator1.setup(rejectionSink, null, overflowSink);
		 }	
}
