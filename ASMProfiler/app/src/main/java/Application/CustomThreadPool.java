package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * packageName    : Application
 * fileName       : CustomThreadPool
 * author         : lucas
 * date           : 2022-09-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-01        lucas       최초 생성
 */
public class CustomThreadPool {

//    holds tasks
    private BlockingQueue<Runnable> runnableQueue;

//    holds the pool of worker threads
    private List<WorkerThread> threads;

//    check if shutdown is initiated
    private AtomicBoolean isThreadPoolShutDownInitiated;

    public CustomThreadPool(final int noOfThreads) {
        this.runnableQueue = new LinkedBlockingQueue<>();
        this.threads = new ArrayList<>(noOfThreads);
        this.isThreadPoolShutDownInitiated = new AtomicBoolean(false);

//        create worker threads
        for (int i = 1; i <= noOfThreads; i++) {
            WorkerThread thread = new WorkerThread(runnableQueue, this);
            thread.setName("Worker Thread - " + i);
            thread.start();
            threads.add(thread);
        }
    }

    public void execute(Runnable r) throws InterruptedException{
        if (!isThreadPoolShutDownInitiated.get()) {
            runnableQueue.put(r);
        }else {
            throw new InterruptedException("Thread Pool shutdown is initiated, unable to execute task");
        }
    }
    public void shutdown(){
        isThreadPoolShutDownInitiated = new AtomicBoolean(true);
    }
    private static class WorkerThread extends Thread {
        private BlockingQueue<Runnable> taskQueue;

        private CustomThreadPool threadPool;

        public WorkerThread(BlockingQueue<Runnable> taskQueue, CustomThreadPool threadPool) {
            this.taskQueue = taskQueue;
            this.threadPool = threadPool;
        }

        @Override
        public void run() {
            try{
//                continue until all tasks finished processing
                while (!threadPool.isThreadPoolShutDownInitiated.get() || !taskQueue.isEmpty()) {
                    Runnable r;
                    while ((r = taskQueue.poll()) != null) {
                        r.run();
                    }
                    Thread.sleep(1);
                }
            } catch (RuntimeException | InterruptedException e) {
                throw new CustomThreadPoolException(e);
            }
            super.run();
        }

        private static class CustomThreadPoolException extends RuntimeException {
            private static final long serialVersionUID = 1L;
            public CustomThreadPoolException(Throwable t) {
                super(t);
            }
        }
    }
}
