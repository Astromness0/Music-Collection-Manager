# Music-Collection-Manager
Miniproyecto 2025B/ALGORITMOS Y PROGRAMACIÓN II/APO25
# Gestor de Colecciones de Música - Miniproyecto

## 📋 Información del Proyecto

**Asignatura:** Programación Orientada a Objetos  
**Período:** Diciembre 2025  
**Vencimiento:** 5 de diciembre de 2025  
**Integrantes:** Santiago Otalvaro Franco

---

## 🎯 Descripción General

Aplicación de escritorio desarrollada en **Java con Swing** que permite gestionar una colección de música: artistas, canciones y listas de reproducción.

La aplicación implementa el patrón **MVC (Modelo-Vista-Controlador)** con separación clara de responsabilidades entre la lógica de datos y la interfaz gráfica.

---

## 📁 Estructura del Proyecto

```
MusicCollectionManager/
├── src/
│   ├── model/
│   │   ├── Artist.java       (Clase modelo para artistas)
│   │   ├── Song.java         (Clase modelo para canciones)
│   │   └── Playlist.java     (Clase modelo para playlists)
│   ├── controller/
│   │   └── DataManager.java  (Controlador central de datos)
│   ├── view/
│   │   ├── MusicCollectionApp.java              (Ventana principal)
│   │   ├── PlaylistManagementPanel.java         (Panel de playlists)
│   │   ├── ArtistManagementPanel.java           (Panel de artistas)
│   │   └── SongManagementPanel.java             (Panel de canciones)
│   └── util/
│       └── FileManager.java  (Gestor de archivos CSV)
├── data/
│   └── playlists.csv         (Archivo de datos)
├── docs/
│   ├── diagrama_uml.png      (Diagrama de clases)
│   └── maquetas_interfaz.png (Maquetas de UI)
└── README.md
```

---

## 🏗️ Arquitectura MVC

### Modelo (Model)
- **Artist.java**: Representa un artista con nombre y país
- **Song.java**: Representa una canción con título, artista, duración y género
- **Playlist.java**: Representa una lista de reproducción con canciones asociadas

### Controlador (Controller)
- **DataManager.java**: Gestiona la lógica de negocio y la persistencia de datos
  - Crear artistas, canciones y playlists
  - Agregar canciones a playlists
  - Cargar y guardar datos desde/a archivo

### Vista (View)
- **MusicCollectionApp.java**: Ventana principal con tres paneles
- **PlaylistManagementPanel.java**: Interfaz para gestionar playlists
- **ArtistManagementPanel.java**: Interfaz para gestionar artistas
- **SongManagementPanel.java**: Interfaz para gestionar canciones

### Utilidades (Util)
- **FileManager.java**: Carga y guarda datos en formato CSV

---

## ✨ Funcionalidades Implementadas

### 1. Gestión de Artistas
- ✅ Crear artista con nombre y país
- ✅ Ver lista de artistas creados
- ✅ Validación de datos

### 2. Gestión de Canciones
- ✅ Crear canción con título, duración y género
- ✅ Asociar canción a un artista existente
- ✅ Ver lista de canciones por artista
- ✅ Validación de duración (número positivo)

### 3. Gestión de Playlists
- ✅ Crear lista de reproducción vacía
- ✅ Ver listas de reproducción creadas
- ✅ Agregar canciones a una playlist
- ✅ Ver cantidad de canciones en cada playlist
- ✅ Evitar duplicados de canciones

### 4. Persistencia de Datos
- ✅ Carga inicial desde archivo CSV
- ✅ Guardado de datos en CSV

---

## 🎨 Interfaz Gráfica

La aplicación contiene una ventana principal con **tres paneles principales**:

### Panel 1: Gestión de Playlists (arriba)
- Campo de texto para nombre de playlist
- Lista de playlists creadas
- Botones: Crear playlist, Agregar canción, Actualizar

### Panel 2: Gestión de Artistas (centro)
- Campos: Nombre, País
- Lista de artistas creados
- Botones: Crear artista, Actualizar

### Panel 3: Gestión de Canciones (abajo)
- Campos: Título, Artista (combo), Duración, Género
- Lista de canciones creadas
- Botones: Crear canción, Actualizar

---

## 🚀 Cómo Ejecutar

### Requisitos
- Java 11 o superior
- Eclipse IDE (o cualquier IDE Java)
- Carpeta `data/` con archivo `playlists.csv`

