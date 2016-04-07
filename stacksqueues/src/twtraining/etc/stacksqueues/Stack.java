package twtraining.etc.stacksqueues;

public interface Stack<T> {
  Stack<T> push(T item);
  
  Stack<T> pop();
  
  T nextValue();
  
  int size();
}

