package Stacks;

public class MainTest{

    public static void main(String args[]) {
        StackUsingArray<Integer> st = new StackUsingArray<>();
        st.push(10);
        st.push(20);
        st.push(30);
        System.out.println(st.pop());
    }

}
