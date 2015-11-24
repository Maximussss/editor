package org.ulco;

public class ID {

    private static int id = 0;

    private ID(){}

    /** Instance unique pré-initialisée */
    private static ID INSTANCE = new ID();

    /** Point d'accès pour l'instance unique du singleton */
    public static ID getInstance(){
        return INSTANCE;
    }

    public int getNewId(){
        id++;
        return id;
    }

    public int getId(){
        return id;
    }


}