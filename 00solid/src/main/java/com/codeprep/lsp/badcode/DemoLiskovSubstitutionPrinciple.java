package com.codeprep.lsp.badcode;

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
 * HOW THIS CODE VIOLATES LSP:
 * ---------------------------
 * 1. The File interface defines both read() and write() methods, establishing a contract that
 *    all implementations should support both operations.
 *
 * 2. ReadOnlyFile implements File but throws UnsupportedOperationException in write() method.
 *    This means ReadOnlyFile cannot be used as a substitute for File in all contexts.
 *
 * 3. When we use ReadOnlyFile through a File reference (line 24), calling write() throws an
 *    exception at runtime. This breaks the contract established by the File interface.
 *
 * 4. The client code expects any File implementation to support write(), but ReadOnlyFile
 *    violates this expectation, making substitution unsafe.
 *
 * CORRECT APPROACH:
 * -----------------
 * - Create separate interfaces: ReadableFile and WritableFile
 * - Have File interface extend both ReadableFile and WritableFile
 * - ReadOnlyFile should only implement ReadableFile
 * - This way, ReadOnlyFile doesn't promise capabilities it cannot deliver
 */
public class DemoLiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        File file = new ReadOnlyFile();
        file.read();
        file.write(); // throwing an error is a violation of liskov substitution principle
    }
}
