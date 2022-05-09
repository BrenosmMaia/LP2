import java.util.LinkedList;

public class LinkedStack implements IStackable{

    private LinkedList<Integer> linked_stack;

    public LinkedStack() {
        this.linked_stack = new LinkedList<Integer>();
    }

    public int size() { return linked_stack.size(); }

    public void push(int v) { linked_stack.addFirst(v); }

    public int pop() { return linked_stack.removeFirst(); }
}
