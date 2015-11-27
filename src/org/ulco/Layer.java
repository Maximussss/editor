package org.ulco;

import java.util.Vector;

public class Layer {
    public Layer() {
        m_list = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().getNewId();
    }

    public Layer(String json) {

        m_list= new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int endIndex = str.lastIndexOf("}");

        Helper.parseObjects(str.substring(objectsIndex + 9, endIndex - 1), m_list);
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int getObjectNumber() {
        //return m_list.size();
        int res = 0;

        for (int i = 0; i < m_list.size(); ++i) {

            GraphicsObject element = m_list.elementAt(i);

            res += element.size();
        }

        return res;
    }

    public int getID() {
        return m_ID;
    }

    public GraphicsObjects select(Point pt, double distance) {
        Select select = new Select();
       return select.select(pt, distance, this);
    }

    public String toJson() {
        String str = "{ type: layer, objects : { ";

        for (int i = 0; i < m_list.size(); ++i) {
            GraphicsObject element = m_list.elementAt(i);

            str += element.toJson();
            if (i < m_list.size() - 1) {
                str += ", ";
            }
        }
        return str + " } }";
    }

    public Vector<GraphicsObject> getM_list()
    {
            return m_list;
    }

    private Vector<GraphicsObject> m_list;
    private int m_ID;
}
