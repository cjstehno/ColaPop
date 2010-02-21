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
 *
 * @author Christopher J. Stehno (chris@stehno.com)
 */
public class StoreStatus {

	private final int count, octets;

	StoreStatus(final int count, final int octets){
		this.count = count;
		this.octets = octets;
	}

	public int getCount() {return count;}

	public int getOctets() {return octets;}

	@Override
	public String toString() {
		return Integer.toString(count) + " " + octets;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(7,13).append(count).append(octets).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		boolean eq = false;
		if(obj instanceof StoreStatus){
			final StoreStatus ss = (StoreStatus)obj;
			eq = new EqualsBuilder().append(count,ss.count).append(octets,ss.octets).isEquals();
		}
		return eq;
	}
}
