import java.util.*;
import java.io.*;

class ArraysHelper {
    public String getUserInput(String promt) {
        String inputLine = null;
        System.out.println( promt + " ");
        try {
            BufferedReader is = new BufferedReader(
                new InputStreamReader(System.in));
                inputLine = is.readLine();
                if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println( "IOException: " + e);
        }
        return inputLine;
    }
}


class TwoArraysMethod{
    public static int[] mergeArrays(int[] a1, int[] a2) {
    int [] a3 = new int [a1.length + a2.length];
    int z = 0;
    int z1 = 0;
    int z2 = 0;
    if (a1 == null) 
        return a2;
    if (a2 == null) 
        return a1;
    for ( int y = 0; y <  a2.length; y++) {
            for ( int x = z1; x < a1.length; x++) {
                if (a1[x] <= a2[y]) { 
                    a3[z] = a1[x]; 
                    z = z + 1;
                    z1 = z1 +1;
                } else { 
                    a3[z] = a2[y];
                    z = z + 1;
                    z2 = z2 + 1;
                 break;  
                } 
            }  
    }  
    for (int x = z1; x < a1.length; x++) {
           a3[z] = a1[x];
           z = z + 1; 
    }        
    for ( int y = z2; y <  a2.length; y++) {
           a3[z] = a2 [y];
           z = z + 1;
    }      
    return a3;
    }
}

    class TwoArrays {
        public static void main(String[] args) { 
           ArraysHelper helper = new ArraysHelper();
           TwoArraysMethod t = new TwoArraysMethod();
           String userinput1 = helper.getUserInput("Введите числа для первого массива. Всe числа вводятся через пробел, в порядке их возрастания");
           String userinput2 = helper.getUserInput("Введите числа для второго массива. Все числа вводятся через пробел, в порядке их возрастания");
           int[] numArr1 = Arrays.stream(userinput1.split(" ")).mapToInt(Integer::parseInt).toArray();
           int[] numArr2 = Arrays.stream(userinput2.split(" ")).mapToInt(Integer::parseInt).toArray();
           int[] result = t.mergeArrays(numArr1, numArr2);
           System.out.println(Arrays.toString(result));
        }
    }       









