package com.javatutor;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar cantidad de empleados
        System.out.print("Ingrese la cantidad de empleados: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Arreglos paralelos para almacenar datos de empleados (Programación
        // Estructurada)
        int[] numero = new int[n];
        String[] nombre = new String[n];
        double[][] ventas = new double[n][12];
        double[] salario = new double[n];

        // Solicitar datos de cada empleado
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Empleado " + (i + 1) + " ---");

            System.out.print("Número de empleado: ");
            numero[i] = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            System.out.print("Nombre: ");
            nombre[i] = scanner.nextLine();

            System.out.print("Salario base: ");
            salario[i] = scanner.nextDouble();

            System.out.println("Ingrese las 12 ventas mensuales:");
            for (int j = 0; j < 12; j++) {
                System.out.print("Venta mes " + (j + 1) + ": ");
                ventas[i][j] = scanner.nextDouble();
            }
        }

        scanner.close();

        // Inciso a) Empleado con mayores ventas
        System.out.println("===== INCISO A) EMPLEADO CON MAYORES VENTAS =====");
        double maxVentas = -1;
        int empleadoMaxVentas = -1;

        for (int i = 0; i < n; i++) {
            double sumaVentas = 0;
            for (int j = 0; j < 12; j++) {
                sumaVentas += ventas[i][j];
            }

            if (sumaVentas > maxVentas) {
                maxVentas = sumaVentas;
                empleadoMaxVentas = i;
            }
        }

        if (empleadoMaxVentas != -1) {
            System.out.println("Número de empleado: " + numero[empleadoMaxVentas]);
            System.out.println("Nombre: " + nombre[empleadoMaxVentas]);
            System.out.println("Total de ventas: " + maxVentas);
        }
        System.out.println();

        // Inciso b) Incrementar salarios 10% a empleados con ventas > 1,000,000
        System.out.println("===== INCISO B) INCREMENTO DE SALARIOS =====");
        int contadorIncrementados = 0;

        for (int i = 0; i < n; i++) {
            double sumaVentas = 0;
            for (int j = 0; j < 12; j++) {
                sumaVentas += ventas[i][j];
            }

            if (sumaVentas > 1000000) {
                double salarioAnterior = salario[i];
                salario[i] = salario[i] * 1.1;
                System.out.println("Empleado: " + nombre[i] +
                        " | Salario anterior: " + salarioAnterior +
                        " | Nuevo salario: " + salario[i]);
                contadorIncrementados++;
            }
        }

        if (contadorIncrementados == 0) {
            System.out.println("No hay empleados con ventas superiores a $1,000,000");
        }
        System.out.println();

        // Inciso c) Empleados con ventas < 30,000 en diciembre (mes 12, índice 11)
        System.out.println("===== INCISO C) EMPLEADOS CON VENTAS BAJAS EN DICIEMBRE =====");
        boolean hayEmpleadosBajos = false;

        for (int i = 0; i < n; i++) {
            if (ventas[i][11] < 30000) {
                System.out.println("Número: " + numero[i] + " | Nombre: " + nombre[i] +
                        " | Ventas en diciembre: " + ventas[i][11]);
                hayEmpleadosBajos = true;
            }
        }

        if (!hayEmpleadosBajos) {
            System.out.println("No hay empleados con ventas inferiores a $30,000 en diciembre");
        }
    }
}
