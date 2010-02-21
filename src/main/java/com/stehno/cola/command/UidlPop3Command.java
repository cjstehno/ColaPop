package com.stehno.cola.command;

import java.util.Arrays;

import org.apache.mina.common.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stehno.cola.store.MsgInfo;
import com.stehno.cola.store.MsgInfoList;
import com.stehno.cola.store.Store;

class UidlPop3Command extends AbstractPop3Command {

	private static final Logger log = LoggerFactory.getLogger(UidlPop3Command.class);

	UidlPop3Command(final Store store) {
		super(store);
	}

	@Override
	public void execute(final IoSession session, final String[] args) {
    	log.debug("UIDL: {}",Arrays.toString(args));

    	if(args.length == 0){
        	final MsgInfoList infoList = getStore().list();

        	writeOk(session, "unique-id listing follows");
        	for(final MsgInfo mi : infoList){
        		session.write("" + mi.getNumber() + " " + mi.getUid() + "\r");
        	}
        	writeEnd(session);
    	} else {
        	final MsgInfo info = getStore().list(Integer.parseInt(args[0]));
        	writeOk(session, "" + info.getNumber() + " " + info.getUid());
    	}
	}
}
