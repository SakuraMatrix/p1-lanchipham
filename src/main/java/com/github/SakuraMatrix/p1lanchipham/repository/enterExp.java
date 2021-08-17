package com.github.SakuraMatrix.p1lanchipham.repository;

import java.util.Scanner;

public class enterExp {
    public static void categories(){
        System.out.println("Welcome to the BudgetReminder.");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter in your budget for the following categories.");
        System.out.println("Housing: $");
        double housing = sc.nextDouble();
        System.out.println(housing);
        System.out.println("Utilities: $");
        double utilities = sc.nextDouble();
        System.out.println(utilities);
        System.out.println("Food: $");
        double food = sc.nextDouble();
        System.out.println(food);
        System.out.println("Transportation: $");
        double transportation = sc.nextDouble();
        System.out.println(transportation);
        System.out.println("Medical: $");
        double medical = sc.nextDouble();
        System.out.println(medical);
        System.out.println("Savings: $");
        double miscellaneous = sc.nextDouble();
        System.out.println(miscellaneous);
        System.out.println("Entertainment: $");
        double entertainment = sc.nextDouble();
        System.out.println(entertainment);
        System.out.println("Personal Care: $");
        double personalCare = sc.nextDouble();
        System.out.println(personalCare);
        sc.close();
    }
}
