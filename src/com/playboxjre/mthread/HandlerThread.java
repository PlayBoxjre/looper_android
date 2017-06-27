/**
 * Copyright statement
 * 
 * The ownership of this program < Shanghai XiaoChi intelligent technology co., LTD. > 
 * and the copyright owner <KongXiang@Aaron> all.Without permission, 
 * can not be used for commercial purposes.
 * Only when used for learning to use.If use, 
 * please contact with my contact: 
 * 
 * FileName : 	HandlerThread.java
 * CreateTime:	2017-05-10
 * Author : 	Kong Xiang_CN@Aaron_EN
 * Contact : 	Email : 316378085@qq.com
				Telephone  : 13122300281
	
 */
package com.playboxjre.mthread;



/**
 * @author Aaron@Kong
 * 
 *         CreateTime: 2017-05-10 Author : Kong Xiang_CN@Aaron_EN
 */
public class HandlerThread extends Thread {

	private boolean main;
	private Runnable run;

	/**
	 * 
	 */
	public HandlerThread() {
		super();
	}

	/**
	 * 
	 */
	public HandlerThread(Runnable target, String name, boolean main) {

		super(target, name);
		this.main = main;
		this.run = target;

	}

	public HandlerThread(Runnable target, boolean main) {
		super(target);
		this.main = main;
		this.run = target;
	}

	public HandlerThread(boolean main) {
		super();
		this.main = main;
	}

	/**
	 * @param target
	 * @param name
	 */
	private HandlerThread(Runnable target, String name) {
		super(target, name);
		this.run = target;
	}

	/**
	 * @param target
	 */
	private HandlerThread(Runnable target) {
		super(target);
		// TODO Auto-generated constructor stub
		this.run = target;
	}

	/**
	 * @param name
	 */
	public HandlerThread(String name) {
		super(name);
	}

	/**
	 * @param group
	 * @param target
	 * @param name
	 * @param stackSize
	 */
	public HandlerThread(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
		this.run = target;
	}

	/**
	 * @param group
	 * @param target
	 * @param name
	 */
	public HandlerThread(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
	}

	/**
	 * @param group
	 * @param target
	 */
	public HandlerThread(ThreadGroup group, Runnable target) {
		super(group, target);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param group
	 * @param name
	 */
	public HandlerThread(ThreadGroup group, String name) {
		super(group, name);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			if (main)
				Looper.perpareMainLooper();
			else
				Looper.perpare();
			new Thread(run, "main-start-run-thread").start();
			Looper.loop();
		} catch (LooperHasExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#interrupt()
	 */
	@Override
	public void interrupt() {
		// TODO Auto-generated method stub
		super.interrupt();
	}

}
