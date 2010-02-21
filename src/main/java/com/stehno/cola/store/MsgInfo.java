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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *	Information about a single message on the server.
 *
 * @author Christopher J. Stehno (chris@stehno.com)
 */
public class MsgInfo {

	private final String uid;
	private final int number, octets;
	private boolean deleted;

	MsgInfo(final String uid, final int number, final int octets){
		this.uid = uid;
		this.number = number;
		this.octets = octets;
	}

	/**
	 * Retrieves the unique message id.
	 *
	 * @return the unique message id
	 */
	public String getUid(){return uid;}

	/**
	 * Retrieves the message number.
	 *
	 * @return the message number
	 */
	public int getNumber() {return number;}

	/**
	 * Retrieves the size of the message in octets.
	 *
	 * @return size of the message in octets
	 */
	public int getOctets() {return octets;}

	/**
	 *	Mark this message as deleted.
	 */
	void delete(){
		this.deleted = true;
	}

	/**
	 * Returns true if the message has been marked deleted.
	 *
	 * @return true if the message is marked as deleted
	 */
	boolean isDeleted(){return deleted;}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(7,13).append(uid).append(number).append(octets).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		boolean eq = false;
		if(obj instanceof MsgInfo){
			final MsgInfo mi = (MsgInfo)obj;
			eq = new EqualsBuilder().append(uid,mi.uid).append(number,mi.number).append(octets,mi.octets).isEquals();
		}
		return eq;
	}
}
