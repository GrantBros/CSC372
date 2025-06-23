package stuselsort;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
/*
 * Modular piece of sorting App for execution
 * manually enter names and info by user for student objects. Rollno automatically increments (changeable)
 * sorting by name first then Rollno to show it works
 * 
 */

public class StudentSortApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int rollCounter = 1;

        System.out.println("Enter details for 10 students:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Enter name for student " + rollCounter + ": ");
            String name = scanner.nextLine();

            System.out.print("Enter address for student " + rollCounter + ": ");
            String address = scanner.nextLine();

            students.add(new Student(rollCounter, name, address));
            rollCounter++;
            System.out.println();
        }

        // Sort by student name
        System.out.println("\nSorting by Name:");
        StudentSorter.selectionSort(students, new NameComparator());
        for (Student s : students) {
            System.out.println(s);
        }

        // Sort by RollNo
        System.out.println("\nSorting by Roll Number:");
        StudentSorter.selectionSort(students, new RollNoComparator());
        for (Student s : students) {
            System.out.println(s);
        }
        //no memory leaks allowed
        scanner.close();
    }
}