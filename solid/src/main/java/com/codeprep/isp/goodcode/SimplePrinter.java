package com.codeprep.isp.goodcode;

/**
 * INTERFACE SEGREGATION PRINCIPLE (ISP) - FIXED IMPLEMENTATION
 *
 * How the ISP violation was fixed:
 *
 * BEFORE (badcode):
 * - Had one fat interface "Printer" with print(), scan(), and copy() methods
 * - SimplePrinter was forced to implement all three methods
 * - Had to throw UnsupportedOperationException for scan() and copy()
 *
 * AFTER (goodcode):
 * - Split the fat "Printer" interface into three focused interfaces:
 *   1. Printable - contains only print() method
 *   2. Scannable - contains only scan() method
 *   3. Copyable - contains only copy() method
 *
 * - SimplePrinter now implements ONLY Printable interface
 * - It only needs to implement the print() method that it actually uses
 * - No more forced implementations or runtime exceptions
 *
 * Benefits of this approach:
 * 1. Role-based interfaces: Each interface represents a specific capability
 * 2. Flexibility: Classes implement only the interfaces they need
 * 3. No dummy implementations: No need for UnsupportedOperationException
 * 4. Clear contract: The interface accurately reflects what the class can do
 * 5. Easy to extend: New printer types can mix and match capabilities
 *    (e.g., MultiPurposePrinter implements all three: Printable, Scannable, Copyable)
 *
 * This follows ISP: "No client should be forced to depend on methods it does not use."
 */
public class SimplePrinter implements Printable{

    @Override
    public void print() {
        System.out.println("SimplePrinter printing");
    }
}
