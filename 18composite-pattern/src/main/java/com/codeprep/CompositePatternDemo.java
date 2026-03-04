package com.codeprep;

public class CompositePatternDemo {
    public static void main(String[] args) {

        // Leaf nodes
        FileSystemComponent file1 = new File("file1.txt", "/usr/");
        FileSystemComponent file2 = new File("file2.txt", "/usr/");
        FileSystemComponent file3 = new File("file3.txt", "/usr/docs/");

        // Composite: nested folder inside folder
        Folder folder1 = new Folder("folder1");
        folder1.addComponent(file1);
        folder1.addComponent(file2);

        Folder folder2 = new Folder("folder2");
        folder2.addComponent(file3);
        folder2.addComponent(folder1); // nested folder

        System.out.println("=== Full tree ===");
        folder2.showDetails();

        // Demonstrate removeComponent
        folder1.removeComponent(file2);
        System.out.println("\n=== After removing file2 from folder1 ===");
        folder2.showDetails();
    }
}
