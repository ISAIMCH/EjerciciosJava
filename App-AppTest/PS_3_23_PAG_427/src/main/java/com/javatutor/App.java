package com.javatutor;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantAlumnos = scanner.nextInt();
        System.out.print("Ingrese la cantidad de calificaciones por alumno: ");
        int cantCalificaciones = scanner.nextInt();

        int[][] alumnos = new int[cantAlumnos][cantCalificaciones + 1];

        for (int i = 0; i < cantAlumnos; i++) {
            System.out.print("Ingrese la matrícula del alumno " + (i + 1) + ": ");
            alumnos[i][0] = scanner.nextInt();

            System.out.print("Ingrese " + cantCalificaciones + " calificaciones (separadas por espacio): ");
            for (int j = 1; j <= cantCalificaciones; j++) {
                alumnos[i][j] = scanner.nextInt();
            }
        }

        scanner.close();

        System.out.println("\n=== RESULTADOS ===\n");

        for (int i = 0; i < cantAlumnos; i++) {
            int mat = alumnos[i][0];
            double suma = 0;

            System.out.print("Alumno MAT=" + mat + " | CAL: ");

            for (int j = 1; j <= cantCalificaciones; j++) {
                double calificacion = alumnos[i][j];
                suma += calificacion;
                System.out.printf("%.2f ", calificacion);
            }

            double promedio = suma / cantCalificaciones;
            System.out.printf("| Promedio: %.4f%n", promedio);
        }
    }
}