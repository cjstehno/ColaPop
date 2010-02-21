package com.stehno.cola.store;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MsgInfoListTest {

	private static final MsgInfo INFO_0 = new MsgInfo("foo0",0,16);
	private static final MsgInfo INFO_1 = new MsgInfo("foo1",1,32);
	private MsgInfoList list;

	@Before
	public void before(){
		this.list = new MsgInfoList();
	}

	@Test
	public void testIt(){
		Assert.assertEquals("List is not empty",0, list.size());
		Assert.assertEquals("List has octets",0, list.getOctets());

		for(final MsgInfo mi : list){
			Assert.fail("List has message information");
		}

		list.add(INFO_0);
		list.add(INFO_1);

		Assert.assertEquals(2, list.size());
		Assert.assertEquals(48, list.getOctets());

		for(final MsgInfo mi : list){
			Assert.assertTrue(mi.equals(INFO_0) || mi.equals(INFO_1));
		}
	}

	@Test
	public void testEqualsAndHashCode(){
		Assert.assertEquals(list, list);
		Assert.assertEquals(list.hashCode(), list.hashCode());

		list.add(INFO_0);
		list.add(INFO_1);

		Assert.assertEquals(list, list);
		Assert.assertEquals(list.hashCode(), list.hashCode());

		final MsgInfoList otherList = new MsgInfoList();
		otherList.add(INFO_0);

		Assert.assertFalse(list.equals(otherList));
		Assert.assertFalse(list.hashCode() == otherList.hashCode());

		Assert.assertFalse(list.equals(null));
	}

	@After
	public void after(){
		this.list = null;
	}
}