### Pasos
1. Clonar el repositorio
2. Importar proyecto en Eclipse
3. Crear carpeta `data/` en raíz del proyecto
4. Crear archivo `playlists.csv` en `data/`
5. Ejecutar `view.MusicCollectionApp.main()`

### Archivo playlists.csv (formato inicial)
```csv
Mi Playlist Favorita,The Beatles|Hey Jude|428,Pink Floyd|Comfortably Numb|383
Rock Clásico,Queen|Bohemian Rhapsody|354,David Bowie|Heroes|365
Pop 2024,Taylor Swift|Anti-Hero|280,The Weeknd|Blinding Lights|200
```

---

## 🔍 Casos de Uso Principales

### Caso de Uso 1: Crear Artista
1. Usuario ingresa nombre y país
2. Sistema valida campos no vacíos
3. Sistema crea artista y lo añade a lista
4. Sistema muestra mensaje de confirmación

### Caso de Uso 2: Crear Canción
1. Usuario selecciona artista del combo
2. Usuario ingresa título, duración y género
3. Sistema valida datos (duración > 0)
4. Sistema crea canción asociada al artista
5. Combo de artistas en panel de playlists se actualiza

### Caso de Uso 3: Crear Playlist
1. Usuario ingresa nombre
2. Sistema valida nombre no vacío
3. Sistema crea playlist vacía
4. Sistema muestra en lista como "Nombre (0 canciones)"

### Caso de Uso 4: Agregar Canción a Playlist
1. Usuario selecciona playlist de la lista
2. Usuario selecciona canción de su respectiva lista
3. Usuario hace clic en "Agregar canción"
4. Sistema valida que ambas estén seleccionadas
5. Sistema agrega canción a playlist
6. Sistema actualiza contador de canciones

---

##  Formato de Datos

### Archivo CSV (playlists.csv)
```
nombre_playlist,artista1|cancion1|duracion1,artista2|cancion2|duracion2,...
```

**Ejemplo:**
```
Favoritas,The Beatles|Hey Jude|428,Pink Floyd|Comfortably Numb|383
```

---

##  Tecnologías Utilizadas

- **Lenguaje:** Java 11+
- **Framework GUI:** Java Swing (AWT)
- **Patrón de Arquitectura:** MVC
- **Persistencia:** Archivos CSV
- **IDE:** Eclipse

---

##  Conceptos OOP Implementados

- **Encapsulación:** Atributos privados con getters/setters
- **Herencia:** Uso de clases base (si aplica)
- **Polimorfismo:** Métodos toString() sobrescritos
- **Abstracción:** Interfaz clara de DataManager
- **Reutilización:** Componentes Swing reutilizables
- **Separación de responsabilidades:** Modelo vs Vista vs Controlador

---

##  Checklist de Funcionalidades

- [x] Diagrama UML completo
- [x] Maquetas de interfaz
- [x] Modelo (Artist, Song, Playlist)
- [x] Controlador (DataManager)
- [x] Vista (Paneles + Ventana principal)
- [x] Crear artistas
- [x] Crear canciones
- [x] Crear playlists
- [x] Agregar canciones a playlists
- [x] Cargar datos desde CSV
- [x] Guardar datos a CSV (opcional)
- [x] Validación de entrada
- [x] Manejo de errores con JOptionPane
- [x] Interfaz con 3 paneles

---

##  Documentación Adicional

- `docs/diagrama_uml.png` - Diagrama de clases UML
- `docs/maquetas_interfaz.png` - Wireframes de la interfaz

---

##  Video de Presentación

Disponible en: (https://pregradov.usc.edu.co/draftfile.php/87819/user/draft/627406890/Explicacion-vid.mp4?time=1764973372000)

**Duración:** 10 minutos  
**Contenido:**
- Explicación de arquitectura MVC (5 min)
- Demostración de funcionalidades (5 min)

---

##  Notas y Mejoras Futuras

### Posibles Mejoras
- Búsqueda y filtrado de canciones
- Editar artistas y canciones existentes
- Eliminar elementos
- Exportar playlists a archivo de audio
- Base de datos SQL en lugar de CSV
- Interfaz más atractiva (cambiar look & feel)
- Reproducir música (si hay archivo de audio)
- Historial de cambios

---

##  Contribuidores

- **Estudiante :** [Santiago Otalvaro] - Paquete Model + Controller + Paquete View + Util

---

##  Licencia

Este proyecto es de propósito educativo. Licencia: MIT

---

**Última actualización:** Diciembre 4, 2025  
**Versión:** 1.0 Final  
**Estado:** ✅ Completo y Funcional
