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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pop3Server {

	private static final Logger log = LoggerFactory.getLogger(Pop3Server.class);
	private final IoAcceptor acceptor;

	public Pop3Server(){
		this.acceptor = new NioSocketAcceptor();
	}

	public void start() throws IOException{
		acceptor.getFilterChain().addLast("logger", new LoggingFilter() );
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.defaultCharset())));

		acceptor.setHandler(new Pop3ServerHandler());

		final int port = 110;
        acceptor.setDefaultLocalAddress(new InetSocketAddress(port));
        acceptor.bind();

        log.info("Started on {}",port);
	}

	public void stop(){
		acceptor.unbind();

		log.info("Stopped");
	}
}
