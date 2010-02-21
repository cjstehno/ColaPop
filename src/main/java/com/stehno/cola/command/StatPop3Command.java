package com.stehno.cola.command;

import java.util.Arrays;

import org.apache.mina.common.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stehno.cola.store.Store;
import com.stehno.cola.store.StoreStatus;

class StatPop3Command extends AbstractPop3Command {

	private static final Logger log = LoggerFactory.getLogger(StatPop3Command.class);

	StatPop3Command(final Store store){
		super(store);
	}

	@Override
	public void execute(final IoSession session, final String[] args) {
		log.info("STAT: {}",Arrays.toString(args));

		final StoreStatus info = getStore().status();
		writeOk(session, "" + info.getCount() + " " + info.getOctets());
	}
}
