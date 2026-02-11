package com.codeprep.lsp.goodcode;

import com.codeprep.lsp.goodcode.File;

public class ReadOnlyFile implements File, Readable {

    @Override
    public void read() {
        System.out.println("Reading file");
    }
}
