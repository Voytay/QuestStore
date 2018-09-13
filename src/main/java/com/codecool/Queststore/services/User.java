package com.codecool.Queststore.services;

public class User {

    private final String NAME;
    private final int ID;
    private final String LAST_NAME;
    private final String LOGIN;
    private final String PASSWORD;
    private final String EMAIL;
    private final int ROLE;
    private final String PHONE;

    public User(int id, String name, String last_name, String login, String password, String phone,
                String email, int role) {
        this.NAME = name;
        this.ID = id;
        this.LAST_NAME = last_name;
        this.LOGIN = login;
        this.PASSWORD = password;
        this.PHONE = phone;
        this.EMAIL = email;
        this.ROLE = role;
    }

    public String getPHONE() {
        return PHONE;
    }

    public int getROLE() {
        return ROLE;
    }

    public String getNAME() {
        return NAME;
    }

    public int getID() {
        return ID;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

}