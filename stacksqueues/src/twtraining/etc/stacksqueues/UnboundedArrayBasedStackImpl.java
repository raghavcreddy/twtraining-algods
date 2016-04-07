package twtraining.etc.stacksqueues;

import java.util.ArrayList;
import java.util.List;

class UnboundedArrayBasedStackImpl<T> implements Stack<T> {

  private final List<T> backingList = new ArrayList<>();
  
  @Override
  public Stack<T> push(T item) {
    backingList.add(item);
    return this;
  }

  @Override
  public Stack<T> pop() {
    backingList.remove(size() - 1);
    return this;
  }

  @Override
  public T nextValue() {
    return backingList.get(size() - 1);
  }
  
  @Override
  public int size() {
    return backingList.size();
  }

}


