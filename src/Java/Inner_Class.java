package Java;


class Outer{
    private String name;

    Outer(String name){
        this.name=name;
    }

    public void outerMethod(){
        System.out.println("Outer method");
    }

    public String getName(){
        return name;
    }

    class Inner{
        private String name;
        private int age;
        Inner(String name,int age){
            this.name=name;
            this.age=age;
        }

        public void innerMethod(){
            System.out.println("Inner Method");
        }
        public void setParentName(String name){
            Outer.this.name=name;
        }
    }
}

public class Inner_Class {
    public static void main(String[] args) {
        Outer outer=new Outer("amish");
        Outer.Inner inner = outer.new Inner("Rashmi",20);
        inner.setParentName("Anshuma");
        System.out.println(outer.getName());
    }
}
