package org.grading.dtos;

import org.grading.model.CourseDetails;

import java.io.Serializable;

public class CourseDetailsDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String courseName;
    private int highestMark;
    private int lowestMark;
    private double avgMark;
    private double midMark;
    private int range;
    private double passPercentage;
    private double failPercentage;

    public CourseDetailsDto(CourseDetails courseDetails) {
        setCourseName(courseDetails.getCourseName());
        setLowestMark(courseDetails.getLowestMark());
        setHighestMark(courseDetails.getHighestMark());
        setRange(courseDetails.getRange());
        setAvgMark(courseDetails.getAvgMark());
        setMidMark(courseDetails.getMidMark());
        setFailPercentage(courseDetails.getFailPercentage());
        setPassPercentage(courseDetails.getPassPercentage());
    }


    // Getters
    public String getCourseName() {
        return courseName;
    }
    public int getHighestMark() {
        return highestMark;
    }
    public int getLowestMark() {
        return lowestMark;
    }
    public double getAvgMark() {
        return avgMark;
    }
    public double getMidMark() {
        return midMark;
    }
    public int getRange() {
        return range;
    }
    public double getPassPercentage() {
        return passPercentage;
    }
    public double getFailPercentage() {
        return failPercentage;
    }

    // Setters
    private void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    private void setHighestMark(int highestMark) {
        this.highestMark = highestMark;
    }
    private void setLowestMark(int lowestMark) {
        this.lowestMark = lowestMark;
    }
    private void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }
    private void setMidMark(double midMark) {
        this.midMark = midMark;
    }
    private void setRange(int range) {
        this.range = range;
    }
    private void setPassPercentage(double passPercentage) {
        this.passPercentage = passPercentage;
    }
    private void setFailPercentage(double failPercentage) {
        this.failPercentage = failPercentage;
    }
}
