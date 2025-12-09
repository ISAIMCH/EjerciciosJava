# Pruebas Unitarias - Ejercicio 1.5

## Descripción

El archivo `AppTest.java` contiene 7 pruebas unitarias que validan la correcta ejecución del ejercicio 1.5.

## Tests Implementados

### 1. `testAppExecutes()`
**Objetivo:** Verifica que el programa ejecuta sin lanzar excepciones.
- Captura la salida estándar
- Ejecuta `App.main()`
- Valida que hay salida

### 2. `testOutputContainsValues()`
**Objetivo:** Verifica que los valores finales son correctos.
- Busca en la salida los valores esperados:
  - `I: 3`
  - `J: 8`
  - `ACUM: 8`
  - `CAR: a`

### 3. `testErrorMessages()`
**Objetivo:** Valida que se muestren los errores en asignaciones inválidas.
- Verifica mensaje de error para asignación 13 (I = REA)
- Verifica mensaje de error para asignación 14 (CAR = J)

### 4. `testFinalResultsSection()`
**Objetivo:** Verifica que hay una sección clara de resultados finales.
- Busca "RESULTADOS FINALES" o "Resultados" en la salida

### 5. `testAllAssignmentsExecuted()`
**Objetivo:** Valida que se ejecutan todas las asignaciones válidas (al menos 10).
- Busca referencias a asignaciones 2-12
- Cuenta las que aparecen en la salida

### 6. `testBandValueIsFalse()`
**Objetivo:** Verifica que el valor final de BAND es false.
- En la tabla final: I=3, J=8 (diferentes)
- Entonces: BAND = BAND || (I == J) = true || false = false

### 7. `testArithmeticOperations()`
**Objetivo:** Valida que las operaciones aritméticas son correctas.
- J debe ser 8 (5² / 3 = 25 / 3 = 8)
- ACUM debe ser 8 (J / I = 8 / 1 = 8)

## Cómo ejecutar los tests

### Ejecutar todos los tests del proyecto:
```bash
cd App-AppTest
mvn test
```

### Ejecutar solo los tests de EJEMPLO_1_5:
```bash
mvn test -pl EJEMPLO_1_5_PAG_23
```

### Ejecutar un test específico:
```bash
mvn test -pl EJEMPLO_1_5_PAG_23 -Dtest=AppTest#testAppExecutes
```

### Generar reporte de cobertura:
```bash
mvn clean test jacoco:report -pl EJEMPLO_1_5_PAG_23
```

## Resultados Esperados

✅ Todos los 7 tests deben pasar:
- `testAppExecutes` - PASS
- `testOutputContainsValues` - PASS
- `testErrorMessages` - PASS
- `testFinalResultsSection` - PASS
- `testAllAssignmentsExecuted` - PASS
- `testBandValueIsFalse` - PASS
- `testArithmeticOperations` - PASS

## Notas Importantes

- Los tests capturan `System.out` para analizar la salida
- Se valida que el programa produce salida válida
- Se verifican los valores finales de las variables
- Se validan los mensajes de error para asignaciones inválidas

## Agregar más tests

Para agregar más tests, simplemente crea un nuevo método con anotación `@Test`:

```java
@Test
public void testNuevoTest() {
    // Tu código de test aquí
    assertTrue(condicion, "Mensaje si falla");
}
```

Luego ejecuta `mvn test` para incluirlo en las pruebas.
