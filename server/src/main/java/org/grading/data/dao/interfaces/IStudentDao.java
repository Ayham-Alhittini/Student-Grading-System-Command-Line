package org.grading.data.dao.interfaces;

import org.grading.model.Student;

public interface IStudentDao {
    Student getStudentByEmail(String email);
}
