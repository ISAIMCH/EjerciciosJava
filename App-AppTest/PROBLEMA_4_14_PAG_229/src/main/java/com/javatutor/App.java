package com.javatutor;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos de entrada
        System.out.print("Ingrese la cantidad de meses (máximo 12): ");
        int meses = scanner.nextInt();

        System.out.print("Ingrese la cantidad de departamentos: ");
        int deptos = scanner.nextInt();

        // Arreglo bidimensional: PROD[meses][departamentos]
        double[][] PROD = new double[meses][deptos];

        // Leer los costos de producción
        System.out.println("\nIngrese los costos de producción para cada mes y departamento:");
        for (int i = 0; i < meses; i++) {
            System.out.println("Mes " + (i + 1) + ":");
            for (int j = 0; j < deptos; j++) {
                System.out.print("  Departamento " + (j + 1) + ": ");
                PROD[i][j] = scanner.nextDouble();
            }
        }

        scanner.close();

        // Inciso a): Mayor costo de producción del departamento 1 (índice 0)
        double MAYDUL = PROD[0][0];
        int MES = 1;
        int i = 1;
        while (i < meses) {
            if (PROD[i][0] > MAYDUL) {
                MAYDUL = PROD[i][0];
                MES = i + 1;
            }
            i++;
        }
        System.out.println("\n===== INCISO A) MAYOR COSTO DEPARTAMENTO 1 =====");
        System.out.println("Mes: " + MES);
        System.out.println("Costo: " + MAYDUL);
        System.out.println();

        // Inciso b): Promedio de costos del departamento 2 (índice 1)
        double SUM = 0;
        i = 0;
        while (i < meses) {
            SUM = SUM + PROD[i][1];
            i++;
        }
        double PROMEDIO = SUM / meses;
        System.out.println("===== INCISO B) PROMEDIO COSTO DEPARTAMENTO 2 =====");
        System.out.printf("Promedio: %.2f%n", PROMEDIO);
        System.out.println();

        // Inciso c): Mayor y menor costo del departamento 2 (índice 1)
        double MAYBEB = PROD[0][1];
        double MENBEB = PROD[0][1];
        int MESMAY = 1;
        int MESMEN = 1;
        i = 1;
        while (i < meses) {
            if (PROD[i][1] > MAYBEB) {
                MAYBEB = PROD[i][1];
                MESMAY = i + 1;
            }
            if (PROD[i][1] < MENBEB) {
                MENBEB = PROD[i][1];
                MESMEN = i + 1;
            }
            i++;
        }
        System.out.println("===== INCISO C) MAYOR Y MENOR COSTO DEPARTAMENTO 2 =====");
        System.out.println("Mayor: " + MAYBEB + " (Mes " + MESMAY + ")");
        System.out.println("Menor: " + MENBEB + " (Mes " + MESMEN + ")");
        System.out.println();

        // Inciso d): Menor costo en el último mes
        double MENOR = PROD[meses - 1][0];
        int DEP = 1;
        i = 1;
        while (i < deptos) {
            if (PROD[meses - 1][i] < MENOR) {
                MENOR = PROD[meses - 1][i];
                DEP = i + 1;
            }
            i++;
        }
        System.out.println("===== INCISO D) MENOR COSTO ÚLTIMO MES =====");
        System.out.println("Departamento: " + DEP);
        System.out.println("Costo: " + MENOR);
    }
}
