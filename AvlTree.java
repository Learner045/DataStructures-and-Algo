package AVL;

public class AvlTree implements Tree{
    private Node root;

    //LEFT ROTATION means moving root to left
    //RIGHT  ROTATION means moving root to right
    @Override
    public void insert(int data) {

        if(root==null)root=new Node(data);

        else
       root= insert(root,data);

    }

    private Node insert(Node head,int data){
        if(head==null)return new Node(data);

        if(data<head.getData()){
           head.setLC(insert(head.getLC(),data));


        }
        else {
            head.setRC(insert(head.getRC(),data));

        }
     //   System.out.println("head  is"+head);
        head.setHeight(height(head));
        head=settleViolation(head,data);

        return head;
    }

    private Node settleViolation(Node head, int data) {
       System.out.println("Settling violation on "+head);
        int balance=getBalance(head);
        //if balance is >1 then tree is left heavy  case 1,3
        //if balance is <-1 then tree is right heavy case 2,4

        if(balance>1 && data< head.getLC().getData())
         return rightRotation(head);  //case 1
         if(balance<-1 && data>head.getRC().getData())
          return leftRotation(head);
         if(balance>1 && data>head.getLC().getData()){
             //case 3 //left right heavy
             head.setLC(leftRotation(head.getLC())); //after this step it will become left heavy
             return rightRotation(head);
         }
        if(balance<-1 && data<head.getRC().getData()){
            //case 4 //right left heavy
            head.setRC(rightRotation(head.getRC()));//after this step it will become right heavy
            return leftRotation(head);
        }

        return head;//if its balanced and does not need rotation just return head or root

    }

    private Node rightRotation(Node node){
       System.out.println(" right rotation on "+node.data);
        Node tempNode=node.getLC();
        Node child=tempNode.getRC();

        tempNode.setRC(node);
        node.setLC(child);

        node.setHeight(height(node));
        tempNode.setHeight(height(tempNode));

        return tempNode;//return new root
    }
    private Node leftRotation(Node node){
       System.out.println("left rotation on "+node.data);
        Node tempNode=node.getRC();
        Node child=tempNode.getLC();

        tempNode.setLC(node);
        node.setRC(child);

        node.setHeight(height(node));
        tempNode.setHeight(height(tempNode));

        return tempNode;//return new root
    }

    public int height(){
        return   height(root);

    }
    public int height(Node head){
        if(head==null)return -1; //because as per formula if its a leaf node then max of( -1 and -1) +1 will make 0
        //so instead of returning 0 we return -1

       return Math.max(height(head.LC),height(head.RC))+1; //+1 because we need to count that edge connecting subtrees
    }

    private int getBalance(Node node){
        if(node==null){
            return 0; //tree has no LC and no RC so its height is 0
        }
        return height(node.getLC())-height(node.getRC());
        //if it is left heavy then number will be positive else it is right heavy so num will be negative
    }





    @Override
    public void traverse() {

        traversal(root);
    }


    //EXACTLY SAME
    private void traversal(Node head){
        if(head==null)return;

        traversal(head.getLC());
        System.out.println(head);
        traversal(head.getRC());
    }

}
