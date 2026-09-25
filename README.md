# Fundamentos de los Principios SOLID

El sistema de gestión de préstamos necesita refactorizarse para mejorar su mantenibilidad y escalabilidad. Los desarrolladores han identificado la necesidad de aplicar los principios SOLID para lograr este objetivo. El problema se centra en entender y aplicar estos principios en un contexto real de desarrollo de software.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Patrones de Diseño SOLID |
| **Nivel** | junior-l1 |
| **Tipo** | theoretical |
| **Tiempo estimado** | 2 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Comprender el Principio de Responsabilidad Única

**Objetivo:** Explicar y aplicar el Principio de Responsabilidad Única en un componente del sistema de gestión de préstamos.

**Tiempo estimado:** 30 minutos

**Instrucciones:**

- Identificar una clase o componente del sistema que viole el Principio de Responsabilidad Única.
- Proponer una refactorización que separe las responsabilidades en clases distintas.
- Discutir los beneficios de esta refactorización en términos de mantenibilidad y escalabilidad.

**Entregable:** Descripción escrita de la refactorización propuesta y sus beneficios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo separar las responsabilidades puede facilitar la modificación y extensión del código.

</details>

### Fase 2: Aplicar el Principio de Abierto/Cerrado

**Objetivo:** Explicar y aplicar el Principio de Abierto/Cerrado en un componente del sistema de gestión de préstamos.

**Tiempo estimado:** 30 minutos

**Instrucciones:**

- Identificar una clase o componente del sistema que viole el Principio de Abierto/Cerrado.
- Proponer una refactorización que permita extender la funcionalidad sin modificar el código existente.
- Discutir los beneficios de esta refactorización en términos de extensibilidad y mantenimiento.

**Entregable:** Descripción escrita de la refactorización propuesta y sus beneficios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo utilizar interfaces o clases abstractas para permitir la extensión sin modificar el código existente.

</details>

### Fase 3: Implementar el Principio de Sustitución de Liskov

**Objetivo:** Explicar y aplicar el Principio de Sustitución de Liskov en un componente del sistema de gestión de préstamos.

**Tiempo estimado:** 30 minutos

**Instrucciones:**

- Identificar una clase o componente del sistema que viole el Principio de Sustitución de Liskov.
- Proponer una refactorización que permita sustituir una clase por sus subclases sin alterar el comportamiento correcto del programa.
- Discutir los beneficios de esta refactorización en términos de robustez y fiabilidad.

**Entregable:** Descripción escrita de la refactorización propuesta y sus beneficios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo garantizar que las subclases no alteren el comportamiento correcto del programa.

</details>

### Fase 4: Aplicar el Principio de Segregación de Interfaces

**Objetivo:** Explicar y aplicar el Principio de Segregación de Interfaces en un componente del sistema de gestión de préstamos.

**Tiempo estimado:** 30 minutos

**Instrucciones:**

- Identificar una interfaz o componente del sistema que viole el Principio de Segregación de Interfaces.
- Proponer una refactorización que separe las interfaces en componentes más pequeños y específicos.
- Discutir los beneficios de esta refactorización en términos de cohesión y acoplamiento.

**Entregable:** Descripción escrita de la refactorización propuesta y sus beneficios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo separar las interfaces puede mejorar la cohesión y reducir el acoplamiento entre componentes.

</details>

### Fase 5: Implementar el Principio de Inversión de Dependencias

**Objetivo:** Explicar y aplicar el Principio de Inversión de Dependencias en un componente del sistema de gestión de préstamos.

**Tiempo estimado:** 30 minutos

**Instrucciones:**

- Identificar una dependencia o componente del sistema que viole el Principio de Inversión de Dependencias.
- Proponer una refactorización que invierta la dependencia hacia abstracciones en lugar de detalles concretos.
- Discutir los beneficios de esta refactorización en términos de flexibilidad y reutilización.

**Entregable:** Descripción escrita de la refactorización propuesta y sus beneficios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo invertir la dependencia hacia abstracciones puede mejorar la flexibilidad y reutilización del código.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es el Principio de Responsabilidad Única y cómo se aplica en el contexto del sistema de gestión de préstamos?
- **paraQueSirve**: ¿Para qué sirve el Principio de Abierto/Cerrado y cómo mejora la extensibilidad del sistema?
- **queDecisionesImplica**: ¿Qué decisiones implica aplicar el Principio de Sustitución de Liskov en el sistema de gestión de préstamos?
- **erroresComunes**: ¿Cuáles son los errores comunes al aplicar el Principio de Segregación de Interfaces y cómo se pueden evitar?
- **comoSeUsa**: ¿Cómo se usa el Principio de Inversión de Dependencias para mejorar la flexibilidad y reutilización del código?

## Criterios de Evaluacion

- Identificar una clase o componente que viole un principio SOLID.
- Proponer una refactorización que aplique el principio SOLID.
- Discutir los beneficios de la refactorización en términos de mantenibilidad, extensibilidad, robustez, cohesión y acoplamiento.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
