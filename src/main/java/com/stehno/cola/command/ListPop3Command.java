package com.stehno.cola.command;

import java.util.Arrays;

import org.apache.mina.common.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stehno.cola.store.MsgInfo;
import com.stehno.cola.store.MsgInfoList;
import com.stehno.cola.store.Store;

class ListPop3Command extends AbstractPop3Command {

	private static final Logger log = LoggerFactory.getLogger(ListPop3Command.class);

	ListPop3Command(final Store store) {
		super(store);
	}

	public void execute(final IoSession session, final String[] args) {
    	log.debug("LIST: {}",Arrays.toString(args));

    	if(args.length == 0){
        	final MsgInfoList infoList = getStore().list();
        	writeOk(session, "" + infoList.size() + " messages (" + infoList.getOctets() + " octets)");
        	for(final MsgInfo mi : infoList){
        		session.write("" + mi.getNumber() + " " + mi.getOctets() + "\r");
        	}
        	writeEnd(session);
    	} else {
        	final MsgInfo info = getStore().list(Integer.parseInt(args[0]));
        	writeOk(session, "" + info.getNumber() + " " + info.getOctets());
    	}
	}
}
