package Java;

public class String_Builder {
    public static void main(String[] args) {
        // StringBuilder and StringBuffer are mutable in nature, but earlier one is not thread safe
        StringBuilder sb=new StringBuilder("java");
        sb.setCharAt(1,'e');
        System.out.println(sb);
        System.out.println(sb.capacity());
        System.out.println(sb.length());
//        StringBuilder sb2 = new StringBuilder(50);
//        sb2.append("amish");
//        System.out.println(sb2);
        String s1="java";
        StringBuilder sb2=new StringBuilder("java");
//        System.out.println(s1==sb2);
//        System.out.println(s1==sb);
        System.out.println(sb==sb2);
        System.out.println(s1.equals(sb2));
        System.out.println(s1.equals(sb));
        System.out.println(sb.equals(sb2));

    }
}
