package com.codeprep;

public class ProxyDemo
{
    public static void main( String[] args )
    {
        Image proxyImage = new ProxyImage("test.jpg");
        proxyImage.display();
        proxyImage.display();
    }
}
