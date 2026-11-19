package Java;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student2 implements Comparable<Student2>{
    String name;
    int rollNo;
    int marks;

    Student2(String name,int rollNo,int marks){
        this.name=name;
        this.marks=marks;
        this.rollNo=rollNo;
    }

    @Override
    public int compareTo(Student2 o) {
        return this.marks-o.marks;
    }
}

class sortByName implements Comparator<Student2> {
    @Override
    public int compare(Student2 o1, Student2 o2) {
        return o1.name.compareTo(o2.name);
    }
}

class sortByRoll implements Comparator<Student2> {
    @Override
    public int compare(Student2 o1, Student2 o2) {
        return o1.rollNo-o2.rollNo;
    }
}

class sortByMarks implements Comparator<Student2> {
    @Override
    public int compare(Student2 o1, Student2 o2) {
        return o1.marks-o2.marks;
    }
}

public class lambdas  {
    public static void main(String[] args) {
        List<Student2> st=new ArrayList<>();
        st.add(new Student2("Amish",12,76));
        st.add(new Student2("Rashmi",11,83));
        st.add(new Student2("Ashish",26,82));
        st.add(new Student2("Priya",9,96));
        st.add(new Student2("Kishan",25,92));
        st.add(new Student2("Ritu",21,89));

//        Collections.sort(st);

//        for(Student2 student:st){
//            System.out.println(student.name+" "+student.rollNo+" "+student.marks);
//        }

        Comparator<Student2> c1=new sortByName();
        Comparator<Student2> c2=new sortByMarks();
        Comparator<Student2> c3=new sortByRoll();

        System.out.println("--------------------------------------------------------");
        Collections.sort(st,c1);
        for(Student2 student:st){
            System.out.println(student.name+" "+student.rollNo+" "+student.marks);
        }

        System.out.println("--------------------------------------------------------");
        Collections.sort(st,c2);
        for(Student2 student:st){
            System.out.println(student.name+" "+student.rollNo+" "+student.marks);
        }

        System.out.println("--------------------------------------------------------");
        Collections.sort(st,c3);
        for(Student2 student:st){
            System.out.println(student.name+" "+student.rollNo+" "+student.marks);
        }

        System.out.println("--------------------------------------------------------");
        Collections.sort(st,(Student2 a,Student2 b) -> {
            if(a.marks==b.marks){
                return b.name.compareTo(a.name);
            } else {
                return a.marks-b.marks;
            }
        });

        for(Student2 student:st){
            System.out.println(student.name+" "+student.rollNo+" "+student.marks);
        }

    }
}
