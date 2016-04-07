package twtraining.etc.stacksqueues;

/**
 * The public API through which stacks can be created.
 * 
 * Which design pattern is this?
 */
public final class Stacks {
  
  public static <T> Stack<T> defaultStack() {
    return new UnboundedArrayBasedStackImpl<>();
  }
  
  private Stacks() {}
}

