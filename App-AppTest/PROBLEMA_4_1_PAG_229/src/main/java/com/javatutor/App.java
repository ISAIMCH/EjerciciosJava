package com.javatutor;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Declaración de variables
        int n;
        int[] vec = new int[500];
        int repet;
        int i;

        // Leer número de elementos
        System.out.print("Ingrese número de elementos del arreglo: ");
        n = scanner.nextInt();

        // Validar rango
        if (n < 1 || n > 500) {
            System.out.println("El número de elementos es incorrecto");
        } else {
            // Leer elementos del arreglo
            System.out.println("Ingrese los " + n + " elementos ordenados:");
            for (i = 0; i < n; i++) {
                System.out.print("Ingrese valor " + (i + 1) + ": ");
                vec[i] = scanner.nextInt();
            }

            // Procesar el arreglo para eliminar repeticiones
            System.out.println("\nLista de números sin repeticiones:");

            // Inicializar REPET con el primer elemento
            repet = vec[0];
            System.out.println(repet);

            // Recorrer el resto del arreglo
            i = 1;
            while (i < n) {
                // Si el elemento es diferente a REPET, imprimir y actualizar REPET
                if (vec[i] != repet) {
                    repet = vec[i];
                    System.out.println(repet);
                }
                i = i + 1;
            }
        }

        scanner.close();
    }
}
