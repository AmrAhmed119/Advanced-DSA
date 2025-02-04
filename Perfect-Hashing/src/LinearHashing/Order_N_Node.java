package LinearHashing;

import UniversalPerfectHashing.UniversalHashingFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Order_N_Node {
    String[] elements;
    int[][] hash_function;
    Integer size = 0;
    Integer numOfCollisions;
    UniversalHashingFamily universalObject;

    public Order_N_Node() {
        this.numOfCollisions = 0;
    }

    public int hashing(ArrayList<String> collisions) {
        numOfCollisions = 0;
        int inserted = 0;
        collisions = removeDuplicates(collisions);
        int n = collisions.size() * collisions.size();
        if(n == 1) n = 2;
        boolean collision = true;
//        System.out.println("nnnnnnnnnn: " + n);
        universalObject = new UniversalHashingFamily(32, n);
        while (collision) {
            inserted = 0;
            collision = false;
            size = Integer.highestOneBit((n - 1) << 1);
            elements = new String[size];
            hash_function = universalObject.getrandomizedH();
            for (String val : collisions) {
                int index = universalObject.computeHash(val, hash_function);
//                if(index >= size){
//                    System.out.println("Problem");
//                    System.out.println("size of hash table = " + universalObject.b);
//                    System.out.println("rows = " + hash_function.length);
//                    System.out.println("Index = " + index);
//                    System.out.println("size = " + size);
//                }
//                index %= size;
                if (elements[index] == null) {
                    inserted++;
                    elements[index] = val;
                } else if (!elements[index].equals(val)) {
                    numOfCollisions++;
                    collision = true;
                    break;
                }
            }
        }

        return inserted;
    }

    private ArrayList<String> removeDuplicates(ArrayList<String> collisions) {
        ArrayList<String> newArray = new ArrayList<>();
        Collections.sort(collisions);

        for(String element : collisions) {
            if(newArray.size() == 0) newArray.add(element);
            else if(!newArray.get(newArray.size() - 1).equals(element)) {
                newArray.add(element);
            }
        }
        return newArray;
    }

    protected int get_b(int tableLength) {
        return (int) (Math.floor(Math.log(tableLength) / Math.log(2)));
    }
}
