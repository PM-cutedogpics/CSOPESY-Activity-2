import java.util.Random;

public class Philosopher extends Thread{
    String state;
    int id;
    DiningPhilosopher dp;
    Random rng;

    public Philosopher(int id, DiningPhilosopher dp) {
        this.state = "THINKING";
        this.id = id;
        this.dp = dp;
        this.rng = new Random();
    }

    /**
     * Philosopher eats, think, and waits to eat (hungry).
     */
    @Override
    public void run() {
        try{
            //
            while (true) {
                // Back to thinking
                System.out.println("Philosopher " + id + " is thinking.");
                Thread.sleep(rng.nextInt(5000 - 1000 + 1) + 1000);

                // Try to pickup and eat
                dp.pickup(id);
                System.out.println("Philosopher " + id + " is eating.");
                Thread.sleep(rng.nextInt(5000 - 1000 + 1) + 1000);

                // Drop
                dp.putdown(id);
            }

        }catch(InterruptedException e) {
            System.out.println("ERROR: The thread for Philosopher " + id + "was interrupted.");
        }
    }
}
