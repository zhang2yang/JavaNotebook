package com.gxb.threadcoreknowledge.juc.pattern;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdConsumerBlockQueueDemo {

    static class MyResource {
        /**
         * 默认开启 进行生产消费的交互
         */
        private volatile boolean flag = true;
        /**
         * 默认值是0
         */
        private AtomicInteger atomicInteger = new AtomicInteger();

        private BlockingQueue<String> blockingQueue;

        public MyResource(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
            System.out.println(blockingQueue.getClass().getName());
        }

        public void myProd() throws Exception {
            String data;
            boolean returnValue;
            while (flag) {
                data = atomicInteger.incrementAndGet() + "";
                returnValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (returnValue) {
                    System.out.println(Thread.currentThread().getName() + "\t 插入队列数据" + data + "成功");
                } else {
                    System.out.println(Thread.currentThread().getName() + "\t 插入队列数据" + data + "失败");
                }
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println(Thread.currentThread().getName() + "\t 停止 表示 flag=" + flag);
        }

        public void myConsumer() throws Exception {
            String result;
            while (flag) {
                result = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if(null==result||"".equalsIgnoreCase(result)){
                    flag=false;
                    System.out.println(Thread.currentThread().getName()+"\t"+"超过2s没有取到 消费退出");
                    System.out.println();
                    System.out.println();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "消费队列" + result + "成功");

            }
        }
        public void stop() throws Exception{
            flag=false;
        }
    }

    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("时间到,停止活动");
        myResource.stop();
    }
}

