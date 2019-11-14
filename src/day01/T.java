package day01;
/*
synchronized关键字
对某个对象加锁

 */
public class T {
    private int count = 10;
    private Object o=new  Object();

    public void m1(){
        synchronized(o){//任何对象要执行下面的代码，必须要拿到o的锁
            count++;
            System.out.println(Thread.currentThread().getName()+"count="+count);
        }
    }
    public synchronized void m2(){
        count++;
        System.out.println(Thread.currentThread().getName()+"count="+count);
    }
}
