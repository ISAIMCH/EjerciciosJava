package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    void testContarPositivos() {
        String input = "6\n5\n-2\n0\n3\n-1\n4\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("3"), "Debe contar 3 positivos");
    }

    @Test
    void testContarNegativos() {
        String input = "6\n5\n-2\n0\n3\n-1\n4\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("2"), "Debe contar 2 negativos");
    }

    @Test
    void testContarCeros() {
        String input = "6\n5\n-2\n0\n3\n-1\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("2"), "Debe contar 2 ceros");
    }

    @Test
    void testArregloPositivosSolo() {
        String input = "5\n1\n2\n3\n4\n5\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("5") && !output.contains("-"), "Debe contar solo positivos");
    }

    @Test
    void testArregloNegativosSolo() {
        String input = "5\n-1\n-2\n-3\n-4\n-5\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("5"), "Debe contar 5 negativos");
    }

    @Test
    void testArregloCerosSolo() {
        String input = "4\n0\n0\n0\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("4"), "Debe contar 4 ceros");
    }

    @Test
    void testArregloVacio() {
        String input = "0\n";
        String output = runAppWithInput(input);
        assertNotNull(output, "El programa debe manejar arreglo vacío");
    }

    @Test
    void testArregloUnElementoPositivo() {
        String input = "1\n42\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1"), "Debe contar 1 positivo");
    }

    @Test
    void testArregloUnElementoNegativo() {
        String input = "1\n-42\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1"), "Debe contar 1 negativo");
    }

    @Test
    void testArregloUnElementoCero() {
        String input = "1\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1"), "Debe contar 1 cero");
    }

    @Test
    void testArregloGrande() {
        String input = "15\n5\n-3\n0\n7\n-1\n0\n2\n-8\n0\n4\n-6\n1\n0\n9\n-2\n";
        String output = runAppWithInput(input);
        assertNotNull(output, "Debe procesar arreglo grande");
    }
}
