package org.grading.utilities;

import java.io.Serializable;

public enum Action implements Serializable {
    LOGIN,
    GET_STUDENT_COURSES,
    GET_COURSE_DETAILS,
    CLOSE_CONNECTION
}
