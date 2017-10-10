public class MainTest {

    public static void main(String args[]){
        List<Integer>list=new LinkedList<>();
        list.insert(10);
        list.insert(20);
        list.insert(5);
        list.insert(8);

        System.out.println("Printing size"+list.getSize());
      //  System.out.println(list.getSize());

        System.out.println("Printing list....................");
        list.printList();

        list.remove(5);

        System.out.println("Printing list..................");
        list.printList();

        list.remove(20);

        System.out.println("Printing list..................");
        list.printList();

        System.out.println("Printing size"+list.getSize());
      //  System.out.println(list.getSize());
    }
}