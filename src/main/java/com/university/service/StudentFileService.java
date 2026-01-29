package com.university.service;

import com.university.model.Student;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class StudentFileService {

    private static final Path EXPORT_PATH = Paths.get("students_export.csv");

    // ===== EXPORT =====
    public static void exportToCSV(Collection<Student> students) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id,name,email,gpa");

        for (Student s : students) {
            lines.add(String.join(",",
                    s.getId(),
                    s.getName(),
                    s.getEmail(),
                    String.valueOf(s.getGpa())
            ));
        }

        Files.write(EXPORT_PATH, lines,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    // ===== IMPORT =====
    public static List<Student> importFromCSV(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        List<Student> students = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) { // skip header
            String[] parts = lines.get(i).split(",");
            students.add(new Student(
                    parts[0],
                    parts[1],
                    parts[2],
                    Double.parseDouble(parts[3])
            ));
        }
        return students;
    }
}
