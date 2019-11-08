# JavaConcurrency
Java线程基础知识梳理  
### [12306卖票程序](https://github.com/JxnuHxh/JavaConcurrency/tree/master/src/system12306)  
### [生产者消费者模型](https://github.com/JxnuHxh/JavaConcurrency/tree/master/src/producter)  
## 1.线程跟进程？<b/><br>
线程是操作系统能够进行运算调度的最小单位，它是进程的子集，是进程中的实际运作单位<br>
一个进程可以有很多线程。每个进程都有自己的内存空间，是个实体，可执行代码和唯一进程标识符（PID）。 <br>
适当使用多线程可以提高运算效率，线程可以异步执行<br>
## 2.用户线程和守护线程的区别<br>
守护线程依赖于创建它的线程，而用户线程则不依赖。举个简单的例子：<br>
如果在main线程中创建了一个守护线程，当main方法运行完毕之后，<br>
守护线程也会随着消亡。而用户线程则不会，用户线程会一直运行直到其运行完毕。<br>
在JVM中，像垃圾收集器线程就是守护线程<br>
## 3.实现多线程的方法<br>
a.继承Thread类；<br>
b.实现Runnable接口；<br>
c.实现Callable接口通过FutureTask包装器来创建Thread线程；<br><br>
Thread本质上也是实现了Runnable接口的一个实例 代表一个线程的实例<br>
Runnable就相当于一个作业，而Thread才是真正的处理线程，我们需要的只是定义这个作业，<br>
然后将作业交给线程去处理，这样就达到了松耦合，也符合面向对象里面组合的使用，另外也<br>
节省了函数开销，继承Thread的同时，不仅拥有了作业的方法run()，还继承了其他所有的方法<br>
start()方法被用来启动新创建的线程，start()内部调用了run()方法<br>
调用start（）并不是立即执行多线程代码 而且该线程为可运行状态 什么时候运行由操作系统决定
## 4.线程的状态<br>
  <img src="img/线程状态.png">
   <img src="img/02.png">
   
## 5.线程调度
wait()：使一个线程处于等待（阻塞）状态，并且释放所持有的对象的锁；<br>
sleep()：使一个正在运行的线程处于睡眠状态，是一个静态方法，调用此方法要处理InterruptedException异常；<br>
notify()：唤醒一个处于等待状态的线程，当然在调用此方法的时候，并不能确切的唤醒<br>
某一个等待状态的线程，而是由JVM确定唤醒哪个线程，而且与优先级无关；<br>
notityAll()：唤醒所有处于等待状态的线程，该方法并不是将对象的锁给所有线程，<br>
而是让它们竞争，只有获得锁的线程才能进入就绪状态；<br>
sleep来自Thread类，和wait来自Object类<br>
一般采用时间片优先级调度算法<br>
## 6.synchronized和Lock<br>

synchronized关键字 表示对某个对象加锁 既有可见性 又有原子性<br>
读写都应该加锁  不然可能会出现脏读  但是会影响性能<br>
一个同步方法可以调用另一个同步方法,一个线程已经拥有某个对象的锁<br>
再次申请的时候仍然会得到该对象的锁,也就是说synchronized获得的锁是<br>
可重入的(线程可以进入任何一个它已经拥有的锁所同步着的代码块)<br>
主要相同点：Lock能完成Synchronized所实现的所有功能。
主要不同点：Lock有比Synchronized更精确的线程予以和更好的性能。<br>
Synchronized会自动释放锁，但是Lock一定要求程序员手工释放，并且必须在finally从句中释放。br>
## 7.synchronized和volatile<br>
volatile修饰的是变量 synchronized修饰的是类和方法<br>
volatile 关键字，使一个变量在多个线程间可见 volatile并不能保证多个线程<br>
共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized<br>
volatile是一种稍弱的同步机制 用来确保将变量的更新操作通知到其他线程<br>
访问volatile变量时不会执行加锁操作，因此也不会使执行线程阻塞，<br>
volatile变量是一种比synchronized关键字更轻量级的同步机制<br>
加锁机制既可以确保可见性又可以确保原子性，而volatile变量只能确保可见性<br>

