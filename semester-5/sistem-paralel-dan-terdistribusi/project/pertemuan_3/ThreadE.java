public class ThreadE {

    public static void main(String[] args) {
        // Extend
        Contoh1 x = new Contoh1();
        x.start();
        // Implement
        Contoh2 ThreadObject = new Contoh2();
        Thread t = new Thread(ThreadObject);
        t.start();

    }
}

// Extending thread class, pake inheritance
class Contoh1 extends Thread {
    public void run() {
        System.out.println("this thread is running - extends");
    }
}

// Implementing runnable interface
class Contoh2 implements Runnable {
    public void run() {
        System.out.println("this thread is running - implements");
    }
}
