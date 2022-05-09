import java.util.ArrayList;


public class ArrayStack implements IStackable{

    private ArrayList<Integer> array_stack;

    public ArrayStack() {
        this.array_stack = new ArrayList<Integer>();
    }

    public int size() { return array_stack.size(); }

    public void push(int v) { array_stack.add(v); }

    public int pop() {
        return array_stack.remove(array_stack.size() - 1 );
    }
}
