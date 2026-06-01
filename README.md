# 🎬 CineMood

> **Discover Movies By Your Mood**

CineMood is a **Kotlin Multiplatform** application for discovering Iranian movies. Built with Compose Multiplatform, it runs natively on Android, iOS, Desktop, Web (JS), and WebAssembly from a single shared codebase.

---

## 📱 Platforms

| Platform | Status |
|---|---|
| Android | ✅ |
| iOS | ✅ |
| Desktop (JVM) | ✅ |
| Web (JS) | ✅ |
| Web (Wasm) | ✅ |

---

## 🏗️ Architecture

The project follows a **multi-module clean architecture** pattern with clear separation of concerns:

```
CineMood/
├── androidApp/          # Android entry point
├── iosApp/              # iOS entry point (Xcode project)
├── desktopApp/          # Desktop (JVM) entry point
├── webApp/              # Web entry point
├── shared/              # Shared KMP module (UI + DI wiring)
├── core/
│   ├── domain/          # Base domain models & interfaces
│   ├── navigation/      # Navigation primitives
│   └── presentation/    # Shared UI components (MovieCard, TopAppBar, etc.)
├── feature/
│   ├── home/            # Home screen — movie list with pagination
│   ├── search/          # Search screen
│   ├── favorite/        # Favorites screen
│   └── settings/        # Settings screen
└── service/
    ├── domain/          # Service-level domain logic
    └── data/
        └── iranianMoviesApi/  # API data source implementation
```

### Layer Responsibilities

- **`core/domain`** — Base entities, repository interfaces, use cases
- **`core/presentation`** — Reusable Compose components shared across features
- **`core/navigation`** — Navigation contracts and route definitions
- **`feature/*`** — Self-contained feature modules (ViewModel, UI, UiState, UiAction)
- **`service/data`** — Network layer (Ktor-based API client for Iranian movies)
- **`shared`** — Composes all modules together; configures Koin DI for each platform

---

## 🛠️ Tech Stack

| Category | Library |
|---|---|
| UI | Compose Multiplatform |
| Architecture | MVVM + UiState/UiAction |
| DI | Koin (koin-compose, koin-compose-viewmodel) |
| Navigation | Navigation3 |
| Async | Kotlin Coroutines + Flow |
| Serialization | Kotlinx Serialization |
| Lifecycle | AndroidX Lifecycle (collectAsStateWithLifecycle) |

---

## ✨ Features

- 🎥 Browse Iranian movies list with infinite scroll / load-more pagination
- 🔍 Search movies
- ❤️ Save favorites
- ⚙️ Settings
- 🌙 Dark / Light theme with Material 3 color scheme
- 📱 Fully adaptive UI across all platforms

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or later (with KMP plugin)
- Xcode 15+ (for iOS)
- JDK 11+

### Clone the repository

```bash
git clone https://github.com/Alimmzdev/CineMood.git
cd CineMood
```

### Run on Android

```bash
# macOS / Linux
./gradlew :androidApp:installDebug

# Windows
.\gradlew.bat :androidApp:installDebug
```

### Run on Desktop

```bash
# macOS / Linux
./gradlew :desktopApp:run

# Windows
.\gradlew.bat :desktopApp:run
```

### Run on Web (Wasm — recommended)

```bash
# macOS / Linux
./gradlew :webApp:wasmJsBrowserDevelopmentRun

# Windows
.\gradlew.bat :webApp:wasmJsBrowserDevelopmentRun
```

### Run on Web (JS — broader browser support)

```bash
# macOS / Linux
./gradlew :webApp:jsBrowserDevelopmentRun

# Windows
.\gradlew.bat :webApp:jsBrowserDevelopmentRun
```

### Run on iOS

Open `iosApp/iosApp.xcodeproj` in Xcode and run on a simulator or device.

---

## 📂 Module Graph (simplified)

```
androidApp / iosApp / desktopApp / webApp
        └──► shared
               ├──► core:domain
               ├──► core:navigation
               ├──► core:presentation
               ├──► feature:home
               ├──► feature:search
               ├──► feature:favorite
               ├──► feature:settings
               ├──► service:domain
               └──► service:data:iranianMoviesApi
```

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## 📄 License

This project is open source. See [LICENSE](LICENSE) for details.