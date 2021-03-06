package com.stehno.cola.command;

import java.util.Arrays;

import org.apache.mina.common.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stehno.cola.store.Store;

class DelePop3Command extends AbstractPop3Command {

	private static final Logger log = LoggerFactory.getLogger(DelePop3Command.class);

	DelePop3Command(final Store store) {
		super(store);
	}

	@Override
	public void execute(final IoSession session, final String[] args) {
		log.info("DELE: {}",Arrays.toString(args));

	}
}
