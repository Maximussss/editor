package org.ulco;

public class ID {

    private static int id = 0;

    public static int currentID(){
        ++id;
        return id;
    }

    public static int getId()
    {
        return id;
    }
}