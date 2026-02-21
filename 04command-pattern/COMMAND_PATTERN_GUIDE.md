# Command Pattern Guide

## Overview
The Command Pattern is a behavioral design pattern that encapsulates a request as an object, thereby allowing you to parameterize clients with different requests, queue or log requests, and support undoable operations.

## Intent
- Encapsulate a request as an object
- Decouple the object that invokes the operation from the one that knows how to perform it
- Support undoable operations
- Support logging and transactional systems

## Structure

### Key Participants

1. **Command** (`Command.java`)
   - Declares an interface for executing operations
   ```java
   public interface Command {
       void execute();
   }
   ```

2. **ConcreteCommand** (`BoldCommand.java`, `ItalicCommand.java`)
   - Defines a binding between a Receiver object and an action
   - Implements execute() by invoking corresponding operation(s) on Receiver
   ```java
   public class BoldCommand implements Command {
       private final TextEditor textEditor;
       
       public BoldCommand(TextEditor textEditor) {
           this.textEditor = textEditor;
       }
       
       @Override
       public void execute() {
           textEditor.bold();
       }
   }
   ```

3. **Invoker** (`Button.java`)
   - Asks the command to carry out the request
   - Doesn't know anything about the concrete command or receiver
   ```java
   public class Button {
       private Command command;
       
       public void onClick() {
           if (command != null) {
               command.execute();
           }
       }
   }
   ```

4. **Receiver** (`TextEditor.java`)
   - Knows how to perform the operations required to carry out a request
   - Any class can serve as a Receiver
   ```java
   public class TextEditor {
       private String content = "";
       
       public void bold() {
           System.out.println("Making text bold");
           content = "<b>" + content + "</b>";
       }
       
       public void italic() {
           System.out.println("Making text italic");
           content = "<i>" + content + "</i>";
       }
   }
   ```

5. **Client** (`CommandPatternDemo.java`)
   - Creates ConcreteCommand objects and sets their Receiver

## How It Works

```
Client creates Commands and associates them with Receivers
    ↓
Client passes Commands to Invoker
    ↓
Invoker stores the Command
    ↓
When needed, Invoker calls Command.execute()
    ↓
Command calls operation(s) on Receiver
    ↓
Receiver performs the actual work
```

## Benefits

### 1. **Decoupling**
- The invoker (Button) is decoupled from the receiver (TextEditor)
- The button doesn't know what action will be performed or who will perform it

### 2. **Flexibility**
- You can change the command associated with an invoker at runtime
- Example: `boldButton.setCommand(italicCommand)` - same button, different behavior

### 3. **Extensibility**
- Easy to add new commands without changing existing code
- Just create a new class implementing the Command interface

### 4. **Composite Commands**
- You can combine multiple commands into macro commands
- Execute a sequence of operations with a single command

### 5. **Undo/Redo Support**
- Commands can store state and implement undo() method
- Easy to build history of operations

### 6. **Logging and Transactions**
- Commands can be logged for auditing
- Can be serialized and stored for replay
- Useful for transaction rollback

## Common Mistakes (Fixed in this Implementation)

### ❌ Circular Dependencies
**Before (Wrong):**
```java
public class TextEditor {
    private Button button;
    
    public void bold() {
        button.onClick();  // BAD: Circular dependency!
    }
}
```

**After (Correct):**
```java
public class TextEditor {
    public void bold() {
        System.out.println("Making text bold");  // GOOD: Does actual work
        content = "<b>" + content + "</b>";
    }
}
```

### ❌ Receiver Not Doing Real Work
The Receiver should perform actual operations, not just delegate back to other objects.

## When to Use

- When you want to parameterize objects with operations
- When you need to queue operations, schedule their execution, or execute them remotely
- When you need to support undo/redo
- When you want to log changes so you can reapply them in case of a system crash
- When you want to structure a system around high-level operations built on primitive operations

## Real-World Examples

1. **GUI Buttons and Menu Items**
   - Each button/menu item has a command
   - Same command can be triggered by button, menu, or keyboard shortcut

2. **Transaction Systems**
   - Each transaction is a command
   - Can be logged, undone, or replayed

3. **Task Schedulers**
   - Tasks are commands that can be queued and executed later

4. **Macro Recording**
   - Record a sequence of commands
   - Replay them later

5. **Remote Control**
   - Each button encapsulates a command
   - Can control different devices (TV, AC, Lights) using same interface

## Extensions

### Adding Undo Support
```java
public interface Command {
    void execute();
    void undo();
}

public class BoldCommand implements Command {
    private final TextEditor textEditor;
    private String previousContent;
    
    @Override
    public void execute() {
        previousContent = textEditor.getContent();
        textEditor.bold();
    }
    
    @Override
    public void undo() {
        textEditor.setContent(previousContent);
    }
}
```

### Macro Commands
```java
public class MacroCommand implements Command {
    private List<Command> commands = new ArrayList<>();
    
    public void addCommand(Command command) {
        commands.add(command);
    }
    
    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
}
```

## Running the Demo

```bash
cd 04command-pattern
mvn clean compile exec:java "-Dexec.mainClass=com.codeprep.CommandPatternDemo"
```

**Expected Output:**
```
Adding text: Hello World
Content: Hello World

Clicking Bold Button:
Making text bold
Content: <b>Hello World</b>

Clicking Italic Button:
Making text italic
Content: <i><b>Hello World</b></i>

Reassigning boldButton to execute italic command:
Making text italic
Final Content: <i><i><b>Hello World</b></i></i>
```

## Summary

The Command Pattern provides a powerful way to:
- ✅ Decouple senders from receivers
- ✅ Parameterize objects with actions
- ✅ Support undo/redo operations
- ✅ Queue and log requests
- ✅ Build complex operations from simple ones

The key is to remember that **Command** encapsulates a request, **Invoker** triggers the command, and **Receiver** does the actual work. There should be no circular dependencies between these components.

