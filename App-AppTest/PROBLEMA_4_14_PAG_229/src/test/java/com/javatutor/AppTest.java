package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class AppTest {

    // Datos de prueba: 3 meses x 3 departamentos (según ejemplo en HTML)
    private String testData = "3\n3\n1500\n2000\n1800\n1600\n2100\n1900\n1700\n2200\n2000\n";

    private String runAppWithInput(String input) {
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        try {
            App.main(new String[] {});
        } catch (Exception e) {
            System.setOut(originalOut);
            fail("El programa lanzó una excepción: " + e.getMessage());
        }

        System.setIn(System.in);
        System.setOut(originalOut);

        return outContent.toString();
    }

    // Inciso A): Mayor costo Departamento 1
    @Test
    void testMayorCostoDulces() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("INCISO A)"),
                "Debe mostrar sección INCISO A");
        assertTrue(output.contains("1700"),
                "Debe encontrar mayor costo: 1700");
    }

    @Test
    void testMayorCostoDulcesValor() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("Costo:"),
                "Debe mostrar etiqueta Costo");
        assertTrue(output.contains("1700.0"),
                "Debe mostrar valor 1700.0");
    }

    // Inciso B): Promedio Departamento 2
    @Test
    void testPromedioBebidas() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("INCISO B)"),
                "Debe mostrar sección INCISO B");
        assertTrue(output.contains("2100"),
                "Debe calcular promedio: 2100");
    }

    @Test
    void testPromedioFormat() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("Promedio:"),
                "Debe mostrar etiqueta Promedio");
        assertTrue(output.contains("2100.00"),
                "Debe mostrar formato con dos decimales");
    }

    // Inciso C): Mayor y Menor costo Departamento 2
    @Test
    void testMayorCostoBebidas() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("INCISO C)"),
                "Debe mostrar sección INCISO C");
        assertTrue(output.contains("2200"),
                "Debe encontrar mayor costo: 2200");
    }

    @Test
    void testMenorCostoBebidas() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("INCISO C)"),
                "Debe mostrar sección INCISO C");
        assertTrue(output.contains("2000.0"),
                "Debe encontrar menor costo: 2000");
    }

    @Test
    void testMayorMenorLabels() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("Mayor:"),
                "Debe mostrar etiqueta Mayor");
        assertTrue(output.contains("Menor:"),
                "Debe mostrar etiqueta Menor");
    }

    // Inciso D): Menor costo último mes
    @Test
    void testMenorCostoUltimoMes() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("INCISO D)"),
                "Debe mostrar sección INCISO D");
        assertTrue(output.contains("Departamento:"),
                "Debe mostrar etiqueta Departamento");
    }

    @Test
    void testDepartamentoMenorCosto() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("Departamento: 1"),
                "Departamento con menor costo debe ser 1");
        assertTrue(output.contains("1700.0"),
                "Costo menor del último mes debe ser 1700");
    }

    @Test
    void testAllSectionsPresent() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("INCISO A)") &&
                output.contains("INCISO B)") &&
                output.contains("INCISO C)") &&
                output.contains("INCISO D)"),
                "Deben estar presentes todos los incisos");
    }
}
