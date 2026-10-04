package Java;

/*
Strings are sequence of characters

*/

import java.util.Arrays;

public class Java_Strings {
    public static void main(String[] args) {
//        String s1="Hello";
//        s1.concat(" Word" ); // new String is returned
//        System.out.println(s1); // stays Hello only. values does not change
//        String s2="Hello";
//        String s3=new String("Hello");
//        System.out.println(s1==s2);
//        System.out.println(s1.equals(s2));
//        System.out.println(s1==s3);
//        System.out.println(s1.equals(s3));
//        String s4="Hello";
//        String s5=s4+" World";
//        String s6="Hello World";
//        System.out.println(s5==s6);

         String s1=new String("amish");
         System.out.println(s1);
        System.out.println(s1.length());
        System.out.println(s1.isBlank()); // is string blank
        System.out.println(s1.isEmpty()); // is string empty
        System.out.println(s1.substring(0,4));

    }
}
