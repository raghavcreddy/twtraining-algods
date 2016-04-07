package twtraining.etc.stacksqueues;

/**
 * The public API through which queues can be created.
 * 
 * Which design pattern is this?
 */
public final class Queues {
  
  public static <T> Queue<T> defaultQueue() {
    return new UnboundedLinkedListBasedQueueImpl<>();
  }
  
  public static <T> Queue<T> stackBasedQueue() {
    return new UnboundedStackBasedQueueImpl<>(
        Stacks.<T>defaultStack(), Stacks.<T>defaultStack());
  }
  
  public static <T> Queue<T> boundedQueue(int capacity) {
    return new BoundedArrayBasedQueueImpl<>(capacity);
  }
  
  private Queues() {}
}

