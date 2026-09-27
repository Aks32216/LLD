package OOPS;

class A{
    void greet(){
        System.out.println("Greeting in parent");
    }
}

class B extends A{
    void greet(){
        super.greet();
    }
}

class C extends A{
    void nonGreet(){
        greet();
    }
}

public class inheritance {
    public static void main(String[] args) {
        A a=new A();
        B b=new B();
        C c=new C();

        a.greet();
        b.greet();
        c.greet();
    }
}
