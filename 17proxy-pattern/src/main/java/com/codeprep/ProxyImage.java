package com.codeprep;

public class ProxyImage implements Image {

    private String fileName;
    private RealImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    private void loadRealImage() {
        realImage = new RealImage(fileName);
    }

    @Override
    public void display() {
        if (realImage == null) {
            loadRealImage();
        }
        realImage.display();
    }
}
