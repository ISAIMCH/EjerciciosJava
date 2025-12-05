#!/usr/bin/env python3
# Script para generar archivos HTML individuales para cada ejercicio

import os

# Datos de los 14 ejercicios restantes (excluimos 1.5 y 1.6 que ya creamos)
ejercicios = [
    {
        "archivo": "ejercicio-2-1.html",
        "numero": "2.1",
        "titulo": "El Grillo como Termómetro",
        "descripcion": "Aquí introduces la estructura de control más importante: la selección.",
        "imagen": "3.png"
    },
    {
        "archivo": "ejercicio-3-3.html",
        "numero": "3.3",
        "titulo": "Contar Ceros",
        "descripcion": "Este ejercicio introduce tu primera estructura repetitiva (bucle).",
        "imagen": "4.png"
    },
    {
        "archivo": "ejercicio-3-14.html",
        "numero": "3.14",
        "titulo": "Taquilla del Estadio",
        "descripcion": "Este problema combina bucles y condicionales.",
        "imagen": "5.png"
    },
    {
        "archivo": "ejercicio-4-1.html",
        "numero": "4.1",
        "titulo": "Lista Sin Repeticiones",
        "descripcion": "Introduce las estructuras de datos: arreglos (arrays).",
        "imagen": "6.png"
    },
    {
        "archivo": "ejercicio-4-14.html",
        "numero": "4.14",
        "titulo": "Producción por Departamentos",
        "descripcion": "Introduce los arreglos bidimensionales (matrices).",
        "imagen": "7.png"
    },
    {
        "archivo": "ejercicio-5-2.html",
        "numero": "5.2",
        "titulo": "Gestión de Empleados",
        "descripcion": "Introduce los Arreglos de Registros en Java.",
        "imagen": "8.png"
    },
    {
        "archivo": "ejercicio-ps-2-1.html",
        "numero": "PS 2.1",
        "titulo": "Calcular Tangente",
        "descripcion": "Refuerza el uso de la estructura if-else.",
        "imagen": "9.png"
    },
    {
        "archivo": "ejercicio-ps-2-2.html",
        "numero": "PS 2.2",
        "titulo": "Calcular Cotangente",
        "descripcion": "Similar al anterior, pero validando el seno.",
        "imagen": "10.png"
    },
    {
        "archivo": "ejercicio-ps-3-23.html",
        "numero": "PS 3.23",
        "titulo": "Promedio de Alumnos",
        "descripcion": "Enseña a usar bucles anidados para procesar datos.",
        "imagen": "11.png"
    },
    {
        "archivo": "ejercicio-ps-3-24.html",
        "numero": "PS 3.24",
        "titulo": "Mejor y Peor Promedio",
        "descripcion": "Añade el algoritmo de búsqueda de máximo y mínimo.",
        "imagen": "12.png"
    },
    {
        "archivo": "ejercicio-ps-3-25.html",
        "numero": "PS 3.25",
        "titulo": "Aptitudes para Básquetbol",
        "descripcion": "Combina bucles while, contadores y condiciones complejas.",
        "imagen": "13.png"
    },
    {
        "archivo": "ejercicio-ps-4-3.html",
        "numero": "PS 4.3",
        "titulo": "Contar Positivos, Negativos y Nulos",
        "descripcion": "Procesamiento de arreglos con clasificación.",
        "imagen": "14.png"
    },
    {
        "archivo": "ejercicio-ps-4-19.html",
        "numero": "PS 4.19",
        "titulo": "Análisis de Calificaciones",
        "descripcion": "Procesamiento completo de matrices.",
        "imagen": "15.png"
    },
    {
        "archivo": "ejercicio-ps-4-35.html",
        "numero": "PS 4.35",
        "titulo": "Directorio Telefónico",
        "descripcion": "Introduce los arreglos paralelos y búsqueda secuencial.",
        "imagen": "16.png"
    }
]

# Plantilla HTML
template = """<!doctype html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Ejercicio {numero} - {titulo}</title>
  <link rel="stylesheet" href="style/styles_desc.css">
</head>
<body>
  <div id="diagramModal" class="modal">
    <div class="modal-content">
      <span class="close">&times;</span>
      <img id="diagramImage" src="" alt="Diagrama de flujo">
    </div>
  </div>

  <div class="container">
    <header class="page-header">
      <h1>Ejercicio {numero} - {titulo}</h1>
      <p class="subtitle">Ejercicios de Programación Estructurada en Java</p>
      <a href="index-main.html" class="back-link">← Volver al menú</a>
    </header>

    <main class="exercises-main">
      <article class="exercise-card">
        <header class="exercise-header">
          <span class="exercise-badge">Ejercicio {numero}</span>
          <h2>{titulo}</h2>
          <p class="exercise-description">{descripcion}</p>
        </header>
        <div class="exercise-content">
          <section class="exercise-main">
            <h3>Descripción</h3>
            <p>Este es el ejercicio {numero}: {titulo}. Para más detalles, consulta el sitio web de origen o tu material de clase.</p>
            
            <h3>Objetivos de Aprendizaje</h3>
            <ul>
              <li>Comprender los conceptos clave del ejercicio.</li>
              <li>Implementar la solución según los requisitos.</li>
              <li>Validar tu solución con los casos de prueba.</li>
            </ul>
          </section>
          
          <aside class="exercise-sidebar">
            <h3>Notas rápidas</h3>
            <ul>
              <li>Consulta tu material de clase para los detalles completos.</li>
              <li>Utiliza el diagrama de flujo como referencia.</li>
              <li>Prueba tu código con varios casos de entrada.</li>
            </ul>
            
            <div class="tip-box">
              <strong>Tip:</strong> Si tienes dudas, revisa los ejercicios anteriores que cubren conceptos similares.
            </div>
            
            <div class="diagram-button-container">
              <button class="diagram-button" onclick="openDiagram('img/{imagen}')">📊 Ver diagrama de flujo</button>
            </div>
          </aside>
        </div>
      </article>
    </main>

    <footer class="page-footer">
      <p class="note-text"><a href="index-main.html">← Volver al menú de ejercicios</a></p>
    </footer>
  </div>

  <script>
    const modal = document.getElementById('diagramModal');
    const closeBtn = document.querySelector('.close');
    function openDiagram(imagePath) {{
      document.getElementById('diagramImage').src = imagePath;
      modal.style.display = 'flex';
      document.body.style.overflow = 'hidden';
    }}
    function closeDiagram() {{
      modal.style.display = 'none';
      document.body.style.overflow = 'auto';
    }}
    closeBtn.onclick = closeDiagram;
    modal.onclick = e => e.target === modal && closeDiagram();
    document.addEventListener('keydown', e => e.key === 'Escape' && modal.style.display === 'flex' && closeDiagram());
  </script>
</body>
</html>
"""

# Ruta base
base_path = r"c:\Users\isaim\OneDrive\Pictures\PAGINAGIT\EjerciciosJava"

# Crear archivos
for ejercicio in ejercicios:
    content = template.format(
        numero=ejercicio["numero"],
        titulo=ejercicio["titulo"],
        descripcion=ejercicio["descripcion"],
        imagen=ejercicio["imagen"]
    )
    
    file_path = os.path.join(base_path, ejercicio["archivo"])
    
    with open(file_path, 'w', encoding='utf-8') as f:
        f.write(content)
    
    print(f"✓ Creado: {ejercicio['archivo']}")

print(f"\n✓ Se han creado {len(ejercicios)} archivos HTML individuales.")
