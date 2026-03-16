# Buscaminas

Implementacion del clasico juego Buscaminas en Java para consola, desarrollado aplicando principios de Programacion Orientada a Objetos, Clean Code y principios SOLID.

---

## Participantes

- Juan Andrés Pulecio
- Diego Fernando Muñoz

---

## Asignatura

-Estructura de datos

---

## Descripcion

El juego permite al usuario elegir una dificultad (Facil, Medio, Difícil o Personalizado), destapar celdas y marcar minas en un tablero generado aleatoriamente. La primera celda destapada siempre esta garantizada como segura.

---

## Estructura del proyecto
```
busca-minas/
│
├── src/
│   ├── accion/
│   │   ├── ControladorJuego.java
│   │   ├── GeneradorTablero.java
│   │   └── ProcesadorJugada.java
│   │
│   ├── modelo/
│   │   ├── Celda.java
│   │   ├── Configuracion.java
│   │   └── Tablero.java
│   │
│   ├── util/
│   │   ├── ImpresorTablero.java
│   │   └── LectorEntrada.java
│   │
│   └── App.java
│
└── README.md
```

---

## Instrucciones para ejecutar

### Requisitos
- Java JDK 11 o superior
- VS Code con Extension Pack for Java

### Desde VS Code
1. Abre la carpeta del proyecto en VS Code
2. Abre `App.java`
3. Presiona **Run** o `Ctrl + F5`

### Desde consola
```bash
javac -d bin src/**/*.java src/App.java
java -cp bin App
```

---

## 🎮 Cómo jugar

1. Elige una dificultad al iniciar:
   - **Facil:** 8×8 con 10 minas
   - **Medio:** 16×16 con 40 minas
   - **Dificil:** 16×30 con 99 minas
   - **Personalizado:** defines filas, columnas y cantidad de minas

2. En cada turno elige una acción:
   - `D` → Destapar celda
   - `M` → Marcar/desmarcar celda con bandera

3. Ganas cuando todas las celdas sin mina están destapadas. Pierdes si destapas una mina.

---

## 🧱 Principios aplicados

- **OOP:** separacion en paquetes `modelo`, `accion` y `util`
- **Single Responsibility:** cada clase tiene una única responsabilidad
- **Open/Closed:** facil de extender con nuevas dificultades o modos de juego
- **Clean Code:** nombres descriptivos, metodos cortos y comentarios precisos