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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *	A List of message information objects.
 *
 * @author Christopher J. Stehno (chris@stehno.com)
 */
public class MsgInfoList implements Iterable<MsgInfo> {

	private final List<MsgInfo> messageInfos;
	private int octets;

	MsgInfoList(){
		messageInfos = new LinkedList<MsgInfo>();
	}

	/**
	 * Adds the given message to the list.
	 *
	 * @param mi the message info to be added
	 */
	void add(final MsgInfo mi){
		if(messageInfos.add(mi)){
			octets += mi.getOctets();
		}
	}

	/**
	 * Retrieves the number of messages in the list.
	 *
	 * @return the number of messages in the list
	 */
	public int size(){return messageInfos.size();}

	/**
	 * Retrieves the size (number of octets) of the whole list.
	 *
	 * @return the number of octets in the whole list
	 */
	public int getOctets(){return octets;}

	/**
	 * Iterator over the list of message information object.
	 */
	@Override
	public Iterator<MsgInfo> iterator() {
		return messageInfos.iterator();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(7,13).append(messageInfos).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		boolean eq = false;
		if(obj instanceof MsgInfoList){
			eq = new EqualsBuilder().append(messageInfos, ((MsgInfoList)obj).messageInfos).isEquals();
		}
		return eq;
	}
}
