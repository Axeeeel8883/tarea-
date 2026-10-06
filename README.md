# PharmaMobil - Actividad Autónoma 09

Autor: Axel Perez  
Curso: Desarrollo de Aplicaciones Móviles  
Actividad: Diferencias por plataforma y evidencia en iOS y Android

## Código específico de plataforma

Esta entrega documenta e implementa tres capacidades nativas usando el patrón `expect/actual` de Kotlin Multiplatform:

1. **Formato de moneda peruana**
   - `commonMain`: `formatearSoles(valor: Double): String`
   - Android: `NumberFormat` + `Locale("es", "PE")`
   - iOS: `NSNumberFormatter` + `NSLocale("es_PE")`

2. **Compartir producto**
   - `commonMain`: `Compartidor`
   - Android: `Intent.ACTION_SEND` usando `Context`
   - iOS: `UIActivityViewController`

3. **Información del dispositivo**
   - `commonMain`: `InfoDispositivo`
   - Android: `Build.VERSION.RELEASE`
   - iOS: `UIDevice.currentDevice.systemVersion`

También se incluye el módulo de plataforma para Koin como ejemplo de aislamiento por source set.

## Estructura

```text
composeApp/src/commonMain/.../platform
composeApp/src/androidMain/.../platform
composeApp/src/iosMain/.../platform
composeApp/src/commonMain/.../ui
```

## Importante sobre evidencias

Las capturas de Android, iOS y el mensaje literal del compilador deben provenir de una ejecución real del proyecto. Por eso la carpeta `evidencias/` contiene una lista de las capturas que debes colocar antes de entregar.

## Commits sugeridos

```bash
git add .
git commit -m "docs: registrar inventario de capacidades nativas"
git commit -am "docs: agregar informe comparativo Android iOS"
git commit -am "feat: implementar informacion del dispositivo con expect actual"
git commit -am "docs: actualizar README y evidencias sesion 09"
```
