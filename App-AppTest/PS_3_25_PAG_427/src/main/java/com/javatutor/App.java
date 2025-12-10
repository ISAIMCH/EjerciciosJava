package com.javatutor;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de candidatos: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] nombres = new String[n];
        char[] sexos = new char[n];
        int[] edades = new int[n];
        int[] pesos = new int[n];
        double[] alturas = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el nombre del candidato " + (i + 1) + ": ");
            nombres[i] = scanner.nextLine();
            System.out.print("Ingrese el sexo (M/F): ");
            sexos[i] = scanner.nextLine().charAt(0);
            System.out.print("Ingrese la edad: ");
            edades[i] = scanner.nextInt();
            System.out.print("Ingrese el peso (kg): ");
            pesos[i] = scanner.nextInt();
            System.out.print("Ingrese la altura (m): ");
            alturas[i] = scanner.nextDouble();
            scanner.nextLine();
        }

        scanner.close();

        int aptasF = 0;
        int aptosM = 0;
        int totalF = 0;
        int totalM = 0;

        System.out.println("\n=== RESULTADOS ===\n");
        System.out.println("===== CANDIDATAS APTAS PARA BÁSQUET =====");
        for (int i = 0; i < n; i++) {
            if (sexos[i] == 'F') {
                totalF++;
                if (alturas[i] > 1.73 && pesos[i] >= 50 && pesos[i] <= 90) {
                    System.out.printf("%s - Peso: %d kg, Altura: %.2f m%n", nombres[i], pesos[i], alturas[i]);
                    aptasF++;
                }
            }
        }

        System.out.println("\n===== CANDIDATOS APTOS PARA BÁSQUET =====");
        for (int i = 0; i < n; i++) {
            if (sexos[i] == 'M') {
                totalM++;
                if (alturas[i] >= 1.83 && pesos[i] >= 73 && pesos[i] <= 110) {
                    System.out.printf("%s - Peso: %d kg, Altura: %.2f m%n", nombres[i], pesos[i], alturas[i]);
                    aptosM++;
                }
            }
        }

        double porcF = totalF > 0 ? (aptasF * 100.0) / totalF : 0;
        double porcM = totalM > 0 ? (aptosM * 100.0) / totalM : 0;

        System.out.printf("\nPorcentaje de Candidatas Aptas: %.2f%%%n", porcF);
        System.out.printf("Porcentaje de Candidatos Aptos: %.2f%%%n", porcM);
    }
}