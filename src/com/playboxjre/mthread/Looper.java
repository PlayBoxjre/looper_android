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
 * Looper.java 2017-5-9
 * 
 * @author {KONG_XIANG}
 * 
 */
public class Looper {

	/*
	 * public static void main(String[] args) { try {
	 * 
	 * Looper.perpareMainLooper();
	 * 
	 * final Handler handler = new Handler() {
	 * 
	 * @param msg
	 * 
	 * @see com.playboxjre.mthread.Handler#handleMessage(com.playboxjre
	 * 
	 * .mthread.Message)
	 * 
	 * @Override public void handleMessage(Message msg) { L.printf(
	 * "Thread name [%5s]  :\n  what : %s  - message : %s \n",
	 * Thread.currentThread().getName(), msg.what + "", (String) msg.obj); } };
	 * 
	 * Thread t = new Thread(new Runnable() {
	 * 
	 * @Override public void run() { // TODO Auto-generated method stub
	 * BufferedReader br = new BufferedReader( new
	 * InputStreamReader(System.in)); while (true) { try {
	 * 
	 * String readLine = br.readLine(); L.printf(
	 * "Thread name [%s]  : System in String [%s] \n",
	 * Thread.currentThread().getName(), readLine); handler.obtainMessage(1,
	 * readLine).sendToTarget();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } } } });
	 * t.setName(" run thread :"); t.start();
	 * 
	 * Looper.loop(); throw new
	 * RuntimeException("main thread loop unexpectedly exited"); } catch
	 * (LooperHasExistException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

	// sThreadLocal.get() will return null unless you've called prepare().
	static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
	static Looper mMianLooper;
	final MessageQueue mQueue;
	final Thread mThread;

	public static void perpare() throws LooperHasExistException {
		perpare(true);
	}

	private static void perpare(boolean allowQuit) throws LooperHasExistException {
		if (sThreadLocal.get() != null)
			throw new LooperHasExistException();
		sThreadLocal.set(new Looper(allowQuit));
	}

	public static Looper myLooper() {
		return sThreadLocal.get();
	}

	public static void perpareMainLooper() throws LooperHasExistException {
		perpare(false);
		synchronized (Looper.class) {

			if (mMianLooper != null)
			//	L.printf("main Looper is %s \n", "has exist . ");
			throw new IllegalStateException();
			else
				mMianLooper = myLooper();
		}
	}

	public static Looper getMainLooper() {
		synchronized (Looper.class) {
			return mMianLooper;
		}
	}

	/**
	 * is current thread
	 * 
	 * @return
	 */
	public boolean isCurrentThread() {
		return mThread == Thread.currentThread();
	}

	/**
	 * @param allowQuit
	 */
	public Looper(boolean allowQuit) {
		mQueue = new MessageQueue(allowQuit);
		mThread = Thread.currentThread();
	}

	public static void loop() {
		final Looper me = myLooper();
		if (me == null)
			throw new IllegalArgumentException("my looper is null , in loop()");
		final MessageQueue queue = me.getQueue();

		for (;;) {
			Message msg = queue.next();
			if (msg == null)
				continue;
			msg.target.dispatchMessage(msg);
			msg.recycleUnchecked();

		}
	}

	public static MessageQueue myQueue() {
		return myLooper().mQueue;
	}

	public Thread getThread() {
		return mThread;
	}

	public MessageQueue getQueue() {
		return mQueue;
	}

}
