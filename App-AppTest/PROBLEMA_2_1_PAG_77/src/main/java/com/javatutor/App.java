package com.javatutor;

import java.util.Scanner;

/**
 * Problema 2.1 - Temperatura de un Grillo
 * 
 * El número de sonidos emitidos por un grillo en un minuto es una función de la temperatura.
 * Como resultado de esto, es posible determinar el nivel de la temperatura haciendo uso de 
 * un grillo como termómetro.
 * 
 * Fórmula:
 * - Temperatura = N / 4 + 40
 * 
 * Donde N es el número de sonidos emitidos por el grillo en un minuto
 * y T es la temperatura en grados Fahrenheit.
 */
public class App {
    
    public static void main(String[] args) {
        // Declaración de variables
        int n;
        double t;
        
        // Crear Scanner para entrada
        Scanner scanner = new Scanner(System.in);
        
        // Leer datos de entrada
        System.out.println("=== CÁLCULO DE TEMPERATURA USANDO SONIDOS DE GRILLO ===\n");
        System.out.print("Ingrese el número de sonidos del grillo: ");
        n = scanner.nextInt();
        
        // Validar que N sea mayor que 0
        if (n > 0) {
            // Calcular temperatura: T = N / 4 + 40
            t = n / 4.0 + 40;
            
            // Mostrar resultados
            System.out.println("\n=== RESULTADOS ===");
            System.out.printf("Número de sonidos: %d%n", n);
            System.out.printf("Temperatura del grillo: %.2f grados Fahrenheit%n", t);
        } else {
            // Mostrar mensaje de error
            System.out.println("\nERROR: El número de sonidos debe ser positivo (N > 0)");
        }
        
        // Cerrar scanner
        scanner.close();
    }
}
