import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
//        CLI cli = new CLI();
//        cli.start();
        Generation generation = new Generation();
        Integer []arr = generation.generateDistinctArray(1000000);
////        Integer[] arr = {1, 5};
        OrderN orderN = new OrderN(arr.length);
////        for(int i : arr) {
////            orderN.insert(i);
////        }
        int[] arrr = new int[arr.length];
        for(int i=0;i<arr.length;i++) arrr[i] = (int)arr[i];
//        for(int a : arrr) {
//            orderN.insert(a);
//        }
        orderN.batchInsert(arrr);
        orderN.insert(2);
        boolean a = orderN.search(2);
        if(a) System.out.println("found");
        int x = orderN.printHTable();
        System.out.println("test " + x);
//        System.out.println(Arrays.toString(arr));
//        ArrayList<Integer> list = new ArrayList<>();
//        for(int i : arr) list.add(i);
//        orderN.hashing(list);
////        orderN2.delete(5);
////        orderN.printTable();
//        System.out.println();
//        orderN.rehashingNum();

//        System.out.println("collision = " + orderN.rehashingNum(););

//            OrderN2 orderN2 = new OrderN2(arr);
//            orderN2.hashValues(arr);
//            System.out.println(orderN2.printHTable());
//            System.out.println("Number of collisions =  " + orderN2.getNumberOfCollisions() );
//        System.out.println(Arrays.deepToString(orderN2.hashing_matrix));
//        UniversalHashingFamily u = new UniversalHashingFamily(4,3);
//        System.out.println(Arrays.deepToString(u.hashing_matrix));
//        int x = 7; // Example key
//        int hashValue = u.HF(x);
//
//        System.out.println("Hash value for key " + x + ": " + hashValue);
    }
}