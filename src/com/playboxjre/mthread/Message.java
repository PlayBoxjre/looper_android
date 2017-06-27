/*   Copyright (C)  2017-5-9  kong xiang

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
 * */
package com.playboxjre.mthread;

import java.lang.annotation.Target;

import com.xkong.util.L;

/**
 * Message.java 2017-5-9
 * 
 * @author {KONG_XIANG}
 * 
 */
public class Message {

	public Bundle data;
	public int arg1;
	public int arg2;
	public Object obj;
	public int what;
	public long when;
	public Runnable callback;
	public Handler target;
	boolean inUse;

	/**
	 * @param handler
	 */
	public Message(Handler handler) {
		target = handler;
	}

	public Message(int what, int arg1, int arg2) {
		this.what = what;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	public Message(int what, Object object, Handler target) {
		this.what = what;
		this.obj = object;
		this.target = target;
	}

	/**
	 * @param what2
	 * @param arg12
	 * @param arg22
	 * @param target2
	 */
	public Message(int what2, int arg12, int arg22, Handler target2) {
		this(what2, arg12, arg22);
		this.target = target2;
	}

	// Message next;

	/**
	 * @return the data
	 */
	public Bundle getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Bundle data) {
		this.data = data;
	}

	void makeInUse() {
		synchronized (this) {
			inUse = true;
		}
	}

	void recycle() {
		synchronized (this) {
			inUse = false;
			recycleUnchecked();
		}
	}

	void recycleUnchecked() {
		// Mark the message as in use while it remains in the recycled object
		// pool.
		// Clear out all other details.
		// flags = FLAG_IN_USE;
		what = 0;
		arg1 = 0;
		arg2 = 0;
		obj = null;
		// replyTo = null;
		// sendingUid = -1;
		when = 0;
		target = null;
		callback = null;
		data = null;

		/*
		 * synchronized (sPoolSync) { if (sPoolSize < MAX_POOL_SIZE) { next =
		 * sPool; sPool = this; sPoolSize++; } }
		 */
	}

	/**
	 * @param handler
	 * @return
	 */
	public static Message obtainMessage(Handler handler) {
		return new Message(handler);
	}

	public static Message obtainMessage(int what, int arg1, int arg2, Handler target) {
		return new Message(what, arg1, arg2, target);
	}

	public static Message obtainMessage(int what, Object obj, Handler target) {
		return new Message(what, obj, target);

	}

	public void sendToTarget() throws InterruptedException {
		if (target != null)
			target.sendMessage(this);

	}

}
