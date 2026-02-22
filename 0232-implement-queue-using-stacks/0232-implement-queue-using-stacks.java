class MyQueue {
    Stack<Integer> s1 = new Stack<>(); // input
    Stack<Integer> s2 = new Stack<>(); // output
    
    public void push(int x) {
        s1.push(x);
    }
    
    public int pop() {
        peek();           // ensures s2 has the front element
        return s2.pop();
    }
    
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}