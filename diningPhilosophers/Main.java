package diningPhilosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static final int NUM_PHILOSPHERS = 100;
  public static final int EAT_UNTIL = 5;

  public static void main(String[] args) throws InterruptedException {

    List<Philosopher> philosophers = new ArrayList<>();

    Chopstick firstChopstick = new Chopstick(0);
    Chopstick prevChopstick = firstChopstick;
    for (int i = 1; i < NUM_PHILOSPHERS; i++) {
      Chopstick nextChopstick = new Chopstick(i);
      Philosopher philosopher = new Philosopher("" + i, EAT_UNTIL, prevChopstick, nextChopstick);
      philosophers.add(philosopher);
      prevChopstick = nextChopstick;
    }

    // Last Philosopher
    Philosopher lastPhilosopher = new Philosopher("" + NUM_PHILOSPHERS, EAT_UNTIL, prevChopstick, firstChopstick);

    List<Thread> ts = philosophers.stream()
        .map(Thread::new)
        .collect(Collectors.toList());

    ts.forEach(Thread::start);

    for (Thread t : ts) {
      t.join();
    }
    System.out.println("Everyone has finished");
  }



}
