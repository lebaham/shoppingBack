package com.shopping.shopping.enumeration;

public enum EtatCompte {
    ACTIF("Actif"),
    INACTIF("Inactif"),
    ARCHIVER("Archiver"),
    BLOQUER("Bloquer");

    String name;

    EtatCompte(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
