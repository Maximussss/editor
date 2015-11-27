package org.ulco;

/**
 * Created by mpillier on 27/11/15.
 */
public class Triangle extends GraphicsObject {

    private Point center;
    private Point sommet1;
    private Point sommet2;
    private Point sommet3;

    public Triangle(Point center, Point sommet1, Point sommet2, Point sommet3){
        this.center = center;
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
        this.sommet3 = sommet3;
    }

    public Triangle(String json){
        json(json);
        int sommet1Index = str.indexOf("sommet1");
        int sommet2Index = str.indexOf("sommet2");
        int sommet3Index = str.indexOf("sommet3");

        center = new Point(str.substring(centerIndex + 7, sommet1Index - 1));
        sommet1 = new Point(str.substring(sommet1Index + 8, sommet2Index - 1));
        sommet2 = new Point(str.substring(sommet2Index + 8, sommet3Index - 1));
        sommet3 = new Point(str.substring(sommet3Index + 8, endIndex));
    }

    @Override
    public GraphicsObject copy() {
        return new Triangle(center.copy(), sommet1, sommet2, sommet3);
    }

    @Override
    boolean isClosed(Point pt, double distance) {
        return this.result(pt, distance, center);
    }

    @Override
    void move(Point delta) {
        center.move(delta);
    }

    @Override
    public String toJson() {
        return "{ type: triangle, center: " + center.toJson() + ", sommet1: " + this.sommet1 + ", sommet2: " + this.sommet2 + ", sommet3: " + this.sommet3 + " }";
    }

    @Override
    public String toString() {
        return "triangle[" + center.toString() + "," + sommet1 + "," + sommet2 + "," + sommet3 + "]";
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public GraphicsObjects getElement() {
        GraphicsObjects list = new GraphicsObjects();
        list.add(this);
        return list;
    }

    public Point getCenter() { return center; }
}
