package twtraining.etc.stacksqueues;

import java.util.LinkedList;

class UnboundedLinkedListBasedQueueImpl<T> implements Queue<T> {

  private final LinkedList<T> backingList = new LinkedList<>();
  
  @Override
  public Queue<T> enqueue(T item) {
    backingList.addLast(item);
    return this;
  }

  @Override
  public Queue<T> dequeue() {
    backingList.removeFirst();
    return this;
  }

  @Override
  public T nextValue() {
    return backingList.getFirst();
  }
  
  @Override
  public int size() {
    return backingList.size();
  }
}

