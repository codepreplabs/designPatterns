package com.codeprep;

public class SingletonDemo
{
    public static void main( String[] args )
    {
        AppSettings settings1 = AppSettings.getInstance();
        AppSettings settings2 = AppSettings.getInstance();

        System.out.println(settings1 == settings2);
    }
}
