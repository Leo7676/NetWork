package com.leo.douban;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 线程池类
 * @author CheWenliang
 *
 */
public class ThreadPool {
    private static volatile ThreadPoolExecutor executor = null;
 
    private ThreadPool() {
    }
 
    public static ThreadPoolExecutor getInstance(int corePoolSize,
            int maximumPoolSize, long aliveTime, TimeUnit unit) {
        if (executor == null) {
            BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
            executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                    aliveTime, unit, queue);
        }
 
        return executor;
    }
 
    public static ThreadPoolExecutor getInstance() {
        if (executor == null) {
            BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
            executor = new ThreadPoolExecutor(20,30,10, TimeUnit.SECONDS, queue);
        }
 
        return executor;
    }
 
}