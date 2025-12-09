package com.javatutor;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Declaración de variables
        double p1, p2, p3, p4, p5;
        int ap1, ap2, ap3, ap4, ap5;
        double recau;
        int clave, cant;
        double pre;

        // Inicialización de acumuladores
        ap1 = 0;
        ap2 = 0;
        ap3 = 0;
        ap4 = 0;
        ap5 = 0;
        recau = 0.0;

        // Lectura de precios
        System.out.print("Ingrese el precio de la localidad 1: ");
        p1 = scanner.nextDouble();

        System.out.print("Ingrese el precio de la localidad 2: ");
        p2 = scanner.nextDouble();

        System.out.print("Ingrese el precio de la localidad 3: ");
        p3 = scanner.nextDouble();

        System.out.print("Ingrese el precio de la localidad 4: ");
        p4 = scanner.nextDouble();

        System.out.print("Ingrese el precio de la localidad 5: ");
        p5 = scanner.nextDouble();

        // Ciclo externo - lectura de ventas
        System.out.println("\nIngrese clave y cantidad (clave = -1 para terminar):");

        clave = 0;
        while (clave != -1) {
            System.out.print("Ingrese clave: ");
            clave = scanner.nextInt();

            if (clave == -1) {
                break;
            }

            System.out.print("Ingrese cantidad: ");
            cant = scanner.nextInt();

            // Validar CLAVE
            if (clave >= 1 && clave <= 5) {
                // Calcular PRE según la localidad
                if (clave == 1) {
                    pre = p1 * cant;
                    ap1 = ap1 + cant;
                } else if (clave == 2) {
                    pre = p2 * cant;
                    ap2 = ap2 + cant;
                } else if (clave == 3) {
                    pre = p3 * cant;
                    ap3 = ap3 + cant;
                } else if (clave == 4) {
                    pre = p4 * cant;
                    ap4 = ap4 + cant;
                } else {
                    pre = p5 * cant;
                    ap5 = ap5 + cant;
                }

                // Acumular recaudación
                recau = recau + pre;

                // Mostrar resultado de la venta
                System.out.printf("Localidad: %d, Cantidad: %d, Total: %.2f%n", clave, cant, pre);
            } else {
                System.out.println("Clave inválida. Debe estar entre 1 y 5.");
            }
        }

        // Mostrar resultados finales
        System.out.println("\nRESULTADO:");
        System.out.println("CANTIDAD BOLETOS TIPO 1: " + ap1);
        System.out.println("CANTIDAD BOLETOS TIPO 2: " + ap2);
        System.out.println("CANTIDAD BOLETOS TIPO 3: " + ap3);
        System.out.println("CANTIDAD BOLETOS TIPO 4: " + ap4);
        System.out.println("CANTIDAD BOLETOS TIPO 5: " + ap5);
        System.out.printf("RECAUDACION DEL ESTADIO: %.2f%n", recau);

        scanner.close();
    }
}
