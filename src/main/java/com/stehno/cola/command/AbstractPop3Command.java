/*
	Copyright 2008 Christopher J. Stehno

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package com.stehno.cola.command;

import org.apache.mina.common.IoSession;

import com.stehno.cola.store.Store;

/**
 * Basic abstraction for a POP3 command.
 *
 * @author Christopher J. Stehno (chris@stehno.com)
 */
abstract class AbstractPop3Command implements Pop3Command {

	private static final String OK_SPACE = "+OK ";
	private static final String ERR_SPACE = "-ERR ";
	private static final String RETURN = "\r";
	private static final String END = ".\r";
	private static final String ERR_RETURN = "-ERR\r";
	private static final String OK_RETURN = "+OK\r";
	private final Store store;

	AbstractPop3Command(final Store store){
		this.store = store;
	}

	/**
	 * Retrieves the configured Store object.
	 *
	 * @return the configured Store object
	 */
	protected final Store getStore(){return store;}

	/**
	 * Writes an OK response to the session.
	 *
	 * @param session
	 */
	protected final void writeOk(final IoSession session) {
		session.write(OK_RETURN);
	}

	/**
	 * Writes an error response to the session.
	 *
	 * @param session
	 */
	protected final void writeError(final IoSession session){
		session.write(ERR_RETURN);
	}

	/**
	 * Writes an error response to the session with the specified message.
	 *
	 * @param session
	 * @param msg the error message to be written
	 */
	protected final void writeError(final IoSession session, final String msg) {
		session.write(ERR_SPACE.concat(msg).concat(RETURN));
	}

	/**
	 * Writes an OK message to the session with the specified message text.
	 *
	 * @param session
	 * @param msg the message to be written
	 */
	protected final void writeOk(final IoSession session, final String msg) {
		session.write(OK_SPACE.concat(msg).concat(RETURN));
	}

	/**
	 * Writes the response END token.
	 *
	 * @param session
	 */
	protected final void writeEnd(final IoSession session){
		session.write(END);
	}
}
