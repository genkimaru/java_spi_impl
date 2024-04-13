package com.cf.thread;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class ExecutorsDemo {

    private  void schedule(){

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        ses.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        } , 3L , TimeUnit.SECONDS);
        ses.shutdown();
    }

    private void scheduleWithFixedDelay() throws InterruptedException {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        } , 1, 3 , TimeUnit.SECONDS);
        Thread.sleep(10000);
        ses.shutdown();

    }


    private void testThreadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10L , TimeUnit.SECONDS , new ArrayBlockingQueue<>(10));

        for(int i = 0 ; i < 10 ; i++){
            threadPoolExecutor.execute(() -> {
                        System.out.println(Thread.currentThread().getName());
                    }
            );
        }
        threadPoolExecutor.shutdown();
    }


    // callable is suitable for the tasks which have return value
    // ThreadPoolExecutor.submit(Callable)
    private void testThreadPoolExecutor2(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10L , TimeUnit.SECONDS , new ArrayBlockingQueue<>(10));

        Callable<String> callable = new Callable<>() {
            @Override
            public String call() throws Exception {
                return "abc";
            }
        };

        ArrayList<Future<String>> list = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            Future<String> future = threadPoolExecutor.submit(callable);
            list.add(future);
        }
        list.forEach(new Consumer<Future<String>>() {
            @Override
            public void accept(Future<String> future) {
                try {
                    String s = future.get();
                    System.out.println(s);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadPoolExecutor.shutdown();
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorsDemo demo = new ExecutorsDemo();
//        demo.schedule();
//        demo.scheduleWithFixedDelay();

//        demo.testThreadPoolExecutor();

        demo.testThreadPoolExecutor2();



    }




}



