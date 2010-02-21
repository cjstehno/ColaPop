package com.stehno.cola.command;

import java.util.Arrays;

import org.apache.mina.common.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stehno.cola.store.Store;

/**
 *	The USER command. The USER command is valid only during the AUTHORIZATION session state.
 *	Instances of this class should not be created directly but by the {@link Pop3CommandFactory}.
 *
 * @author Chris
 */
class UserPop3Command extends AbstractPop3Command {

	private static final Logger log = LoggerFactory.getLogger(UserPop3Command.class);

	/**
	 * Creates a user command object with the given store.
	 *
	 * @param store the store to be used
	 */
	UserPop3Command(final Store store) {
		super(store);
	}

	public void execute(final IoSession session, final String[] args) {
    	log.debug("USER: {}",Arrays.toString(args));

    	session.setAttribute("username", args[0]);
    	writeOk(session);
	}
}
