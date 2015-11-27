package org.ulco;


public class Square extends Rectangle {
    public Square(Point center, double length) {
        super(center,length,length);
        m_length = length;
    }

    public Square(String json) {
        super();
        json(json);
        int lengthIndex = str.indexOf("length");

        m_origin = new Point(str.substring(centerIndex + 7, lengthIndex - 1));
        m_length = Double.parseDouble(str.substring(lengthIndex + 7, endIndex));

    }

    public GraphicsObject copy() {
        return new Square(m_origin.copy(), m_length);
    }

    public Point getOrigin() { return m_origin; }

    void move(Point delta) { m_origin.move(delta); }

    public String toJson() {
        return "{ type: square, center: " + m_origin.toJson() + ", length: " + this.m_length + " }";
    }

    public String toString() {
        return "square[" + m_origin.toString() + "," + m_length + "]";
    }

    public int size(){
        return 1;
    }

    public GraphicsObjects getElement(){
        GraphicsObjects list = new GraphicsObjects();
        list.add(this);
        return list;
    }

    private final double m_length;
}
