public class LinkedList<T extends Comparable<T>> implements List<T> {

    Node<T>head;
    int size=0;
    @Override
    public void insert(T data) {
      if(head==null){
          head=new Node<T>(data);
          size++;
      }else if(head.next==null){
          head.next=new Node<T>(data);
          size++;
      }
      else{
          insert(head.next,data);
      }
    }
    //insert at end
    private void insert(Node<T> node,T data){
        if(node.next==null){
            node.next=new Node<T>(data);
            size++;

        }else{
            insert(node.next,data);
        }
    }
    @Override
    public void remove(T data) {
        if(head==null)return;
        if(head.data==data){

            head=head.next;
            size--;

        }else{
            remove(head,head.next,data);
        }
    }

    private void remove(Node<T>prev,Node<T> curr,T data){
        if(curr.data==data){
            prev.next=curr.next;
            curr=null;
            size--;

        }else{
            remove(curr,curr.next,data);
        }
    }
    @Override
    public void printList() {

        if(head==null)return;
        Node<T>curr=head;
        while(curr!=null){
            System.out.println(curr.data);
            curr=curr.next;
        }

    }

    public void recurseivePrint(Node<T> head){

        if(head==null)return;
         System.out.println(head.data);
         recurseivePrint(head.next);
    }
    @Override
    public int getSize() {
     return this.size;

    }
}
