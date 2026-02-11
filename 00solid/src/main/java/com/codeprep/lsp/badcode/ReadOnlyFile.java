package com.codeprep.lsp.badcode;

import com.codeprep.lsp.goodcode.Readable;

public class ReadOnlyFile implements File {

    @Override
    public void read() {
        System.out.println("Reading file");
    }

    @Override
    public void write() {
        System.out.println("Writing file");
    }
}
