package BinarySearchTrees;

public class BinarySearchTrees<T extends Comparable<T>> implements BST<T> {
    Node<T>head; Node<T>root;
    @Override
    public void insert(T data) {

        if(head==null){

            head=new Node<T>(data);
            root=head;
            return;

        }else{
            insertNode(data,head);
        }


    }
    private Node<T> insertNode(T data,Node<T>head){
        if(head==null)
            return new Node<T>(data);
        if(data.compareTo(head.getData())<0){
            head.setLeftChild(insertNode(data,head.getLeftChild()));
        }else
        {
            head.setRightChild(insertNode(data,head.getRightChild()));
        }
        return head;
    }

    @Override
    public void delete(T data) {

        if(head==null)return;
        deleteOp(data,head);
    }

    private Node<T> deleteOp(T data,Node<T>head){
        if(head==null)return null;

        if(data.compareTo(head.data)<0){
            head.setLeftChild(deleteOp(data,head.getLeftChild()));
        }else if(data.compareTo(head.data)>0){
            head.setRightChild(deleteOp(data,head.getRightChild()));
        }else{
            //we have found node to be deleted
            //case 1:leaf

            if(head.getLeftChild()==null && head.getRightChild()==null){
                System.out.println("removing leaf");
                head=null;
                return head;
            }
            //case 2 : 1 child
            if(head.getLeftChild()!=null && head.getRightChild()==null){
                System.out.println("only L child present");
                Node<T>temp=head.getLeftChild();
                head=null;
                return temp;
            }
            if(head.getRightChild()!=null && head.getLeftChild()==null){
                System.out.println("only R child present");
                Node<T>temp=head.getRightChild();
                head=null;
                return temp;
            }
            //case 3 : both childrent present--so find max node in left sub tree or min node in right subtree
            Node<T>predeccssorNode=getPredec(head.getLeftChild());
            //swap node's data to predeccssorNode's data
            T val=head.getData();
            head.setData(predeccssorNode.getData());
            predeccssorNode.setData(val);
            head.setLeftChild(deleteOp(val,head.getLeftChild()));
        }
        return head;
    }
    private Node<T>getPredec(Node<T>node){
        if(node.getRightChild()!=null)
            return getPredec(node.getRightChild());
        return node;
    }
    @Override
    public T getMax() {
        if(head==null)return null;
       T val= getMaxi(head);
       return val;
    }
    private T getMaxi(Node<T>head){
        if(head.getRightChild()!=null){
           return getMaxi(head.getRightChild());
        }
        return head.data;
    }

    @Override
    public T getMin() {

       if(head==null)return null;
       T val=getMini(head);

       return val;

    }
    private T getMini(Node<T>head){

        if(head.getLeftChild()!=null){
          return  getMini(head.getLeftChild());
        }
      return  head.getData();

    }

    @Override
    public void traverse() {

        DFS(root);
      //  System.out.println(root.data);

    }
    private void DFS(Node<T>root){
        if(root==null)return;

        DFS(root.getLeftChild());
        System.out.println(root.data);
        DFS(root.getRightChild());

    }
}
