# App-AppTest - Ejercicios de Programación Estructurada en Java

## Estructura del Proyecto

Este es un **proyecto Maven multi-módulo** donde todos los ejercicios comparten una única configuración padre.

```
App-AppTest/
├── pom.xml                      (POM PADRE - Define toda la configuración)
├── pom-template.xml             (Plantilla para nuevos ejercicios)
├── .project                     (Configuración del workspace)
├── EJEMPLO_1_5_PAG_23/
│   ├── pom.xml                  (POM mínimo que hereda del padre)
│   └── src/main/java/com/javatutor/App.java
├── EJEMPLO_3_3_PAG_113/
│   ├── pom.xml
│   └── src/main/java/com/javatutor/App.java
├── PROBLEMA_1_6_PAG_44/
│   ├── pom.xml
│   └── src/main/java/com/javatutor/App.java
└── ... (más ejercicios)
```

## ¿Cómo agregar un nuevo ejercicio?

### 1. Crear la carpeta con la estructura estándar:
```
NOMBRE_EJERCICIO_X_X_PAG_XXX/
└── src/
    ├── main/java/com/javatutor/App.java
    └── test/java/com/javatutor/AppTest.java
```

### 2. Copiar la plantilla `pom-template.xml` a `pom.xml`:
```bash
cp pom-template.xml NOMBRE_EJERCICIO_X_X_PAG_XXX/pom.xml
```

### 3. Editar el `pom.xml` de la carpeta:
- Cambiar `<artifactId>` a algo único (ej: `problema-2-1-pag-77`)
- Cambiar `<name>` al nombre del ejercicio
- Cambiar `<description>` con la descripción

### 4. Agregar el módulo al POM padre:
En `/App-AppTest/pom.xml`, agregar a la sección `<modules>`:
```xml
<module>NOMBRE_EJERCICIO_X_X_PAG_XXX</module>
```

### 5. Crear la clase `App.java`:
Mantener el package `com.javatutor` y la clase `App` con método `main`.

## Compilar y ejecutar

### Compilar todo el proyecto:
```bash
cd App-AppTest
mvn clean compile
```

### Compilar un ejercicio específico:
```bash
mvn clean compile -pl EJEMPLO_1_5_PAG_23
```

### Ejecutar un ejercicio específico:
```bash
mvn exec:java -pl EJEMPLO_1_5_PAG_23 -Dexec.mainClass="com.javatutor.App"
```

### Empaquetar un ejercicio (generar JAR):
```bash
mvn clean package -pl EJEMPLO_1_5_PAG_23
```

### Ejecutar el JAR generado:
```bash
java -jar EJEMPLO_1_5_PAG_23/target/ejemplo-1-5-pag-23-1.0-SNAPSHOT.jar
```

## Configuración global (POM Padre)

El archivo `/App-AppTest/pom.xml` define:
- **Versión de Java**: 11
- **Encoding**: UTF-8
- **Dependencias compartidas**: JUnit 4.13.2
- **Plugins**: Maven Compiler Plugin y Maven JAR Plugin
- **Módulos**: Todos los ejercicios

Todos los ejercicios heredan automáticamente esta configuración.

## Ventajas de esta estructura

✅ Un solo `pom.xml` padre para configuración global
✅ POMs mínimos en cada ejercicio
✅ Fácil de agregar nuevos ejercicios
✅ Compilación y ejecución desde la raíz
✅ Consistencia en todas las carpetas
✅ Gestión centralizada de versiones y dependencias
