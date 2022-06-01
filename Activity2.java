/**
 * When thinking - print "Philosopher i is thinking" and sleep random time
 * When eating - print "Philosopher i is eating" and sleep random time
 * When picked up both - print
 * When released - print and return state to thinking
 */
public class Activity2 {
    public static void main(String[] args) {
        DiningPhilosopher dp = new DiningPhilosopher();
        Philosopher philosophers[] = new Philosopher[5];
        Thread threads[] = new Thread[5];

        // Instantiate Dining Philosopher object
        dp = new DiningPhilosopher();
        dp.initialize();

        // Instantiate philosophers and start threads
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher(i, dp);
            threads[i] = new Thread(philosophers[i], "Philosopher " + i);
            threads[i].start();
        }

        // Join calls for threads
        for (int i = 0; i < 5; i++) {
            try {
                threads[i].join();
            }catch(InterruptedException e) {
                System.out.println("Main: " + threads[i].getName() + " was interrupted.");
                System.out.println(e.getMessage());
            }
        }
    }
}
