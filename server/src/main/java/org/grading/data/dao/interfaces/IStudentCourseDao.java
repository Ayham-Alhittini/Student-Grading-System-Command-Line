package org.grading.data.dao.interfaces;

import org.grading.model.StudentCourse;

import java.util.List;

public interface IStudentCourseDao {
    List<StudentCourse> getStudentCourses(int studentId);
    List<Integer> getCourseMarks(int courseId);
    boolean verifyStudentCourseEnrollment(int studentId, int courseId);
}
