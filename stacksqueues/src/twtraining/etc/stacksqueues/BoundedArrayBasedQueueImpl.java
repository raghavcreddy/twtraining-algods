package twtraining.etc.stacksqueues;

import java.util.ArrayList;
import java.util.List;

class BoundedArrayBasedQueueImpl<T> implements Queue<T> {

  private final int capacity;
  private final List<T> backingArray;
  
  /**
   * The inclusive index on the {@code BoundedArrayBasedQueueImpl#backingArray} where the queue
   * starts. i.e. where its first value is contained.
   */
  private int start;
  
  /**
   * The exclusive index on the {@code BoundedArrayBasedQueueImpl#backingArray} where the queue
   * ends. i.e. one index logically ahead of where the last value is contained.
   */  
  private int end;
  
  BoundedArrayBasedQueueImpl(int capacity) {
    // Reserve space per the requested capacity and additionally for one sentinel value.
    this.capacity = capacity + 1;
    backingArray = new ArrayList<>(this.capacity);
  }
  
  @Override
  public Queue<T> enqueue(T item) {
    if (full()) {
      throw new RuntimeException("Cannot enqueue into a full queue.");
    }
    
    backingArray.set(end, item);
    end = mod(end + 1);
    return this;
  }

  @Override
  public Queue<T> dequeue() {
    if (empty()) {
      throw new RuntimeException("Cannot dequeue from an empty queue.");
    }
    
    backingArray.set(start, null); // Allow garbage collection.
    start = mod(start + 1);
    return this;
  }

  @Override
  public T nextValue() {
    if (empty()) {
      throw new RuntimeException("Cannot get next value from an empty queue.");
    }
    
    return backingArray.get(start);
  }

  @Override
  public int size() {
    int diff = end - start;
    if (start <= end) {
      return diff;
    } else {
      return capacity + diff;
    }
  }
  
  private boolean full() {
    return start == mod(end + 1);
  }
  
  private boolean empty() {
    return start == end;
  }
  
  private int mod(int i) {
    return i % capacity;
  }
}