## 8.什么是线程安全？举例说明<br>
如果每次运行结果和单线程运行结果一样，而且其他变量结果的值和预期效果也是一样的
就是线程安全的<br>
同步容器类<br>
ArrayList和Vector有什么区别？HashMap和HashTable有什么区别？StringBuilder和<br>
StringBuffer有什么区别？这些都是Java面试中常见的基础问题。面对这样的问题，回答是：<br>
ArrayList是非线程安全的，Vector是线程安全的；HashMap是非线程安全的，HashTable<br>
是线程安全的；StringBuilder是非线程安全的，StringBuffer是线程安全的<br>
## 9.并发与并行<br>

并发： 同一时间段，多个任务都在执行 (单位时间内不一定同时执行)；<br>
并行： 单位时间内，多个任务同时执行。<br>
## 10.如何停止一个线程？<br>
1、通过修改共享变量来通知目标线程停止运行<br>
2、通过Thread.interrupt方法中断线程<br>
3、不提倡的stop()方法<br>
## 11.线程内抛出异常<br>
 程序在执行的过程中，如果出现异常，默认情况锁会被释放<br>所以，在并发处理的过程中，<br>
 有异常要多加小心，不然可能会发生不一致的情况<br> 比如在一个web app处理过程中，<br>
 多个servlet线程共同访问同一个资源，这时异常情况处理不合适，在一个线程中抛出异常，<br>
 其他线程就会进入同步代码区，有可能会访问到异常产生时数据。因此要非常小心的处理同步业务逻辑中的异常<br>
 ## 12.什么是线程池，为什么要用它<br>
 是一组同步工作的资源池，与工作队列密切相关的，可以减少线程创建和销毁的开销<br>
 不会犹豫等待创建线程而延迟任务的执行，从而提高了响应性<br>
 线程的创建要花费昂贵的资源和时间 如果任务来了才创建线程 响应时间会变长<br>
 newFixedThreadPool：创建一个固定长度的线程池；<br>
 ExecutorService executorService = Executors.newFixedThreadPool(1);<br>
 newCachedThreadPool：创建一个可缓存的线程池；<br>
 newSingleThreadExecutor：是一个单线程的Executor，它创建单个工作者线程来执行任务；<br>
 newScheduleThreadPool：创建一个固定长度的线程池，而且以延迟或定时的方式来执行任务。<br>
 ## 13.什么是死锁！ 如何避免死锁？
 <img src="img/02.jpeg"><br>
 死锁是指两个或两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象，若无外力作用，<br>
 它们都将无法推进下去,会让你的程序挂起无法完成任务<br>
 死锁的四个条件<br>
a. 互斥条件：一个资源每次只能被一个进程使用。<br>
b. 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。<br>
c. 不剥夺条件：进程已获得的资源，在末使用完之前，不能强行剥夺。<br>
d. 循环等待条件：若干进程之间形成一种头尾相接的循环等待资源关系<br>
避免死锁最简单的方法就是阻止循环等待条件，将系统中所有的资源设置标志位、排序，<br>
规定所有的进程申请资源必须以一定的顺序（升序或降序）做操作来避免死锁<br>

## 14.活锁与死锁的区别<br>
活锁和死锁的主要区别是前者进程的状态可以改变但是却不能继续执行。<br>
就像两个过于礼貌的人在半路上面对面地相遇：他们彼此都让出对方的路，<br>
然而又在另一条路上相遇了。因此他们就这样反复地避让下去<br>
         
## Join()方法就是让两个线程合并 用于实现同步功能


<font color="#666600">浅黄色字体测试</font>
<table><tr><td bgcolor=#FF00FF>背景色是按16进制颜色值 </td></tr></table>
> 这是引用  

                  