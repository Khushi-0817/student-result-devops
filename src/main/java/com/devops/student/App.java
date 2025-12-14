package com.devops.student;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter marks for Subject 1: ");
        int m1 = sc.nextInt();

        System.out.print("Enter marks for Subject 2: ");
        int m2 = sc.nextInt();

        System.out.print("Enter marks for Subject 3: ");
        int m3 = sc.nextInt();

        // Create student object
        Student s = new Student(name, m1, m2, m3);

        // Print results
        System.out.println("\nStudent Name : " + s.getName());
        System.out.println("Total Marks  : " + s.total());
        System.out.println("Percentage   : " + String.format("%.2f", s.percentage()));
        System.out.println("Grade        : " + ResultService.grade(s.percentage()));

        sc.close();
    }
}
