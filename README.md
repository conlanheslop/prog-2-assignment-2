# Basketball Association JavaFX Application

A desktop application for managing basketball teams and players. This project was developed as part of a university assignment using JavaFX with **JDK 1.8.0_351** (with JavaFX built-in).

## Features

- Manage teams and players
- View and explore team/player information
- Generate error messages and validation feedback
- JavaFX-based GUI using FXML
- Follows MVC architecture

## Requirements

- Java Development Kit (JDK) 1.8.0_351 **with built-in JavaFX**
- Visual Studio Code (VS Code) or another IDE that supports JavaFX and Java 8
- Java Extension Pack (for VS Code users)

## Getting Started

### 1. Install JDK 1.8.0_351

Ensure that you have JDK 1.8.0_351 installed. You can download it from [Oracle's archive](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) or use an OpenJDK distribution that includes JavaFX.

### 2. Configure VS Code (if using)

Open your `settings.json` and add the following:

```json
"java.configuration.runtimes": [
  {
    "name": "JavaSE-1.8",
    "path": "C:\\Program Files\\Java\\jdk1.8.0_351",
    "default": true
  }
]