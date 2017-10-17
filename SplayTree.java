package Splay;

public class SplayTree<T extends Comparable<T>> implements Tree<T> {

    private int size;
    private Node<T>root;

    @Override
    public void insert(T data) {

        Node<T> parent=null;
        Node<T>tempNode=this.root;

        while(tempNode!=null){
            parent=tempNode;

            if(data.compareTo(tempNode.getData())<0){
                tempNode=tempNode.getLC();
            }else{
                tempNode=tempNode.getRC();
            }
        }

        //found place for our node ..so create our node and set its parent
        tempNode=new Node<T>(data);
        tempNode.setParent(parent);

        //fix  parent with our node
        if(parent==null){
            this.root=tempNode; // this is the 1st node we have inserted so no parent
        }else if(tempNode.getData().compareTo(parent.getData())<0){
            parent.setLC(tempNode);  //if our data is less than parent then node must be on left so set left side of parent
        }else{
            parent.setRC(tempNode);
        }

        splay(tempNode); //make newly inserted node -root

        this.size++;
    }

    //EXACTLY SAME as RED BLACK
    private void rightRotation(Node<T> node){
       // System.out.println("Rotating right on"+node);

        Node<T> tempLC=node.getLC();
        node.setLC(tempLC.getRC());
        if(node.getLC()!=null){
            node.getLC().setParent(node);
        }

        tempLC.setParent(node.getParent());
        if(tempLC.getParent()==null){
            this.root=tempLC;
        }else if(node==node.getParent().getLC()){
            node.getParent().setLC(tempLC);
        }else{
            node.getParent().setRC(tempLC);
        }

        tempLC.setRC(node);
        node.setParent(tempLC);

    }
    //EXACTLY SAME as RED BLACK
    private void leftRotation(Node<T>node){
       //System.out.println("Rotating to left on node "+node);

        Node<T> tempRC=node.getRC();
        node.setRC(tempRC.getLC());


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
    private void splay(Node<T>node){

        //make the passed node as root
        //while node does not become root..continue rotations
        while(node.getParent()!=null){

            //ZIG situation as only 2 nodes and no grandparent
            if(node.getParent().getParent()==null){
                if(node.getParent().getLC()==node){
                    rightRotation(node.getParent()); //node is located on left so rightrotation on parent
                }else{
                    leftRotation(node.getParent());
                }

            }//ZIG ZIG situation Condition 1:node is on left && Condition 2: parent is also on left
            else if(node.getParent().getLC()==node && node.getParent().getParent().getLC()==node.getParent()){
                //LL
                rightRotation(node.getParent().getParent()); //right rot on GP
                rightRotation(node.getParent()); //right rot on parent
            }else if(node.getParent().getRC()==node && node.getParent().getParent().getRC()==node.getParent()){
                //RR Symmetrical case
                leftRotation(node.getParent().getParent());
                leftRotation(node.getParent());

            }//ZIG ZAG Situation node on right & parent on left
            else if(node.getParent().getRC()==node && node.getParent().getParent().getLC()==node.getParent()){
                //LR
                leftRotation(node.getParent());
                rightRotation(node.getParent());
            }else{
                //RL
                rightRotation(node.getParent());
                leftRotation(node.getParent());
            }
        }
    }

    //SAME
    @Override
    public void traverse() {

        traverse(root);
    }
    private void traverse(Node node){
        if(node==null)return;

        traverse(node.getLC());
        System.out.println(node.getData());
        traverse(node.getRC());

    }

    @Override
    public Node<T> find(T data) {
        Node<T>tempNode=this.root;

        while(tempNode!=null){
            if(data.compareTo(tempNode.getData())<0){
                tempNode=tempNode.getLC();
            }else if(data.compareTo(tempNode.getData())>0){
                tempNode=tempNode.getRC();
            }else{
                splay(tempNode); //make it root
                return tempNode;
            }
        }
        return null;
    }

    public boolean isEmpty(){
        if(root==null)return true;
        else return false;
    }
    public int size(){
        return this.size;
    }

    //SAME
    @Override
    public T getMax() {
        if(root==null)return null;
        return getMax(root);
    }
    private T getMax(Node<T>node){
        if(node.getRC()!=null){
            return getMax(node.getRC());
        }else{
        return node.getData();}
    }

    public void printRoot(){
        System.out.println(this.root.getData());
    }
//SAME
    @Override
    public T getMin() {
        if(root==null)return null;
        return getMin(root);
    }
    private T getMin(Node<T>node){
        if(node.getLC()!=null){
            return getMin(node.getLC());
        }else {
            return node.getData();
        }

    }
}
