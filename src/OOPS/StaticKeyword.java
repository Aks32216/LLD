package OOPS;


class parent {
    static int p1=54;
    static int p2;
    static int p3;

    static {
        p2=19;
    }

    static {
        System.out.println("Parent static executing");
    }

    {
        System.out.println("Initializer"+p1+" "+p2+" "+p3);
    }

    parent(){
        System.out.println("parent created"+p1+" "+p2+" "+p3);

    }
}

class child extends parent {
    static int v=14;
    static int c;
    static int d;

    static {
        c=19;
    }

    static {
        System.out.println("Child creating static");
    }

    {
        System.out.println("Initializer"+v+" "+c+" "+d);
    }

    child(){
        System.out.println("Child created"+v+" "+c+" "+d);
    }

}

public class StaticKeyword {

    static  {
        System.out.println("Inside main static");
    }

    {
        System.out.println("Inside main block");
    }

    public static void main(String[] args) {
        System.out.println("Starting main");
//        parent p=new parent();
//        child c=new child();
        System.out.println(parent.p1);
        System.out.println("Ending main");
        StaticKeyword st=new StaticKeyword();
    }
}
