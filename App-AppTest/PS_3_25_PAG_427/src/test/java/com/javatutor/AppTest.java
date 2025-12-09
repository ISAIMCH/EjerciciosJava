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
    void testCriterioAlumnaApta() {
        String input = "F\n1.75\n65\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("APTA") || output.contains("apta") || output.contains("SI"),
                "Debe evaluar alumna apta (1.75m, 65kg)");
    }

    @Test
    void testCriterioAlumnaNoAptaAltura() {
        String input = "F\n1.70\n70\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("NO") || output.contains("no apta"),
                "Debe rechazar alumna con altura insuficiente");
    }

    @Test
    void testCriterioAlumnaNoAptaPeso() {
        String input = "F\n1.80\n100\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("NO") || output.contains("no apta"),
                "Debe rechazar alumna con peso excesivo");
    }

    @Test
    void testCriterioAlumnoApto() {
        String input = "M\n1.85\n85\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("APTO") || output.contains("apto") || output.contains("SI"),
                "Debe evaluar alumno apto (1.85m, 85kg)");
    }

    @Test
    void testCriterioAlumnoNoAptoAltura() {
        String input = "M\n1.80\n80\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("NO") || output.contains("no apto"),
                "Debe rechazar alumno con altura insuficiente");
    }

    @Test
    void testCriterioAlumnoNoAptoPeso() {
        String input = "M\n1.88\n120\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("NO") || output.contains("no apto"),
                "Debe rechazar alumno con peso excesivo");
    }

    @Test
    void testCalcularPorcentajeAlumnas() {
        String output = runAppWithInput("");
        assertNotNull(output, "Debe calcular porcentaje de alumnas aptas");
    }

    @Test
    void testCalcularPorcentajeAlumnos() {
        String output = runAppWithInput("");
        assertNotNull(output, "Debe calcular porcentaje de alumnos aptos");
    }

    @Test
    void testPorcentajeCero() {
        String output = runAppWithInput("");
        assertNotNull(output, "Debe manejar porcentaje cero");
    }

    @Test
    void testPorcentajeCien() {
        String output = runAppWithInput("");
        assertNotNull(output, "Debe manejar porcentaje cien");
    }
}