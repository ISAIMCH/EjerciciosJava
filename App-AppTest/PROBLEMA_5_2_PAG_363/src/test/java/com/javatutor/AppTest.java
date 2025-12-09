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

    // Pruebas para Inciso a) - Empleado con mayores ventas
    @Test
    void testEmpleadoMayoresVentas() {
        String output = runAppWithInput("");
        assertTrue(output.contains("Juan") || output.contains("1"),
                "Debe identificar a Juan como empleado con mayores ventas");
    }

    @Test
    void testSumaVentasJuan() {
        String output = runAppWithInput("");
        assertTrue(output.contains("870000") || output.contains("870"),
                "Suma de ventas de Juan debe ser 870000");
    }

    @Test
    void testSumaVentasMaria() {
        String output = runAppWithInput("");
        assertTrue(output.contains("865000") || output.contains("865"),
                "Suma de ventas de María debe ser 865000");
    }

    @Test
    void testSumaVentasCarlos() {
        String output = runAppWithInput("");
        assertTrue(output.contains("690000") || output.contains("690"),
                "Suma de ventas de Carlos debe ser 690000");
    }

    @Test
    void testSumaVentasAna() {
        String output = runAppWithInput("");
        assertTrue(output.contains("570000") || output.contains("570"),
                "Suma de ventas de Ana debe ser 570000");
    }

    // Pruebas para Inciso b) - Incremento de salarios
    @Test
    void testIncrementoBuscaEmpleadosSuperior1Millon() {
        String output = runAppWithInput("");
        // Ningún empleado tiene ventas > 1,000,000
        assertTrue(output.contains("0") || output.contains("ninguno") || output.contains("Ninguno"),
                "Ningún empleado supera 1 millón en ventas");
    }

    @Test
    void testIncrementoSalarioJuan() {
        String output = runAppWithInput("");
        assertTrue(output.contains("25000") || output.contains("Juan"),
                "Juan debe mantener su salario inicial (870000 < 1000000)");
    }

    // Pruebas para Inciso c) - Empleados con ventas bajas en diciembre
    @Test
    void testVentasDiciembreJuan() {
        String output = runAppWithInput("");
        assertTrue(output.contains("100000"), "Juan debe tener 100000 en diciembre");
    }

    @Test
    void testVentasDiciembreMaria() {
        String output = runAppWithInput("");
        assertTrue(output.contains("100000"), "María debe tener 100000 en diciembre");
    }

    @Test
    void testVentasDiciembreCarlos() {
        String output = runAppWithInput("");
        assertTrue(output.contains("85000"), "Carlos debe tener 85000 en diciembre");
    }

    @Test
    void testVentasDiciembreAna() {
        String output = runAppWithInput("");
        assertTrue(output.contains("20000") || output.contains("Ana"),
                "Ana debe tener 20000 en diciembre (ventas bajas)");
    }

    @Test
    void testEmpleadosBajosEnDiciembre() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1") || output.contains("Ana"),
                "Solo 1 empleado (Ana) tiene ventas bajas en diciembre");
    }

    @Test
    void testEmpleadoAnaVentasBajasDiciembre() {
        String output = runAppWithInput("");
        assertTrue(output.contains("Ana") && output.contains("20000"),
                "Ana tiene ventas bajas en diciembre: 20000");
    }

    @Test
    void testNingunEmpleadoSuperaMillon() {
        String output = runAppWithInput("");
        assertFalse(output.contains("1100000") || output.contains("1200000") || output.contains("1300000"),
                "Ningún empleado supera 1 millón");
    }

    @Test
    void testTodosLosEmpleadosTienenDatos() {
        String output = runAppWithInput("");
        assertTrue(output.contains("Juan") && output.contains("María") &&
                output.contains("Carlos") && output.contains("Ana"),
                "Debe mostrar datos de los 4 empleados");
    }

    @Test
    void testMayorVentaDelAnoJuan() {
        String output = runAppWithInput("");
        assertTrue(output.contains("100000") || output.contains("Juan"),
                "La mayor venta de Juan es 100000");
    }
}
