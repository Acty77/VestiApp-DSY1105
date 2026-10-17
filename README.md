# 📱 Aplicación de Gestión de Probadores (ProbadoresApp)

Proyecto desarrollado para la **Evaluación Parcial 2** de la asignatura **Desarrollo de Aplicaciones Móviles (DSY1105)** en Duoc UC.

---

## 👥 Integrantes del Equipo
* **Estudiante 1:** Nombre Completo - `RUT / Sección`
* **Estudiante 2:** Nombre Completo - `RUT / Sección`

---

## 📌 Descripción del Proyecto
La aplicación **VestiApp** es una solución móvil diseñada para optimizar la gestión de probadores en tiendas de *retail*. Permite controlar el estado de disponibilidad de los probadores en tiempo real, registrar clientes y cantidad de prendas, mantener un historial de uso persistente y emitir alertas visuales y táctiles ante tiempos excedidos.

---

## 🚀 Tecnologías y Arquitectura Utilizadas
* **Lenguaje:** Kotlin
* **Interfaz de Usuario:** Jetpack Compose (Material 3)
* **Arquitectura:** MVVM (Model-View-ViewModel) con Unidirectional Data Flow (UDF)
* **Persistencia Local:** Room Database (SQLite)
* **Navegación:** Jetpack Navigation Compose
* **Recursos Nativos del Dispositivo:**
    1. **Cámara:** Para captura/registro de prendas de clientes.
    2. **Servicio de Vibración (`Vibrator`):** Para alertas táctiles al superar el tiempo de uso del probador.

---

## 📋 Funcionalidades Principales
- [x] Dashboard de probadores con indicación visual de estado (Disponible, Ocupado, Limpieza).
- [x] Formulario de ingreso de cliente con validaciones de campos en tiempo real (mínimo de caracteres, límite de prendas).
- [x] Persistencia local de registros e historial de turnos mediante Room.
- [x] Animaciones de estado y transición entre vistas.
- [x] Integración de permisos y funciones de recursos nativos.

---

## 🛠️ Instrucciones para Ejecutar el Proyecto
1. Clonar el repositorio público:
   ```bash
   git clone https://github.com/TU_USUARIO/ProbadoresApp-DSY1105.git