package com.javatutor;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese la cantidad de alumnos: ");
        int numAlumnos = scanner.nextInt();
        System.out.print("Ingrese la cantidad de exámenes: ");
        int numExamenes = scanner.nextInt();
        
        double[][] calificaciones = new double[numAlumnos][numExamenes];
        
        for (int i = 0; i < numAlumnos; i++) {
            System.out.println("Ingrese las " + numExamenes + " calificaciones del alumno " + (i + 1) + ":");
            for (int j = 0; j < numExamenes; j++) {
                System.out.print("  Examen " + (j + 1) + ": ");
                calificaciones[i][j] = scanner.nextDouble();
            }
        }
        
        scanner.close();
        
        int numAlumnosLocal = calificaciones.length;
        int numExamenesLocal = calificaciones[0].length;
        
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("=".repeat(50));
        
        // Calcular promedios por examen
        double[] promediosExamen = new double[numExamenesLocal];
        for (int j = 0; j < numExamenesLocal; j++) {
            double suma = 0;
            for (int i = 0; i < numAlumnosLocal; i++) {
                suma += calificaciones[i][j];
            }
            promediosExamen[j] = suma / numAlumnosLocal;
        }
        
        // Mostrar promedios por examen
        System.out.println("\nPromedios por Examen:");
        for (int j = 0; j < numExamenesLocal; j++) {
            System.out.printf("Examen %d: %.2f%n", (j + 1), promediosExamen[j]);
        }
        
        // Mostrar promedios por alumno
        System.out.println("\nPromedios por Alumno:");
        for (int i = 0; i < numAlumnosLocal; i++) {
            double suma = 0;
            for (int j = 0; j < numExamenesLocal; j++) {
                suma += calificaciones[i][j];
            }
            double promedioAlumno = suma / numExamenesLocal;
            System.out.printf("Alumno %d: %.2f%n", (i + 1), promedioAlumno);
        }
        
        // Encontrar examen con mayor promedio
        int examenMaximo = 0;
        double promedioMaximo = promediosExamen[0];
        for (int j = 1; j < numExamenesLocal; j++) {
            if (promediosExamen[j] > promedioMaximo) {
                promedioMaximo = promediosExamen[j];
                examenMaximo = j;
            }
        }
        
        System.out.println("\nExamen con Mayor Promedio:");
        System.out.printf("Examen: %d%n", (examenMaximo + 1));
        System.out.printf("Promedio: %.2f%n", promedioMaximo);
    }
}
