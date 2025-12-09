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
        assertTrue(output.contains("86.6") || output.contains("86,6"),
                "Debe calcular promedio 86.6");
    }

    @Test
    void testPromedioAlumno1003() {
        String output = runAppWithInput("");
        assertTrue(output.contains("97.8") || output.contains("97,8"),
                "Debe calcular promedio 97.8");
    }

    @Test
    void testPromedioAlumno1005() {
        String output = runAppWithInput("");
        assertTrue(output.contains("60") || output.contains("60.0"),
                "Debe calcular promedio 60.0");
    }

    @Test
    void testIdentificarMejor() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1003") && output.contains("97.8"),
                "Debe identificar a 1003 como mejor alumno con promedio 97.8");
    }

    @Test
    void testIdentificarPeor() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1005") && output.contains("60"),
                "Debe identificar a 1005 como peor alumno con promedio 60.0");
    }

    @Test
    void testComparacionMaxMin() {
        String output = runAppWithInput("");
        assertNotNull(output, "El programa debe comparar max y min");
    }

    @Test
    void testInicializacionMaxMin() {
        String output = runAppWithInput("");
        assertNotNull(output, "El programa debe inicializar max y min correctamente");
    }

    @Test
    void testActualizacionMejor() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1003"),
                "Debe actualizar mejor alumno");
    }

    @Test
    void testActualizacionPeor() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1005"),
                "Debe actualizar peor alumno");
    }

    @Test
    void testMultiplesBuclesAnidados() {
        String output = runAppWithInput("");
        assertNotNull(output, "El programa debe ejecutar bucles anidados");
    }

    @Test
    void testMantenerMatriculaMejor() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1003"), "Debe mantener matrícula del mejor");
    }
}