package org.ulco;


abstract public class GraphicsObject {
    public GraphicsObject() {
        m_ID = ID.getInstance().getNewId();
    }

    abstract public GraphicsObject copy();

    public int getID() {
        return m_ID;
    }

    public boolean result(Point pt, double distance, Point m_center){
        return Math.sqrt((m_center.getX() - pt.getX()) * (m_center.getX() - pt.getX()) +
                ((m_center.getY() - pt.getY()) * (m_center.getY() - pt.getY()))) <= distance;
    }

    public void json(String json){
        str = json.replaceAll("\\s+", "");
        centerIndex = str.indexOf("center");
        endIndex = str.lastIndexOf("}");

    }

    abstract boolean isClosed(Point pt, double distance);

    abstract void move(Point delta);

    abstract public String toJson();

    abstract public String toString();

    abstract public int size();

    abstract public GraphicsObjects getElement();

    private int m_ID;
    protected String str;
    protected int centerIndex;
    protected int endIndex;
}
