# Memento Pattern - Quick Gist

## What is it?
A behavioral pattern that captures and externalizes an object's internal state so it can be restored later, without violating encapsulation.

## Think of it as:
A "time machine" or "checkpoint system" for objects - like Ctrl+Z / Ctrl+Y in applications.

## The 3 Key Players:
1. **Originator** (TextEditor) - The object whose state you want to save
2. **Memento** (EditorMemento) - A snapshot of the state (read-only, immutable)
3. **CareTaker** (CareTaker) - Manages the history of snapshots (doesn't peek inside them)

## Real-World Analogy:
Think of a video game with save points:
- **Originator** = Your game character with health, position, inventory
- **Memento** = The save file containing all that data
- **CareTaker** = The save system that manages all your save files

## Core Concept:
```
Save State → Create Memento → Store in History → Later → Retrieve Memento → Restore State
```

## When to Use:
- ✅ Need undo/redo functionality
- ✅ Need to take snapshots of object state
- ✅ Direct state access would violate encapsulation
- ✅ Need to rollback to previous states

## Simple Example Flow:
```java
TextEditor editor = new TextEditor();
CareTaker history = new CareTaker();

editor.write("Hello");
history.save(editor);           // Checkpoint 1

editor.write("Hello World");
history.save(editor);           // Checkpoint 2

history.undo(editor);           // Back to "Hello"
history.redo(editor);           // Forward to "Hello World"
```

## Key Benefit:
You can implement undo/redo without making your object's internals public!

## Remember:
- Memento = **What** (the snapshot)
- Originator = **Who** (the object being saved)
- CareTaker = **How** (the management of snapshots)

---
**Bottom Line**: Memento pattern = Save & restore object state without breaking encapsulation. Perfect for undo/redo! 🔄
