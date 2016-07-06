package thread;

/**
 * 不常用
 * thread 实现方式二： extends Thread
 * User: hejm
 * Date: 2016/7/6
 * Time: 13:00
 */
public class HelloThread extends Thread {


    @Override
    public void run() {
        System.out.println("Hello from a thread");

    }

    public static void main(String[] args) {
        HelloThread thread = new HelloThread();
        thread.start();
    }
}
