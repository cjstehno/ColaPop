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

import java.util.HashMap;
import java.util.Map;

import com.stehno.cola.store.Store;

public class Pop3CommandFactory {

	private static enum CommandName { QUIT, USER, PASS, LIST, RETR, AUTH, CAPA, STAT, UIDL, DELE};

	private final Map<CommandName, Pop3Command> commandFactories;

	public Pop3CommandFactory(final Store store){
		this.commandFactories = new HashMap<CommandName, Pop3Command>();

		commandFactories.put(CommandName.QUIT, new QuitPop3Command(store));
		commandFactories.put(CommandName.LIST, new ListPop3Command(store));
		commandFactories.put(CommandName.USER, new UserPop3Command(store));
		commandFactories.put(CommandName.PASS, new PassPop3Command(store));
		commandFactories.put(CommandName.RETR, new RetrPop3Command(store));
		commandFactories.put(CommandName.AUTH, new AuthPop3Command(store));
		commandFactories.put(CommandName.CAPA, new CapaPop3Command(store));
		commandFactories.put(CommandName.STAT, new StatPop3Command(store));
		commandFactories.put(CommandName.UIDL, new UidlPop3Command(store));
		commandFactories.put(CommandName.DELE, new DelePop3Command(store));
	}

	public Pop3Command findCommand(final String name) throws Exception {
		return commandFactories.get(CommandName.valueOf(name.toUpperCase()));
	}
}
