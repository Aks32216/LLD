package OOPS;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void swap(Pair p){
        int temp=p.a;
        p.a=p.b;
        p.b=temp;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a=scn.nextInt();
        int b=scn.nextInt();
        Pair p = new Pair(a,b);
        swap(p);
        System.out.println(p);
        System.out.println(p.a);
        System.out.println(p.b);
    }
}

class Pair{
    int a;
    int b;
    Pair(int a,int b){
        this.a=a;
        this.b=b;
    }

    @Override
    public String toString(){
        return a+" "+b;
    }
}
