package thread;

/**
 * 经常使用
 *
 * User: hejm
 * Date: 2016/7/6
 * Time: 13:09
 */
public class HelloRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("hello from Runnale");
    }

    public static void main(String[] args) {
        HelloThread helloThread = new HelloThread();
        helloThread.start();
    }
}
