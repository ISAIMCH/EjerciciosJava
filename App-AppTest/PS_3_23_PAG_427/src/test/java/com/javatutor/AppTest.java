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

    // Test data: 5 students with 5 grades each
    // Student 1001: grades 80, 90, 85, 88, 92 -> sum=435, avg=87.0000
    // Student 1002: grades 75, 78, 80, 79, 75 -> sum=387, avg=77.4000
    // Student 1003: grades 95, 98, 96, 99, 97 -> sum=485, avg=97.0000
    // Student 1004: grades 70, 70, 70, 70, 70 -> sum=350, avg=70.0000
    // Student 1005: grades 60, 60, 60, 60, 60 -> sum=300, avg=60.0000
    private static final String TEST_DATA = "5\n5\n" +
            "1001\n80 90 85 88 92\n" +
            "1002\n75 78 80 79 75\n" +
            "1003\n95 98 96 99 97\n" +
            "1004\n70 70 70 70 70\n" +
            "1005\n60 60 60 60 60\n";

    @Test
    void testPromedioAlumno1001() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("1001"), "Debe contener matrícula 1001");
        assertTrue(output.contains("87.0000") || output.contains("87.00"),
                "Debe calcular promedio para alumno 1001: 87.0000 (435/5)");
    }

    @Test
    void testPromedioAlumno1002() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("1002"), "Debe contener matrícula 1002");
        assertTrue(output.contains("77.4000") || output.contains("77.40"),
                "Debe calcular promedio para alumno 1002: 77.4000 (387/5)");
    }

    @Test
    void testPromedioAlumno1003() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("1003"), "Debe contener matrícula 1003");
        assertTrue(output.contains("97.0000") || output.contains("97.00"),
                "Debe calcular promedio para alumno 1003: 97.0000 (485/5)");
    }

    @Test
    void testPromedioAlumno1004() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("1004"), "Debe contener matrícula 1004");
        assertTrue(output.contains("70.0000") || output.contains("70.00"),
                "Debe calcular promedio para alumno 1004: 70.0000");
    }

    @Test
    void testPromedioAlumno1005() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("1005"), "Debe contener matrícula 1005");
        assertTrue(output.contains("60.0000") || output.contains("60.00"),
                "Debe calcular promedio para alumno 1005: 60.0000");
    }

    @Test
    void testBuclesAnidados() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("RESULTADOS"), "Debe mostrar sección RESULTADOS");
        assertTrue(output.contains("MAT="), "Debe mostrar matrícula con formato MAT=");
        assertTrue(output.contains("CAL:"), "Debe mostrar calificaciones con etiqueta CAL:");
    }

    @Test
    void testSumaCalificaciones() {
        String output = runAppWithInput(TEST_DATA);
        // Verify sums are calculated (shown via promedio calculations)
        assertTrue(output.contains("Promedio"), "Debe calcular promedios (suma/cantidad)");
        // Verify all 5 students have promedios calculated
        int countPromedios = output.split("Promedio:").length - 1;
        assertEquals(5, countPromedios, "Debe calcular promedio para 5 estudiantes");
    }

    @Test
    void testMostrarCalificacionesIndividuales() {
        String output = runAppWithInput(TEST_DATA);
        // Student 1001: 80.00 90.00 85.00 88.00 92.00
        assertTrue(output.contains("80.00") && output.contains("90.00") && output.contains("85.00"),
                "Debe mostrar calificaciones individuales formateadas a 2 decimales");
    }

    @Test
    void testFormatoPromedioConCuatroDecimales() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("87.0000") || output.contains("77.4000") ||
                output.contains("97.0000") || output.contains("70.0000") ||
                output.contains("60.0000"),
                "Debe mostrar promedio con 4 decimales (%.4f)");
    }

    @Test
    void testTodosLosAlumnosProcessados() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("1001") && output.contains("1002") &&
                output.contains("1003") && output.contains("1004") &&
                output.contains("1005"),
                "Debe procesar todos los 5 estudiantes");
    }

    @Test
    void testValidacionFormatoSalida() {
        String output = runAppWithInput(TEST_DATA);
        // Check format: "Alumno MAT=XXXX | CAL: X.XX X.XX ... | Promedio: X.XXXX"
        assertTrue(output.contains("Alumno MAT="), "Debe mostrar 'Alumno MAT='");
        assertTrue(output.contains("Promedio:"), "Debe mostrar 'Promedio:'");
        assertTrue(output.matches("(?s).*Alumno MAT=\\d+.*Promedio:.*"),
                "Debe tener formato correcto: Alumno MAT=... Promedio:...");
    }

    @Test
    void testValidacionCalculoPromedio() {
        // Test with specific values where wrong calculation would be caught
        // Student: califs 50, 50 -> promedio must be 50.0000, not 100.0000 or 25.0000
        String input = "1\n2\n9999\n50 50\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("50.0000") || output.contains("50.00"),
                "Debe calcular correctamente: (50+50)/2 = 50.0000, no 100 o 25");
        assertFalse(output.contains("100.0000"), "No debe duplicar la suma");
    }
}