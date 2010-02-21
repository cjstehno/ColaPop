package com.stehno.cola.command;

import org.apache.mina.common.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stehno.cola.store.Store;

class PassPop3Command extends AbstractPop3Command {

	private static final Logger log = LoggerFactory.getLogger(PassPop3Command.class);

	PassPop3Command(final Store store) {
		super(store);
	}

	public void execute(final IoSession session, final String[] args) {
    	log.debug("pass called");

    	final String uname = (String)session.getAttribute("username");
    	if(getStore().authenticate(uname, args[0])){
    		writeOk(session);
    	} else {
    		session.write("-ERR\r\n");
    	}
	}
}
