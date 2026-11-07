# SauceDemo · Serenity BDD + Cucumber + Screenplay

Proyecto de automatización web sobre [saucedemo.com](https://www.saucedemo.com) con Serenity BDD 5.4.1, Cucumber 8 (JUnit Platform) y el patrón Screenplay.

## Requisitos

- Java 17 o superior
- Maven 3.9 o superior
- Google Chrome instalado. El driver compatible se descarga automáticamente, no hace falta instalar `chromedriver`.

## Estructura

```
src/test
├── java/co/com/saucedemo
│   ├── runners/           # CucumberTestSuite (JUnit Platform Suite)
│   ├── stepdefinitions/   # Pasos de Cucumber (InicioSesionStepDefinitions) y Hooks
│   ├── tasks/             # Tareas de negocio: AbrirLaAplicacion, IniciarSesion
│   ├── interactions/      # Interacciones reutilizables: LimpiarYEscribir
│   ├── questions/         # Preguntas al sistema: TituloDeProductos, MensajeDeError
│   ├── userinterfaces/    # Páginas estilo POM con sus Target: PaginaInicioSesion, PaginaProductos
│   ├── models/            # Modelos de datos: Usuario
│   └── utils/             # Constantes
└── resources
    ├── features/          # login.feature (palabras clave en inglés, escenarios en español)
    └── serenity.conf      # Configuración de Chrome y URLs
```

## Ejecución

```bash
# Compila, ejecuta los escenarios del tag configurado en el runner y genera el reporte de Serenity
mvn clean verify

# Chrome en modo headless
mvn clean verify -Dheadless.mode=true
```

### Filtro por tags

El runner `CucumberTestSuite` solo ejecuta los escenarios marcados con `@Regresion`:

```java
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@Regresion")
```

Para ejecutar otro grupo, cambia el valor. Se admiten expresiones de Cucumber, por ejemplo `"@smoke"`, `"@Regresion and not @wip"` o `"@login or @smoke"`.

Las pruebas se ejecutan con `maven-failsafe-plugin` en la fase `integration-test`. El reporte se genera en `post-integration-test`, así que se crea aunque haya escenarios fallidos. En ese caso, el build falla en la fase `verify`.

## Reporte de Serenity

Al terminar la ejecución, abre el reporte:

```bash
open target/site/serenity/index.html        # macOS
xdg-open target/site/serenity/index.html    # Linux
start target\site\serenity\index.html       # Windows
```

Para regenerar el reporte sin volver a ejecutar las pruebas:

```bash
mvn serenity:aggregate
```

## Integración continua con Jenkins

El proyecto incluye un [`Jenkinsfile`](Jenkinsfile) con un pipeline declarativo que:

1. **Detecta cambios**: revisa el repositorio cada 5 minutos (`pollSCM('H/5 * * * *')`) y se ejecuta solo si hay commits nuevos.
2. **Ejecuta las pruebas** en la etapa `Pruebas Serenity` con Chrome en modo headless:
   ```bash
   mvn -B clean verify -Dheadless.mode=true
   ```
3. **Publica los resultados** siempre, aunque el build falle:
   - Resultados JUnit desde `target/failsafe-reports/TEST-*.xml`.
   - El reporte de Serenity (`target/site/serenity/**`) como artefacto archivado del build.

### Configuración necesaria en Jenkins

- En **Manage Jenkins → Tools**, registra las herramientas con estos nombres exactos:
  - JDK: `jdk21`
  - Maven: `maven3`
- El agente donde corre el pipeline debe tener Google Chrome instalado.
- Crea un job de tipo **Pipeline** (o *Multibranch Pipeline*) que apunte a este repositorio y use *Pipeline script from SCM* con la ruta `Jenkinsfile`.

Para ver el reporte de Serenity de una ejecución, abre el build en Jenkins, entra en **Artifacts** y abre `target/site/serenity/index.html`.

## Notas

- Las URLs y las opciones de Chrome se configuran en `src/test/resources/serenity.conf`.
- Failsafe define `SE_SKIP_DRIVER_IN_PATH=true` para que Selenium Manager ignore cualquier `chromedriver` desactualizado que haya en el `PATH` y use siempre la versión que corresponde al Chrome instalado.

## Autoría

Proyecto desarrollado por Samuel Gutierrez con asistencia de [Claude Code](https://claude.com/claude-code) para la generación y documentación de partes del código.
