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

import ca.ucalgary.seng300.simulation.InvalidArgumentSimulationException;
import ca.ucalgary.seng300.simulation.NullPointerSimulationException;
import powerutility.PowerGrid;

public class AbstractCVTest {
	private Currency currency = Currency.getInstance("CAD");
	private List<BigDecimal> coinDenominations = new ArrayList<>(Arrays.asList(new BigDecimal(0.05)));
	private CoinValidator coinValidator = new CoinValidator(currency,coinDenominations );
	private Map<BigDecimal, Sink<Coin>> standardSinks  = new HashMap<>();
	private Sink<Coin> rejectionSink = new SinkStub<Coin>();
	private Sink<Coin> overflowSink = new SinkStub<Coin>();
	@Before
	public void setUp() {
//		Sink<Coin> rejectionSink = new SinkStub<Coin>();
//		Sink<Coin> overflowSink = new SinkStub<Coin>();
//		Map<BigDecimal, Sink<Coin>> standardSinks = new HashMap<>();
        coinValidator.connect(PowerGrid.instance());
        coinValidator.activate();
        standardSinks.put(new BigDecimal("0.05"), new SinkStub<Coin>());
        coinValidator.setup(rejectionSink,standardSinks, overflowSink);   
            }
	
	 @Test(expected = NullPointerSimulationException.class)
	 public void testSetupWithNullRejectionSink() {
        CoinValidator coinValidator1 = new CoinValidator (currency,coinDenominations); 
        coinValidator1.setup(null, standardSinks, overflowSink);
	 }
	 
	 @Test(expected = NullPointerSimulationException.class)
	 public void testSetupWithNullOverflowSink() {
	        CoinValidator coinValidator1 = new CoinValidator (currency,coinDenominations); 
	        coinValidator1.setup(rejectionSink, standardSinks, null);
		 }
	 @Test(expected = NullPointerSimulationException.class)
	 public void testSetupWithNullStandardSinks() {
	        CoinValidator coinValidator1 = new CoinValidator (currency,coinDenominations); 
	        coinValidator1.setup(rejectionSink, null, overflowSink);
		 }
	 
	 @Test(expected = InvalidArgumentSimulationException.class)
		 public void testequalstdSinksandDenom() {
		 	standardSinks.put(new BigDecimal("0.10"), new SinkStub<Coin>());
		 	standardSinks.put(new BigDecimal("0.15"), new SinkStub<Coin>());
	        coinValidator.setup(rejectionSink,standardSinks, overflowSink); 
	 }
	 @Test(expected = NullPointerSimulationException.class)
	 public void testNullSinkDenominations() {
		 SinkStub<Coin> nullStub = null;
         standardSinks.put(new BigDecimal(0.25), nullStub);
         coinDenominations.add(new BigDecimal(0.25));
         coinValidator.setup(rejectionSink,standardSinks, overflowSink); 
	 }
	public void testUniqueSink() {
		SinkStub<Coin> sink1 = new SinkStub<Coin>();
	     	standardSinks.put(new BigDecimal("0.05"), sink1);
	        standardSinks.put(new BigDecimal("0.25"), sink1);
	        coinValidator.setup(rejectionSink,standardSinks, overflowSink);
	 }
	
	@Test(expected = InvalidArgumentSimulationException.class)
	 public void testsetcontainRejectionSink() {
	     standardSinks.put(new BigDecimal("0.05"), rejectionSink);
         coinValidator.setup(rejectionSink,standardSinks, overflowSink);
	 }
	 
	 @Test(expected = InvalidArgumentSimulationException.class)
	 public void testsetcontainOverflowSink() {
	     standardSinks.put(new BigDecimal("0.05"), overflowSink);
         coinValidator.setup(rejectionSink,standardSinks, overflowSink);
	 }
	
}
