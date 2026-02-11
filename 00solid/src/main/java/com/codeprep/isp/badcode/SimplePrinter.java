package com.codeprep.isp.badcode;

/**
 * INTERFACE SEGREGATION PRINCIPLE (ISP) VIOLATION
 *
 * ISP states: "No client should be forced to depend on methods it does not use."
 *
 * Why this class violates ISP:
 * - SimplePrinter is a basic printer that only needs to print documents
 * - However, it's forced to implement the entire Printer interface which includes
 *   scan() and copy() methods that are irrelevant to a simple printer
 * - This leads to implementing these methods with UnsupportedOperationException
 *   which is a code smell and indicates poor interface design
 *
 * Problems caused by this violation:
 * 1. Fat interface: The Printer interface is too "fat" - it bundles unrelated responsibilities
 * 2. Unnecessary coupling: SimplePrinter is coupled to methods it doesn't need
 * 3. Runtime exceptions: Calling scan() or copy() will throw exceptions at runtime
 * 4. Misleading API: The interface suggests SimplePrinter can scan/copy, but it can't
 *
 * Solution (see goodcode package):
 * - Split the fat Printer interface into smaller, more focused interfaces like:
 *   Printable, Scannable, Copyable
 * - SimplePrinter would only implement Printable
 * - MultiPurposePrinter would implement all three interfaces
 */
public class SimplePrinter implements Printer{
    @Override
    public void print(Document document) {
        System.out.println("printing");
    }

    @Override
    public void scan(Document document) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void copy(Document document) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
