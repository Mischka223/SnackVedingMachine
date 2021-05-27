package com.intelliarts;


import com.intelliarts.chekings.Checking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Checking checking = new Checking();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            checking.methodCheck(command);
        }
    }
}