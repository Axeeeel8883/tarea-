# S09 - Actividad Autónoma: Diferencias por plataforma

**Estudiante:** Axel Perez  
**Asignatura:** Desarrollo de Aplicaciones Móviles  
**Sesión:** 9 - Unidad 2, sesión 3  
**Proyecto:** PharmaMobil sobre PharmaSoft

## Producto 1. Inventario de capacidades nativas

| Capacidad | Firma expect | Android | iOS |
|---|---|---|---|
| Formato de moneda | `fun formatearSoles(valor: Double): String` | `NumberFormat` + `Locale("es", "PE")` | `NSNumberFormatter` + `NSLocale("es_PE")` |
| Compartir producto | `expect class Compartidor` | `Intent.ACTION_SEND` + `Context` | `UIActivityViewController` |
| Módulo de inyección | `expect val platformModule: Module` | `module { single { Compartidor(androidContext()) } }` | `module { single { Compartidor() } }` |
| Información del dispositivo | `expect class InfoDispositivo()` | `Build.VERSION.RELEASE` | `UIDevice.currentDevice.systemVersion` |

### Rutas de los actual

- Formato Android: `composeApp/src/androidMain/kotlin/pe/edu/upeu/pharmamobil/platform/FormatoMoneda.android.kt`
- Formato iOS: `composeApp/src/iosMain/kotlin/pe/edu/upeu/pharmamobil/platform/FormatoMoneda.ios.kt`
- Compartidor Android: `composeApp/src/androidMain/kotlin/pe/edu/upeu/pharmamobil/platform/Compartidor.android.kt`
- Compartidor iOS: `composeApp/src/iosMain/kotlin/pe/edu/upeu/pharmamobil/platform/Compartidor.ios.kt`
- Módulo Android: `composeApp/src/androidMain/kotlin/pe/edu/upeu/pharmamobil/platform/PlatformModule.android.kt`
- Módulo iOS: `composeApp/src/iosMain/kotlin/pe/edu/upeu/pharmamobil/platform/PlatformModule.ios.kt`
- Información Android: `composeApp/src/androidMain/kotlin/pe/edu/upeu/pharmamobil/platform/InfoDispositivo.android.kt`
- Información iOS: `composeApp/src/iosMain/kotlin/pe/edu/upeu/pharmamobil/platform/InfoDispositivo.ios.kt`

## Producto 2. Informe comparativo de diferencias

### 1. Diferencia en el formato de moneda

Aunque Android e iOS usan el mismo locale peruano, la implementación no es idéntica porque cada plataforma utiliza una biblioteca nativa diferente. En Android, el código se ejecuta sobre la JVM y usa `NumberFormat.getCurrencyInstance(Locale("es", "PE"))`. En iOS, Kotlin/Native accede a Foundation y emplea `NSNumberFormatter` con `NSLocale("es_PE")`. Las dos APIs persiguen el mismo objetivo, pero las reglas de representación, espacios, símbolo monetario y redondeo pueden variar según la versión del sistema operativo y la biblioteca. Por eso el código común solo declara qué necesita la aplicación mediante `expect fun formatearSoles`, mientras cada `actual` decide cómo conseguir el resultado usando la API correcta. La evidencia final debe mostrar el mismo producto y el resultado literal de Android y de iOS para registrar cualquier diferencia visible.

### 2. Por qué CompartidorAndroid necesita Context

En Android, iniciar una acción del sistema como compartir requiere un `Intent`. Para lanzar ese `Intent` es necesario un `Context`, ya que el modelo de Android se basa en componentes administrados por el sistema, como actividades, servicios y aplicaciones. Por ese motivo `CompartidorAndroid` recibe un `Context` y el módulo de Koin usa `androidContext()` para entregarlo. En iOS el patrón es diferente: la hoja de compartir se presenta mediante `UIActivityViewController`, que pertenece a UIKit. La implementación obtiene un controlador visible desde `UIApplication` y presenta desde allí la interfaz nativa. Koin no necesita entregar un contexto equivalente en iOS. Esta diferencia muestra por qué la lógica de compartir debe estar aislada en `androidMain` e `iosMain`, manteniendo en `commonMain` únicamente el contrato `Compartidor`.

