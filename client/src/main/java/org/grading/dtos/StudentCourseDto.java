package org.grading.dtos;
import java.io.Serializable;

public class StudentCourseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int courseId;
    private String courseName;
    private int mark;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}