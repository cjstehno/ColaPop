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

/**
 *	Interface used to define commands recognised by the POP3 server.
 *
 * @author Christopher J. Stehno (chris@stehno.com)
 */
public interface Pop3Command {

	/**
	 * Executes the command with the given server session and arguments. The command name
	 * will not be present in the argument list.
	 *
	 * @param session the active server session
	 * @param args the command arguments
	 */
	void execute(IoSession session, String[] args);
}
