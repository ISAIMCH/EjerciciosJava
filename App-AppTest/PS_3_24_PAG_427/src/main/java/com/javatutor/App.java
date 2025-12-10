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

        double maxProm = -1;
        double minProm = 101;
        int matMax = 0;
        int matMin = 0;

        for (int i = 0; i < cantAlumnos; i++) {
            int mat = alumnos[i][0];
            double suma = 0;

            for (int j = 1; j <= cantCalificaciones; j++) {
                suma += alumnos[i][j];
            }

            double promedio = suma / cantCalificaciones;

            if (promedio > maxProm) {
                maxProm = promedio;
                matMax = mat;
            }

            if (promedio < minProm) {
                minProm = promedio;
                matMin = mat;
            }
        }

        System.out.println("\n=== RESULTADOS ===\n");
        System.out.printf("Mejor Alumno: MAT=%d | Promedio: %.4f%n", matMax, maxProm);
        System.out.printf("Peor Alumno: MAT=%d | Promedio: %.4f%n", matMin, minProm);
    }
}