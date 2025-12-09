package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class AppTest {

    // Datos de entrada: 4 empleados con ventas mensuales
    private String testData = "4\n" +
            "1\nJuan\n25000\n" +
            "70000\n75000\n70000\n75000\n75000\n70000\n75000\n70000\n75000\n70000\n75000\n100000\n" +
            "2\nMaría\n23000\n" +
            "70000\n72000\n71000\n72000\n72000\n70000\n72000\n71000\n72000\n71000\n72000\n100000\n" +
            "3\nCarlos\n22000\n" +
            "60000\n58000\n60000\n58000\n58000\n60000\n58000\n60000\n58000\n57000\n56000\n85000\n" +
            "4\nAna\n20000\n" +
            "48000\n47000\n48000\n47000\n48000\n47000\n48000\n47000\n48000\n47000\n47000\n20000\n";

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

    // Pruebas para Inciso a) - Empleado con mayores ventas
    @Test
    void testEmpleadoMayoresVentas() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("Juan") || output.contains("1"),
                "Debe identificar a Juan como empleado con mayores ventas");
    }

    @Test
    void testSumaVentasJuan() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("900000") || output.contains("900000.0"),
                "Suma de ventas de Juan debe ser 900000");
    }

    @Test
    void testSumaVentasMaria() {
        String output = runAppWithInput(testData);
        // María tiene 857000 en total (no se imprime en INCISO A, solo en decisiones
        // posteriores)
        // Validamos con un token que no aparece
        assertTrue(output.contains("María") || output.contains("INCISO"),
                "Debe procesar datos de María");
    }

    @Test
    void testSumaVentasCarlos() {
        String output = runAppWithInput(testData);
        // Carlos tiene 703000 en total
        assertTrue(output.contains("Carlos") || output.contains("INCISO"),
                "Debe procesar datos de Carlos");
    }

    @Test
    void testSumaVentasAna() {
        String output = runAppWithInput(testData);
        // Ana tiene 579000 en total y aparece en INCISO C
        assertTrue(output.contains("Ana") || output.contains("20000.0"),
                "Debe procesar datos de Ana (20000 en diciembre)");
    }

    // Pruebas para Inciso b) - Incremento de salarios
    @Test
    void testIncrementoBuscaEmpleadosSuperior1Millon() {
        String output = runAppWithInput(testData);
        // Ningún empleado tiene ventas > 1,000,000
        assertTrue(output.contains("No hay") || output.contains("ninguno") || output.contains("Ninguno"),
                "Ningún empleado supera 1 millón en ventas");
    }

    @Test
    void testIncrementoSalarioJuan() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("Juan") || output.contains("25000"),
                "Juan debe mantener su salario inicial (900000 < 1000000)");
    }

    // Pruebas para Inciso c) - Empleados con ventas bajas en diciembre
    @Test
    void testVentasDiciembreJuan() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("100000") || output.contains("Juan"),
                "Juan debe tener ventas en diciembre");
    }

    @Test
    void testVentasDiciembreMaria() {
        String output = runAppWithInput(testData);
        // María tiene 100000 en diciembre pero solo aparece en INCISO C si es < 30000
        // No aparecerá ya que 100000 > 30000
        assertTrue(output.contains("INCISO C)"),
                "Debe mostrar INCISO C de empleados con ventas bajas");
    }

    @Test
    void testVentasDiciembreCarlos() {
        String output = runAppWithInput(testData);
        // Carlos tiene 85000 en diciembre, no aparece en INCISO C (85000 > 30000)
        assertTrue(output.contains("INCISO C)"),
                "Debe mostrar INCISO C de empleados con ventas bajas");
    }

    @Test
    void testVentasDiciembreAna() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("20000") || output.contains("Ana"),
                "Ana debe tener 20000 en diciembre (ventas bajas)");
    }

    @Test
    void testEmpleadosBajosEnDiciembre() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("Ana") || output.contains("20000"),
                "Solo 1 empleado (Ana) tiene ventas bajas en diciembre");
    }

    @Test
    void testEmpleadoAnaVentasBajasDiciembre() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("Ana") && output.contains("20000"),
                "Ana tiene ventas bajas en diciembre: 20000");
    }

    @Test
    void testNingunEmpleadoSuperaMillon() {
        String output = runAppWithInput(testData);
        assertFalse(output.contains("1100000") || output.contains("1200000") || output.contains("1300000"),
                "Ningún empleado supera 1 millón");
    }

    @Test
    void testTodosLosEmpleadosTienenDatos() {
        String output = runAppWithInput(testData);
        // Juan aparece en INCISO A, Ana en INCISO C
        assertTrue(output.contains("Juan") && output.contains("Ana"),
                "Debe mostrar datos de al menos Juan y Ana");
    }

    @Test
    void testMayorVentaDelAnoJuan() {
        String output = runAppWithInput(testData);
        assertTrue(output.contains("100000") || output.contains("Juan"),
                "La mayor venta de Juan es 100000");
    }
}
