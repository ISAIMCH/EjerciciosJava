# Ejercicios de Programación Estructurada en Java

## 📋 Estructura del Proyecto

Este proyecto ha sido reorganizado para separar cada ejercicio en su propio archivo HTML, facilitando el mantenimiento y distribución en Git.

### 🗂️ Archivos Principales

- **`index-main.html`** - Página principal con grid de 16 ejercicios y links a cada uno
- **`index.html`** - Redirige automáticamente a `index-main.html` (compatibilidad)
- **`ejercicio-X-Y.html`** - Archivos individuales para cada uno de los 16 ejercicios

### 📚 Ejercicios Disponibles

| # | Archivo | Nombre |
|---|---------|--------|
| 1.5 | `ejercicio-1-5.html` | Prueba de Escritorio (Traza de Memoria) |
| 1.6 | `ejercicio-1-6.html` | Segundos en Días |
| 2.1 | `ejercicio-2-1.html` | El Grillo como Termómetro |
| 3.3 | `ejercicio-3-3.html` | Contar Ceros |
| 3.14 | `ejercicio-3-14.html` | Taquilla del Estadio |
| 4.1 | `ejercicio-4-1.html` | Lista Sin Repeticiones |
| 4.14 | `ejercicio-4-14.html` | Producción por Departamentos |
| 5.2 | `ejercicio-5-2.html` | Gestión de Empleados |
| PS 2.1 | `ejercicio-ps-2-1.html` | Calcular Tangente |
| PS 2.2 | `ejercicio-ps-2-2.html` | Calcular Cotangente |
| PS 3.23 | `ejercicio-ps-3-23.html` | Promedio de Alumnos |
| PS 3.24 | `ejercicio-ps-3-24.html` | Mejor y Peor Promedio |
| PS 3.25 | `ejercicio-ps-3-25.html` | Aptitudes para Básquetbol |
| PS 4.3 | `ejercicio-ps-4-3.html` | Contar Positivos, Negativos y Nulos |
| PS 4.19 | `ejercicio-ps-4-19.html` | Análisis de Calificaciones |
| PS 4.35 | `ejercicio-ps-4-35.html` | Directorio Telefónico |

## 🚀 Cómo Usar

### Opción 1: Abrir desde el navegador
```
1. Abre "index-main.html" en tu navegador
2. Haz clic en cualquier ejercicio para ver sus detalles
3. Cada ejercicio tiene un botón "📊 Ver diagrama de flujo" para visualizar el diagrama
4. Usa el botón "← Volver al menú" en cada página para regresar
```

### Opción 2: Acceso directo
Si sabes el número del ejercicio, puedes abrir directamente:
```
- ejercicio-1-5.html
- ejercicio-2-1.html
- etc.
```

## 📁 Estructura de Directorios

```
EjerciciosJava/
├── index.html                 (Redirige a index-main.html)
├── index-main.html            (Página principal con grid de ejercicios)
├── ejercicio-1-5.html         (Ejercicios individuales)
├── ejercicio-1-6.html
├── ... (más ejercicios)
├── generar_ejercicios.py      (Script de generación)
├── style/
│   └── styles_desc.css        (Estilos compartidos)
├── img/
│   ├── 1.png                  (Diagramas de flujo 1-16)
│   ├── 2.png
│   └── ... (más imágenes)
├── diagramas/
│   ├── ejercicio_1_5.dot      (Diagramas en formato DOT)
│   └── ... (más archivos DOT)
└── README.md
```

## 🎨 Características

- ✅ **Interfaz limpia y moderna** - Grid responsivo con cards de ejercicios
- ✅ **Navegación intuitiva** - Menú principal + botón "volver" en cada página
- ✅ **Diagramas de flujo** - Modal para ver diagramas en alta calidad
- ✅ **Responsive design** - Funciona en desktop, tablet y mobile
- ✅ **Fácil mantenimiento** - Cada ejercicio es un archivo independiente
- ✅ **Compatible con Git** - Archivos pequeños, fáciles de trackear

## 🔧 Mantenimiento

### Actualizar un ejercicio
1. Abre el archivo `ejercicio-X-Y.html`
2. Edita el contenido según sea necesario
3. Guarda los cambios
4. El cambio es automáticamente visible

### Agregar un nuevo ejercicio
1. Copia la plantilla de cualquier archivo `ejercicio-X-Y.html`
2. Actualiza el número y título
3. Agrega el enlace en `index-main.html`
4. (Opcional) Ejecuta `generar_ejercicios.py` si quieres que se regenere automáticamente

## 📊 Estadísticas

- **Total de ejercicios:** 16
- **Archivos HTML:** 18 (16 ejercicios + 1 principal + 1 redireccionador)
- **Tamaño total aproximado:** ~90 KB
- **Diagramas de flujo:** 16 imágenes (1 por ejercicio)

## 💡 Ventajas de esta estructura

1. **Mejor organización:** Cada ejercicio en su propio archivo
2. **Más fácil de navegar:** No desplazarse por una mega-página
3. **Mejor para Git:** Cambios aislados por archivo
4. **Más rápido de cargar:** Archivos más pequeños
5. **Más mantenible:** Cambios no afectan otros ejercicios
6. **SEO mejorado:** Si se publica online, mejor indexación
7. **Compartible:** Puedes compartir links directos a ejercicios específicos

## 📝 Notas

- Los archivos originales (`index.html` con todos los ejercicios) están preservados si necesitas referencia
- Todos los estilos CSS se importan desde `style/styles_desc.css`
- Los diagramas de flujo están en `img/1.png` hasta `img/16.png`
- Las funciones JavaScript para modales están incrustadas en cada página

## 🔗 Links Útiles

- Página Principal: `index-main.html`
- Ver todos los diagramas: `diagramas/` (archivos .dot y generados)
- Estilos: `style/styles_desc.css`

---
**Última actualización:** 2025  
**Versión:** 2.0 (Estructura Modular)
