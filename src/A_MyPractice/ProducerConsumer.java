package A_MyPractice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ProducerConsumer {
    public static void main(String[] args) {
        Q q = new Q(20);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(new Producer(q)));
        }
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread((new Consumer(q))));
        }
        for (Thread t : threads) {
            t.start();;
        }
    }

    static class Producer implements Runnable {
        Q q;
        public Producer(Q q) {
            super();
            this.q = q;
        }
        @Override
        public void run() {
            q.put(0);
        }
    }

    static class Consumer implements  Runnable {
        Q q;
        public Consumer(Q q) {
            super();
            this.q = q;
        }
        @Override
        public void run() {
            q.take();
        }
    }

     static class Q {
        final int limit;
        Queue<Integer> q;
        public Q(int limit) {
            this.limit = limit;
            q = new ArrayDeque<>();
        }
         //public is the first one, the return type is the last one.
         public synchronized void put(int ele) {
             while (q.size() == limit) {
                 try {
                     wait();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
             if (q.size() == 0) {
                 notifyAll();
             }
             q.offer(ele);
         }

         public synchronized int take() {
             while (q.size() == 0) {
                 try {
                     wait();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
             if (q.size() == limit) {
                 notifyAll();
             }
             return q.poll();
         }
    }


}
