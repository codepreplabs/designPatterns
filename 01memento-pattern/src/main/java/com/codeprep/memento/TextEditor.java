package com.codeprep.memento;

public class TextEditor {

    private String content;

    public void write(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    // save the current state and return a memento
    public EditorMemento save(){
        return new EditorMemento(content);
    }

    // restore (memento -> update the state of the current content)
    public void restore(EditorMemento memento){
        content = memento.getContent();
    }
}
