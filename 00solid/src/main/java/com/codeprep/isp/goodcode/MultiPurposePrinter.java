package com.codeprep.isp.goodcode;

public class MultiPurposePrinter implements Printable, Scannable, Copyable{

    @Override
    public void copy() {
        System.out.println("copying");
    }

    @Override
    public void print() {
        System.out.println("printing");
    }

    @Override
    public void scan() {
        System.out.println("scaning");
    }
}
