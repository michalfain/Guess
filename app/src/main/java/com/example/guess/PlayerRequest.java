package com.example.guess;

public class PlayerRequest {

    private String name;
    private String email;
    private int [] attempts;
    private String testId = "781893349416";

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

    public int [] getAttempts() {
        return attempts;
    }

    public void setAttempts(int[] attempts) {
        this.attempts = attempts;
    }

    public String getTestId() {
        return testId;
    }
}
