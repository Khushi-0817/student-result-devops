package com.devops.student;

public class Student {
    private String name;
    private int m1, m2, m3;

    public Student(String name, int m1, int m2, int m3) {
        this.name = name;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    public int total() {
        return m1 + m2 + m3;
    }

    public double percentage() {
        return total() / 3.0;
    }

    public String getName() {
        return name;
    }
}
