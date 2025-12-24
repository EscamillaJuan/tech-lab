## Sequential vs Concurrent vs Parallel

- Sequential programs can only work on one task at the time
- Concurrent programs can have multiple tasks in progress at the same time
- Parallel programs can actively be working on multiple tasks at the same time

## Threads
Threads can run independently its own Java code, which includes variables, 
loops, conditionals, method calls, etc.  
Threads require hardware and operating system support. Each thread has its own 
stack memory but all the threads share the same heap. This means you can have two
or more threads accessing shared state, such as an ArrayList or a HashMap.

## Virtual threads
When you create a Thread object in Java, you're actually creating a virtual thread
that is managed by the JVM. Virtual threads make it so that the program could create
1,000 Threads, even if the computer's CPU only supports 4.

## Theoretical Limits of Parallelism
Parallelism can enable programs to do more work in less time, but it's not possible
to just keep adding more threads to the program and expect it to keep getting faster.

#### Amdahl's Law
$ S = {1 \over 1 - p} $

Where:  
**p** is the fraction of the program that can be parallelized  
**S** is how much the program could, in theory, speed up from parallelism

## Thread pool
It is a collection of threads that is used to efficiently execute and manage 
asynchronous work. Pools reduce the cost of using threads by storing them in a 
worker thread pool. That way, the program cam reuse existing threads instead of 
creating new thread for each piece of work that need to be done.
- Limits the number of threads used by the program
- Creates threads one and reuses them, instead of creating new thread of 
each unit of work

#### Creating Thread pools
Pools can be created using the Executor API

**A thread pool with only one thread**
```Java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

ExecutorService pool = Executors.newSingleThreadExecutor();
```

**A thread pool that reuses threads but does not limit the number of threads it can create**
```Java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

ExecutorService pool = Executors.newCachedThreadPool();
```

**A thread pool that reuses threads and limits the number of threads to 12**

```Java
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

ExecutorService pool = Executors.newFixedThreadPool(12);
```

#### Submitting Asynchronous Work
Thread pools have several methods to submit work and executed it asynchronous
**Submits a Runnable with no return value, and returns a Future**
```Java
Future<?> print = pool.submit(() -> System.out.println("Hello"));
```
**Submits a Runnable and returns void**
```Java
pool.execute(() -> System.out.println("Hello"))
```
**Submits a Callable, whose return value will be accessible via the Future**
```Java
Future<Path> print = pool.submit(() -> dowloadFile());
```

**Future** is a class that contains the result of an asynchronous computation

 ### ForkJoinPool
ForkJoinPool is a specialized kind of thread pool that has the following advantages over traditional
thread pools:
- It uses a technique called work stealing so that idle worker threads can find work to do
- Its API is optimized for asynchronous work that creates more work. You might also hear this called
recursive work