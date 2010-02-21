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
package com.stehno.cola;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stehno.cola.command.Pop3Command;
import com.stehno.cola.command.Pop3CommandFactory;
import com.stehno.cola.store.DefaultStore;

public class Pop3ServerHandler extends IoHandlerAdapter {

	private static final Logger log = LoggerFactory.getLogger(Pop3ServerHandler.class);
	private final Pop3CommandFactory commandFactory;

	public Pop3ServerHandler(){
		this.commandFactory = new Pop3CommandFactory(new DefaultStore());
	}

    @Override
    public void exceptionCaught( final IoSession session, final Throwable cause ) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived( final IoSession session, final Object message ) throws Exception {
    	final String[] line = StringUtils.split(message.toString(), ' ');
    	final Pop3Command command = commandFactory.findCommand(line[0]);
    	if(command != null){
    		command.execute(session, (String[])ArrayUtils.subarray(line, 1, line.length));
    	} else {
    		session.write("-ERR\r");
    		log.info("Nowhere: {}",line);
    	}
    }

    @Override
    public void messageSent(final IoSession session, final Object message) throws Exception {
    	if(message.toString().contains("quitting")){
    		session.closeOnFlush();
    	}
    	log.debug("Message sent: {}",message);
    }

    @Override
    public void sessionOpened(final IoSession session) throws Exception {
    	session.setAttribute("session.state",SessionState.Authorization);
    	session.write("+OK\r");

    	log.debug("Session opened");
    }

    @Override
    public void sessionClosed(final IoSession session) throws Exception {
    	log.debug("Session closed");
    }

    @Override
    public void sessionIdle( final IoSession session, final IdleStatus status ) throws Exception {
        log.debug("Session idle");
    }
}