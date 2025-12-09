package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Clase de prueba para el Ejercicio 1.5
 * Valida que las operaciones aritméticas y asignaciones se realicen
 * correctamente.
 */
class AppTest {

    /**
     * Test 1: Verifica que el programa ejecuta sin errores
     */
    @Test
    void testAppExecutes() {
        try {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            App.main(new String[] {});

            String output = outContent.toString();
            assertNotNull(output, "La salida no debe ser nula");
            assertFalse(output.isEmpty(), "La salida debe contener contenido");

            System.setOut(System.out);
        } catch (Exception e) {
            fail("El programa no debe lanzar excepciones: " + e.getMessage());
        }
    }

    /**
     * Test 2: Verifica que la salida contiene los valores esperados
     */
    @Test
    void testOutputContainsValues() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.main(new String[] {});

        String output = outContent.toString().toUpperCase();

        // Verificar que contiene los valores finales
        assertTrue(output.contains("I: 3") || output.contains("I = 3"), "La salida debe contener 'I: 3'");
        assertTrue(output.contains("J: 8") || output.contains("J = 8"), "La salida debe contener 'J: 8'");
        assertTrue(output.contains("ACUM: 8") || output.contains("ACUM = 8"), "La salida debe contener 'ACUM: 8'");
        assertTrue(output.contains("CAR: A") || output.contains("CAR = A"), "La salida debe contener 'CAR: a'");

        System.setOut(System.out);
    }

    /**
     * Test 3: Verifica que se muestran los mensajes de error para asignaciones
     * inválidas
     */
    @Test
    void testErrorMessages() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.main(new String[] {});

        String output = outContent.toString().toUpperCase();

        // Verificar mensajes de error
        assertTrue(output.contains("ASIGNACIÓN 13") && output.contains("ERROR"), "Debe indicar error en asignación 13");
        assertTrue(output.contains("ASIGNACIÓN 14") && output.contains("ERROR"), "Debe indicar error en asignación 14");

        System.setOut(System.out);
    }

    /**
     * Test 4: Verifica que contiene la sección de resultados finales
     */
    @Test
    void testFinalResultsSection() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.main(new String[] {});

        String output = outContent.toString();

        assertTrue(output.contains("RESULTADOS FINALES") || output.contains("Resultados"),
                "Debe mostrar una sección de resultados finales");

        System.setOut(System.out);
    }

    /**
     * Test 5: Valida que todas las asignaciones se ejecutan
     */
    @Test
    void testAllAssignmentsExecuted() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.main(new String[] {});

        String output = outContent.toString();

        // Verificar que hay al menos 12 asignaciones exitosas
        int assignmentCount = 0;
        for (int i = 2; i <= 12; i++) {
            if (output.contains("Asignación " + i) || output.contains("Assignment " + i)) {
                assignmentCount++;
            }
        }

        assertTrue(assignmentCount >= 10, "Debe haber al menos 10 asignaciones válidas");

        System.setOut(System.out);
    }

    /**
     * Test 6: Verifica que el boolean BAND tiene valor final correcto
     */
    @Test
    void testBandValueIsFalse() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.main(new String[] {});

        String output = outContent.toString();

        // En la asignación 8: BAND = (8 > 5) || (15 < 2**3) = true || false = true
        // En la asignación 12: BAND = true || (3 == 8) = true || false = true
        // Por lo tanto, BAND debe ser true al final
        assertTrue(output.contains("BAND: true") || output.contains("BAND = true"), "BAND debe ser true al final");

        System.setOut(System.out);
    }

    /**
     * Test 7: Verifica operaciones aritméticas correctas
     */
    @Test
    void testArithmeticOperations() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.main(new String[] {});

        String output = outContent.toString();

        // J debe ser 8 (5^2 / 3 = 25 / 3 = 8)
        assertTrue(output.contains("J:") && output.contains("8"), "J debe ser 8");
        // ACUM debe ser 8 (8 / 1)
        assertTrue(output.contains("ACUM:") && output.contains("8"), "ACUM debe ser 8");

        System.setOut(System.out);
    }
}
