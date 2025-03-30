public class MaxStackImpl<T> implements MaxStack<T> {

    static class LinkedListNode<T> {
        T val;
        LinkedListNode left;
        LinkedListNode right;

        public LinkedListNode(T val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    static class LinkedList<T> {
        LinkedListNode<T> head;
        LinkedListNode<T> tail;

        public LinkedList() {
            head = tail = null;
        }

        public LinkedListNode<T> insert(T val) {
            LinkedListNode<T> newnode = new LinkedListNode(T);
            if (head == null) {
                head = tail = newnode;
            } else {
                // specialized case of a sigle entry
                tail.right = newnode;
                newnode.left = tail;
                tail = newnode;
            }
            return newnode;
        }
    }

    public MaxStackImpl() {

    }

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
