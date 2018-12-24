package com.shopping.shopping.enumeration;

public enum CommandeStatus {
    EN_PREPARATION ("En_Preparation"),
    ENVOYER ("Envoyer"),
    ANNULER("Annuler"),
    RENVOYER("Renvoyer"),
    LIVRER("Livrer");

    private String name;

    //Constructeur
    CommandeStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
