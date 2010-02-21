package com.stehno.cola.store;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StoreStatusTest {

	private static final int COUNT = 21;
	private static final int OCTETS = 128;
	private StoreStatus status;

	@Before
	public void before(){
		this.status = new StoreStatus(COUNT,OCTETS);
	}

	@Test
	public void testIt(){
		Assert.assertEquals(COUNT, status.getCount());
		Assert.assertEquals(OCTETS, status.getOctets());
	}

	@Test
	public void testEqualsAndHashCode(){
		Assert.assertEquals(status, status);
		Assert.assertEquals(status.hashCode(), status.hashCode());
		Assert.assertFalse(status.equals(null));

		final StoreStatus stat = new StoreStatus(10,100);
		Assert.assertFalse(status.equals(stat));
		Assert.assertFalse(status.hashCode() == stat.hashCode());
	}

	@Test
	public void testToString(){
		Assert.assertEquals(COUNT + " " + OCTETS, status.toString());
	}

	@After
	public void after(){
		this.status = null;
	}
}
