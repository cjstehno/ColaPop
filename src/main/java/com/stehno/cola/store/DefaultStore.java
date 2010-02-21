package com.stehno.cola.store;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DefaultStore implements Store {

	private Map<String, String> users;
	private List<MsgInfo> msgInfos;
	private List<MsgContent> msgContents;

	public DefaultStore(){

		// dummy content
		users = new HashMap<String, String>();
		users.put("foo", "bar");

		msgInfos = new ArrayList<MsgInfo>();
		msgInfos.add(new MsgInfo(UUID.randomUUID().toString(),0,100));
		msgInfos.add(new MsgInfo(UUID.randomUUID().toString(),1,125));

		msgContents = new ArrayList<MsgContent>();

		final String msg1 = "This is some content for an email # 0";
		msgContents.add(new MsgContent(new StringReader(msg1),msg1.getBytes().length));

		final String msg2 = "This is some content for an email # 1";
		msgContents.add(new MsgContent(new StringReader(msg2),msg2.getBytes().length));
	}

	@Override
	public boolean authenticate(final String username, final String password) {
		final String upw = users.get(username);
		return password.equals(upw);
	}

	@Override
	public MsgInfo list(final int messageNum) {
		throw new UnsupportedOperationException("list(num) not supported yetd");
	}

	@Override
	public MsgInfoList list() {
		final MsgInfoList list = new MsgInfoList();
		for(final MsgInfo mi : msgInfos){
			list.add(mi);
		}
		return list;
	}

	@Override
	public MsgContent retrieve(final int messageNum) {
		return msgContents.get(messageNum);
	}

	@Override
	public StoreStatus status() {
		int octets = 0;
		for(final MsgInfo mi : msgInfos){
			octets += mi.getOctets();
		}
		return new StoreStatus(msgInfos.size(),octets);
	}

	@Override
	public boolean delete(final int messageNum) {
		throw new UnsupportedOperationException("delete not supported yet");
	}
}
