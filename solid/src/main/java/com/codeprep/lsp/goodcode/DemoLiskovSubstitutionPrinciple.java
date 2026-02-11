package com.codeprep.lsp.goodcode;

import com.codeprep.lsp.badcode.File;
import com.codeprep.lsp.badcode.ReadOnlyFile;

/*
 * LISKOV SUBSTITUTION PRINCIPLE (LSP):
 * ------------------------------------
 * The Liskov Substitution Principle states that objects of a superclass should be replaceable
 * with objects of its subclasses without breaking the application. In other words, a subclass
 * should be able to substitute its parent class without causing unexpected behavior or errors.
 *
 * Formally: If S is a subtype of T, then objects of type T may be replaced with objects of type S
 * without altering any of the desirable properties of the program.
 *
 */
public class DemoLiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        File file = new ReadOnlyFile();
        file.read();
    }
}
