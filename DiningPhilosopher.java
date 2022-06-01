import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosopher {
    String states[] = new String[5];
    Condition chopsticks[] = new Condition[5];
    ReentrantLock lock = new ReentrantLock();

    /**
     * Used to make a philosopher pickup chopsticks.
     * @param i - index of Philosopher wishing to eat
     */
    void pickup(int i) {
        lock.lock();
        states[i] = "HUNGRY";
        test(i);
        // If chopstick still held by other philosopher, wait until chopstick is signaled
        if(states[i] != "EATING") {
            try{
                // STATE: WAITING
                chopsticks[i].await();
            } catch (InterruptedException e) {
                System.out.println("ERROR: Philosopher " + i + " failed to wait");
                e.printStackTrace();
            }
        }
        lock.unlock();
    }

    /**
     * Used when a philosopher has finished eating. Tries to signal left and right chopsticks.
     * @param i - index of Philosopher that has finished eating.
     */
    void putdown(int i) {
        lock.lock();
        // STATE: PUTTING DOWN -> THINKING
        System.out.println("Philosopher " + i + " released its left and right chopsticks.");
        states[i] = "THINKING";
        test((i+4) % 5);
        test((i+1) % 5);
        lock.unlock();
    }

    /**
     * Checks if chopstick i can be signaled/retrieved.
     * @param i - index of Philosopher to test
     */
    void test(int i) {
        // If left and right philosophers are NOT eating && current philosopher is hungry
        if (states[(i+4) % 5] != "EATING" && states[(i+1) % 5] != "EATING" && states[i] == "HUNGRY") {
            // STATE: ACQUIRED LEFT AND RIGHT CHOPSTICK -> EATING
            states[i] = "EATING";
            System.out.println("Philosopher " + i + " acquired its left and right chopsticks.");
            chopsticks[i].signal();
        }
    }

    void initialize() {
        for(int i = 0; i < 5; i++) {
            states[i] = "THINKING";
            chopsticks[i] = lock.newCondition();
        }
    }
}
