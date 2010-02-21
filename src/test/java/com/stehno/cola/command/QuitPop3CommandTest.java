package com.stehno.cola.command;

import junit.framework.Assert;

import org.apache.commons.lang.ArrayUtils;
import org.apache.mina.common.IoSession;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stehno.cola.SessionState;
import com.stehno.cola.store.Store;
import com.stehno.cola.util.Utils;

public class QuitPop3CommandTest {

	private Mockery mockery;
	private QuitPop3Command command;

	@Before
	public void before(){
		this.mockery = new JUnit4Mockery();

		final Store store = mockery.mock(Store.class);
		this.command = new QuitPop3Command(store);
	}

	@Test
	public void testExecute(){
		final IoSession session = mockery.mock(IoSession.class);
		mockery.checking(new Expectations(){
			{
				one(session).write("+OK quitting\r");
			}
		});

		command.execute(session, null);
	}

	@Test
	public void testAllowedStates(){
		final SessionState[] states = Utils.extractAllowedStates(command);
		Assert.assertTrue(ArrayUtils.contains(states, SessionState.Authorization));
		Assert.assertTrue(ArrayUtils.contains(states, SessionState.Transaction));
		Assert.assertTrue(ArrayUtils.contains(states, SessionState.Update));
	}

	@After
	public void after(){
		this.mockery.assertIsSatisfied();
		this.mockery = null;
		this.command = null;
	}
}
