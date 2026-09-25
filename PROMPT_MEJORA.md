# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/loanmanagement/LoanManagementApplication.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanmanagement/infrastructure/InMemoryLoanRepository.java` — `LoanStatus.equals`: Se invoca `equals` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/interfaces/LoanController.java` — `LoanService.findAll`: Se invoca `findAll` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanServiceImpl.java` — `Loan.isEmpty`: Se invoca `isEmpty` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanServiceImpl.java` — `Loan.get`: Se invoca `get` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Explicar los 5 principios SOLID con ejemplos teoricos sin codigo ejecutable

### Reto
- Tema: Patrones de Diseño SOLID
- Seniority: junior-l1
- Tipo: theoretical
- Título: Fundamentos de los Principios SOLID
- Tiempo estimado: 2 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Comprender el Principio de Responsabilidad Única — objetivo: Explicar y aplicar el Principio de Responsabilidad Única en un componente del sistema de gestión de préstamos. — entregable (NO resolver): Descripción escrita de la refactorización propuesta y sus beneficios.
- Fase 2: Aplicar el Principio de Abierto/Cerrado — objetivo: Explicar y aplicar el Principio de Abierto/Cerrado en un componente del sistema de gestión de préstamos. — entregable (NO resolver): Descripción escrita de la refactorización propuesta y sus beneficios.
- Fase 3: Implementar el Principio de Sustitución de Liskov — objetivo: Explicar y aplicar el Principio de Sustitución de Liskov en un componente del sistema de gestión de préstamos. — entregable (NO resolver): Descripción escrita de la refactorización propuesta y sus beneficios.
- Fase 4: Aplicar el Principio de Segregación de Interfaces — objetivo: Explicar y aplicar el Principio de Segregación de Interfaces en un componente del sistema de gestión de préstamos. — entregable (NO resolver): Descripción escrita de la refactorización propuesta y sus beneficios.
- Fase 5: Implementar el Principio de Inversión de Dependencias — objetivo: Explicar y aplicar el Principio de Inversión de Dependencias en un componente del sistema de gestión de préstamos. — entregable (NO resolver): Descripción escrita de la refactorización propuesta y sus beneficios.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: loan-management
  profiles:
    active: dev
  datasource:
    url: jdbc:h2:mem:loan_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

logging:
  level:
    root: INFO
    com.pragma.loanmanagement: DEBUG
    org.springframework.web: INFO
    org.hibernate: INFO

server:
  port: 8080
  servlet:
    context-path: /api/loans

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always

# Configuración para notificaciones
notification:
  email:
    enabled: true
    host: smtp.example.com
    port: 587
    username: user@example.com
    password: password
    from: no-reply@example.com
  sms:
    enabled: false
    provider-url: https://api.smsprovider.com/send
    api-key: your-api-key
    from-number: +1234567890

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>loan-management</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>loan-management</name>
    <description>Sistema de gestión de préstamos con aplicación de principios SOLID</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Base de datos -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Spring Boot Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                    <encoding>${project.build.sourceEncoding}</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/LoanManagementApplication.java ===
package com.pragma.loanmanagement;


import com.pragma.loanmanagement.domain.Loan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pragma.loanmanagement"})
@EnableAsync
public class LoanManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanManagementApplication.class, args);
    }

    @Configuration
    static class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .maxAge(3600);
        }
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Loan Management API")
                        .version("1.0")
                        .description("API para la gestión de préstamos aplicando principios SOLID")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("loans")
                .pathsToMatch("/api/loans/**")
                .build();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/swagger-ui/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/")
                        .resourceChain(false);
            }
        };
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/domain/Loan.java ===
package com.pragma.loanmanagement.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private final UUID id;
    private final String customerId;
    private final BigDecimal amount;
    private final BigDecimal interestRate;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LoanStatus status;
    private final String purpose;

    public Loan(UUID id, String customerId, BigDecimal amount, BigDecimal interestRate,
                LocalDate startDate, LocalDate endDate, LoanStatus status, String purpose) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.purpose = purpose;
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public String getPurpose() {
        return purpose;
    }

    public BigDecimal calculateTotalPayment() {
        BigDecimal totalInterest = amount.multiply(interestRate).multiply(BigDecimal.valueOf(getLoanTermInMonths()));
        return amount.add(totalInterest);
    }

    public int getLoanTermInMonths() {
        return (int) java.time.temporal.ChronoUnit.MONTHS.between(startDate, endDate);
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(endDate) && status != LoanStatus.PAID;
    }

    public Loan withStatus(LoanStatus newStatus) {
        return new Loan(this.id, this.customerId, this.amount, this.interestRate,
                       this.startDate, this.endDate, newStatus, this.purpose);
    }
}

