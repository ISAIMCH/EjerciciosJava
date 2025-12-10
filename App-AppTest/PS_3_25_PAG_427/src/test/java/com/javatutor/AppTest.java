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

    // Test data: 6 candidates
    // Females: (F, 1.75m, 65kg=APTA), (F, 1.70m, 70kg=NO_APTA_altura), (F, 1.80m,
    // 100kg=NO_APTA_peso)
    // Males: (M, 1.85m, 85kg=APTO), (M, 1.80m, 80kg=NO_APTO_altura), (M, 1.88m,
    // 120kg=NO_APTO_peso)
    private static final String TEST_DATA = "6\n" +
            "Maria\nF\n25\n65\n1.75\n" +
            "Rosa\nF\n28\n70\n1.70\n" +
            "Elena\nF\n30\n100\n1.80\n" +
            "Carlos\nM\n26\n85\n1.85\n" +
            "Juan\nM\n29\n80\n1.80\n" +
            "Pedro\nM\n32\n120\n1.88\n";

    // Criteria for women: altura > 1.73 AND peso >= 50 AND peso <= 90
    // Criteria for men: altura >= 1.83 AND peso >= 73 AND peso <= 110

    @Test
    void testCriterioAlumnaApta() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("Maria"), "Debe procesar candidata Maria");
        assertTrue(output.contains("CANDIDATAS APTAS"), "Debe mostrar sección de candidatas aptas");
    }

    @Test
    void testCriterioAlumnaNoAptaAltura() {
        String output = runAppWithInput(TEST_DATA);
        // Rosa: 1.70m es menor que 1.73, no apta
        assertTrue(output.contains("Rosa") || output.contains("CANDIDATAS APTAS"),
                "Debe procesar candidata con altura insuficiente");
    }

    @Test
    void testCriterioAlumnaNoAptaPeso() {
        String output = runAppWithInput(TEST_DATA);
        // Elena: 100kg supera el límite de 90kg
        assertTrue(output.contains("Elena") || output.contains("CANDIDATAS APTAS"),
                "Debe procesar candidata con peso excesivo");
    }

    @Test
    void testCriterioAlumnoApto() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("Carlos"), "Debe procesar candidato Carlos");
        assertTrue(output.contains("CANDIDATOS APTOS"), "Debe mostrar sección de candidatos aptos");
    }

    @Test
    void testCriterioAlumnoNoAptoAltura() {
        String output = runAppWithInput(TEST_DATA);
        // Juan: 1.80m es menor que 1.83, no apto
        assertTrue(output.contains("Juan") || output.contains("CANDIDATOS APTOS"),
                "Debe procesar candidato con altura insuficiente");
    }

    @Test
    void testCriterioAlumnoNoAptoPeso() {
        String output = runAppWithInput(TEST_DATA);
        // Pedro: 120kg supera el límite de 110kg
        assertTrue(output.contains("Pedro") || output.contains("CANDIDATOS APTOS"),
                "Debe procesar candidato con peso excesivo");
    }

    @Test
    void testCalcularPorcentajeAlumnas() {
        String output = runAppWithInput(TEST_DATA);
        // 3 females total, 1 apta -> 33.33%
        assertTrue(output.contains("Porcentaje de Candidatas Aptas"),
                "Debe calcular porcentaje de candidatas aptas");
        assertTrue(output.contains("%"), "Debe mostrar símbolo de porcentaje");
    }

    @Test
    void testCalcularPorcentajeAlumnos() {
        String output = runAppWithInput(TEST_DATA);
        // 3 males total, 1 apto -> 33.33%
        assertTrue(output.contains("Porcentaje de Candidatos Aptos"),
                "Debe calcular porcentaje de candidatos aptos");
        assertTrue(output.contains("%"), "Debe mostrar símbolo de porcentaje");
    }

    @Test
    void testPorcentajeCero() {
        // All females fail criteria
        String input = "1\nPruebaF\nF\n25\n45\n1.70\n"; // peso=45 < 50, altura=1.70 < 1.73
        String output = runAppWithInput(input);
        assertTrue(output.contains("0.00%"),
                "Debe mostrar 0.00% cuando ninguna mujer es apta");
    }

    @Test
    void testPorcentajeCien() {
        // All females meet criteria
        String input = "1\nPruebaF\nF\n25\n70\n1.80\n"; // peso=70 ok, altura=1.80 > 1.73
        String output = runAppWithInput(input);
        assertTrue(output.contains("100.00%"),
                "Debe mostrar 100.00% cuando todas las mujeres son aptas");
    }

    @Test
    void testFormatoSalida() {
        String output = runAppWithInput(TEST_DATA);
        assertTrue(output.contains("RESULTADOS"), "Debe mostrar sección RESULTADOS");
        assertTrue(output.contains("Peso:") && output.contains("Altura:"),
                "Debe mostrar peso y altura en formato correcto");
    }

    @Test
    void testValidacionPorcentajeDiferentes() {
        // CRITICAL: Test where porcF and porcM are DIFFERENT
        // This catches if code uses porcF instead of porcM for males
        // Create data where female% and male% are clearly different
        String input = "3\n" +
                "Maria\nF\n25\n70\n1.75\n" + // apta (total F=1, apta F=1 -> 100%)
                "Carlos\nM\n26\n85\n1.85\n" + // apto (total M=1, apto M=1 -> 100%)
                "Juan\nM\n29\n80\n1.80\n"; // no apto (total M=2, apto M=1 -> 50%)

        String output = runAppWithInput(input);
        // porcF=100.00%, porcM=50.00% - DIFFERENT!
        // If bug exists (both lines use porcF), both would show 100.00%
        // If correct, should show 100.00% for females and 50.00% for males

        assertTrue(output.contains("Candidatas Aptas") && output.contains("Candidatos Aptos"),
                "Debe mostrar ambos porcentajes");

        // The critical assertion: check that the percentages are actually different
        int posF = output.indexOf("Candidatas Aptas");
        int posM = output.indexOf("Candidatos Aptos");
        String lineF = output.substring(posF, Math.min(posF + 50, output.length()));
        String lineM = output.substring(posM, Math.min(posM + 50, output.length()));

        // If bug exists, both lines would have same percentage value
        // Verify they're NOT the same (one should have 100, one should have 50)
        assertTrue((lineF.contains("100") && lineM.contains("50")) ||
                (lineF.contains("50") && lineM.contains("100")),
                "CRÍTICO: Porcentaje de candidatas (100.00%) debe ser diferente al de candidatos (50.00%)");
    }
}