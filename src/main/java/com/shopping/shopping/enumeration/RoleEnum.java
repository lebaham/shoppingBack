package com.shopping.shopping.enumeration;

public enum RoleEnum {
    CLIENT("Client"),
    ADMIN("Admin");
    private String name;
    RoleEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
