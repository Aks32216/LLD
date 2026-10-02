package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Input_Output {
    public static void main(String[] args) throws IOException {
//        System.out.println("Hello");
//        System.err.println("Error");
//
//        int c=0;
//        while(c!='\n'){
//            c=System.in.read();
//            System.out.print((char)c);
//        }
//        System.out.println();

//        InputStreamReader isr = new InputStreamReader(System.in);
//        BufferedReader br= new BufferedReader(isr);
//
//        String name=br.readLine();
//        System.out.println(name);

        Scanner scn = new Scanner(System.in);

        int n=scn.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;++i){
            arr[i]=scn.nextInt();
        }
        System.out.println(Arrays.toString(arr));

    }
}