### 3. expect/actual frente a interfaz con inyección de dependencias

También sería posible definir una interfaz común, por ejemplo `Compartidor`, y crear implementaciones Android e iOS registradas mediante inyección de dependencias. Esa estrategia reduce la dependencia directa del mecanismo `expect/actual` y puede facilitar pruebas unitarias porque se reemplaza la implementación por un doble de prueba. Sin embargo, para capacidades estrictamente ligadas a la plataforma, `expect/actual` hace explícito desde el compilador que debe existir una implementación para cada target. Esto mejora la legibilidad porque los archivos están separados por source set y el contrato permanece cercano a la funcionalidad. En PharmaMobil se usa `expect/actual` para APIs nativas como moneda e información del dispositivo, mientras Koin se aprovecha para crear objetos que requieren dependencias distintas, como el `Context` de Android. Las dos estrategias pueden convivir y no son excluyentes.

### 4. Qué ocurre si falta una implementación actual

Si una declaración `expect` existe en `commonMain` pero una plataforma no proporciona el `actual` correspondiente, la compilación de ese target falla. El compilador compara paquete, nombre, firma, tipos y parámetros. Esto protege el proyecto frente a una capacidad que existe conceptualmente en el código común, pero no tiene forma de ejecutarse en una plataforma. Para completar la evidencia exigida, se debe comentar temporalmente uno de los archivos `actual`, compilar el target afectado y copiar literalmente el mensaje mostrado por el compilador. Después se restaura el archivo para dejar el repositorio en estado compilable.

**Mensaje literal del compilador:** PENDIENTE DE EJECUCIÓN REAL. Debe pegarse aquí exactamente como aparezca en Android Studio/Xcode/Gradle.

### 5. Capacidad que no debe bajar a código específico

Una capacidad como la validación de datos de un producto no debería implementarse por plataforma si las reglas son exactamente las mismas en Android e iOS. Por ejemplo, comprobar que el nombre no esté vacío, que el precio sea mayor que cero o que un código tenga un formato determinado es lógica de negocio independiente del sistema operativo. Llevar esas reglas a `androidMain` e `iosMain` duplicaría código, aumentaría el riesgo de comportamientos diferentes y haría más costoso el mantenimiento. La regla de decisión es simple: si la funcionalidad puede resolverse con Kotlin común y no necesita una API nativa, debe permanecer en `commonMain`. Solo se baja al source set específico cuando la plataforma obliga a usar una API distinta o un modelo de ejecución diferente.

## Producto 3. Tercera capacidad nativa

Se implementó **Información del dispositivo**. El contrato común expone `sistema` y `version`. Android obtiene la versión mediante `Build.VERSION.RELEASE`; iOS usa `UIDevice.currentDevice.systemVersion`. La pantalla `AcercaDeScreen` consume el contrato desde `commonMain`, sin importar `android.*` ni `platform.*`.

## Producto 4. Evidencias

Las evidencias deben provenir de ejecución real. Colocar en la carpeta `evidencias/` las capturas del precio en Android/iOS, compartir en Android/iOS, información del dispositivo en Android/iOS y el error literal del compilador.

## Lista de cotejo

- [x] Inventario con firma, source set, archivo y API nativa.
- [x] Informe con las cinco preguntas.
- [x] Tercera capacidad con `expect` y dos `actual`.
- [x] Tercera capacidad conectada a una pantalla común.
- [x] README con sección "Código específico de plataforma".
- [ ] Capturas reales de Android e iOS.
- [ ] Mensaje literal del compilador.
- [ ] Enlace real a la rama y commits del repositorio.
