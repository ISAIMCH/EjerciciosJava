package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

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
    void testDosAlumnosDosExamenes() {
        String input = "2\n2\n8.0\n7.0\n6.0\n8.0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Examen 1: 7.00"));
        assertTrue(output.contains("Examen 2: 7.50"));
    }

    @Test
    void testTresAlumnosTresExamenes() {
        String input = "3\n3\n8.0\n7.0\n9.0\n6.0\n8.0\n7.0\n10.0\n9.0\n8.0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Examen 1: 8.00"));
        assertTrue(output.contains("Alumno 1: 8.00"));
    }

    @Test
    void testPromediosPorExamen() {
        String input = "2\n3\n9.0\n8.0\n7.0\n9.0\n8.0\n7.0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Promedios por Examen:"));
        assertTrue(output.contains("Examen 1: 9.00"));
        assertTrue(output.contains("Examen 2: 8.00"));
        assertTrue(output.contains("Examen 3: 7.00"));
    }

    @Test
    void testPromediosPorAlumno() {
        String input = "3\n2\n10.0\n8.0\n6.0\n8.0\n8.0\n8.0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Promedios por Alumno:"));
        assertTrue(output.contains("Alumno 1: 9.00"));
        assertTrue(output.contains("Alumno 2: 7.00"));
        assertTrue(output.contains("Alumno 3: 8.00"));
    }

    @Test
    void testExamenMaximoCorrect() {
        String input = "3\n3\n7.0\n8.0\n9.0\n7.0\n8.0\n9.0\n7.0\n8.0\n9.0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Examen con Mayor Promedio:"));
        assertTrue(output.contains("Examen: 3"));
        assertTrue(output.contains("Promedio: 9.00"));
    }

    @Test
    void testValoresIguales() {
        String input = "3\n3\n7.5\n7.5\n7.5\n7.5\n7.5\n7.5\n7.5\n7.5\n7.5\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Examen 1: 7.50"));
        assertTrue(output.contains("Alumno 1: 7.50"));
    }

    @Test
    void testUnAlumnoVariosExamenes() {
        String input = "1\n5\n10.0\n9.0\n8.0\n7.0\n6.0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Examen 1: 10.00"));
        assertTrue(output.contains("Examen 5: 6.00"));
        assertTrue(output.contains("Alumno 1: 8.00"));
    }

    @Test
    void testVariosAlumnosUnExamen() {
        String input = "5\n1\n10.0\n9.0\n8.0\n7.0\n6.0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Examen 1: 8.00"));
        assertTrue(output.contains("Alumno 1: 10.00"));
        assertTrue(output.contains("Alumno 5: 6.00"));
    }

    @Test
    void testValoresDecimales() {
        String input = "2\n2\n7.33\n8.66\n7.34\n8.67\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Examen 1"));
        assertTrue(output.contains("Examen 2"));
    }

    @Test
    void testValoresAltos() {
        String input = "2\n3\n9.8\n9.9\n10.0\n9.7\n9.8\n9.9\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Examen 3: 9.95"));
    }

    @Test
    void testValoresBajos() {
        String input = "2\n3\n1.0\n2.0\n3.0\n1.5\n2.5\n3.5\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Examen 1: 1.25"));
    }

    @Test
    void testMatrizGrande() {
        String input = "10\n4\n9.0\n8.5\n8.0\n7.5\n8.9\n8.4\n7.9\n7.4\n8.8\n8.3\n7.8\n7.3\n8.7\n8.2\n7.7\n7.2\n8.6\n8.1\n7.6\n7.1\n8.5\n8.0\n7.5\n7.0\n8.4\n7.9\n7.4\n6.9\n8.3\n7.8\n7.3\n6.8\n8.2\n7.7\n7.2\n6.7\n8.1\n7.6\n7.1\n6.6\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Examen 1"));
        assertTrue(output.contains("Alumno 1"));
    }

    @Test
    void testValidacionExamenMaximoConVariablesDistintas() {
        // CRITICAL: Test where examenMaximo must be different from index
        // Data arranged so promedio por examen: Examen1=8.00, Examen2=6.00,
        // Examen3=9.00
        // So Examen 3 should be maximum with promedio 9.00
        String input = "3\n3\n8.0\n6.0\n9.0\n8.0\n6.0\n9.0\n8.0\n6.0\n9.0\n";
        String output = runAppWithInput(input);

        // If code uses wrong variable or swaps assignment, this would fail
        assertTrue(output.contains("Examen: 3"),
                "CRÍTICO: Debe mostrar 'Examen: 3' (no 1 o 2)");
        assertTrue(output.contains("Promedio: 9.00"),
                "CRÍTICO: Mayor promedio debe ser 9.00");

        // Verify promedios are correct
        assertTrue(output.contains("Examen 1: 8.00") && output.contains("Examen 2: 6.00"),
                "Debe calcular correctamente todos los exámenes");
    }

    @Test
    void testConDatosAleatorios() {
        // Random test data with variable dimensions
        // 4 alumnos, 3 exámenes con valores aleatorios
        // Alumno 1: 7.5, 8.2, 6.9
        // Alumno 2: 6.8, 7.5, 8.1
        // Alumno 3: 8.1, 6.9, 7.2
        // Alumno 4: 7.3, 8.4, 7.8

        String input = "4\n3\n7.5\n8.2\n6.9\n6.8\n7.5\n8.1\n8.1\n6.9\n7.2\n7.3\n8.4\n7.8\n";
        String output = runAppWithInput(input);

        // Verify structure
        assertTrue(output.contains("RESULTADOS"), "Debe mostrar sección RESULTADOS");
        assertTrue(output.contains("Promedios por Examen:"), "Debe mostrar promedios por examen");
        assertTrue(output.contains("Promedios por Alumno:"), "Debe mostrar promedios por alumno");
        assertTrue(output.contains("Examen con Mayor Promedio:"), "Debe identificar examen máximo");

        // Calculate expected values
        // Examen 1: (7.5 + 6.8 + 8.1 + 7.3) / 4 = 29.7 / 4 = 7.425 ≈ 7.43
        // Examen 2: (8.2 + 7.5 + 6.9 + 8.4) / 4 = 31.0 / 4 = 7.75
        // Examen 3: (6.9 + 8.1 + 7.2 + 7.8) / 4 = 30.0 / 4 = 7.50

        // Alumno 1: (7.5 + 8.2 + 6.9) / 3 = 22.6 / 3 ≈ 7.53
        // Alumno 2: (6.8 + 7.5 + 8.1) / 3 = 22.4 / 3 ≈ 7.47
        // Alumno 3: (8.1 + 6.9 + 7.2) / 3 = 22.2 / 3 = 7.40
        // Alumno 4: (7.3 + 8.4 + 7.8) / 3 = 23.5 / 3 ≈ 7.83

        assertTrue(output.contains("Examen 1: 7.43") || output.contains("Examen 1: 7.42"),
                "Examen 1 debe promediar ~7.43");
        assertTrue(output.contains("Examen 2: 7.75"),
                "Examen 2 debe promediar 7.75 (máximo)");
        assertTrue(output.contains("Examen 3: 7.50"),
                "Examen 3 debe promediar 7.50");

        assertTrue(output.contains("Alumno 1: 7.53"),
                "Alumno 1 debe promediar ~7.53");
        assertTrue(output.contains("Alumno 4: 7.83"),
                "Alumno 4 debe promediar ~7.83");

        // Examen 2 debe ser el máximo (7.75)
        assertTrue(output.contains("Examen: 2"),
                "CRÍTICO: Examen 2 tiene el máximo promedio");
        assertTrue(output.contains("Promedio: 7.75"),
                "Máximo promedio debe ser 7.75");
    }
}
