package Sorting;

import Sorting.EfficientSort;
import Sorting.SortArray;
//
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSortTest {
    int[] readFile(String Path){
        int[] arr = new int[5];
        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
            String word = reader.readLine();
            String[] temp = word.split(",");
            arr = new int[temp.length];
            for(int i=0;i<arr.length;i++) {
                arr[i] = Integer.parseInt(temp[i]);
            }
        } catch (IOException e) {
            System.out.println("Please enter a valid file path");
        }
        return arr;
    }

    static final String PATH = "C:\\Users\\Adel\\Desktop\\6th term\\DS 2\\labs\\Data-Structure2\\Heap - Sorting Algorithms\\src\\Test_Cases";



    @Test
    void test0(){
        //Test against one element only
        String filePath = PATH+"\\oneElement.txt";
        int []inputArray = readFile(filePath);

        int []outArray = readFile(filePath);
        new SimpleSort().bubbleSort(inputArray,0);
        assertArrayEquals(outArray, inputArray);
    }

    @Test
    void test1(){
        //Test correctness under 100 integers with average case with positive values
        String filePath = PATH+"\\in100\\in100_average.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in100\\in100_best.txt";
        int []outArray = readFile(filePath);
        new SimpleSort().bubbleSort(inputArray,0);
        assertArrayEquals(outArray, inputArray);
    }

    @Test
    void test2(){
        //Test correctness under 100 integers with worst case with positive values
        String filePath = PATH+"\\in100\\in100_worst.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in100\\in100_best.txt";
        int []outArray = readFile(filePath);
        new SimpleSort().bubbleSort(inputArray,0);
        assertArrayEquals(outArray, inputArray);
    }

    @Test
    void test3(){
        //average case under a very large input with positive values
        String filePath = PATH+"\\in100000\\in100000_average.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in100000\\in100000_best.txt";
        int []outArray = readFile(filePath);
        new SimpleSort().bubbleSort(inputArray,0);
        assertArrayEquals(outArray, inputArray);
    }

    @Test
    void test4(){
        //worst case on a very large input file with positive valuse
        String filePath = PATH+"\\in100000\\in100000_worst.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in100000\\in100000_best.txt";
        int []outArray = readFile(filePath);
        new SimpleSort().bubbleSort(inputArray,0);
        assertArrayEquals(outArray, inputArray);
    }

    @Test
    void Test5(){
        //mix of negative and positive numbers small input file(10_000)
        String filePath = PATH+"\\in_neg10000\\in_neg10000_worst.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in_neg10000\\in_neg10000_best.txt";
        int []outArray = readFile(filePath);
         new SimpleSort().bubbleSort(inputArray,0);
        assertArrayEquals(outArray, inputArray);
    }

    @Test
    void Test6(){
        //mix of negative and positive numbers large input file(100_000)
        String filePath = PATH+"\\in_neg100000\\in_neg100000_worst.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in_neg100000\\in_neg100000_best.txt";
        int []outArray = readFile(filePath);
         new SimpleSort().bubbleSort(inputArray,0);
        assertArrayEquals(outArray, inputArray);
    }
}