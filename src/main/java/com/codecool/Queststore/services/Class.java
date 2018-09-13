package com.codecool.Queststore.services;

public class Class {
    private final String className;
    private final String description;

    Class(String className, String description) {
        this.className = className;
        this.description = description;
    }

    public String getClassName() {
        return className;
    }

    public String getDescription() {
        return description;
    }
}
