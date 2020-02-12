package diningPhilosophers;

import java.util.Random;

public class Philosopher implements Runnable{

  private String name;

  private final int eatUntil;
  private final Chopstick leftChopstick;
  private final Chopstick rightChopstick;
  private int numEaten = 0;

  public Philosopher(String name, int eatUntil, Chopstick leftChopstick, Chopstick rightChopstick) {
    this.name = name;
    this.eatUntil = eatUntil;
    this.leftChopstick = leftChopstick;
    this.rightChopstick = rightChopstick;
  }

  public String getName() {
    return name;
  }

  @Override
  public void run() {
    Random random = new Random();
    while (numEaten < eatUntil) {

      System.out.println("Philosopher" + name + " thinking");
      try {
        Thread.sleep(random.nextInt(2000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // Deadlock resolution logic
      // Always start with lower id
      Chopstick firstChopstick = leftChopstick.getId() < rightChopstick.getId()
          ? leftChopstick : rightChopstick;
      Chopstick secondChopstick = leftChopstick.getId() < rightChopstick.getId()
          ? rightChopstick : leftChopstick;

      firstChopstick.pickUp(this);
      secondChopstick.pickUp(this);

      System.out.println("Philosopher" + name + " eating");
      numEaten++;

      secondChopstick.putDown(this);
      firstChopstick.putDown(this);

    }
    System.out.println("Philosopher" + name + " has finished eating");
  }
}
