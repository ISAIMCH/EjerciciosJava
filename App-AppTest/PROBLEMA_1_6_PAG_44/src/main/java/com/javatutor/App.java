package com.javatutor;

import java.util.Scanner;

/**
 * Problema 1.6 - Conversión de Días a Segundos
 * 
 * Construya un diagrama de flujo que calcule e imprima el número de segundos
 * que hay en un determinado número de días.
 * 
 * Fórmula:
 * - SEG = DIAS * 24 * 60 * 60
 * 
 * Donde: 1 día = 24 horas, 1 hora = 60 minutos, 1 minuto = 60 segundos
 */
public class App {

    public static void main(String[] args) {
        // Declaración de variables
        int dias, seg;

        // Crear Scanner para entrada
        Scanner scanner = new Scanner(System.in);

        // Leer datos de entrada
        System.out.println("=== CONVERSIÓN DE DÍAS A SEGUNDOS ===\n");
        System.out.print("Ingrese el número de días: ");
        dias = scanner.nextInt();

        // Calcular segundos: SEG = DIAS * 24 * 60 * 60
        seg = dias * 24 * 60 * 60;

        // Mostrar resultado
        System.out.println("\nRESULTADO:");
        System.out.printf("En %d días, hay: SEG: \"%d\" segundos.%n", dias, seg);

        // Cerrar scanner
        scanner.close();
    }
}
