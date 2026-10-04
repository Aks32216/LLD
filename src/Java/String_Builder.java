package Java;

public class String_Builder {
    public static void main(String[] args) {
        // StringBuilder and StringBuffer are mutable in nature, but earlier one is not thread safe
        StringBuilder sb=new StringBuilder("java");
        sb.setCharAt(1,'e');
        System.out.println(sb);
        System.out.println(sb.capacity());
        System.out.println(sb.length());
        StringBuilder sb2 = new StringBuilder(50);
        sb2.append("amish");
        System.out.println(sb2);

    }
}
