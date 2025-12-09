package com.javatutor;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese la cantidad de números: ");
        int n = scanner.nextInt();
        
        int[] arreglo = new int[n];
        
        System.out.println("Ingrese " + n + " números (pueden ser positivos, negativos o cero):");
        for (int i = 0; i < n; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            arreglo[i] = scanner.nextInt();
        }
        
        scanner.close();
        
        System.out.println("\n=== RESULTADOS ===\n");
        
        System.out.print("Arreglo: ");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i] + " ");
        }
        System.out.println();
        
        int positivos = 0;
        int negativos = 0;
        int ceros = 0;
        
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] > 0) {
                positivos++;
            } else if (arreglo[i] < 0) {
                negativos++;
            } else {
                ceros++;
            }
        }
        
        System.out.println("Positivos: " + positivos);
        System.out.println("Negativos: " + negativos);
        System.out.println("Ceros: " + ceros);
    }
}
