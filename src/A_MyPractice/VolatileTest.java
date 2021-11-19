package A_MyPractice;

//Volatile can only guarantee a single read/write operation is atomic
//but sometimes it does not work for a read-then-update case, like value++
//flag can cause data race, flag = true is a write operation
//so we should add volatile on the flag variable
public class VolatileTest {
    public static volatile boolean flag = false;
    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (!flag) {
//                System.out.println("The thread is running");
                i++;
            }
            System.out.println("The thread is finished");
        }
    }
    public  static void main(String[] args) throws InterruptedException {
        Thread newThread = new Thread(new MyRunnable());
        newThread.start();
        //main thread
        Thread.sleep(1000);
        flag = true;
        System.out.println("Main thread is finished");
    }
}
