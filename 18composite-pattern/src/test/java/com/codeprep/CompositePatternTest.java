package com.codeprep;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CompositePatternTest {

    private String captureOutput(Runnable action) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return baos.toString().trim();
    }

    @Test
    void filePrintsNameAndPath() {
        File file = new File("notes.txt", "/home/user/");
        String output = captureOutput(file::showDetails);
        assertTrue(output.contains("File Name: notes.txt"));
        assertTrue(output.contains("Path: /home/user/"));
    }

    @Test
    void emptyFolderPrintsOnlyItsName() {
        Folder folder = new Folder("emptyFolder");
        String output = captureOutput(folder::showDetails);
        assertTrue(output.contains("Folder Name: emptyFolder"));
    }

    @Test
    void folderDelegatesToChildren() {
        File file1 = new File("a.txt", "/docs/");
        File file2 = new File("b.txt", "/docs/");
        Folder folder = new Folder("docs");
        folder.addComponent(file1);
        folder.addComponent(file2);

        String output = captureOutput(folder::showDetails);
        assertTrue(output.contains("Folder Name: docs"));
        assertTrue(output.contains("File Name: a.txt"));
        assertTrue(output.contains("File Name: b.txt"));
    }

    @Test
    void nestedFolderShowsAllDescendants() {
        File file1 = new File("file1.txt", "/usr/");
        File file2 = new File("file2.txt", "/usr/");
        File file3 = new File("file3.txt", "/usr/docs/");

        Folder inner = new Folder("folder1");
        inner.addComponent(file1);
        inner.addComponent(file2);

        Folder outer = new Folder("folder2");
        outer.addComponent(file3);
        outer.addComponent(inner);

        String output = captureOutput(outer::showDetails);
        assertTrue(output.contains("Folder Name: folder2"));
        assertTrue(output.contains("Folder Name: folder1"));
        assertTrue(output.contains("File Name: file1.txt"));
        assertTrue(output.contains("File Name: file2.txt"));
        assertTrue(output.contains("File Name: file3.txt"));
    }

    @Test
    void removeComponentExcludesChildFromOutput() {
        File file1 = new File("keep.txt", "/docs/");
        File file2 = new File("remove.txt", "/docs/");
        Folder folder = new Folder("docs");
        folder.addComponent(file1);
        folder.addComponent(file2);

        folder.removeComponent(file2);

        String output = captureOutput(folder::showDetails);
        assertTrue(output.contains("File Name: keep.txt"));
        assertFalse(output.contains("File Name: remove.txt"));
    }

    @Test
    void removeNonExistentComponentDoesNotThrow() {
        Folder folder = new Folder("docs");
        File file = new File("ghost.txt", "/docs/");
        assertDoesNotThrow(() -> folder.removeComponent(file));
    }
}

