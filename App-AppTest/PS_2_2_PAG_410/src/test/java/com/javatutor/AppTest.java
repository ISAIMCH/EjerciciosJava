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
    void testCotangente60Grados() {
        String input = "0.8660\n0.5000\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("0.5774") || output.contains("0.57"),
                "Debe calcular cotangente de 60° ≈ 0.5774");
    }

    @Test
    void testCotangente45Grados() {
        String input = "0.7071\n0.7071\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1.0"), "Debe calcular cotangente de 45° = 1.0");
    }

    @Test
    void testCotangente30Grados() {
        String input = "0.5000\n0.8660\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1.7320") || output.contains("1.73"),
                "Debe calcular cotangente de 30° ≈ 1.7320");
    }

    @Test
    void testCotangente0Grados() {
        String input = "1.0000\n0.0000\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("0.0"), "Debe calcular cotangente de 0° = 0.0");
    }

    @Test
    void testSenoIgualCero() {
        String input = "0.0\n0.5\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("ERROR") || output.contains("error") || output.contains("indefinida"),
                "Debe rechazar seno = 0");
    }

    @Test
    void testsenoDiferenteDeZero() {
        String input = "0.5\n0.8660\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1.73") || output.contains("cotangente"),
                "Debe aceptar seno diferente de 0");
    }

    @Test
    void testCotangenteNegativa() {
        String input = "0.5000\n-0.8660\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("-1.7320") || output.contains("-1.73"),
                "Debe calcular cotangente negativa");
    }

    @Test
    void testCotangentePositivaEnCuadranteIII() {
        String input = "-0.5000\n-0.8660\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1.7320") || output.contains("1.73"),
                "Debe calcular cotangente en cuadrante III");
    }

    @Test
    void testPrecisionDecimales() {
        String input = "0.8660\n0.5000\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("0.57") || output.contains("0.5774"),
                "Debe mostrar precisión en decimales");
    }

    @Test
    void testValoresEnLimites() {
        String input = "0.0001\n1.0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("10000") || output.contains("cotangente"),
                "Debe manejar valores en límites");
    }
}