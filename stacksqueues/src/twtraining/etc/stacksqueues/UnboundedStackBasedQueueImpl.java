package twtraining.etc.stacksqueues;

class UnboundedStackBasedQueueImpl<T> implements Queue<T> {

  private final Stack<T> masterStack;
  private final Stack<T> slaveStack;
  
  UnboundedStackBasedQueueImpl(Stack<T> masterStack, Stack<T> slaveStack) {
    this.masterStack = masterStack;
    this.slaveStack = slaveStack;
  }
  
  @Override
  public Queue<T> enqueue(T item) {
    masterStack.push(item);
    return this;
  }

  @Override
  public Queue<T> dequeue() {
    dump();
    slaveStack.pop();
    return this;
  }

  @Override
  public T nextValue() {
    dump();
    return slaveStack.nextValue();
  }

  @Override
  public int size() {
    return masterStack.size() + slaveStack.size();
  }
  
  private void dump() {
    if (slaveStack.size() == 0) {
      while (masterStack.size() > 0) {
        slaveStack.push(masterStack.nextValue());
        masterStack.pop();
      }
    }
  }
}


