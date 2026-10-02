package Java;

import java.util.Objects;

public class same_objects {
    public static void main(String[] args) {
        Student s1=new Student("Amish","42506",25);
        Student s2=new Student("Amish","42506",25);

        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
    }
}

class Student{
    String name;
    String id;
    int age;

    Student(String name,String id,int age){
        this.age=age;
        this.id=id;
        this.name=name;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            // if passed same object s1.equals(s1)
            return true;
        }
        if(obj==null || getClass() != obj.getClass()){
            // they belong to different class
            return false;
        }
        Student temp=(Student) obj;
        return temp.age==age
                && Objects.equals(temp.name,name)
                && Objects.equals(temp.id,id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name,id,age);
    }
}
