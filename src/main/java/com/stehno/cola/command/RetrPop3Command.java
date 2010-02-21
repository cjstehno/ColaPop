package com.stehno.cola.command;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.mina.common.IoSession;

import com.stehno.cola.store.MsgContent;
import com.stehno.cola.store.Store;

class RetrPop3Command extends AbstractPop3Command {

	RetrPop3Command(final Store store) {
		super(store);
	}

	public void execute(final IoSession session, final String[] args) {

		final MsgContent content = getStore().retrieve(Integer.parseInt(args[0]));

		writeOk(session, Integer.toString(content.getOctets()) + " octets");

		final BufferedReader reader = new BufferedReader(content.getContent());
		try {
			for(String line = reader.readLine(); line != null; line = reader.readLine()){
				session.write(line + "\r");
			}
		} catch(final IOException ioe){
			ioe.printStackTrace();
		} finally {
			try {reader.close();} catch(final Exception e){}
		}

		writeEnd(session);
	}
}
