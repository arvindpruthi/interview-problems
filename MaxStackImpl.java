import java.util.PriorityQueue;
import java.util.Stack;

public class MaxStackImpl<T extends Comparable<T>> implements MaxStack<T> {

    static class LinkedListNode<T> {
        T val;
        LinkedListNode<T> left;
        LinkedListNode<T> right;

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

        public LinkedListNode<T> push(T val) {
            LinkedListNode<T> newnode = new LinkedListNode<T>(val);
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

        public LinkedListNode<T> pop() {
            // Remove from tail
            LinkedListNode<T> node = tail;
            if (tail == null)
                return null;
            tail = tail.left;
            if (tail == null)
                head = null;
            else {
                tail.right = null;
            }
            return node;
        }

        public LinkedListNode<T> peek() {
            LinkedListNode<T> node = tail;
            return node;
        }

        public void delete(LinkedListNode<T> node) {
            if (head == node) {
                head = head.right;
                if (head != null)
                    head.left = null;
            } else if (tail == node) {
                tail = tail.left;
                tail.right = null;
            } else {
                node.right.left = node.left;
                node.left.right = node.right;
                node.left = node.right = null;
            }
        }
    }

    LinkedList<T> list;
    PriorityQueue<LinkedListNode<T>> maxHeap;

    public MaxStackImpl() {
        list = new LinkedList<>();
        maxHeap = new PriorityQueue<LinkedListNode<T>>((o1, o2) -> (o2.val - o1.val)); 
    }

   /** Add an element to the stack. */
   public void push(T toPush) {
        LinkedListNode<T> node = list.push(toPush);
        maxHeap.add(node);
   }
 
   /** Return the top value on the stack. */
   public T peek() {
        LinkedListNode<T> node = list.peek();
        return node.val;
   }
 
   /** Remove and return the top value from the stack. */
   public T pop() {
        LinkedListNode<T> node = list.pop();
        if (node != null) {
            maxHeap.remove(node);
            return node.val;
        }
        return null;
   }
    
   // Two special methods, so this isn't just 'implement a stack':

   /** Return the largest value in the stack. (Remember that T must implement Comparable.) */
   public T peekMax() {
        LinkedListNode<T> node = maxHeap.peek();
        return node.val;
   }

   /** Remove and return the largest value from the stack. */
   public T popMax() {
        LinkedListNode<T> node = maxHeap.poll();
        if (node != null) {
            list.delete(node);
            return node.val;
        }
        return null;
    }

}