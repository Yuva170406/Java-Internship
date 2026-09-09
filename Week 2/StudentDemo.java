class Student {

    String name;
    int rollNo;
    String department;
    int age;

    Student(String name, int rollNo, String department, int age) {
        this.name = name;
        this.rollNo = rollNo;
        this.department = department;
        this.age = age;
    }

    void displayInfo() {
        System.out.println("Student Name : " + name);
        System.out.println("Roll Number  : " + rollNo);
        System.out.println("Department   : " + department);
        System.out.println("Age          : " + age);
    }
}

public class StudentDemo {
    public static void main(String[] args) {

        Student s1 = new Student("Yuvashri", 101, "CSE", 21);

        s1.displayInfo();
    }
}