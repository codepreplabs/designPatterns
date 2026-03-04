package com.codeprep;

public class File implements FileSystemComponent {

    private String name;
    private String path;

    public File(String name, String path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public void showDetails() {
        System.out.println("File Name: " + name);
        System.out.println("Path: " + path);
    }
}
