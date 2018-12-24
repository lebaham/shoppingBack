package com.shopping.shopping.enumeration;

public enum Role {
    CLIENT("client"),
    ADMIN("administrateur");
    private String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
