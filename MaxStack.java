/**
 * A stacklike data structure that also allows stacklike access
 * to its elements by their value.
 * For example, given a stack of {1, 3, 2, 5, 3, 4, 5, 2}
 * peek() -> 2, peekMax() -> 5
 * pop() -> 2; peek() -> 5, peekMax() -> 5
 * pop() -> 5; peek() -> 4, peekMax() -> 5
 * push(6); peek() -> 6, peekMax() -> 6
 * popMax() -> 6; peek -> 4, peekMax() -> 5
 * popMax() -> 5; peek -> 4, peekMax() -> 4
 */
public interface MaxStack<T extends Comparable<T>> {
  
    /** Add an element to the stack. */
    public void push(T toPush);
 
    /** Return the top value on the stack. */
    public T peek();
  
    /** Remove and return the top value from the stack. */
    public T pop();
     
    // Two special methods, so this isn't just 'implement a stack':
 
    /** Return the largest value in the stack. (Remember that T must implement Comparable.) */
    public T peekMax();
 
    /** Remove and return the largest value from the stack. */
    public T popMax();
}