package org.grading.dtos;

import java.io.Serializable;
import org.grading.model.Student;

public class StudentDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String email;
    private String name;

    public StudentDto(Student student) {
        this(student.getId(), student.getEmail(), student.getName());
    }

    public StudentDto(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
