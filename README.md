# 🎬 CineMood

> **Discover movies by your mood**

CineMood is a **Kotlin Multiplatform** app for browsing Iranian cinema. Built with Compose Multiplatform, it targets Android, iOS, Desktop (JVM), and Web (Kotlin/JS and Wasm) from a shared codebase.

Gradle root project: **CineMookKmp** · Package: `tech.nullexdev.cinemood`

Portfolio / learning project — fork and reuse are welcome under the [MIT License](LICENSE).

---

## 📱 Platforms

| Platform | Status |
|----------|--------|
| Android | ✅ |
| iOS | ✅ |
| Desktop (JVM) | ✅ |
| Web (JS) | ✅ |
| Web (Wasm) | ✅ |

---

## 🏗️ Architecture

Multi-module **clean architecture** with feature modules and a shared presentation layer:

```
CineMookKmp/
├── androidApp/              # Android entry point
├── iosApp/                  # iOS entry point (Xcode)
├── desktopApp/              # Desktop (JVM) entry point
├── webApp/                  # Web entry point (JS + Wasm)
├── shared/                  # App shell, navigation, DI wiring, theme
├── core/
│   ├── domain/              # MVI contracts, entities, repository interfaces
│   ├── data/                # Ktor HTTP client, theme repo, SQLDelight / Room
│   ├── navigation/          # Screen routes (Navigation3 NavKey)
│   └── presentation/        # Shared UI (MovieCard, nav chrome, SystemAppearance)
├── feature/
│   ├── home/                # Movie list, pagination, movie detail
│   ├── search/              # Search with pagination
│   ├── favorite/            # Favorites grid
│   └── settings/            # Theme and app preferences
└── service/
    ├── domain/              # Movie models, use cases, repository contract
    └── data/
        └── iranianMoviesApi/  # Ktor client for moviesapi.ir
```

### Layer responsibilities

| Module | Role |
|--------|------|
| `core/domain` | `MviViewModel`, `MviUiState` / `MviUiAction`, base use cases and repository interfaces |
| `core/data` | Platform `HttpClient`, `ThemeRepository`, SQLDelight database (favorites schema); Room on Android, iOS, and JVM |
| `core/presentation` | Reusable Compose UI, Coil image loading, cross-platform `SystemAppearance` |
| `core/navigation` | Typed `Screen` destinations for Navigation3 |
| `feature/*` | Feature screens, ViewModels, and Koin modules |
| `service/domain` | `GetMoviesUseCase`, `SearchMoviesUseCase`, domain models |
| `service/data:iranianMoviesApi` | Remote API ( [moviesapi.ir](https://moviesapi.ir) ) |
| `shared` | `App` composable, bottom bar / navigation rail, Koin `initKoin()` |

---

## 🛠️ Tech stack

| Category | Library / version |
|----------|-------------------|
| Language | Kotlin **2.4** |
| UI | Compose Multiplatform **1.11** · Material 3 |
| Architecture | MVI (`UiState` / `UiAction` + `MviViewModel`) |
| DI | Koin 4 (`koin-compose`, `koin-compose-viewmodel`) |
| Navigation | Navigation3 (`navigation3-ui`) |
| Networking | Ktor 3 · Kotlinx Serialization |
| Images | Coil 3 (`coil-compose`, `coil-network-ktor3`) |
| Local data | SQLDelight 2 · AndroidX Room (native targets) |
| Async | Kotlin Coroutines · Flow |
| Lifecycle | AndroidX Lifecycle (`collectAsStateWithLifecycle`) |

---

## ✨ Features

- 🎥 **Home** — paginated Iranian movie catalog from moviesapi.ir with load-more
- 🎞️ **Movie detail** — detail screen with shared-element style transitions
- 🔍 **Search** — query movies with pagination
- ❤️ **Favorites** — saved-movies grid (SQLDelight schema in `core:data`; UI wired with sample data)
- ⚙️ **Settings** — light / dark / system theme via `ThemeRepository`
- 📐 **Adaptive layout** — bottom navigation on portrait/narrow; navigation rail on wide screens
- 🌙 **Material 3** theming with per-platform system bar styling (`SystemAppearance`)

---

## 🚀 Getting started

### Prerequisites

- **JDK 11+**
- **Android Studio** Ladybug or newer with Kotlin Multiplatform and Compose Multiplatform support
- **Xcode 15+** (iOS)
- **Node.js** (optional; used by Kotlin/JS and Wasm web toolchains)

### Clone

```bash
git clone https://github.com/Alimmzdev/CineMood.git
cd CineMood   # local folder may be named CineMookKmp
```

### Android

```bash
./gradlew :androidApp:installDebug
```

On Windows: `.\gradlew.bat :androidApp:installDebug`

### Desktop

```bash
./gradlew :desktopApp:run
```

### Web (Wasm — recommended)

```bash
./gradlew :webApp:wasmJsBrowserDevelopmentRun
```

### Web (JS — broader browser support)

```bash
./gradlew :webApp:jsBrowserDevelopmentRun
```

### iOS

Open `iosApp/iosApp.xcodeproj` in Xcode, then run on a simulator or device.

---

## 📂 Module graph

```
androidApp / iosApp / desktopApp / webApp
        └──► shared
               ├──► core:domain
               ├──► core:data
               ├──► core:navigation
               ├──► core:presentation
               ├──► feature:home
               ├──► feature:search
               ├──► feature:favorite
               ├──► feature:settings
               ├──► service:domain
               └──► service:data:iranianMoviesApi
                      └──► core:data (HTTP client)
```

---

## 🤝 Contributing

Pull requests are welcome. For larger changes, open an issue first to discuss the approach.

---

## 📄 License

This project is released under the [MIT License](LICENSE). You may fork, study, and reuse the code (including in commercial apps) as long as you keep the copyright notice and license text.

Third-party libraries and the [moviesapi.ir](https://moviesapi.ir) API remain subject to their own terms.
