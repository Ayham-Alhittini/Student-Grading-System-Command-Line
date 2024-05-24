package org.grading;

import org.grading.data.dao.implementations.CourseDao;
import org.grading.data.dao.implementations.StudentCourseDao;
import org.grading.data.dao.implementations.StudentDao;
import org.grading.model.Student;
import org.grading.model.StudentCourse;
import org.grading.model.CourseDetails;
import org.grading.data.dao.interfaces.ICourseDao;
import org.grading.data.dao.interfaces.IStudentCourseDao;
import org.grading.data.dao.interfaces.IStudentDao;
import org.grading.services.AuthenticationService;
import org.grading.services.CourseService;
import org.grading.services.StudentService;
import org.grading.dtos.*;
import org.grading.socket.SocketConnection;
import org.grading.utilities.Action;

import java.util.List;

public class ServerRouter {

    private static AuthenticationService authenticationService;
    private static CourseService courseService;
    private static StudentService studentService;

    private static SocketConnection socketConnection;

    private static StudentDto loggedStudent = null;

    private static void init() {

        IStudentCourseDao studentCourseDao = new StudentCourseDao();
        IStudentDao studentDao = new StudentDao();
        ICourseDao courseDao = new CourseDao();

        authenticationService = new AuthenticationService(studentDao);
        courseService = new CourseService(courseDao, studentCourseDao);
        studentService = new StudentService(studentDao, studentCourseDao);

        socketConnection = new SocketConnection();
    }

    public static void main(String[] args) {

        init();

        boolean running = true;

        while (running) {
            Action clientAction = (Action) socketConnection.receiveObjectFromClient();

            switch (clientAction) {
                case LOGIN -> handleAuthenticationInServer();

                case GET_STUDENT_COURSES -> handleStudentCoursesMarkInServer();

                case GET_COURSE_DETAILS -> handleCourseDetailsInServer();

                default -> { socketConnection.closeConnection() ; running = false;}
            }
        }

    }

    private static void handleAuthenticationInServer() {
        String email = (String) socketConnection.receiveObjectFromClient();
        String password = (String) socketConnection.receiveObjectFromClient();

        String credentials = "["  + email + ", " + password + "]";

        System.out.println("Student with credentials: " + credentials + " trying to login");
        
        if (authenticationService.isValidCredentials(email, password)) {

            Student student = studentService.getStudentByEmail(email);
            loggedStudent = new StudentDto(student);
            System.out.println(loggedStudent.getName() +  " login successfully.");

        } else {
            System.out.println("invalid credentials " + credentials);
        }

        socketConnection.sendObjectToClient(loggedStudent);
    }

    private static void handleStudentCoursesMarkInServer() {
        List<StudentCourse> courses = studentService.getStudentCourses(loggedStudent.getId());

        List<StudentCourseDto> courseDtos = courses
                .stream()
                .map(StudentCourseDto::new)
                .toList();

        socketConnection.sendObjectToClient(courseDtos);
        System.out.println(loggedStudent.getName() + " get his/her courses mark");
    }

    private static void handleCourseDetailsInServer() {
        int courseId = (int)socketConnection.receiveObjectFromClient();

        if (courseService.isStudentEnrolledInCourse(loggedStudent.getId(), courseId)) {

            CourseDetails courseDetails = courseService.getCourseDetails(courseId);
            socketConnection.sendObjectToClient(new CourseDetailsDto(courseDetails));

            System.out.println(loggedStudent.getName() + " show details for course with id " + courseId);
        } else {
            socketConnection.sendObjectToClient(null);
        }
    }

}