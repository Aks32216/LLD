package Java;

/*
Strings are sequence of characters

*/

public class Java_Strings {
    public static void main(String[] args) {
        String s1="Hello";
//        s1.concat(" Word" ); // new String is returned
        System.out.println(s1); // stays Hello only. values does not change
        String s2="Hello";
        String s3=new String("Hello");
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1==s3);
        System.out.println(s1.equals(s3));
        String s4="Hello";
        String s5=s4+" World";
        String s6="Hello World";
        System.out.println(s5==s6);
    }
}
