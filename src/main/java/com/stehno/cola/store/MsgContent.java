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
package com.stehno.cola.store;

import java.io.Reader;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Stores the content of a message for transport.
 *
 * @author Christopher J. Stehno (chris@stehno.com)
 */
public class MsgContent {

	private final int octets;
	private final Reader content;

	MsgContent(final Reader content, final int octets){
		this.content = content;
		this.octets = octets;
	}

	/**
	 * Retrieves the number of octets in the message.
	 *
	 * @return the number of octets in the message
	 */
	public int getOctets(){return octets;}

	/**
	 * Retrieves a Reader containing the raw content of the message.
	 *
	 * @return
	 */
	public Reader getContent(){return content;}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(7,13).append(octets).append(content).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		boolean eq = false;
		if(obj instanceof MsgContent){
			final MsgContent mc = (MsgContent)obj;
			eq = new EqualsBuilder().append(octets, mc.octets).append(content,mc.content).isEquals();
		}
		return eq;
	}
}
