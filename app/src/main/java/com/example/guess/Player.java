package com.example.guess;

public class Player {
    String name;
    String email;
    int attempts;

    public Player(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Player(String name, String email, int attempts) {
        this.name = name;
        this.email = email;
        this.attempts = attempts;
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

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
