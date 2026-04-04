# Sistema de Registro de Actividades

## Descripción del proyecto
Aplicación desarrollada en Java que permite gestionar usuarios y registrar actividades diarias utilizando archivos de texto como almacenamiento persistente.  

El sistema carga los datos desde `Usuarios.txt` y `Registros.txt` al iniciar, almacenándolos en arreglos para su manipulación en memoria. A través de un menú interactivo en consola, el usuario puede registrar nuevas actividades, modificarlas, eliminarlas y realizar análisis sobre la información almacenada.

Entre los análisis implementados se incluyen:
- Cálculo de la actividad más realizada en función de horas acumuladas
- Determinación de la actividad más frecuente por usuario
- Identificación del usuario con mayor cantidad de horas registradas
- Visualización estructurada de todos los registros

El sistema considera validación de datos de entrada y manejo básico de errores para evitar fallos en la ejecución.

## Integrantes
- Ignacio Antonio Pastén Durán  
  RUT: 22.067.577-7  
  GitHub: Tallarin51

## Estructura del proyecto
- `src/logica/Main.java`  
  Contiene la lógica completa del sistema, incluyendo:
  - Lectura de archivos mediante `Scanner`
  - Escritura y actualización de datos con `BufferedWriter`
  - Menús interactivos para navegación del usuario
  - Métodos de gestión (registrar, modificar, eliminar)
  - Métodos de análisis de datos

- `Usuarios.txt`  
  Archivo que almacena credenciales en formato:
  usuario;contraseña

- `Registros.txt`  
  Archivo que almacena las actividades en formato:
  usuario;fecha;horas;actividad

## Instrucciones de ejecución
1. Tener instalado Java (JDK).
2. Clonar o descargar el proyecto.
3. Verificar que los archivos `Usuarios.txt` y `Registros.txt` se encuentren en la raíz del proyecto.
4. Compilar el programa:
   javac src/logica/Main.java
5. Ejecutar:
   java logica.Main
6. Interactuar con el sistema mediante el menú en consola.

## Consideraciones
- El sistema utiliza arreglos de tamaño fijo, por lo que existe un límite máximo de registros.
- Al modificar o eliminar actividades, el archivo `Registros.txt` se reescribe completamente.
- Se implementa control de errores para evitar fallos en la conversión de datos numéricos.
- El acceso al menú de usuario requiere autenticación previa.
