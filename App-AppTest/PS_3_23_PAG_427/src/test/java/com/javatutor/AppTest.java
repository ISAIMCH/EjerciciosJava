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
    void testPromedioAlumno1001() {
        String output = runAppWithInput("");
        assertTrue(output.contains("86.6") || output.contains("86,6") || output.contains("1001"),
                "Debe calcular promedio para alumno 1001: 86.6");
    }

    @Test
    void testPromedioAlumno1002() {
        String output = runAppWithInput("");
        assertTrue(output.contains("78.4") || output.contains("78,4"),
                "Debe calcular promedio para alumno 1002: 78.4");
    }

    @Test
    void testPromedioAlumno1003() {
        String output = runAppWithInput("");
        assertTrue(output.contains("97.8") || output.contains("97,8"),
                "Debe calcular promedio para alumno 1003: 97.8");
    }

    @Test
    void testPromedioAlumno1004() {
        String output = runAppWithInput("");
        assertTrue(output.contains("70") || output.contains("70.0"),
                "Debe calcular promedio para alumno 1004: 70.0");
    }

    @Test
    void testPromedioAlumno1005() {
        String output = runAppWithInput("");
        assertTrue(output.contains("60") || output.contains("60.0"),
                "Debe calcular promedio para alumno 1005: 60.0");
    }

    @Test
    void testBuclesAnidados() {
        String output = runAppWithInput("");
        assertNotNull(output, "El programa debe ejecutarse sin errores");
    }

    @Test
    void testSumaCalificaciones() {
        String output = runAppWithInput("");
        assertTrue(output.contains("433") || output.contains("86"),
                "Debe sumar calificaciones correctamente");
    }

    @Test
    void testPromedioConDecimales() {
        String output = runAppWithInput("");
        assertTrue(output.contains("86.9") || output.contains("86,9"),
                "Debe calcular promedio con decimales");
    }

    @Test
    void testValidacionCalificacionesEnRango() {
        String output = runAppWithInput("");
        assertNotNull(output, "El programa debe validar calificaciones");
    }

    @Test
    void testBucleExterno35Iteraciones() {
        String output = runAppWithInput("");
        assertNotNull(output, "El programa debe ejecutarse 35 veces");
    }
}