package Stacks;

public class StackUsingArray <T>{

    int size=0;
    T[]stack;

    public StackUsingArray(){
        stack=(T[])new Object[1];
    }

    public T pop(){
        T value=stack[--size];

        if(size>0 && size==this.stack.length/4)
        resize(stack.length/2);
        return value;
    }

    public void push(T data){
      if(ifFull()){
          resize(this.size*2);
      }
      stack[size]=data;
      size++;
    }
    private void resize(int size){
        T[] newstack= (T[]) new Object[size];
        for(int i=0;i<this.size;i++)newstack[i]=stack[i];

        this.stack=newstack;
    }
    public boolean ifFull(){
       return stack.length==size;
    }
}
