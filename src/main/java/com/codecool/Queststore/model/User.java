package com.codecool.Queststore.model;

public class User {
    private String username;
    private String passwdhash;
    private String salt;
    private String name;
    private String lastname;
    private int role;
    private String email;
    private String phone;

    public User(String username, String passwdhash, String salt, String name,
         String lastname, int role, String email, String phone){
        this.username = username;
        this.passwdhash = passwdhash;
        this.salt = salt;
        this.name = name;
        this.lastname = lastname;
        this.role = role;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswdhash() {
        return passwdhash;
    }

    public void setPasswdhash(String passwd_hash) {
        this.passwdhash = passwd_hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String last_name) {
        this.lastname = last_name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
