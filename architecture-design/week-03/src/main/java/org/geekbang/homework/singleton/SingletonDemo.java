package org.geekbang.homework.singleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单例模式 示例代码
 * */
public class SingletonDemo {
    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance();
        Singleton3 singleton3 = Singleton3.getInstance();
        Singleton4 singleton4 = Singleton4.getInstance();
    }
}

/**
 * 最简形式单例：饿汉单例
 */
class Singleton1{
    private Singleton1(){}
    private static Singleton1 instance=new Singleton1();
    public static Singleton1 getInstance(){
        return instance;
    }
}
/**
 * 懒汉形式单例
 * */
class Singleton2{
    private Singleton2(){}
    private static Singleton2 instance=null;
    public static synchronized Singleton2 getInstance(){
        if(instance==null){
            instance=new Singleton2();
        }
        return instance;
    }
}

/**
 * 内部锁单例
 * */
class Singleton3{
    private Singleton3 (){}
    private static Object lockObj = new Object();
    private static Singleton3 instance = null;
    public static Singleton3 getInstance(){
        synchronized (lockObj){
            if(instance==null){
                instance=new Singleton3();
            }
        }
        return instance;
    }
}

class Singleton4{
    private Singleton4(){}
    private static Singleton4 instance=null;
    private static Lock lock = new ReentrantLock();
    public static Singleton4 getInstance(){
        lock.lock();
        try{
            if(instance==null){
                instance=new Singleton4();
            }
            return instance;
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            lock.unlock();
        }
    }
}
