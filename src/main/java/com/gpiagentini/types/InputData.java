package com.gpiagentini.types;

public record InputData(String name, String email, String subject, String message) {

    @Override
    public String toString() {
        return "Contact name: " + name + " | Contact email: " + email + "\n\n" + message;
    }
}
