package stack;

//package OOP;
public interface Stack {

	public void push(int item);
	public int pop();
	public void peek();
	public void print();
        public boolean isEmpty();
        public boolean isFull();
        public void clear();
}