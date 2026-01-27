package com.university.app;

import com.university.repository.InMemoryStudentRepository;
import com.university.service.StudentService;
import com.university.model.Student;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        StudentService studentService =
                new StudentService(new InMemoryStudentRepository());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> addStudent(scanner, studentService);
                    case 2 -> listStudents(studentService);
                    case 3 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addStudent(Scanner scanner, StudentService service) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("GPA: ");
        double gpa = scanner.nextDouble();

        service.addStudent(id, name, email, gpa);
        System.out.println("Student added successfully!");
    }

    private static void listStudents(StudentService service) {
        List<Student> students = service.listStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        students.forEach(System.out::println);
    }
}
