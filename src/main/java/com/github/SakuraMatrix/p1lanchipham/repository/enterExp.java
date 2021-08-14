package com.github.SakuraMatrix.p1lanchipham.repository;

import java.util.Scanner;

public class enterExp {
    public static void categories(){
        System.out.println("Please enter in your monthly income.");
        Scanner sc = new Scanner(System.in);
        double income = sc.nextDouble();
        System.out.println(income);
        System.out.println("Please enter in your budget for the following categories.");
        System.out.println("Housing: $");
        double housing = sc.nextDouble();
        System.out.println(housing);
        System.out.println("Utilities: $");
        double utilities = sc.nextDouble();
        System.out.println(housing);
        System.out.println("Food: $");
        double food = sc.nextDouble();
        System.out.println(housing);
        System.out.println("Transportation: $");
        double transportation = sc.nextDouble();
        System.out.println(housing);
        System.out.println("Medical: $");
        double medical = sc.nextDouble();
        System.out.println(housing);
        System.out.println("Savings: $");
        double savings = sc.nextDouble();
        System.out.println(housing);
        System.out.println("Entertainment: $");
        double entertainment = sc.nextDouble();
        System.out.println(housing);
        System.out.println("Personal Care: $");
        double personalCare = sc.nextDouble();
        System.out.println(housing);
        sc.close();
    }
}
