# Command Pattern - Quick Reference

## Core Concept
**Encapsulate a request as an object** - Turn requests into stand-alone objects that contain all information about the request.

## Structure at a Glance

```
┌─────────┐
│ Client  │ Creates commands and sets receivers
└────┬────┘
     │
     ↓
┌──────────┐      ┌─────────┐
│ Invoker  │─────→│ Command │ (interface)
└──────────┘      └────┬────┘
   (Button)            │
                       ↓
              ┌────────────────┐
              │ ConcreteCommand│───→ ┌──────────┐
              └────────────────┘     │ Receiver │
              (BoldCommand)          └──────────┘
                                     (TextEditor)
```

## Key Components

| Component | Role | Example |
|-----------|------|---------|
| **Command** | Interface declaring execute() | `Command.java` |
| **ConcreteCommand** | Implements Command, binds to Receiver | `BoldCommand.java` |
| **Invoker** | Triggers commands | `Button.java` |
| **Receiver** | Performs actual work | `TextEditor.java` |
| **Client** | Creates and configures commands | `CommandPatternDemo.java` |

## Quick Code Template

```java
// 1. Command Interface
public interface Command {
    void execute();
}

// 2. Receiver (does actual work)
public class Receiver {
    public void action() {
        System.out.println("Performing action");
    }
}

// 3. Concrete Command
public class ConcreteCommand implements Command {
    private final Receiver receiver;
    
    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    
    @Override
    public void execute() {
        receiver.action();
    }
}

// 4. Invoker
public class Invoker {
    private Command command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void executeCommand() {
        command.execute();
    }
}

// 5. Client Usage
Receiver receiver = new Receiver();
Command command = new ConcreteCommand(receiver);
Invoker invoker = new Invoker();
invoker.setCommand(command);
invoker.executeCommand();
```

## Common Use Cases

✅ **GUI Actions** - Buttons, menu items, keyboard shortcuts  
✅ **Undo/Redo** - Reversible operations  
✅ **Macro Recording** - Composite command sequences  
✅ **Transaction Systems** - Atomic operations  
✅ **Task Queues** - Deferred execution  
✅ **Logging/Auditing** - Command history  

## Benefits vs Drawbacks

### ✅ Benefits
- Decouples sender from receiver
- Easy to add new commands (Open/Closed Principle)
- Supports undo/redo
- Can combine commands (Composite Pattern)
- Can queue and schedule commands

### ⚠️ Drawbacks
- Increases number of classes
- Can be overkill for simple operations
- May add complexity if not needed

## Critical Rules

1. **No Circular Dependencies** - Receiver should NOT call back to Invoker
2. **Receiver Does Real Work** - Not just delegation
3. **Command is Immutable** - Use `final` for receiver reference
4. **Single Responsibility** - Each command does one thing

## Testing Your Understanding

Ask yourself:
- [ ] Can the Invoker work with any Command without knowing its details?
- [ ] Can I add a new command without modifying existing code?
- [ ] Is the Receiver independent of the Invoker?
- [ ] Could I easily implement undo/redo?
- [ ] Are there any circular dependencies?

## Related Patterns

- **Memento Pattern** - Store command state for undo
- **Composite Pattern** - Macro commands (command of commands)
- **Chain of Responsibility** - Pass command along a chain
- **Strategy Pattern** - Similar structure, different intent

## Remember

> **"The Command Pattern turns a request into a stand-alone object."**  
> The Invoker doesn't need to know what the command does or who does it!

