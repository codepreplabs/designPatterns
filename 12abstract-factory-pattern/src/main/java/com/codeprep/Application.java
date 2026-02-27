package com.codeprep;

public class Application {

    private Button button;
    private ScrollBar scrollBar;

    public Application(UIFactory factory){
        button = factory.createButton();
        scrollBar = factory.createScrollBar();
    }

    public void render(){
        button.render();
        scrollBar.scroll();
    }

    public static void main(String[] args){
        UIFactory factory = new WindowsFactory();
        Application app = new Application(factory);
        app.render();
    }
}
