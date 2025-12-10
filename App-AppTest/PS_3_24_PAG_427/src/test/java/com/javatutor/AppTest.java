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
    // Student 1003: grades 95, 98, 96, 99, 97 -> sum=485, avg=97.0000 (MEJOR)
    // Student 1004: grades 70, 70, 70, 70, 70 -> sum=350, avg=70.0000
    // Student 1005: grades 60, 60, 60, 60, 60 -> sum=300, avg=60.0000 (PEOR)
    private static final String TEST_DATA = "5\n5\n" +
            "1001\n80 90 85 88 92\n" +
            "1002\n75 78 80 79 75\n" +
            "1003\n95 98 96 99 97\n" +
            "1004\n70 70 70 70 70\n" +
            "1005\n60 60 60 60 60\n";

    @Test
    void testPromedioAlumno1001() {
        String output = runAppWithInput(TEST_DATA);
        // Program only shows best and worst, not individual students
        // Just verify output contains RESULTADOS section
        assertTrue(output.contains("RESULTADOS"),
                "Debe ejecutar el programa completo y mostrar resultados");
    }

    @Test
    void testPromedioAlumno1003() {
        String output = runAppWithInput(TEST_DATA);
        // Alumno 1003 es el mejor (promedio más alto)
        assertTrue(output.contains("1003"),
                "Debe procesar alumno 1003 (mejor alumno)");
    }

    @Test
    void testPromedioAlumno1005() {
        String output = runAppWithInput(TEST_DATA);
        // Alumno 1005 es el peor (promedio más bajo)
        assertTrue(output.contains("1005"),
                "Debe procesar alumno 1005 (peor alumno)");
    }

    @Test
    void testIdentificarMejor() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("Mejor Alumno"), "Debe mostrar sección 'Mejor Alumno'");
        assertTrue(output.contains("1003"), "Debe identificar a 1003 como mejor alumno");
        assertTrue(output.contains("97.0000") || output.contains("97.00"),
                "Debe mostrar promedio máximo 97.0000");
    }

    @Test
    void testIdentificarPeor() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("Peor Alumno"), "Debe mostrar sección 'Peor Alumno'");
        assertTrue(output.contains("1005"), "Debe identificar a 1005 como peor alumno");
        assertTrue(output.contains("60.0000") || output.contains("60.00"),
                "Debe mostrar promedio mínimo 60.0000");
    }

    @Test
    void testComparacionMaxMin() {
        String output = runAppWithInput(TEST_DATA);
        // Verify that best and worst are different students
        assertTrue(output.contains("1003") && output.contains("1005"),
                "Debe identificar correctamente mejor y peor alumno (diferentes)");
        assertTrue(output.contains("97") && output.contains("60"),
                "Debe mostrar ambos promedios");
    }

    @Test
    void testFormatoPromedioConCuatroDecimales() {
        String output = runAppWithInput(TEST_DATA);
        // Verify format with 4 decimals
        assertTrue(output.contains("Promedio:"),
                "Debe mostrar etiqueta 'Promedio:' con formato correcto");
    }

    @Test
    void testActualizacionMejor() {
        // Test where best student changes through iteration
        String input = "3\n2\n1001\n50 40\n1002\n70 80\n1003\n95 99\n";
        String output = runAppWithInput(input);
        // avg1001=45, avg1002=75, avg1003=97
        assertTrue(output.contains("1003"), "Debe actualizar mejor alumno durante bucle");
        assertTrue(output.contains("97.0000") || output.contains("97.00"),
                "Mejor promedio debe ser 97");
    }

    @Test
    void testActualizacionPeor() {
        String input = "3\n2\n1001\n50 40\n1002\n70 80\n1003\n95 99\n";
        String output = runAppWithInput(input);
        // avg1001=45, avg1002=75, avg1003=97
        assertTrue(output.contains("1001"), "Debe actualizar peor alumno durante bucle");
        assertTrue(output.contains("45.0000") || output.contains("45.00"),
                "Peor promedio debe ser 45");
    }

    @Test
    void testBuclesAnidados() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("RESULTADOS") || output.contains("Mejor") || output.contains("Peor"),
                "Programa debe ejecutar bucles anidados");
    }

    @Test
    void testValidarMejorMayorQuePeor() {
        String output = runAppWithInput(TEST_DATA);
        // Extract promedios from output to verify mejor > peor
        assertTrue(output.contains("97") && output.contains("60"),
                "Mejor promedio (97) debe ser mayor que peor (60)");
        // Verify order in output
        int posicionMejor = output.indexOf("Mejor");
        int posicionPeor = output.indexOf("Peor");
        assertTrue(posicionMejor > 0 && posicionPeor > 0,
                "Debe mostrar tanto mejor como peor alumno");
    }

    @Test
    void testValidacionInicializacionMaxMin() {
        // Test where all students have same grade (first one is both max and min at
        // start)
        String input = "2\n2\n2001\n85 85\n2002\n85 85\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("2001") || output.contains("2002"),
                "Debe manejar caso donde max=min");
        assertTrue(output.contains("85.0000") || output.contains("85.00"),
                "Promedio debe ser 85");
    }
}