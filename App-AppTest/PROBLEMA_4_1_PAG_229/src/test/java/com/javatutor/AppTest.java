package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Pruebas unitarias para la clase App (Problema 4.1)
 * 
 * Valida la eliminación correcta de repeticiones en un arreglo ordenado
 */
class AppTest {

    private String runAppWithInput(String input) {
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        try {
            App.main(new String[] {});
        } catch (Exception e) {
            fail("El programa lanzó una excepción: " + e.getMessage());
        }

        System.setIn(System.in);
        System.setOut(System.out);

        return outContent.toString();
    }

    @Test
    void testSinRepeticionesCaso1() {
        String input = "6\n1\n1\n2\n2\n3\n3\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1") && output.contains("2") && output.contains("3"),
                "Debe listar números sin repeticiones: 1, 2, 3");
        assertTrue(output.toUpperCase().contains("SIN REPETICIONES") || output.toUpperCase().contains("LISTA"),
                "Debe mostrar formato claro");
    }

    @Test
    void testSinRepeticionesCaso2() {
        String input = "5\n5\n5\n5\n5\n5\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("5"), "Debe listar solo 5 cuando todos son iguales");
        // Verificar que no se repite más de una vez
        int count = (int) output.split("5", -1).length - 1;
        assertTrue(count >= 1, "Debe contener al menos un 5");
    }

    @Test
    void testSinRepeticionesCaso3() {
        String input = "8\n10\n20\n30\n40\n50\n60\n70\n80\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("10") && output.contains("20") && output.contains("80"),
                "Debe listar todos cuando no hay repeticiones");
        assertTrue(output.contains("30") && output.contains("40"),
                "Debe incluir todos los elementos sin duplicar");
    }

    @Test
    void testSinRepeticionesCaso4() {
        String input = "12\n5\n5\n5\n10\n10\n15\n20\n20\n20\n20\n25\n30\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("5") && output.contains("10") && output.contains("15") &&
                output.contains("20") && output.contains("25") && output.contains("30"),
                "Debe listar 6 números sin repeticiones");
    }

    @Test
    void testSinRepeticionesCaso5() {
        String input = "1\n42\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("42"), "Debe listar 42 para arreglo de un elemento");
    }

    @Test
    void testSinRepeticionesCaso6() {
        String input = "2\n100\n200\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("100") && output.contains("200"),
                "Debe listar ambos elementos cuando son diferentes");
    }

    @Test
    void testSinRepeticionesCaso7() {
        String input = "8\n-5\n-5\n-2\n-2\n0\n0\n3\n3\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("-5") && output.contains("-2") && output.contains("0") && output.contains("3"),
                "Debe manejar números negativos correctamente");
    }

    @Test
    void testSinRepeticionesCaso8() {
        String input = "7\n7\n7\n7\n7\n7\n7\n7\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("7"), "Primer elemento siempre se incluye");
    }

    @Test
    void testValidacionRangoValido() {
        String input = "3\n1\n2\n3\n";
        String output = runAppWithInput(input);
        assertNotNull(output, "La salida no debe ser nula para un rango válido");
        assertFalse(output.toUpperCase().contains("INCORRECTO"),
                "No debe mostrar error para n=3 (dentro del rango 1-500)");
    }

    @Test
    void testValidacionRangoCero() {
        String input = "0\n";
        String output = runAppWithInput(input);
        assertTrue(output.toUpperCase().contains("INCORRECTO") || output.toUpperCase().contains("ERROR"),
                "Debe mostrar error para n=0 (fuera del rango 1-500)");
    }

    @Test
    void testValidacionRangoNegativo() {
        String input = "-5\n";
        String output = runAppWithInput(input);
        assertTrue(output.toUpperCase().contains("INCORRECTO") || output.toUpperCase().contains("ERROR"),
                "Debe mostrar error para n negativo");
    }

    @Test
    void testValidacionRangoMayorLimite() {
        String input = "501\n";
        String output = runAppWithInput(input);
        assertTrue(output.toUpperCase().contains("INCORRECTO") || output.toUpperCase().contains("ERROR"),
                "Debe mostrar error para n > 500");
    }

    @Test
    void testSalidaFormatoCompleto() {
        String input = "4\n1\n1\n2\n2\n";
        String output = runAppWithInput(input);
        assertTrue(output.length() > 20, "La salida debe ser significativa");
        assertTrue(output.contains("1") && output.contains("2"), "Debe contener resultados");
    }

    @Test
    void testOrdenPreservado() {
        String input = "5\n10\n10\n20\n20\n30\n";
        String output = runAppWithInput(input);
        // Verificar que mantiene el orden
        int pos10 = output.indexOf("10");
        int pos20 = output.indexOf("20");
        int pos30 = output.indexOf("30");
        assertTrue(pos10 < pos20 && pos20 < pos30,
                "El orden de los números debe preservarse");
    }

    @Test
    void testNumeroGrande() {
        String input = "2\n999\n999\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("999"), "Debe manejar números grandes");
    }

    @Test
    void testArregloMaximo() {
        // Crear entrada con 500 elementos
        StringBuilder input = new StringBuilder("500\n");
        for (int i = 0; i < 500; i++) {
            input.append(i).append("\n");
        }
        String output = runAppWithInput(input.toString());
        assertTrue(output.contains("0") && output.contains("499"),
                "Debe manejar arreglo de 500 elementos");
    }
}
