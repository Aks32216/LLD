package Structural_Design_Pattern.Composite_Design_Pattern;

import java.util.ArrayList;
import java.util.List;

interface OrgComponent{
    int getSalary();
    int getHeadcount();
    void printHierarchy(String indent);
}

class Employee implements OrgComponent{

    private final String name;
    private String title;
    private int salary;

    Employee(String name,String title,int salary){
        this.name=name;
        this.title=title;
        this.salary=salary;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public int getHeadcount() {
        return 1;
    }

    @Override
    public void printHierarchy(String indent) {
        System.out.println(indent + "- " + name + " (" + title + ", $" + salary + ")");
    }
}

class Manager implements OrgComponent{
    private final String name;
    private String title;
    private int salary;
    private List<OrgComponent> members= new ArrayList<>();

    public Manager(String name, String title, int salary) {
        this.name = name;
        this.title = title;
        this.salary = salary;
    }

    public void addMember(OrgComponent member){
        members.add(member);
    }

    public void removeMember(OrgComponent member){
        members.remove(member);
    }

    @Override
    public int getSalary() {
        int total = salary;
        for (OrgComponent member : members) {
            total += member.getSalary();
        }
        return total;
    }

    @Override
    public int getHeadcount() {
        int count = 1;
        for (OrgComponent member : members) {
            count += member.getHeadcount();
        }
        return count;
    }

    @Override
    public void printHierarchy(String indent) {
        System.out.println(indent + "+ " + name + " (" + title + ", $" + salary + ")");
        for (OrgComponent member : members) {
            member.printHierarchy(indent + "  ");
        }
    }
}

public class Organization_Structure {
    public static void main(String[] args) {
        Employee dev1 = new Employee("Alice", "Senior Engineer", 120000);
        Employee dev2 = new Employee("Bob", "Engineer", 95000);
        Employee dev3 = new Employee("Charlie", "Engineer", 90000);
        Employee designer = new Employee("Diana", "Designer", 100000);

        Manager techLead = new Manager("Eve", "Tech Lead", 140000);
        techLead.addMember(dev1);
        techLead.addMember(dev2);

        Manager vpEng = new Manager("Frank", "VP Engineering", 200000);
        vpEng.addMember(techLead);
        vpEng.addMember(dev3);

        Manager vpProduct = new Manager("Grace", "VP Product", 190000);
        vpProduct.addMember(designer);

        Manager ceo = new Manager("Hank", "CEO", 300000);
        ceo.addMember(vpEng);
        ceo.addMember(vpProduct);

        System.out.println("---- Organization Chart ----");
        ceo.printHierarchy("");

        System.out.println("\nTotal Payroll: $" + ceo.getSalary());
        System.out.println("Total Headcount: " + ceo.getHeadcount());
        System.out.println("\nEngineering Payroll: $" + vpEng.getSalary());
        System.out.println("Engineering Headcount: " + vpEng.getHeadcount());
    }
}
