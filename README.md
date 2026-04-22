# 🍽️ Recipe & Nutrition Tracker

A full-featured **desktop application** for tracking recipes, managing nutritional intake, and receiving personalized diet recommendations based on BMI analysis. Built with **Java**, **Swing GUI**, **SQLite**, and **Maven**, applying industry-standard **OOP design patterns** throughout.

[![Release](https://github.com/ucoruh/eclipse-java-maven-template/actions/workflows/release.yml/badge.svg)](https://github.com/ucoruh/eclipse-java-maven-template/actions/workflows/release.yml)

![Java](https://img.shields.io/badge/Java-8+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-3.36-003B57?style=for-the-badge&logo=sqlite&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-4.11-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![JaCoCo](https://img.shields.io/badge/JaCoCo-Coverage-red?style=for-the-badge)

---

## 📖 Overview

Recipe & Nutrition Tracker allows users to register, log in, and manage their food/recipe database with full CRUD operations. The application calculates total macronutrient intake (calories, protein, carbs, fat), computes BMI, and provides tailored dietary recommendations. It features both a **console-based CLI** and a **Swing-based GUI** interface.

### Key Highlights

- **User Authentication** — Secure registration & login with SQLite-backed persistence  
- **Product Management** — Full CRUD for food items with nutritional metadata  
- **Calorie & BMI Calculator** — Aggregates macros across all tracked products and classifies BMI  
- **Personalized Diet Advice** — Recommends daily caloric/protein/carb/fat targets based on BMI category  
- **Dual Interface** — CLI for quick terminal usage, Swing GUI for a richer desktop experience  
- **Design Patterns** — Factory, Observer, DAO, Singleton, and MVC patterns applied in practice

---

## 🏗️ Architecture & Design Patterns

The codebase is organized into clearly separated packages, each embodying a specific responsibility:

```
com.samet.erdem.tracker
├── model/        # Domain models (Product, Recipe, Food, Ingredient, User, NutritionInfo)
├── dao/          # Data Access Objects (UserDAO, ProductDAO) — SQLite via JDBC
├── factory/      # RecipeFactory — Factory Pattern for object creation
├── observer/     # NutritionObserver, NutritionTracker — Observer Pattern
├── manager/      # RecipeManager (Singleton), ProductManager
├── gui/          # Swing frames (Login, Register, MainMenu, CRUD screens)
├── database/     # DatabaseConnection utility
├── Tracker.java  # Main controller (CLI flow)
└── TrackerApp.java  # Entry point
```

| Pattern | Implementation | Purpose |
|---------|---------------|---------|
| **Factory** | `RecipeFactory` | Centralized creation of `Recipe` and `Ingredient` objects with UUID generation |
| **Observer** | `NutritionObserver` / `NutritionTracker` | Real-time notifications on nutrition updates, goal tracking, and warnings |
| **Singleton** | `RecipeManager` | Single shared instance for recipe lifecycle management |
| **DAO** | `UserDAO`, `ProductDAO` | Abstracts all database operations behind a clean interface |
| **MVC** | `gui/` + `model/` + `Tracker` | Separation of UI, business logic, and data |

---

## ✨ Features

### 🔐 Authentication
- User registration with username, password, height, and weight
- Input validation (empty fields, duplicate usernames, numeric checks)
- Secure login with credential verification

### 🥗 Product & Recipe Management
- **Add** food items with name, calories, protein, carbs, and fat
- **View** all tracked products in a formatted list
- **Update** any field selectively (press Enter to skip unchanged fields)
- **Delete** products with ownership verification

### 📊 Nutrition Analysis
- Aggregate totals for calories, protein, carbs, and fat
- BMI calculation from user height/weight
- BMI classification: Underweight · Normal · Overweight · Obese

### 🎯 Diet Recommendations
Based on BMI category, the app provides personalized daily targets:

| BMI Category | Calories | Protein | Carbs | Fat |
|-------------|----------|---------|-------|-----|
| Underweight (< 18.5) | 2500–3000 kcal | 2 g/kg | 4–5 g/kg | 1 g/kg |
| Normal (18.5–24.9) | 2000–2500 kcal | 1.6 g/kg | 3–4 g/kg | 0.8 g/kg |
| Overweight (≥ 25) | 1500–2000 kcal | 2 g/kg | 2–3 g/kg | 0.6 g/kg |

### 🖥️ Desktop GUI
A full Java Swing interface with:
- Login & Registration screens
- Main menu with navigation buttons
- Dedicated frames for each CRUD operation
- Calorie calculation and diet adjustment panels

---

## 🚀 Getting Started

### Prerequisites

| Tool | Version | Download |
|------|---------|----------|
| **JDK** | 8 or higher | [Eclipse Temurin](https://adoptium.net/) |
| **Maven** | 3.x | [Apache Maven](https://maven.apache.org/download.cgi) |

### Build & Run

```bash
# Clone the repository
git clone https://github.com/sametayca/cen206-hw-ayca-samet-java.git
cd cen206-hw-ayca-samet-java

# Build the project (compile, test, package)
cd tracker-app
mvn clean test package

# Run the application
java -jar target/tracker-app-1.0-SNAPSHOT.jar
```

### Run Tests Only

```bash
cd tracker-app
mvn clean test
```

### Generate Coverage Report

```bash
# After running tests, generate HTML coverage report
reportgenerator "-reports:target/site/jacoco/jacoco.xml" "-sourcedirs:src/main/java" "-targetdir:coveragereport" -reporttypes:Html
```

---

## 🧪 Testing

The project includes comprehensive unit tests across all layers:

| Test Class | Covers |
|-----------|--------|
| `TrackerTest` | Core tracker logic and utility methods |
| `TrackerAppTest` | Application entry point with I/O redirection |
| `ProductManagerTest` | Product management operations |
| `AddProductFrameTest` | Add product GUI |
| `AllProductsFrameTest` | Product listing GUI |
| `UpdateProductFrameTest` | Product update GUI |
| `DeleteProductFrameTest` | Product deletion GUI |
| `CalculateCaloriesFrameTest` | Calorie calculation GUI |
| `AdjustDietFrameTest` | Diet adjustment GUI |
| `UserAuthFrameTest` | Login GUI |
| `RegisterFrameTest` | Registration GUI |
| `MainMenuFrameTest` | Main menu GUI |
| `LogoutFrameTest` | Logout flow |

Code coverage is tracked via **JaCoCo** and can be viewed after building with `mvn clean test site`.

---

## 🛠️ Tech Stack

- **Language:** Java 8+
- **Build Tool:** Apache Maven
- **Database:** SQLite (via `sqlite-jdbc 3.36`)
- **GUI:** Java Swing (AWT/Swing)
- **Logging:** SLF4J + Logback
- **Testing:** JUnit 4 + Hamcrest
- **Coverage:** JaCoCo
- **Documentation:** Doxygen
- **CI/CD:** GitHub Actions (multi-platform: Ubuntu, macOS, Windows)

---

## 📁 Project Structure

```
cen206-hw-ayca-samet-java/
├── tracker-app/
│   ├── pom.xml                    # Maven configuration
│   ├── src/
│   │   ├── main/java/com/samet/erdem/tracker/
│   │   │   ├── TrackerApp.java    # Entry point
│   │   │   ├── Tracker.java       # Main controller (CLI)
│   │   │   ├── AppConfig.java     # Configuration
│   │   │   ├── model/             # Domain models
│   │   │   ├── dao/               # Database access layer
│   │   │   ├── factory/           # Factory pattern
│   │   │   ├── observer/          # Observer pattern
│   │   │   ├── manager/           # Business logic managers
│   │   │   ├── gui/               # Swing GUI frames
│   │   │   └── database/          # DB connection utility
│   │   └── test/                  # Unit tests (mirrors main structure)
│   └── tracker.db                 # SQLite database file
├── .github/workflows/             # CI/CD pipeline
├── Doxyfile                       # Doxygen configuration
├── assets/                        # Badges and screenshots
└── diagrams/                      # Architecture diagrams
```

---

## 👥 Team

| Name | Role |
|------|------|
| **Emre Kıran** | Developer |
| **Samet Ayca** | Developer |
| **Arda Firidin** | Developer |
| **Erdem Bekir Akturk** | Developer |

---

## 📄 License

This project is licensed under the terms included in the [LICENSE](LICENSE) file.
