package com.javatutor;

import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("===== CÁLCULO DE LA TANGENTE DE UN ÁNGULO =====");
        System.out.println();
        
        // Solicitar datos por consola
        System.out.print("Ingrese el valor del SENO: ");
        double seno = scanner.nextDouble();
        
        System.out.print("Ingrese el valor del COSENO: ");
        double coseno = scanner.nextDouble();
        
        scanner.close();
        
        System.out.println();
        System.out.println("Valores ingresados:");
        System.out.println("SENO: " + seno);
        System.out.println("COSENO: " + coseno);
        System.out.println();
        
        // Calcular tangente si coseno es diferente de 0
        double tangente = 0;
        if (coseno != 0) {
            tangente = seno / coseno;
            System.out.printf("La tangente del ángulo es: %.4f%n", tangente);
        } else {
            System.out.println("Error: La tangente no está definida (COSENO = 0)");
        }
    }
}
