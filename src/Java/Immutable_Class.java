package Java;

/*
Immutable classes are classes whose variables and methods cannot be changes.

Rules to make class immutable:
 - Mark class as final
 - mark all instance variables as private and final
 - No setters method
 - For Non Prmititves as it will hold references:
     - either make the other class aslo immutable or
        - defensive copy in constructor + getter i,e never initialze or send direct references, use new


 */

class College{
    String name;
    String address;

    College(String name,String address){
        this.name=name;
        this.address=address;
    }
}

// Immutable class
final class Student1{
    private final int age;
    private final String name;
    private final College college;

    Student1(int age,String name,College college){
        this.age=age;
        this.name=name;
        this.college=new College(college.name,college.address);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public College getCollege(){
        return new College(college.name, college.address);
    }
}

// Not allowed as class is final
//class CSEStudent extends Student1{
//
//}


public class Immutable_Class {
    public static void main(String[] args) {
        College c1=new College("TIU","Salt Lake");
        Student1 s1=new Student1(1,"amish",c1);
        System.out.println(s1.getAge());
        System.out.println(s1.getName());
        s1.getCollege().name="IIT B";
        System.out.println(s1.getCollege().name);
    }
}
