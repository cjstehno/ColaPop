package com.stehno.cola.command;

import junit.framework.Assert;

import org.apache.mina.common.IoSession;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stehno.cola.store.Store;

public class AbstractPop3CommandTest {

	private AbstractPop3Command command;
	private Store store;
	private Mockery mockery;

	@Before
	public void before(){
		this.mockery = new JUnit4Mockery();

		this.store = mockery.mock(Store.class);
		this.command = new AbstractPop3Command(store){
			@Override
			public void execute(final IoSession session, final String[] args) {}
		};
	}

	@Test
	public void testGetStore(){
		Assert.assertEquals(store, command.getStore());
	}

	@Test
	public void testWriteOk(){
		final IoSession session = mockery.mock(IoSession.class);
		mockery.checking(new Expectations(){
			{
				one(session).write("+OK\r");
				one(session).write("+OK Some message\r");
			}
		});

		command.writeOk(session);
		command.writeOk(session, "Some message");
	}

	@Test
	public void testWriteError(){
		final IoSession session = mockery.mock(IoSession.class);
		mockery.checking(new Expectations(){
			{
				one(session).write("-ERR\r");
				one(session).write("-ERR Some error\r");
			}
		});

		command.writeError(session);
		command.writeError(session, "Some error");
	}

	@Test
	public void testEnd(){
		final IoSession session = mockery.mock(IoSession.class);
		mockery.checking(new Expectations(){
			{
				one(session).write(".\r");
			}
		});

		command.writeEnd(session);
	}

	@After
	public void after(){
		this.mockery.assertIsSatisfied();
		this.command = null;
	}
}
