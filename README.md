# 🛠️ Drove Task Processor & Data Analyzer

Sistema multitarea y multihilo desarrollado en **Java** diseñado para el análisis de texto, procesamiento de datos estructurados CSV, cómputo estadístico sobre arreglos decimales y ejecución aislada mediante subprocesos del sistema operativo.

---

## 📁 Estructura del Proyecto

El código está organizado modularmente en 4 paquetes dentro del directorio `src/`:
src/
├── excepciones/       # Excepciones personalizadas del sistema (CsvException, OperacionException...)
├── modelo/            # Entidades de dominio (Trabajo) y mapeadores CSV (TrabajoCsvMapper)
├── main/              # Lógica multihilo, I/O, parsing numérico y punto de entrada (Main)
└── subprocesos/       # Módulos independientes ejecutables vía CLI para cómputo aislado

---

## 🛠️ Distribución de Módulos y Funcionalidad

### 1. Paquete `excepciones`
* **`CsvException`**: Control de errores en la lectura y mapeo de datos CSV.
* **`OperacionException`**: Control de fallos durante la ejecución lógica de un trabajo.
* **`TrabajoInvalidoException`**: Identificación de trabajos que no cumplen los requisitos del dominio.

### 2. Paquete `modelo`
* **`Trabajo`**: Clase principal que representa la tarea, gestionando su estado (`PENDIENTE`, `EN_COLA`, `EJECUTANDO`, `COMPLETADO`, `FALLIDO`, `CANCELADO`), tipo de operación (`ANALIZAR_TEXTO`, `BUSCAR_TEXTO`, `ESTADISTICAS_CSV`), parámetros y duración.
* **`TrabajoCsvMapper`**: Mapeador estático (`toClass`) para convertir líneas CSV en instancias validables de `Trabajo`.

### 3. Paquete `main`
* **`Archivo`**: Lectura/escritura síncrona de ficheros planos mediante Java NIO (`Files`, `Paths`).
* **`AnalizarDoubles`**: Conversión y operaciones matemáticas sobre arreglos numéricos (`suma`, `promedio`, `min`, `max`, `contarValidos`).
* **`Base64Wrapper`**: Codificación y decodificación en Base64 UTF-8 para transferencia segura de datos entre procesos.
* **`Subproceso`**: Lanzador de JVMs secundarias (`ProcessBuilder`) y captura de streams de salida.
* **`HiloProductor`**: Hilo encargado de volcar trabajos validados a una `BlockingQueue` acotada.
* **`HiloTrabajador`**: Hilos del pool (`ExecutorService`) encargados de ejecutar las tareas y gestionar la comunicación con subprocesos.
* **`ListaTrabajo`**: Utilidades para procesar, validar e impedir IDs duplicados en listas de trabajos.
* **`Main`**: Punto de entrada del programa. Configura el pool de hilos y arranca el flujo Productor-Consumidor.

### 4. Paquete `subprocesos`
* **`EstadisticasCsv`**: Clase ejecutable independiente invocado por el hilo trabajador. Decodifica el contenido del archivo recibido en Base64 y calcula estadísticas avanzadas en un entorno totalmente aislado.

---

## 🚀 Requisitos e Instalación

* **JDK**: Java 17 o superior.
* **IDE**: Eclipse / IntelliJ IDEA / VS Code o compilación mediante terminal.

### Ejecución desde consola:
```bash
# Compilar el código fuente
javac -d bin src/excepciones/*.java src/modelo/*.java src/main/*.java src/subprocesos/*.java

# Ejecutar el programa principal
java -cp bin main.Main
