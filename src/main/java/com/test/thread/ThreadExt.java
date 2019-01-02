package com.test.thread;

public class ThreadExt extends Thread {
    private int count=0;
    public synchronized void run(){
        count++;
        System.out.println(currentThread().getName()+"-"+count);
    }

    public static void main(String[] args) {
        ThreadExt t=new ThreadExt();
        Thread t1=new Thread(t,"t1");
        Thread t2=new Thread(t,"t2");
        Thread t3=new Thread(t,"t3");
        Thread t4=new Thread(t,"t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
class ThreadEnable implements Runnable{
    private int count=0;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"-"+count);
    }

    public static void main(String[] args) {
        ThreadEnable myThread = new ThreadEnable();
        Thread t1=new Thread(myThread);
        Thread t2=new Thread(myThread);
        Thread t3=new Thread(myThread);
        Thread t4=new Thread(myThread);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class ThreadInner{
//锁: 最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。

    public void test(){
        System.out.println(Thread.currentThread().getName());
    }


    public static void main(String[] args) {
        ThreadInner a=new ThreadInner();
        ThreadInner b=new ThreadInner();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                a.test();
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                b.test();
            }
        });
        t1.start();
        t2.start();

        new Thread( () -> System.out.println("In Java8!") ).start();

    }
}