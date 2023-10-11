package com.thelocalmarketplace.hardware.test;

import com.tdc.CashOverloadException;
import com.tdc.DisabledException;
import com.tdc.Sink;

public class SinkStub<T> implements Sink<T> {
		
	public void receive(T cash) throws CashOverloadException, DisabledException {
		
	}
	
	public boolean hasSpace() {
		return true;
	}

}
