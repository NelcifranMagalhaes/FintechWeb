package br.com.fintech.fintechweb.model;

import br.com.fintech.fintechweb.util.EncryptionUtils;

public class FintechUser {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String birthDate;
    private String passwordHash;
    private String createdAt;

    public FintechUser(int id, String name, String email, String gender, String birthDate, String passwordHash, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    public FintechUser(String name, String email, String gender, String birthDate, String passwordHash, String createdAt) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    public FintechUser(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
