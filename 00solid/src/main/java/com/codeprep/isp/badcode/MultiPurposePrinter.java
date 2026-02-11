package com.codeprep.isp.badcode;

public class MultiPurposePrinter implements Printer{

    @Override
    public void print(Document document) {
        System.out.println("printing");
    }

    @Override
    public void scan(Document document) {
        System.out.println("scanning");
    }

    @Override
    public void copy(Document document) {
        System.out.println("copying");
    }
}
