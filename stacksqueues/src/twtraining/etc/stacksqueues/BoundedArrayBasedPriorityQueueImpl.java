package twtraining.etc.stacksqueues;

import java.util.*;

class BoundedArrayBasedPriorityQueueImpl<T> implements Queue<T> {

  private final int capacity;
  private final List<T> backingArray;

  private static final int START = 1;
  private int end;
  private Comparator<T> comparator;

  BoundedArrayBasedPriorityQueueImpl(int capacity, Comparator<T> comparator) {
    this.capacity = capacity + 1;
    this.comparator = comparator;
    backingArray = new ArrayList<>(this.capacity);
  }

  @Override
  public Queue<T> enqueue(T item) {
    backingArray.add(end, item);
    heapifyUp(end);
    end++;
    return this;
  }

  @Override
  public Queue<T> dequeue() {
    Collections.swap(backingArray, START, end - 1);
    end--;
    heapifyDown(START);
    return this;
  }

  @Override
  public T nextValue() {
    if (empty()) {
      throw new RuntimeException("Priority queue is empty.");
    }
    return backingArray.get(START);
  }

  @Override
  public int size() {
    return end - START;
  }

  public Queue<T> addAll(List<T> items) {
    Collections.copy(backingArray, items);
    for (int i = end/2 + 1; i >= START; i++) {
      heapifyDown(i);
    }
    return this;
  }

  private boolean full() {
    return (end != capacity);
  }

  private boolean empty() {
    return (end != 1);
  }

  private void heapifyUp(int node) {
    while (node > START && comparator.compare(value(node), value(parent(node))) > 0 ) {
      Collections.swap(backingArray, node, parent(node));
      node = parent(node);
    }
  }

  private void heapifyDown(int node) {
    while (comparator.compare(backingArray.get(node), backingArray.get(leftChild(node))) < 0 ||
            comparator.compare(backingArray.get(node), backingArray.get(rightChild(node))) < 0) {
      int greaterNode;
      if (comparator.compare(backingArray.get(rightChild(node)), backingArray.get(leftChild(node))) > 0) {
        greaterNode = rightChild(node);
      } else {
        greaterNode = leftChild(node);
      }
      Collections.swap(backingArray, node, greaterNode);
      node = greaterNode;
    }
  }

  private int parent(int node) {
    if (node > capacity) {
      throw new RuntimeException("Node specified is out of bounds of queue size.");
    }
    return node / 2;
  }

  private int leftChild(int node) {
    if (node > capacity) {
      throw new RuntimeException("Left child for given node is out of bounds.");
    }
    return node * 2;
  }

  private int rightChild(int node) {
    if (node > capacity) {
      throw new RuntimeException("Left child for given node is out of bounds.");
    }
    return node * 2 + 1;
  }

  private T value(int node) {
    return backingArray.get(node);
  }

}
