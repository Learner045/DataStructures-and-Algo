package AVL;

public class Test {

    public static void main(String args[]){

        AvlTree tree=new AvlTree();
        tree.insert(10);
        tree.insert(15);
        tree.insert(12);
        tree.insert(4);
        tree.insert(10);
        tree.insert(20);
        tree.insert(60);
        tree.insert(80);
        tree.insert(100);
        System.out.println(tree.height()+"height");


     tree.traverse();

     //if we insert 1 then 2 --it will check no violation
        //now we enter 3 so it will insert 3 then return to statements to chekc violation for 2
        //but 2 has only 1 RC and no LC so no violation
        //so it will return to 1
        //again it will check violation...now there's violation
    }
}
