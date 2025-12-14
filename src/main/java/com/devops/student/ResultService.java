package com.devops.student;

public class ResultService {

    public static String grade(double percentage) {
        if (percentage >= 75) return "A";
        if (percentage >= 60) return "B";
        if (percentage >= 50) return "C";
        return "Fail";
    }
}
