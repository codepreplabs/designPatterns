# Memento Pattern - Complete Implementation

## Pattern Overview

The **Memento Pattern** is a behavioral design pattern that allows you to save and restore the previous state of an object without revealing the details of its implementation. It's particularly useful for implementing undo/redo functionality.

## Key Components

### 1. **Originator (TextEditor)**
- The object whose state needs to be saved
- Creates a memento containing a snapshot of its current state
- Can restore its state from a memento

### 2. **Memento (EditorMemento)**
- Stores the internal state of the Originator
- Immutable object that protects against external modification
- Only the Originator can read/write to the Memento's state

### 3. **CareTaker (CareTaker)**
- Manages the mementos (history of states)
- Never examines or modifies the contents of a memento
- Provides undo/redo functionality by managing a list of mementos

## Implementation Details

### Previous Implementation Issue
The original implementation had a critical flaw:
```java
public void undo(TextEditor textEditor) {
    if (!history.isEmpty()) {
        history.pop();  // ❌ Removes current state permanently
        if (!history.isEmpty()) {
            textEditor.restore(history.peek());
        }
    }
}
```

**Problems:**
- ❌ No redo capability
- ❌ History is destroyed on undo
- ❌ States are lost permanently

### Improved Implementation
The new implementation uses an **index-based approach**:

```java
private final List<EditorMemento> history = new ArrayList<>();
private int currentIndex = -1; // Tracks current position in history

public void save(TextEditor textEditor) {
    // Clear forward history when saving after undo
    if (currentIndex < history.size() - 1) {
        history.subList(currentIndex + 1, history.size()).clear();
    }
    history.add(textEditor.save());
    currentIndex++;
}

public void undo(TextEditor textEditor) {
    if (currentIndex > 0) {
        currentIndex--;
        textEditor.restore(history.get(currentIndex));
    }
}

public void redo(TextEditor textEditor) {
    if (currentIndex < history.size() - 1) {
        currentIndex++;
        textEditor.restore(history.get(currentIndex));
    }
}
```

**Benefits:**
- ✅ Full undo/redo support
- ✅ History is preserved until overwritten
- ✅ Efficient memory management (clears forward history on new save after undo)
- ✅ Helper methods to check if undo/redo is possible

## How It Works

### Scenario 1: Normal Save and Undo
```
1. Save "State 1" → history: [State 1], currentIndex: 0
2. Save "State 2" → history: [State 1, State 2], currentIndex: 1
3. Save "State 3" → history: [State 1, State 2, State 3], currentIndex: 2
4. Undo → currentIndex: 1, restored to "State 2"
5. Undo → currentIndex: 0, restored to "State 1"
6. Redo → currentIndex: 1, restored to "State 2"
```

### Scenario 2: Save After Undo (Branching)
```
1. Save "State 1" → history: [State 1], currentIndex: 0
2. Save "State 2" → history: [State 1, State 2], currentIndex: 1
3. Save "State 3" → history: [State 1, State 2, State 3], currentIndex: 2
4. Undo → currentIndex: 1, restored to "State 2"
5. Save "State 4" → history: [State 1, State 2, State 4], currentIndex: 2
   (State 3 is cleared because we branched from State 2)
```

## Key Features

1. **Encapsulation**: The Memento protects the Originator's internal state
2. **History Management**: Full undo/redo with efficient memory usage
3. **Branching Support**: Clearing forward history prevents confusion when saving after undo
4. **State Queries**: Helper methods to check if undo/redo is possible

## Use Cases

- **Text Editors**: Undo/redo typing operations
- **Graphics Applications**: Revert drawing operations
- **Game Development**: Save/load game states
- **Database Transactions**: Rollback to previous state
- **Configuration Management**: Revert settings changes

## Advantages

✅ **Single Responsibility**: Memento handles state storage, CareTaker handles history
✅ **Encapsulation**: Internal state is not exposed
✅ **Simplicity**: Easy to implement and understand
✅ **Flexibility**: Can save multiple states and navigate through them

## Potential Drawbacks

⚠️ **Memory Usage**: Storing many states can consume significant memory
⚠️ **Serialization**: Complex objects might be expensive to clone
⚠️ **State Size**: Large objects create large mementos

## Best Practices

1. **Immutable Mementos**: Make memento objects immutable to prevent accidental modifications
2. **Selective State**: Only save the minimum state needed for restoration
3. **Memory Limits**: Consider implementing a maximum history size
4. **Deep Copies**: Ensure proper deep copying of mutable objects
5. **Clear Branching**: Clear forward history when saving after undo to avoid confusion

## Testing

The implementation includes comprehensive tests:
- Basic save/undo operations
- Multiple undo/redo operations
- Edge cases (empty history, single state)
- Branching scenarios
- Large history management
- Special characters and null handling

All 46 tests pass successfully! ✅
