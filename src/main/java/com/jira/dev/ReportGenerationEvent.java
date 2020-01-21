package com.jira.dev;

import java.util.UUID;

public class ReportGenerationEvent {

    private final String fConfig;
    private final String fKey;

    public ReportGenerationEvent(String reportConfiguration) {
        fConfig = reportConfiguration;
        fKey = System.currentTimeMillis() + "" + UUID.randomUUID();
    }

    public String getConfig() {
        return fConfig;
    }

    public String getKey() {
        return fKey;
    }
}

