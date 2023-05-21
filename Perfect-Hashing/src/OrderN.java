import java.util.ArrayList;
import java.util.Objects;

public class OrderN extends PerfectHashing implements IPerfectHashing {

    private ArrayList<Integer>[] collisions;
    private Order_N_Node[] hashTable;

    public OrderN(int length) {
        hashTable = new Order_N_Node[length];
        collisions = new ArrayList[length];
        for(int i=0;i<length;i++) {
            collisions[i] = new ArrayList<Integer>();
        }
        for(int i=0;i<length;i++) {
            hashTable[i] = new Order_N_Node();
            hashTable[i].universalObject = new UniversalHashingFamily(32,get_b(1));
            hashTable[i].hash_function = hashTable[i].universalObject.getrandomizedH();
            hashTable[i].elements = new Integer[1];
        }
        universalHashingFamily = new UniversalHashingFamily(32, get_b(length));
        hashing_matrix = universalHashingFamily.getrandomizedH();
    }

    public boolean insert(int key) {
        if(search(key))
            return false;

        int index_in_first_level = universalHashingFamily.HF(key, hashing_matrix);
        Order_N_Node node = hashTable[index_in_first_level];
        collisions[index_in_first_level].add(key);

        int index_in_second_level = node.universalObject.HF(key, node.hash_function);
        if(node.elements[index_in_second_level] == null){
            node.elements[index_in_second_level] = key;
        } else {
            rehash_second_level(collisions[index_in_first_level],hashTable[index_in_first_level]);
        }
        return true;
    }

    public int[] batchInsert(int[] arr) {
       firstLevelHashing(arr);

        int inserted = 0;
        for(int i=0;i<hashTable.length;i++) {
            Order_N_Node node = hashTable[i];
            if(collisions[i].size() > 0) {
                node.size = collisions[i].size() * collisions[i].size();
                node.elements = new Integer[node.size];
                node.universalObject = new UniversalHashingFamily(32,get_b(node.size));
                node.hash_function = hashTable[i].universalObject.getrandomizedH();
                for(Integer val : collisions[i]) {
                    if(search(val)) continue;
                    int index_in_second_level = node.universalObject.HF(val, node.hash_function);
                    if(node.elements[index_in_second_level] == null){
                        inserted++;
                        node.elements[index_in_second_level] = val;
                    } else {
                        rehash_second_level(collisions[i], node);
                    }
                }
            }


        }
        return new int[]{inserted,arr.length - inserted};
    }


    public int[] batchDelete(int[] arr) {
        int deleted = 0;
        for(int val : arr) {
            if(delete(val)) deleted++;
        }
        return new int[]{deleted, arr.length - deleted};
    }

    public boolean delete(int key) {
        if(!search(key))
            return false;
        int index = universalHashingFamily.HF(key, hashing_matrix);
        Order_N_Node node = hashTable[index];
        int index_in_second_level = node.universalObject.HF(key, node.hash_function);
        node.elements[index_in_second_level] = null;
        return true;
    }

    public boolean search(int key) {
        int index = universalHashingFamily.HF(key, hashing_matrix);
        Order_N_Node node = hashTable[index];
        int index_in_second_level = node.universalObject.HF(key, node.hash_function);
        return Objects.equals(node.elements[index_in_second_level], (Integer) key);
    }

    public int printHTable() {
        int count = 0;
        for (Order_N_Node node : hashTable) {
            for(Integer val : node.elements) {
                if (val != null) count++;
//                System.out.print(val + " ");
            }
//            System.out.println();
        }
        return count;
    }

    private void firstLevelHashing(int[] arr) {
        for(int value : arr) {
            int index = universalHashingFamily.HF(value, hashing_matrix);
            index %= hashTable.length;
            collisions[index].add(value);
        }
    }
    public void rehash_second_level(ArrayList<Integer> collisions, Order_N_Node node) {
        int n = collisions.size();
        node.size = n * n;
        node.elements = new Integer[n * n];
        node.universalObject = new UniversalHashingFamily(32,get_b(node.size));
        node.hash_function = node.universalObject.getrandomizedH();
        for(Integer val : collisions) {
            int index = node.universalObject.HF(val, node.hash_function);
            if(node.elements[index] == null){
                node.elements[index] = val;
//            } else { // this shouldn't happen
//                node.numOfCollisions++;
//                rehash_second_level(collisions,node);

            }
        }

    }
}
