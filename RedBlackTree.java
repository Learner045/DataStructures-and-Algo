package RedBlack;

public class RedBlackTree {
    private Node root;

    public void traverse(){
        traverse(root);
    }

    private void traverse(Node rootx) {
        if(rootx==null)return;
        traverse(rootx.getLC());
        System.out.println(rootx.getData());
        traverse(rootx.getRC());
    }

    private void rightRotation(Node node){
        System.out.println("Rotating to right on node "+node);

        Node tempLC=node.getLC();
        node.setLC(tempLC.getRC());

        if(node.getLC()!=null){
            node.getLC().setParent(node); //Setting Parent of node which is fixed on left of our node
        }
        //Setting parent of tempLC && attaching it to nodes parent
        tempLC.setParent(node.getParent());
        if(tempLC.getParent()==null)
            root=tempLC; //node does not have parent
        else if(node==node.getParent().getLC()){
            node.getParent().setLC(tempLC); //node is located to left of its parent so attach our tempLC there
        }else{
            node.getParent().setRC(tempLC); //node is located to right of its parent
        }

        //attaching node to tempLC & setting its parent
        tempLC.setRC(node);
        node.setParent(tempLC);
    }

    private void leftRotation(Node node){
        System.out.println("Rotating to left on node "+node);

        Node tempRC=node.getRC();
        node.setRC(tempRC.getRC());

        if(node.getRC()!=null){
            node.getRC().setParent(node);
        }

        tempRC.setParent(node.getParent());
        if(tempRC.getParent()==null)
            root=tempRC;
        else if(node==node.getParent().getLC()){
            node.getParent().setLC(tempRC);
        }else{
            node.getParent().setRC(tempRC);
        }

        tempRC.setLC(node);
        node.setParent(tempRC);
    }

    public void insert(int data){

        Node node=new Node(data);
       root= insert(node,root);

       fixViolations(node); //we fix violations on node and not on root
    }

    private Node insert(Node node, Node head) {

        if(head==null)return node;

        if(node.getData()<head.getData()){
            head.setLC(insert(node,head.getLC()));
            head.getLC().setParent(head); //imp step
        }else if(node.getData()>head.getData()){
            head.setRC(insert(node,head.getRC()));
            head.getRC().setParent(head);
        }
        return head;
    }

    private void fixViolations(Node node) {

        Node parent=null;
        Node grandParent=null;

        while(node!=root && node.getColor()!=NodeColor.BLACK && node.getParent().getColor()==NodeColor.RED){
            //node and parent both are red--conflict
            parent=node.getParent();
            grandParent=parent.getParent();

            if(parent==grandParent.getLC()){

                Node uncle=grandParent.getRC();
                if(uncle!=null && uncle.getColor()==NodeColor.RED){
                    //Case 1 and 4 where parent and uncle are both red
                    //Only recoloring
                    grandParent.setColor(NodeColor.RED);
                    parent.setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    //now we move up to detect violations. we don't take node as parent because uncle and parent
                    // already are color balanced
                    node=grandParent;
                }else{
                    //parent is red and uncle is black
                    //check to see if node is on right or left...
                    if(node==parent.getRC()){
                        //case 2 LR
                        leftRotation(parent); //left rot on parent -now it becomes case 3
                        node=parent;             //we try to recursively move up to check for any other violations
                        parent=node.getParent();  //node becomes parent and parent becomes grandparent

                    }
                        //case 3 LL
                        rightRotation(grandParent);
                        //swap colours
                        NodeColor temp=parent.getColor();
                        parent.setColor(grandParent.getColor());
                        grandParent.setColor(temp);
                        node=parent;

                }

            }else{
                //parent is on right oh grandparent
                Node uncle=grandParent.getLC();
                if(uncle!=null && uncle.getColor()==NodeColor.RED){
                    //recolouring case 1 and 4
                    grandParent.setColor(NodeColor.RED);
                    parent.setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node=grandParent;
                }else{
                    //case 2 or 3
                    if(node==parent.getLC()){
                        //case 2 RL
                        rightRotation(parent); //now becomes case 3
                        node=parent;
                        parent=node.getParent();

                    }
                    //case 3
                    leftRotation(grandParent);
                    //swap colours
                    NodeColor temp=parent.getColor();
                    parent.setColor(grandParent.getColor());
                    grandParent.setColor(temp);
                    node=parent;
                }

            }
        }//end of while

        if(root.getColor()==NodeColor.RED){
            root.setColor(NodeColor.BLACK); //root must be black by definition
        }
    }

}
