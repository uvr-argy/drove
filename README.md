# 🛠️ Analizador de Texto y Procesador de Archivos (Proyecto Grupal)

Este proyecto consiste en una biblioteca de utilidades en **Java** diseñada para la manipulación de archivos de texto, análisis de cadenas de caracteres, procesamiento de datos en formato CSV y cálculos estadísticos sobre arreglos numéricos. El desarrollo se divide modularmente en 4 clases lógicas distribuidas entre los miembros del equipo.

---

## Reparto del Trabajo y Funciones

### 1. Clase Archivo
* **Desarrollador:** Iker
* **Responsabilidad:** Persistencia de datos e interacción con el sistema de archivos.
* `volcarArchivo(String nombreArchivo) -> String`
  * Lee el contenido completo de un archivo de texto y lo devuelve como una sola cadena.
* `escribirArchivo(String nombreArchivo, String contenido) -> void`
  * Escribe (o sobrescribe) el contenido de texto dentro del archivo indicado.
* `existeArchivo(String nombreArchivo) -> boolean`
  * Comprueba de forma segura si un archivo existe en la ruta especificada.

---

### 2. Clase Analizador de Strings
* **Desarrollador:** Ibai
* **Responsabilidad:** Análisis de texto, conteo de componentes y validación de formatos.
* `contarApariciones(String texto, String subcadena) -> int`
  * Cuenta cuántas veces se encuentra la subcadena dentro del texto principal.
* `contarPalabras(String texto) -> int`
  * Devuelve el número total de palabras detectadas en el texto.
* `contarLineas(String texto) -> int`
  * Cuenta la cantidad de líneas o saltos de línea presentes en el cuerpo del texto.
* `contarCaracteres(String texto) -> int`
  * Devuelve la longitud total (`length`) de la cadena de caracteres.
* `esDoubleValido(String texto) -> boolean`
  * Verifica si la cadena tiene un formato numérico válido para ser convertida a decimal de forma segura.

---

### 3. Clase Procesador CSV
* **Desarrollador:** Alvaro
* **Responsabilidad:** Segmentación de estructuras de datos separadas por delimitadores y generación de reportes métricos.
* `csvToArray(String contenidoCsv, String separador) -> String[]`
  * Segmenta el contenido plano de un CSV utilizando el carácter delimitador y lo almacena en un arreglo unidimensional.
* `calcularEstadisticasCsv(String nombreArchivo) -> void`
  * Lee un archivo CSV y muestra/calcula sus métricas clave: promedio, suma, cantidad de registros, valor mínimo, valor máximo y total de registros no válidos.

---

### 4. Clase Analizador de Doubles
* **Desarrollador:** Unax
* **Responsabilidad:** Módulo matemático para la conversión limpia de datos de texto a tipos numéricos y cálculos estadísticos.
* `convertirAArregloDoubles(String[] registros) -> double[]`
  * Filtra y transforma los registros de texto válidos en un arreglo de números decimales. *(Depende de `esDoubleValido` de Ibai)*.
* `contarRegistrosInvalidos(String[] registros) -> int`
  * Cuenta cuántos elementos de un arreglo de texto no pueden transformarse a número. *(Depende de `esDoubleValido` de Ibai)*.
* `calcularPromedio(double[] numeros) -> double`
  * Obtiene el valor medio aritmético del arreglo.
* `calcularSuma(double[] numeros) -> double`
  * Devuelve la sumatoria de todos los elementos numéricos.
* `calcularCantidad(double[] numeros) -> int`
  * Retorna el conteo total de elementos numéricos en el arreglo.
* `obtenerMinimo(double[] numeros) -> double`
  * Busca y devuelve el valor más bajo del arreglo.
* `obtenerMaximo(double[] numeros) -> double`
  * Busca y devuelve el valor más alto del arreglo.

---

## 📄 Memoria del Proyecto
* **Autor:** Iker

---

## Tecnologías Utilizadas
* **Lenguaje principal:** Java
