package com.stehno.cola.store;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MsgInfoTest {

	private static final String uid = "foomsg";
	private static final int num = 13;
	private static final int octets = 32;
	private MsgInfo msgInfo;

	@Before
	public void before(){
		this.msgInfo = new MsgInfo(uid,num,octets);
	}

	@Test
	public void testIt(){
		Assert.assertEquals("Uid does not match",uid, msgInfo.getUid());
		Assert.assertEquals("Number does not match",num, msgInfo.getNumber());
		Assert.assertEquals("Octets do not match",octets, msgInfo.getOctets());
		Assert.assertFalse("Should not be deleted",msgInfo.isDeleted());
	}

	@Test
	public void testDelete(){
		Assert.assertFalse("Should not be deleted",msgInfo.isDeleted());

		msgInfo.delete();

		Assert.assertTrue("Should be deleted",msgInfo.isDeleted());
	}

	@Test
	public void testEqualsAndHash(){
		Assert.assertEquals(msgInfo, msgInfo);
		Assert.assertEquals(msgInfo.hashCode(), msgInfo.hashCode());
		Assert.assertFalse(msgInfo.equals(null));

		final MsgInfo info = new MsgInfo("somefoo",7,32);

		Assert.assertFalse(msgInfo.equals(info));
	}

	@After
	public void after(){
		this.msgInfo = null;
	}
}
