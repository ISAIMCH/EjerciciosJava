package com.javatutor;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Declaración de variables
        int n, i, num, cuecer;

        // Inicialización
        cuecer = 0;
        i = 1;

        // Entrada
        System.out.print("Ingrese la cantidad de números a procesar: ");
        n = scanner.nextInt();

        // Procesamiento - Ciclo while para contar ceros
        while (i <= n) {
            System.out.print("Ingrese número " + i + ": ");
            num = scanner.nextInt();

            // Si el número es cero, incrementar contador
            if (num == 0) {
                cuecer = cuecer + 1;
            }

            // Incrementar el contador del ciclo
            i = i + 1;
        }

        // Salida
        System.out.println("\nRESULTADO:");
        System.out.println("Total de ceros encontrados: " + cuecer);

        scanner.close();
    }
}
