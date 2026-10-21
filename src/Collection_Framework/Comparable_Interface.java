package Collection_Framework;

/*
for prmitives + string, comparing two values is easier due
to logical ordering or lexicographical ordering

But for user defined datatypes like classes this is a complex operation.

JAVA has comparable interface to provide comparison b/w user defined data types

Any class implementing comparable interface tells that
i know how to compare my self with another object of same type

compare to returns 3 values
- -ve -> 1st is less than second
- +ve -> 2nd is less than first
= 0 -> both are equal and their equals must also result to true (this should be maintained by dev)
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Student implements Comparable<Student>{
    String name;
    int marks;

    Student(String name,int marks){
        this.name=name;
        this.marks=marks;
    }

    @Override
    public int compareTo(Student other) {
        if(this.marks==other.marks){
            return this.name.compareTo(other.name);
        } else {
            return this.marks-other.marks;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}

public class Comparable_Interface {
    public static void main(String[] args) {
        List<Student> studentList=new ArrayList<>();
        studentList.add(new Student("Amish",92));
        studentList.add(new Student("Rnshuman",89));
        studentList.add(new Student("Anshuman",89));
        studentList.add(new Student("Arman",76));

        Collections.sort(studentList);
        System.out.println(studentList);
    }
}
