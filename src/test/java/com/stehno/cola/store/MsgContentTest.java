package com.stehno.cola.store;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MsgContentTest {

	private static final String RAW_CONTENT = "This would be raw email content";
	private StringReader reader = new StringReader(RAW_CONTENT);
	private MsgContent content;

	@Before
	public void before(){
		this.content = new MsgContent(reader,RAW_CONTENT.getBytes().length);
	}

	@Test
	public void testIt() throws IOException{
		Assert.assertEquals(RAW_CONTENT.getBytes().length, content.getOctets());

		final StringBuilder str = new StringBuilder();
		final Reader r = content.getContent();
		for(int x = r.read(); x != -1; x = r.read()){
			str.append((char)x);
		}
		Assert.assertEquals(RAW_CONTENT, str.toString());
	}

	@Test
	public void testEqualsAndHashCode(){
		Assert.assertEquals(content, content);
		Assert.assertEquals(content.hashCode(), content.hashCode());
		Assert.assertFalse(content.equals(null));

		final MsgContent cont = new MsgContent(new StringReader("foo"),2);
		Assert.assertFalse(content.equals(cont));
		Assert.assertFalse(content.hashCode() == cont.hashCode());
	}

	@After
	public void after(){
		this.content = null;
	}
}
