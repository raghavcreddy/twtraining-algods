package twtraining.etc.stacksqueues;

public interface Queue<T> {
  Queue<T> enqueue(T item);
  
  Queue<T> dequeue();
  
  T nextValue();
  
  int size();
}



