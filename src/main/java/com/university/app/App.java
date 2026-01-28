package com.university.app;

import com.university.exception.InvalidStudentDataException;
import com.university.repository.InMemoryStudentRepository;
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
                    5. Exit
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
                    case 5 -> System.exit(0);
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
