package com.javatutor;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el valor del SENO: ");
        double seno = scanner.nextDouble();
        System.out.print("Ingrese el valor del COSENO: ");
        double coseno = scanner.nextDouble();
        scanner.close();

        double cotangente;

        if (seno != 0) {
            cotangente = coseno / seno;
            System.out.printf("La cotangente del ángulo es: %.4f%n", cotangente);
        } else {
            System.out.println("Error: La cotangente no está definida (SENO = 0)");
        }
    }
}