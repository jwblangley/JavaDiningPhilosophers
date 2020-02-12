package diningPhilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {

  private Lock lock = new ReentrantLock();
  private Philosopher heldBy;
  private final int id;

  public Chopstick(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public Philosopher getHeldBy() {
    return heldBy;
  }
  // Will wait until it can be picked up

  public void pickUp(Philosopher philosopher) {
    lock.lock();
    heldBy = philosopher;
  }

  public void putDown(Philosopher philosopher) {
    assert heldBy == philosopher
        : "A philosopher just tried to put down a chopstick they were not holding";

    heldBy = null;
    lock.unlock();
  }
}