enum LoanStatus {
    ACTIVE,
    PAID,
    OVERDUE,
    DEFAULTED
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/domain/LoanRepository.java ===
package com.pragma.loanmanagement.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository {
    Loan save(Loan loan);
    
    Optional<Loan> findById(UUID id);
    
    List<Loan> findByCustomerId(String customerId);
    
    List<Loan> findAll();
    
    void deleteById(UUID id);
    
    List<Loan> findOverdueLoans();
    
    List<Loan> findByStatus(LoanStatus status);
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/domain/LoanService.java ===
package com.pragma.loanmanagement.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LoanService {
    Loan createLoan(String customerId, BigDecimal amount, BigDecimal interestRate,
                    LocalDate startDate, LocalDate endDate, String purpose);
    
    Loan getLoanById(UUID loanId);
    
    List<Loan> getLoansByCustomer(String customerId);
    
    Loan updateLoanStatus(UUID loanId, LoanStatus newStatus);
    
    void deleteLoan(UUID loanId);
    
    BigDecimal calculateTotalPayment(UUID loanId);
    
    List<Loan> getOverdueLoans();
    
    List<Loan> getLoansByStatus(LoanStatus status);
    
    Loan approveLoan(UUID loanId);
    
    Loan rejectLoan(UUID loanId);
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/infrastructure/InMemoryLoanRepository.java ===
package com.pragma.loanmanagement.infrastructure;

import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.domain.LoanRepository;
import com.pragma.loanmanagement.domain.LoanStatus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class InMemoryLoanRepository implements LoanRepository {
    private final Map<UUID, Loan> loansStore = new ConcurrentHashMap<>();
    private final AtomicReference<UUID> idGenerator = new AtomicReference<>(UUID.randomUUID());

    @Override
    public Loan save(Loan loan) {
        if (loan == null) {
            throw new IllegalArgumentException("El préstamo no puede ser nulo");
        }
        
        Loan loanToSave;
        if (loan.getId() == null) {
            UUID newId = UUID.randomUUID();
            loanToSave = new Loan(
                newId,
                loan.getCustomerId(),
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getStartDate(),
                loan.getEndDate(),
                loan.getStatus(),
                loan.getPurpose()
            );
        } else {
            loanToSave = loan;
        }
        
        loansStore.put(loanToSave.getId(), loanToSave);
        return loanToSave;
    }

    @Override
    public Optional<Loan> findById(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(loansStore.get(id));
    }

    @Override
    public List<Loan> findByCustomerId(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return loansStore.values().stream()
            .filter(loan -> customerId.equals(loan.getCustomerId()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findAll() {
        return new ArrayList<>(loansStore.values());
    }

    @Override
    public void deleteById(UUID id) {
        if (id != null) {
            loansStore.remove(id);
        }
    }

    @Override
    public List<Loan> findOverdueLoans() {
        LocalDate today = LocalDate.now();
        return loansStore.values().stream()
            .filter(loan -> loan.getStatus() == LoanStatus.ACTIVE)
            .filter(loan -> loan.getEndDate().isBefore(today))
            .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findByStatus(LoanStatus status) {
        if (status == null) {
            return Collections.emptyList();
        }
        return loansStore.values().stream()
            .filter(loan -> status.equals(loan.getStatus()))
            .collect(Collectors.toList());
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/interfaces/LoanController.java ===
package com.pragma.loanmanagement.interfaces;

import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.domain.LoanService;
import com.pragma.loanmanagement.domain.LoanStatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;
    private final Map<String, Object> metricsCache = new HashMap<>();
    private long requestCount = 0;
    
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Map<String, Object> request) {
        requestCount++;
        
        String customerId = (String) request.get("customerId");
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        BigDecimal interestRate = new BigDecimal(request.get("interestRate").toString());
        LocalDate startDate = LocalDate.parse((String) request.get("startDate"));
        LocalDate endDate = LocalDate.parse((String) request.get("endDate"));
        String purpose = (String) request.get("purpose");
        
        Loan loan = loanService.createLoan(
            customerId, amount, interestRate, startDate, endDate, purpose
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable UUID id) {
        requestCount++;
        
        Loan loan = loanService.getLoanById(id);
        if (loan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Loan>> getLoansByCustomer(@PathVariable String customerId) {
        requestCount++;
        
        List<Loan> loans = loanService.getLoansByCustomer(customerId);
        return ResponseEntity.ok(loans);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Loan> updateLoanStatus(
            @PathVariable UUID id, 
            @RequestBody Map<String, String> request) {
        requestCount++;
        
        String statusStr = request.get("status");
        LoanStatus newStatus = LoanStatus.valueOf(statusStr);
        
        Loan updatedLoan = loanService.updateLoanStatus(id, newStatus);
        if (updatedLoan == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable UUID id) {
        requestCount++;
        
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/total-payment")
    public ResponseEntity<Map<String, BigDecimal>> calculateTotalPayment(@PathVariable UUID id) {
        requestCount++;
        
        BigDecimal totalPayment = loanService.calculateTotalPayment(id);
        Map<String, BigDecimal> response = new HashMap<>();
        response.put("totalPayment", totalPayment);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Loan>> getOverdueLoans() {
        requestCount++;
        
        List<Loan> overdueLoans = loanService.getOverdueLoans();
        return ResponseEntity.ok(overdueLoans);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Loan>> getLoansByStatus(@PathVariable String status) {
        requestCount++;
        
        LoanStatus loanStatus = LoanStatus.valueOf(status);
        List<Loan> loans = loanService.getLoansByStatus(loanStatus);
        return ResponseEntity.ok(loans);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Loan> approveLoan(@PathVariable UUID id) {
        requestCount++;
        
        Loan approvedLoan = loanService.approveLoan(id);
        if (approvedLoan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(approvedLoan);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Loan> rejectLoan(@PathVariable UUID id) {
        requestCount++;
        
        Loan rejectedLoan = loanService.rejectLoan(id);
        if (rejectedLoan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rejectedLoan);
    }

    @GetMapping("/metrics")
    public ResponseEntity<Map<String, Object>> getMetrics() {
        metricsCache.put("totalRequests", requestCount);
        metricsCache.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(new HashMap<>(metricsCache));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Loan>> searchLoans(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount) {
        
        requestCount++;
        List<Loan> allLoans = loanService.getLoansByCustomer(
            customerId != null ? customerId : ""
        );
        
        if (customerId == null) {
            allLoans = loanService.findAll();
        }
        
        List<Loan> filtered = allLoans.stream()
            .filter(loan -> status == null || loan.getStatus().name().equals(status))
            .filter(loan -> minAmount == null || loan.getAmount().compareTo(minAmount) >= 0)
            .filter(loan -> maxAmount == null || loan.getAmount().compareTo(maxAmount) <= 0)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(filtered);
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/domain/NotificationService.java ===
package com.pragma.loanmanagement.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface NotificationService {
    void sendLoanApprovedNotification(Loan loan);
    void sendLoanRejectedNotification(Loan loan);
    void sendPaymentReminder(Loan loan, int daysUntilDue);
    void sendOverdueNotification(Loan loan);
    void sendLoanCreatedNotification(Loan loan);
    void sendSmsNotification(String phoneNumber, String message);
    void sendEmailNotification(String email, String subject, String body);
    void sendPushNotification(String userId, String title, String message);
    void sendBulkNotification(List<String> recipients, String message);
    Map<String, Object> getNotificationStatus(String notificationId);
    void retryFailedNotification(String notificationId);
    List<Map<String, Object>> getNotificationHistory(String recipientId);
    void scheduleNotification(Loan loan, String notificationType, LocalDateTime scheduledTime);
    void cancelScheduledNotification(String notificationId);
    boolean isNotificationServiceAvailable();
    int getPendingNotificationCount();
    void markNotificationAsRead(String notificationId);
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/domain/EmailNotificationService.java ===
package com.pragma.loanmanagement.domain;

import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailNotificationService implements NotificationService {
    
    private static final String SMTP_HOST = "smtp.pragma.com";
    private static final int SMTP_PORT = 587;
    private static final String SENDER_EMAIL = "noreply@loanmanagement.pragma.com";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private final String smtpHost;
    private final int smtpPort;
    private final String senderEmail;
    private boolean emailServerAvailable;
    
    public EmailNotificationService() {
        this.smtpHost = SMTP_HOST;
        this.smtpPort = SMTP_PORT;
        this.senderEmail = SENDER_EMAIL;
        this.emailServerAvailable = true;
    }
    
    public EmailNotificationService(String smtpHost, int smtpPort, String senderEmail) {
        this.smtpHost = smtpHost != null ? smtpHost : SMTP_HOST;
        this.smtpPort = smtpPort > 0 ? smtpPort : SMTP_PORT;
        this.senderEmail = senderEmail != null ? senderEmail : SENDER_EMAIL;
        this.emailServerAvailable = true;
    }
    
    @Override
    public void sendLoanApprovedNotification(String customerId, Loan loan) {
        if (!emailServerAvailable) {
            throw new IllegalStateException("Email server is not available");
        }
        
        String recipient = customerId + "@pragma.com";
        String subject = "Your Loan Has Been Approved";
        String body = buildApprovalEmailBody(loan);
        
        sendEmail(recipient, subject, body);
    }
    
    @Override
    public void sendLoanRejectedNotification(String customerId, Loan loan, String reason) {
        if (!emailServerAvailable) {
            throw new IllegalStateException("Email server is not available");
        }
        
        String recipient = customerId + "@pragma.com";
        String subject = "Your Loan Application Status";
        String body = buildRejectionEmailBody(loan, reason);
        
        sendEmail(recipient, subject, body);
    }
    
    @Override
    public void sendPaymentReminder(String customerId, Loan loan, int daysUntilDue) {
        if (!emailServerAvailable) {
            throw new IllegalStateException("Email server is not available");
        }
        
        String recipient = customerId + "@pragma.com";
        String subject = "Payment Reminder - " + daysUntilDue + " Days Remaining";
        String body = buildReminderEmailBody(loan, daysUntilDue);
        
        sendEmail(recipient, subject, body);
    }
    
    @Override
    public void sendOverdueNotification(String customerId, Loan loan, int daysOverdue) {
        if (!emailServerAvailable) {
            throw new IllegalStateException("Email server is not available");
        }
        
        String recipient = customerId + "@pragma.com";
        String subject = "URGENT: Loan Payment Overdue";
        String body = buildOverdueEmailBody(loan, daysOverdue);
        
        sendEmail(recipient, subject, body);
        
        if (daysOverdue > 30) {
            sendEmail(recipient, "URGENT: Immediate Action Required", 
                "Your loan is significantly overdue. Please contact us immediately.");
        }
    }
    
    private void sendEmail(String recipient, String subject, String body) {
        String messageId = UUID.randomUUID().toString();
        String timestamp = LocalDateTime.now().format(FORMATTER);
        
        System.out.println("[EMAIL]" + 
            " | MessageID: " + messageId +
            " | From: " + senderEmail +
            " | To: " + recipient +
            " | Subject: " + subject +
            " | Timestamp: " + timestamp);
        System.out.println("[EMAIL BODY]\n" + body);
    }
    
    private String buildApprovalEmailBody(Loan loan) {
        StringBuilder body = new StringBuilder();
        body.append("Dear Customer,\n\n");
        body.append("We are pleased to inform you that your loan application has been APPROVED.\n\n");
        body.append("Loan Details:\n");
        body.append("- Loan ID: ").append(loan.getId()).append("\n");
        body.append("- Amount: $").append(loan.getAmount()).append("\n");
        body.append("- Interest Rate: ").append(loan.getInterestRate()).append("%");
        body.append("\n");
        body.append("- Start Date: ").append(loan.getStartDate()).append("\n");
        body.append("- End Date: ").append(loan.getEndDate()).append("\n");
        body.append("- Purpose: ").append(loan.getPurpose()).append("\n\n");
        body.append("Total Payment: $").append(loan.calculateTotalPayment()).append("\n\n");
        body.append("Thank you for choosing our services.\n\n");
        body.append("Best regards,\n");
        body.append("Loan Management Team");
        return body.toString();
    }
    
    private String buildRejectionEmailBody(Loan loan, String reason) {
        StringBuilder body = new StringBuilder();
        body.append("Dear Customer,\n\n");
        body.append("Thank you for your loan application. After careful review, ");
        body.append("we regret to inform you that your application has been declined.\n\n");
        body.append("Reason: ").append(reason).append("\n\n");
        body.append("If you have any questions, please contact our support team.\n\n");
        body.append("Best regards,\n");
        body.append("Loan Management Team");
        return body.toString();
    }
    
    private String buildReminderEmailBody(Loan loan, int daysUntilDue) {
        StringBuilder body = new StringBuilder();
        body.append("Dear Customer,\n\n");
        body.append("This is a friendly reminder that your loan payment is due in ");
        body.append(daysUntilDue).append(" days.\n\n");
        body.append("Loan Details:\n");
        body.append("- Loan ID: ").append(loan.getId()).append("\n");
        body.append("- Due Date: ").append(loan.getEndDate()).append("\n");
        body.append("- Amount Due: $").append(loan.calculateTotalPayment()).append("\n\n");
        body.append("Please ensure your payment is made on time to avoid any late fees.\n\n");
        body.append("Best regards,\n");
        body.append("Loan Management Team");
        return body.toString();
    }
    
    private String buildOverdueEmailBody(Loan loan, int daysOverdue) {
        StringBuilder body = new StringBuilder();
        body.append("URGENT NOTICE\n\n");
        body.append("Dear Customer,\n\n");
        body.append("Your loan payment is ").append(daysOverdue).append(" days overdue. ");
        body.append("Please make your payment immediately to avoid further penalties.\n\n");
        body.append("Loan Details:\n");
        body.append("- Loan ID: ").append(loan.getId()).append("\n");
        body.append("- Days Overdue: ").append(daysOverdue).append("\n");
        body.append("- Total Amount Due: $").append(loan.calculateTotalPayment()).append("\n\n");
        body.append("Contact us immediately to discuss payment options.\n\n");
        body.append("Regards,\n");
        body.append("Loan Management Team");
        return body.toString();
    }
    
    public void setEmailServerAvailable(boolean available) {
        this.emailServerAvailable = available;
    }
    
    public boolean isEmailServerAvailable() {
        return emailServerAvailable;
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/domain/SmsNotificationService.java ===
package com.pragma.loanmanagement.domain;

import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SmsNotificationService implements NotificationService {
    
    private static final String SMS_GATEWAY_URL = "https://sms.pragma.com/api/send";
    private static final String DEFAULT_SENDER = "LoanMgr";
    private static final int MAX_SMS_LENGTH = 160;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private final String gatewayUrl;
    private final String senderId;
    private boolean gatewayAvailable;
    private int messagesSent;
    private int failedMessages;
    
    public SmsNotificationService() {
        this.gatewayUrl = SMS_GATEWAY_URL;
        this.senderId = DEFAULT_SENDER;
        this.gatewayAvailable = true;
        this.messagesSent = 0;
        this.failedMessages = 0;
    }
    
    public SmsNotificationService(String gatewayUrl, String senderId) {
        this.gatewayUrl = gatewayUrl != null ? gatewayUrl : SMS_GATEWAY_URL;
        this.senderId = senderId != null ? senderId : DEFAULT_SENDER;
        this.gatewayAvailable = true;
        this.messagesSent = 0;
        this.failedMessages = 0;
    }
    
    @Override
    public void sendLoanApprovedNotification(String customerId, Loan loan) {
        if (!gatewayAvailable) {
            throw new IllegalStateException("SMS gateway is not available");
        }
        
        String phoneNumber = normalizePhoneNumber(customerId);
        String message = buildShortApprovalMessage(loan);
        
        sendSms(phoneNumber, message);
    }
    
    @Override
    public void sendLoanRejectedNotification(String customerId, Loan loan, String reason) {
        if (!gatewayAvailable) {
            throw new IllegalStateException("SMS gateway is not available");
        }
        
        String phoneNumber = normalizePhoneNumber(customerId);
        String shortReason = reason.length() > 50 ? reason.substring(0, 47) + "..." : reason;
        String message = "Loan rejected. Reason: " + shortReason + ". Contact support for details.";
        
        sendSms(phoneNumber, message);
    }
    
    @Override
    public void sendPaymentReminder(String customerId, Loan loan, int daysUntilDue) {
        if (!gatewayAvailable) {
            throw new IllegalStateException("SMS gateway is not available");
        }
        
        String phoneNumber = normalizePhoneNumber(customerId);
        String message = String.format("Reminder: Your loan payment is due in %d days. Amount: $%.2f. Please pay on time to avoid fees.",
            daysUntilDue, loan.calculateTotalPayment());
        
        if (message.length() > MAX_SMS_LENGTH) {
            message = String.format("Reminder: Payment due in %d days. Amount: $%.2f. Log in to portal for details.",
                daysUntilDue, loan.calculateTotalPayment());
        }
        
        sendSms(phoneNumber, message);
    }
    
    @Override
    public void sendOverdueNotification(String customerId, Loan loan, int daysOverdue) {
        if (!gatewayAvailable) {
            throw new IllegalStateException("SMS gateway is not available");
        }
        
        String phoneNumber = normalizePhoneNumber(customerId);
        String urgencyMessage = daysOverdue > 30 ? "URGENT: " : "";
        String message = String.format("%sLoan overdue by %d days. Amount: $%.2f. Contact us NOW to avoid further penalties.",
            urgencyMessage, daysOverdue, loan.calculateTotalPayment());
        
        sendSms(phoneNumber, message);
        
        if (daysOverdue > 60) {
            String followUpMessage = "URGENT: Your loan is severely overdue. Immediate payment required. Call now.";
            sendSms(phoneNumber, followUpMessage);
        }
    }
    
    private void sendSms(String phoneNumber, String message) {
        String messageId = UUID.randomUUID().toString();
        String timestamp = LocalDateTime.now().format(FORMATTER);
        
        if (message.length() > MAX_SMS_LENGTH) {
            int segments = (int) Math.ceil((double) message.length() / MAX_SMS_LENGTH);
            System.out.println("[SMS]" + 
                " | MessageID: " + messageId +
                " | From: " + senderId +
                " | To: " + phoneNumber +
                " | Segments: " + segments +
                " | Timestamp: " + timestamp);
        } else {
            System.out.println("[SMS]" + 
                " | MessageID: " + messageId +
                " | From: " + senderId +
                " | To: " + phoneNumber +
                " | Timestamp: " + timestamp);
        }
        System.out.println("[SMS BODY] " + message);
        
        messagesSent++;
    }
    
    private String normalizePhoneNumber(String customerId) {
        String digits = customerId.replaceAll("[^0-9]", "");
        if (digits.length() == 10) {
            return "+1" + digits;
        } else if (digits.length() == 11 && digits.startsWith("1")) {
            return "+" + digits;
        }
        return "+1" + String.format("%010d", Long.parseLong(customerId.replaceAll("[^0-9]", "")));
    }
    
    private String buildShortApprovalMessage(Loan loan) {
        String amountStr = String.format("$%.0f", loan.getAmount());
        return String.format("Loan approved! Amount: %s. Rate: %.1f%%. Log in to accept.",
            amountStr, loan.getInterestRate());
    }
    
    public void setGatewayAvailable(boolean available) {
        this.gatewayAvailable = available;
    }
    
    public boolean isGatewayAvailable() {
        return gatewayAvailable;
    }
    
    public int getMessagesSent() {
        return messagesSent;
    }
    
    public int getFailedMessages() {
        return failedMessages;
    }
    
    public void resetCounters() {
        this.messagesSent = 0;
        this.failedMessages = 0;
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/application/LoanServiceImpl.java ===
package com.pragma.loanmanagement.application;

import com.pragma.loanmanagement.domain.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class LoanServiceImpl implements LoanService {
    
    private final LoanRepository loanRepository;
    private final NotificationService notificationService;
    private final List<String> auditLog;
    private BigDecimal totalInterestCollected;
    private int approvalCount;
    private int rejectionCount;
    
    public LoanServiceImpl(LoanRepository loanRepository, NotificationService notificationService) {
        if (loanRepository == null) {
            throw new IllegalArgumentException("LoanRepository cannot be null");
        }
        if (notificationService == null) {
            throw new IllegalArgumentException("NotificationService cannot be null");
        }
        this.loanRepository = loanRepository;
        this.notificationService = notificationService;
        this.auditLog = new ArrayList<>();
        this.totalInterestCollected = BigDecimal.ZERO;
        this.approvalCount = 0;
        this.rejectionCount = 0;
    }
    
    @Override
    public Loan createLoan(String customerId, BigDecimal amount, BigDecimal interestRate,
                          LocalDate startDate, LocalDate endDate, String purpose) {
        validateLoanInput(customerId, amount, interestRate, startDate, endDate, purpose);
        
        UUID loanId = UUID.randomUUID();
        Loan loan = new Loan(loanId, customerId, amount, interestRate, startDate, endDate, purpose);
        
        Loan savedLoan = loanRepository.save(loan);
        
        logAction("Loan created: " + loanId + " for customer: " + customerId + 
                 " | Amount: " + amount + " | Rate: " + interestRate + "%");
        
        return savedLoan;
    }
    
    @Override
    public Loan getLoanById(UUID loanId) {
        if (loanId == null) {
            throw new IllegalArgumentException("Loan ID cannot be null");
        }
        
        Optional<Loan> loan = loanRepository.findById(loanId);
        if (loan.isEmpty()) {
            logAction("Loan not found: " + loanId);
            throw new RuntimeException("Loan not found with ID: " + loanId);
        }
        
        logAction("Loan retrieved: " + loanId);
        return loan.get();
    }
    
    @Override
    public List<Loan> getLoansByCustomer(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        
        List<Loan> loans = loanRepository.findByCustomerId(customerId);
        logAction("Retrieved " + loans.size() + " loans for customer: " + customerId);
        
        return loans;
    }
    
    @Override
    public Loan updateLoanStatus(UUID loanId, LoanStatus newStatus) {
        Loan loan = getLoanById(loanId);
        Loan updatedLoan = loan.withStatus(newStatus);
        Loan savedLoan = loanRepository.save(updatedLoan);
        
        logAction("Loan status updated: " + loanId + " | Old status: " + loan.getStatus() + 
                 " | New status: " + newStatus);
        
        return savedLoan;
    }
    
    @Override
    public void deleteLoan(UUID loanId) {
        if (loanId == null) {
            throw new IllegalArgumentException("Loan ID cannot be null");
        }
        
        Loan loan = getLoanById(loanId);
        
        if (loan.getStatus() == LoanStatus.ACTIVE || loan.getStatus() == LoanStatus.OVERDUE) {
            throw new IllegalStateException("Cannot delete active or overdue loans");
        }
        
        loanRepository.deleteById(loanId);
        logAction("Loan deleted: " + loanId);
    }
    
    @Override
    public BigDecimal calculateTotalPayment(UUID loanId) {
        Loan loan = getLoanById(loanId);
        return loan.calculateTotalPayment();
    }
    
    @Override
    public List<Loan> getOverdueLoans() {
        List<Loan> overdueLoans = loanRepository.findOverdueLoans();
        logAction("Retrieved " + overdueLoans.size() + " overdue loans");
        return overdueLoans;
    }
    
    @Override
    public List<Loan> getLoansByStatus(LoanStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        
        List<Loan> loans = loanRepository.findByStatus(status);
        logAction("Retrieved " + loans.size() + " loans with status: " + status);
        return loans;
    }
    
    @Override
    public Loan approveLoan(UUID loanId) {
        Loan loan = getLoanById(loanId);
        
        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new IllegalStateException("Only pending loans can be approved. Current status: " + loan.getStatus());
        }
        
        BigDecimal totalPayment = loan.calculateTotalPayment();
        BigDecimal interest = totalPayment.subtract(loan.getAmount());
        this.totalInterestCollected = this.totalInterestCollected.add(interest);
        
        Loan approvedLoan = loan.withStatus(LoanStatus.ACTIVE);
        Loan savedLoan = loanRepository.save(approvedLoan);
        
        notificationService.sendLoanApprovedNotification(loan.getCustomerId(), savedLoan);
        
        approvalCount++;
        logAction("Loan approved: " + loanId + " | Interest earned: " + interest);
        
        return savedLoan;
    }
    
    @Override
    public Loan rejectLoan(UUID loanId) {
        Loan loan = getLoanById(loanId);
        
        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new IllegalStateException("Only pending loans can be rejected. Current status: " + loan.getStatus());
        }
        
        String rejectionReason = determineRejectionReason(loan);
        
        Loan rejectedLoan = loan.withStatus(LoanStatus.REJECTED);
        Loan savedLoan = loanRepository.save(rejectedLoan);
        
        notificationService.sendLoanRejectedNotification(loan.getCustomerId(), savedLoan, rejectionReason);
        
        rejectionCount++;
        logAction("Loan rejected: " + loanId + " | Reason: " + rejectionReason);
        
        return savedLoan;
    }
    
    private void validateLoanInput(String customerId, BigDecimal amount, BigDecimal interestRate,
                                   LocalDate startDate, LocalDate endDate, String purpose) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than zero");
        }
        
        if (amount.compareTo(new BigDecimal("1000000")) > 0) {
            throw new IllegalArgumentException("Loan amount exceeds maximum allowed (1,000,000)");
        }
        
        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        
        if (interestRate.compareTo(new BigDecimal("50")) > 0) {
            throw new IllegalArgumentException("Interest rate exceeds maximum allowed (50%)");
        }
        
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
        
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        
        long monthsBetween = ChronoUnit.MONTHS.between(startDate, endDate);
        if (monthsBetween > 360) {
            throw new IllegalArgumentException("Loan term cannot exceed 30 years (360 months)");
        }
        
        if (purpose == null || purpose.trim().isEmpty()) {
            throw new IllegalArgumentException("Loan purpose cannot be null or empty");
        }
    }
    
    private String determineRejectionReason(Loan loan) {
        if (loan.getAmount().compareTo(new BigDecimal("500000")) > 0) {
            return "Loan amount exceeds credit limit";
        }
        
        if (loan.getInterestRate().compareTo(new BigDecimal("30")) > 0) {
            return "Interest rate too high for approval";
        }
        
        long months = loan.getLoanTermInMonths();
        if (months > 240) {
            return "Loan term too long";
        }
        
        return "Does not meet credit criteria";
    }
    
    private void logAction(String action) {
        String logEntry = java.time.LocalDateTime.now() + ": " + action;
        auditLog.add(logEntry);
        System.out.println("[AUDIT] " + logEntry);
    }
    
    public List<String> getAuditLog() {
        return new ArrayList<>(auditLog);
    }
    
    public BigDecimal getTotalInterestCollected() {
        return totalInterestCollected;
    }
    
    public int getApprovalCount() {
        return approvalCount;
    }
    
    public int getRejectionCount() {
        return rejectionCount;
    }
}

// === ARCHIVO: README.md ===
# Sistema de Gestión de Préstamos - Práctica SOLID

## Descripción del Proyecto

Este proyecto es un sistema de gestión de préstamos desarrollado con Spring Boot 3.4 y Java 21. El objetivo principal del reto es aplicar los principios SOLID en un contexto real de desarrollo de software, refactorizando un sistema que gestiona préstamos para mejorar su mantenibilidad y escalabilidad.

## Tecnologías y Versiones

- **Framework**: Spring Boot 3.4.0
- **Lenguaje**: Java 21
- **Base de datos**: H2 (en memoria)
- **Build Tool**: Maven
- **Lombok**: 1.18.30

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas estándar de Spring Boot:

```
src/main/java/com/pragma/loanmanagement/
├── LoanManagementApplication.java       # Punto de entrada de la aplicación
├── domain/                               # Capa de dominio
│   ├── Loan.java                        # Entidad principal
│   ├── LoanRepository.java              # Interfaz de repositorio
│   ├── LoanService.java                 # Interfaz de servicio
│   ├── LoanStatus.java                  # Enumeración de estados
│   ├── NotificationService.java         # Interfaz de notificaciones
│   ├── EmailNotificationService.java    # Implementación de email
│   └── SmsNotificationService.java      # Implementación de SMS
├── application/                          # Capa de aplicación
│   └── LoanServiceImpl.java             # Implementación del servicio
├── infrastructure/                       # Capa de infraestructura
│   └── InMemoryLoanRepository.java      # Repositorio en memoria
└── interfaces/                           # Capa de interfaces
    └── LoanController.java              # Controlador REST
```

## Configuración del Entorno

### Prerrequisitos

1. **JDK 21** - Verificar instalación:
   ```bash
   java -version
   ```
   
2. **Maven 3.8+** - Verificar instalación:
   ```bash
   mvn -version
   ```

### Instalación y Ejecución

1. **Clonar o extraer el proyecto**

2. **Compilar el proyecto**:
   ```bash
   mvn clean compile
   ```

3. **Ejecutar la aplicación**:
   ```bash
   mvn spring-boot:run
   ```

4. **Verificar que está funcionando**:
   - API REST: `http://localhost:8080/api/loans`
   - Health check: `http://localhost:8080/actuator/health`
   - Documentación Swagger: `http://localhost:8080/swagger-ui.html`

## Contexto del Reto: Principios SOLID

Este proyecto sirve como base para un ejercicio de refactorización aplicando los cinco principios SOLID:

### Fase 1: Principio de Responsabilidad Única (SRP)
Cada clase debe tener una única razón para cambiar. En el contexto de préstamos, esto significa que la lógica de cálculo, validación y persistencia deben estar separadas.

### Fase 2: Principio de Abierto/Cerrado (OCP)
Las entidades del sistema deben estar abiertas para extensión pero cerradas para modificación. Por ejemplo, nuevos tipos de préstamos no deberían modificar el código existente.

### Fase 3: Principio de Sustitución de Liskov (LSP)
Las implementaciones de notificaciones (email, SMS) deben poder intercambiarse sin alterar el comportamiento del sistema.

### Fase 4: Principio de Segregación de Interfaces (ISP)
Es preferible tener interfaces específicas antes que una interfaz general. El sistema debe definir contratos pequeños y enfocados.

### Fase 5: Principio de Inversión de Dependencias (DIP)
Los módulos de alto nivel no deben depender de módulos de bajo nivel. Las dependencias deben invertirse, dependendiendo de abstracciones.

## Endpoints de la API

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /api/loans | Crear un nuevo préstamo |
| GET | /api/loans/{id} | Obtener préstamo por ID |
| GET | /api/loans/customer/{customerId} | Obtener préstamos de un cliente |
| GET | /api/loans | Listar todos los préstamos |
| PUT | /api/loans/{id}/status | Actualizar estado del préstamo |
| DELETE | /api/loans/{id} | Eliminar un préstamo |
| GET | /api/loans/overdue | Listar préstamos vencidos |
| POST | /api/loans/{id}/approve | Aprobar un préstamo |
| POST | /api/loans/{id}/reject | Rechazar un préstamo |

## Configuración de la Aplicación

La configuración se encuentra en `src/main/resources/application.yml`. Los valores por defecto incluyen:

- Puerto: 8080
- Base de datos H2 en memoria
- Configuración de logging

## Ejecución de Pruebas

```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas con cobertura
mvn test -Dcoverage
```

## Objetivos de Aprendizaje

Al completar este reto, el participante será capaz de:

1. Identificar violaciones de los principios SOLID en código existente
2. Aplicar refactorizaciones específicas para cumplir cada principio
3. Explicar los beneficios de cada principio en términos de mantenibilidad
4. Implementar soluciones que respeten la arquitectura de capas
5. Escribir código que sea más fácil de probar y extender

## Notas para el Participante

- El código base funciona correctamente antes de cualquier refactorización
- Cada principio SOLID se evaluará de forma independiente
- Las refactorizaciones deben mantener la funcionalidad existente
- Se valorará la capacidad de explicar el "por qué" de cada cambio
- El código debe seguir las convenciones del proyecto (nombres, estructura)

## Comandos Útiles

```bash
# Compilar sin ejecutar tests
mvn compile -DskipTests

# Ejecutar en modo desarrollo
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Ver información de dependencias
mvn dependency:tree

# Limpiar build anterior
mvn clean
```

---

**Proyecto desarrollado como ejercicio práctico de principios SOLID en el contexto de un sistema de gestión de préstamos.**
```
