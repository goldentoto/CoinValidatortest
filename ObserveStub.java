
package com.thelocalmarketplace.hardware.test;
import java.math.BigDecimal;

/**
 * 
 */
import com.tdc.IComponent;
import com.tdc.IComponentObserver;
import com.tdc.coin.*;

class ObserveStub implements CoinValidatorObserver {
	public boolean validCoin = false;

	@Override
	public void enabled(IComponent<? extends IComponentObserver> component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(IComponent<? extends IComponentObserver> component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnedOn(IComponent<? extends IComponentObserver> component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnedOff(IComponent<? extends IComponentObserver> component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validCoinDetected(AbstractCoinValidator validator, BigDecimal value) {
		validCoin = true;
	}

	@Override
	public void invalidCoinDetected(AbstractCoinValidator validator) {
		validCoin = false;
		
	}

}
