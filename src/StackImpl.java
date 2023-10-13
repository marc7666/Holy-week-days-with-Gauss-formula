import java.util.ArrayList;
import java.util.List;

public class StackImpl<E> implements Stack<E> {
    private List<E> stack;
    private int size;

    public StackImpl() {
        this.stack = new ArrayList<>();
        this.size = 0;
    }

    @Override
    public void push(E elem) {
        this.stack.add(elem);
        this.size++;
    }

    @Override
    public void pop() {
        this.stack.remove(this.stack.size() - 1);
        this.size--;
    }

    @Override
    public E top() {
        return this.stack.get(this.stack.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.size;
    }
}
