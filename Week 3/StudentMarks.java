import java.util.ArrayList;
import java.util.Scanner;

public class StudentMarks {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> marks = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        // Store marks
        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks of student " + (i + 1) + ": ");
            marks.add(sc.nextInt());
        }

        // Find highest and lowest
        int highest = marks.get(0);
        int lowest = marks.get(0);

        for (int mark : marks) {
            if (mark > highest) {
                highest = mark;
            }

            if (mark < lowest) {
                lowest = mark;
            }
        }

        System.out.println("\nStudent Marks: " + marks);
        System.out.println("Highest Mark: " + highest);
        System.out.println("Lowest Mark: " + lowest);

        sc.close();
    }
}