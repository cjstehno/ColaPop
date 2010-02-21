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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stehno.cola.AllowedStates;
import com.stehno.cola.SessionState;
import com.stehno.cola.store.Store;

/**
 * Implements the POP3 Quit command.
 *
 * @author Christopher J. Stehno (chris@stehno.com)
 */
@AllowedStates({SessionState.Authorization,SessionState.Transaction,SessionState.Update})
class QuitPop3Command extends AbstractPop3Command {

	private static final Logger log = LoggerFactory.getLogger(QuitPop3Command.class);

	QuitPop3Command(final Store store){
		super(store);
	}

	public void execute(final IoSession session, final String[] args) {
		log.debug("quit called");
		writeOk(session, "quitting");
	}
}
