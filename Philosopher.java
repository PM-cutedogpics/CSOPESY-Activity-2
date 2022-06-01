import java.util.Random;

public class Philosopher extends Thread{
    int id;
    DiningPhilosopher dp;
    Random rng;

    public Philosopher(int id, DiningPhilosopher dp) {
        this.id = id;
        this.dp = dp;
        this.rng = new Random();
    }

    /**
     * Philosopher eats, think, and waits to eat (hungry).
     */
    @Override
    public void run() {
        int min = 3000;
        int max = 10000;
        try{
            while (true) {
                // Back to thinking
                System.out.println("Philosopher " + id + " is thinking.");
                Thread.sleep(rng.nextInt(max - min + 1) + min);

                // Try to pickup and eat
                dp.pickup(id);
                System.out.println("Philosopher " + id + " is eating.");
                Thread.sleep(rng.nextInt(max - min + 1) + min);

                // Drop
                dp.putdown(id);
            }

        }catch(InterruptedException e) {
            System.out.println("ERROR: The thread for Philosopher " + id + "was interrupted.");
        }
    }
}
