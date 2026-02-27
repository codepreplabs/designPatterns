package com.codeprep;

public class AppSettings {

    private static volatile AppSettings INSTANCE;

    private final String apiUrl;
    private final String apiKey;

    private AppSettings() {
        this.apiUrl = "https://api.example.com";
        this.apiKey = "1234567890";
    }

    public static AppSettings getInstance() {
        if(INSTANCE == null){
            synchronized (AppSettings.class){
                if(INSTANCE == null){
                    INSTANCE = new AppSettings();
                }
            }
        }
        return INSTANCE;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getApiKey() {
        return apiKey;
    }
}
