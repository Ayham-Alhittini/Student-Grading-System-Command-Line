package org.grading.utilities;

import org.grading.dtos.CourseDetailsDto;
import org.grading.dtos.StudentCourseDto;

import java.util.List;

public class TablePrintUtility {

    public static void printCoursesAsTable(List<StudentCourseDto> courses) {
        int tableRowSize = 52;

        System.out.println();

        printRow(tableRowSize);
        System.out.println("| " + placeMargin("Course Id" , 15)+ "| " + placeMargin("Course Name", 20) + "| " + placeMargin("Your Mark", 10) + "|");
        printRow(tableRowSize);

        for (StudentCourseDto course: courses) {
            System.out.println("| " + placeMargin(course.getCourseId()+"", 15) + "| " + placeMargin(course.getCourseName(), 20) + "| " + placeMargin(course.getMark() + "%", 10) + "|");
            printRow(tableRowSize);
        }
    }

    public static void printCourseDetails(CourseDetailsDto courseDetails) {
        int tableRowSize = 30;

        System.out.println();

        printRow(tableRowSize);
        System.out.println("| " + placeMargin(courseDetails.getCourseName(), tableRowSize - 3) + "|");
        printRow(tableRowSize);

        System.out.println("| " + placeMargin("Average mark = " + courseDetails.getAvgMark(), 27) + "|");
        printRow(tableRowSize);


        System.out.println("| " + placeMargin("Median mark = " + courseDetails.getMidMark(), 27) + "|");
        printRow(tableRowSize);

        System.out.println("| " + placeMargin("Highest mark = " + courseDetails.getHighestMark(), 27) + "|");
        printRow(tableRowSize);

        System.out.println("| " + placeMargin("Lowest mark = " + courseDetails.getLowestMark(), 27) + "|");
        printRow(tableRowSize);

        System.out.println("| " + placeMargin("Mark range = " + courseDetails.getRange(), 27) + "|");
        printRow(tableRowSize);

        System.out.println("| " + placeMargin("Pass percentage = " + courseDetails.getPassPercentage() + "%", 27) + "|");
        printRow(tableRowSize);

        System.out.println("| " + placeMargin("Fail percentage = " + courseDetails.getFailPercentage() + "%", 27) + "|");
        printRow(tableRowSize);
    }

    private static void printRow(int rowSize) {
        System.out.println("-".repeat(rowSize));
    }


    private static String placeMargin(String str, int fullSize) {
        return str + " ".repeat(fullSize - str.length());
    }
}
