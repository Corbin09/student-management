package com.university.app;

import com.university.exception.InvalidStudentDataException;
import com.university.repository.InMemoryStudentRepository;
import com.university.service.StudentFileService;
import com.university.service.StudentService;

import java.util.Scanner;

public class App {

    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {

        StudentService service =
                new StudentService(new InMemoryStudentRepository());

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    
                    ==== STUDENT MANAGEMENT ====
                    1. Add student
                    2. Search student
                    3. Top students
                    4. Average GPA
                    5. Export CSV
                    6. Heavy Load Test
                    7. Exit
                    """);

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> add(sc, service);
                    case 2 -> search(sc, service);
                    case 3 -> service.getTopStudents(3).forEach(System.out::println);
                    case 4 -> System.out.println("Average GPA: " + service.getAverageGPA());
                    case 5 -> {
                        try {
                            StudentFileService.exportToCSV(service.getAllStudents());
                            System.out.println("✅ Exported to students_export.csv");
                        } catch (Exception e) {
                            System.err.println("❌ Export failed: " + e.getMessage());
                        }
                    }
                    case 6 -> {
                        System.out.println("⚙️ Running heavy load test...");
                        service.heavyLoadTest();
                        System.out.println("✅ Heavy load test started (5 threads, 10.000 students)");
                    }
                    case 7 -> System.exit(0);
                    default -> System.out.println("Invalid option");
                }
            } catch (InvalidStudentDataException e) {
                System.out.println(RED + "ERROR: " + e.getMessage() + RESET);
            }
        }
    }

    private static void add(Scanner sc, StudentService service) {
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("GPA: ");
        double gpa = sc.nextDouble();

        service.addStudent(id, name, email, gpa);
        System.out.println("Student added!");
    }

    private static void search(Scanner sc, StudentService service) {
        System.out.print("Search name: ");
        String name = sc.nextLine();
        service.searchStudents(name).forEach(System.out::println);
    }
}
