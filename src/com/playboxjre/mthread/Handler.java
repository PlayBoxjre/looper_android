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

/**
 * Hander.java 2017-5-9
 * 
 * @author {KONG_XIANG}
 * 
 */
public class Handler {
	private Callback callback;
	private Looper mLooper;
	private MessageQueue mQueue;
	private boolean asyn;

	public interface Callback {
		public boolean handleMessage(Message msg);
	}

	public Handler(Looper looper) {
		this(looper, null, false);
	}

	public Handler(Looper looper, Callback callback, boolean asyn) {
		this.mLooper = looper;
		this.mQueue = looper.mQueue;
		this.callback = callback;
		this.asyn = asyn;
	}

	public Handler(Callback callback) {
		this(null, callback, false);
	}

	/**
	 * 
	 */
	public Handler() {
		this.mLooper = Looper.myLooper();
		this.mQueue = mLooper.mQueue;
	}

	public Message obtainMessage() {
		return Message.obtainMessage(this);
	}

	public Message obtainMessage(int what, int arg1, int arg2) {
		return Message.obtainMessage(what, arg1, arg2, this);
	}

	public Message obtainMessage(int what, Object obj) {
		return Message.obtainMessage(what, obj, this);
	}

	/**
	 * null implements
	 * 
	 * @param msg
	 */
	public void handleMessage(Message msg) {
	}

	/**
	 * @param msg
	 */
	public void dispatchMessage(Message msg) {
		if (msg.callback != null) {
			handleCallback(msg);
		} else {
			if (callback != null && callback.handleMessage(msg)) {
				return;
			}
			handleMessage(msg);
		}
	}

	/**
	 * @param msg
	 */
	private void handleCallback(Message msg) {
		msg.callback.run();
	}

	/**
	 * @param message
	 * @throws InterruptedException 
	 */
	public void sendMessage(Message message) throws InterruptedException  {
		mQueue.enqueueMessage(message);
	}

}
