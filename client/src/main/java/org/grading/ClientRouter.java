package org.grading;
import org.grading.dtos.CourseDetailsDto;
import org.grading.dtos.StudentCourseDto;
import org.grading.dtos.StudentDto;
import org.grading.socket.SocketConnection;
import org.grading.utilities.Action;
import org.grading.utilities.TablePrintUtility;


import java.util.List;
import java.util.Scanner;


public class ClientRouter {
    private static final Scanner scanner = new Scanner(System.in);
    private static StudentDto loggedStudent = null;
    private static final SocketConnection socketConnection = new SocketConnection();


    public static void main(String[] args) {

        handleAuthenticationInClient();

        handleStudentCoursesMarkInClient();

        handleCourseDetailsInClient();

        socketConnection.sendObjectToServer(Action.CLOSE_CONNECTION);

    }

    private static void handleAuthenticationInClient() {
        System.out.println("---------------------- Login -------------------------");
        while (loggedStudent == null) {
            socketConnection.sendObjectToServer(Action.LOGIN);

            System.out.print("Enter email: ");
            socketConnection.sendObjectToServer(scanner.next());

            System.out.print("Enter password: ");
            socketConnection.sendObjectToServer(scanner.next());

            loggedStudent = (StudentDto) socketConnection.receiveObjectFromServer();

            if (loggedStudent != null) {
                System.out.println(loggedStudent.getName() + " logged successfully.");
            } else {
                printError("Invalid credentials");
            }
        }
    }

    private static void handleStudentCoursesMarkInClient() {
        socketConnection.sendObjectToServer(Action.GET_STUDENT_COURSES);

        List<StudentCourseDto> courses = (List<StudentCourseDto>) socketConnection.receiveObjectFromServer();

        TablePrintUtility.printCoursesAsTable(courses);
    }

    private static void handleCourseDetailsInClient() {

        int courseId = 0;

        while (courseId != -1) {
            socketConnection.sendObjectToServer(Action.GET_COURSE_DETAILS);

            System.out.print("Enter course id to show details and -1 to exit: ");
            courseId = scanner.nextInt();


            socketConnection.sendObjectToServer(courseId);

            CourseDetailsDto courseDetailsDto = (CourseDetailsDto) socketConnection.receiveObjectFromServer();
            if (courseDetailsDto != null) {
                TablePrintUtility.printCourseDetails(courseDetailsDto);
            } else if (courseId != -1) {
                printError("You have no access for this course details");
            }
        }
    }

    private static void printError(String str) {
        System.out.println("\033[1;31m" + str + "\033[0m");
    }
}