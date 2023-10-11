package com.thelocalmarketplace.hardware.test;

import com.tdc.CashOverloadException;
import com.tdc.DisabledException;
import com.tdc.Sink;

public class SinkStub<T> implements Sink<T> {
		
	public void receive(T cash) throws CashOverloadException, DisabledException {
		private T lastReceivedCash;
		
		public void receive(T cash) throws CashOverloadException, DisabledException {
			if (!hasSpace()) {
	            throw new CashOverloadException("The sink is overloaded.");}
	        lastReceivedCash = cash;

	}
	}
	
	public boolean hasSpace() {
		return true;
	}

}
