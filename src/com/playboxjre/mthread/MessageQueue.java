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

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * MessageQueue.java 2017-5-9
 * 
 * @author {KONG_XIANG}
 * 
 */
public class MessageQueue {
	/**
	 * @param allowQuit
	 */

	
	private int capacity = 100;

	public MessageQueue(boolean allowQuit) {
	
	}

	/**
	 * 
	 */
	public MessageQueue() {
	}

	private BlockingQueue<Message> mQueue = new LinkedBlockingQueue();

	public Message next() {
		//lock.lock();
		try {
			return mQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return mQueue.poll();
		} finally {
		//	lock.unlock();
		}

	}

	public void offer(Message msg) {
		mQueue.offer(msg);

	}

	/**
	 * @param message
	 * @throws InterruptedException
	 */
	public void put(Message message) throws InterruptedException {
	//	lock.lock();
		try {
			mQueue.put(message);
		}  finally {
		//	lock.unlock();
		}
		// (message);

	}

	/**
	 * @param message
	 * @throws InterruptedException
	 */
	public void enqueueMessage(Message message) throws InterruptedException {
		put(message);
	}
}
