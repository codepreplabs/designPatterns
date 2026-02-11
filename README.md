# Design Patterns

A comprehensive Java project demonstrating various design patterns and SOLID principles with practical examples.

## 📋 Table of Contents
- [Overview](#overview)
- [Project Structure](#project-structure)
- [SOLID Principles](#solid-principles)
- [Design Patterns](#design-patterns)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Building the Project](#building-the-project)
- [Running Examples](#running-examples)

## 🎯 Overview

This repository contains practical implementations of:
- **SOLID Principles**: Examples of good and bad code practices
- **Design Patterns**: Common design patterns with real-world scenarios

The project uses Java 24 and Maven for build management.

## 📁 Project Structure

```
designPatterns/
├── solid/                    # SOLID principles examples
│   └── src/main/java/com/codeprep/
│       ├── srp/             # Single Responsibility Principle
│       ├── ocp/             # Open/Closed Principle
│       ├── lsp/             # Liskov Substitution Principle
│       ├── isp/             # Interface Segregation Principle
│       └── dip/             # Dependency Inversion Principle
│
├── 01memento-pattern/       # Memento Design Pattern
│   └── src/main/java/com/codeprep/
│       ├── MementoApp.java
│       └── memento/
│           ├── TextEditor.java
│           ├── EditorMemento.java
│           └── CareTaker.java
│
└── pom.xml                  # Parent POM
```

## 🏗️ SOLID Principles

Each principle is implemented with both **bad code** (anti-pattern) and **good code** (best practice) examples:

### 1. Single Responsibility Principle (SRP)
A class should have only one reason to change. Each class should have a single, well-defined responsibility.

### 2. Open/Closed Principle (OCP)
Software entities should be open for extension but closed for modification.

### 3. Liskov Substitution Principle (LSP)
Objects of a superclass should be replaceable with objects of a subclass without breaking the application.

### 4. Interface Segregation Principle (ISP)
Clients should not be forced to depend on interfaces they don't use.

### 5. Dependency Inversion Principle (DIP)
High-level modules should not depend on low-level modules. Both should depend on abstractions.

## 🎨 Design Patterns

### Behavioral Patterns

#### Memento Pattern
**Purpose**: Capture and restore an object's internal state without violating encapsulation.

**Implementation**: Text Editor with undo functionality
- `TextEditor`: Originator that creates mementos
- `EditorMemento`: Memento storing the state
- `CareTaker`: Manages memento history

**Use Case**: Implementing undo/redo functionality in applications.

## ⚙️ Prerequisites

- **Java**: JDK 24 or higher
- **Maven**: 3.6+ for dependency management and build
- **IDE**: IntelliJ IDEA, Eclipse, or any Java IDE (optional)

## 🚀 Getting Started

### Clone the Repository
```bash
git clone <repository-url>
cd designPatterns
```

### Install Dependencies
```bash
mvn clean install
```

## 🔨 Building the Project

### Build All Modules
```bash
mvn clean package
```

### Build Specific Module
```bash
# Build SOLID principles module
cd solid
mvn clean package

# Build Memento pattern module
cd 01memento-pattern
mvn clean package
```

## ▶️ Running Examples

### Run Memento Pattern Example
```bash
cd 01memento-pattern
mvn exec:java -Dexec.mainClass="com.codeprep.MementoApp"
```

Or run the JAR directly:
```bash
java -cp target/01momemto-pattern-1.0-SNAPSHOT.jar com.codeprep.MementoApp
```

### Run SOLID Principles Tests
```bash
cd solid
mvn test
```

## 📚 Learning Path

1. **Start with SOLID Principles**: Understand the foundational principles
   - Review bad code examples to identify anti-patterns
   - Study good code examples to learn best practices
   
2. **Explore Design Patterns**: Apply patterns to solve common problems
   - Memento Pattern: State management and undo/redo

3. **Practice**: Extend examples with your own implementations

## 🤝 Contributing

Feel free to add more design patterns or improve existing examples:
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the terms specified in the LICENSE file.

## 📝 Notes

- Each module is self-contained with its own `pom.xml`
- Examples include both "bad code" and "good code" implementations for comparison
- All code follows Java best practices and conventions

---

**Happy Learning! 🎓**
