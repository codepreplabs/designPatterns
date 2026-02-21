package com.codeprep;

public class TemplateMethodPatternDemo {

    public static void main(String[] args) {
        DataParser csvParser = new CSVParser();
        csvParser.parse();

        System.out.println();

        DataParser jsonParser = new JSONParser();
        jsonParser.parse();
    }
}
