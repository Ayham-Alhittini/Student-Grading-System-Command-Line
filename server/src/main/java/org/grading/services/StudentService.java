package org.grading.services;

import org.grading.model.StudentCourse;
import org.grading.model.Student;
import org.grading.data.dao.interfaces.IStudentCourseDao;
import org.grading.data.dao.interfaces.IStudentDao;

import java.util.List;

public class StudentService {

    private final IStudentCourseDao studentCourseDao;
    private final IStudentDao studentDao;
    public StudentService(IStudentDao studentDao, IStudentCourseDao studentCourseDao) {
        this.studentDao = studentDao;
        this.studentCourseDao = studentCourseDao;
    }

    public List<StudentCourse> getStudentCourses(int studentId) {
        return studentCourseDao.getStudentCourses(studentId);
    }

    public Student getStudentByEmail(String email) {
        return studentDao.getStudentByEmail(email);
    }

}
