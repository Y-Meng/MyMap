

package com.mcy.mobile.core;

 
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.util.Log;

/**
 * 线程�?
 */
public class DefaultThreadPool {
	/**
	 * BaseRequest队列
	 */
	static ArrayBlockingQueue<Runnable> blockingQueue= new ArrayBlockingQueue<Runnable>(15);
	/**
	 * 线程池对�?
	 */
	static AbstractExecutorService pool = new ThreadPoolExecutor(10,20,15L,TimeUnit.SECONDS,
			blockingQueue,new ThreadPoolExecutor.DiscardOldestPolicy());
	
	private  static DefaultThreadPool instance = null;
	public  static DefaultThreadPool getInstance(){
		if(instance == null){
			instance = new DefaultThreadPool();
		} 
		return instance;
	}
	
	public void execute(Runnable r){
		pool.execute(r);
	}
	/**
	 * 关闭
	 */
	public static  void shutdown(){
		if(pool!=null){
			pool.shutdown();
			Log.i(DefaultThreadPool.class.getName(), "DefaultThreadPool shutdown");
		}
	}
	
	/**
	 * 立刻关闭
	 */
	public  static void shutdownRightnow(){
		if(pool!=null){
			//List<Runnable>  tasks =pool.shutdownNow();
			pool.shutdownNow();
			try {
				//立即终止
				pool.awaitTermination(1, TimeUnit.MICROSECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Log.i(DefaultThreadPool.class.getName(), "DefaultThreadPool shutdownRightnow");
//			for(Runnable   task:tasks){
//				task.
//			}
		}
	}
	
	
	public  static void removeTaskFromQueue(){
		//blockingQueue.contains(o);
	}
}
