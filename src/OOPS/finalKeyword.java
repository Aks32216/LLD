package OOPS;

class dummy{
    final int x;
    static final int PI;

    static {
        PI=10; // has to be instantiated during declaration/static block else compilation error
    }

    dummy(){
        x=10; // has to be initilized by constructor/instance initalizatin blcok/during declaration
    }

    final void dummyMethod(){
        System.out.println("Dummy Method");
    }
}

final class dummyChild extends dummy{ //
    dummyChild(){
        super();
    }

//    void dummyMethod(){ // error cannot override a final method
//        System.out.println("Dummy child method");
//    }
}

//class dummyChildChild extends dummyChild{ // erro cannot extend final class
//
//}

public class finalKeyword {

    public static void main(String[] args) {
        dummy d=new dummy();
        System.out.println(d);

        final int k;
        k=10;
        System.out.println(k);
    }
}
