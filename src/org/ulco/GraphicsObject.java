package org.ulco;

import java.util.Vector;

abstract public class GraphicsObject {
    public GraphicsObject() {
        m_ID = ID.getInstance().getNewId();
    }

    abstract public GraphicsObject copy();

    public int getID() {
        return m_ID;
    }

    abstract boolean isClosed(Point pt, double distance);

    abstract void move(Point delta);

    abstract public String toJson();

    abstract public String toString();

    abstract public int size();

    abstract public GraphicsObjects getElement();

    private int m_ID;
}
