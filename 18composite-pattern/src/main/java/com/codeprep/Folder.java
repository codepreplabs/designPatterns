package com.codeprep;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {

    private String name;
    private List<FileSystemComponent> fileSystemComponents = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        fileSystemComponents.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        fileSystemComponents.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder Name: " + name);
        for (FileSystemComponent component : fileSystemComponents) {
            component.showDetails();
        }
    }
}
