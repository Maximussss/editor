package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject{

    //private Vector<Group> m_groupList;
    private Vector<GraphicsObject> m_objectList;
    private int m_ID;

    public Group() {
        //m_groupList = new  Vector<Group>();
        m_objectList = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().getNewId();
    }

    public Group(String json) {
        //m_groupList = new  Vector<Group>();
        m_objectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2));
        parseGroups(str.substring(groupsIndex + 8, endIndex - 1));
    }

    public void add(Object object) {
        m_objectList.add((GraphicsObject) object);
    }

    public Group copy() {
        Group g = new Group();

        for (GraphicsObject o : m_objectList) {

            g.add(o.copy());
        }

        return g;
    }

    public int getID() {
        return m_ID;
    }

    @Override
    boolean isClosed(Point pt, double distance) {
        //boolean close = true;

        for(int i = 0; i < m_objectList.size(); i++)
        {
            if(!(m_objectList.get(i).isClosed(pt, distance)))
                //close = false;
                return false;
        }

        //return close;
        return true;
    }

    public void move(Point delta) {
        Group g = new Group();

        for (GraphicsObject  o : m_objectList) {

            o.move(delta);
        }
    }

    private int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    private void parseGroups(String groupsStr) {
        while (!groupsStr.isEmpty()) {
            int separatorIndex = searchSeparator(groupsStr);
            String groupStr;

            if (separatorIndex == -1) {
                groupStr = groupsStr;
            } else {
                groupStr = groupsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parseGroup(groupStr));
            if (separatorIndex == -1) {
                groupsStr = "";
            } else {
                groupsStr = groupsStr.substring(separatorIndex + 1);
            }
        }
    }

    private void parseObjects(String objectsStr) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    public int size() {
        int size =  m_objectList.size();

        for (int i = 0; i < m_objectList.size(); i++) {
            GraphicsObject element = m_objectList.elementAt(i);

            if (element instanceof Group){
                size += ((Group)element).size() - 1;
            }
        }

        return size;
    }

    public String toJson() {
        String str = "{ type: group, objects : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {

            GraphicsObject element = m_objectList.elementAt(i);

            if (!(element instanceof Group)) {
                str += element.toJson();
                if (i < m_objectList.size() - 1) {
                    str += ", ";
                }
            }
        }


        str += " }, groups : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {

            GraphicsObject element = m_objectList.elementAt(i);

            if (element instanceof Group) {
                str += element.toJson();
            }
        }
        return str + " } }";
    }

    public Vector<GraphicsObject> getM_objectList()
    {
        return m_objectList;
    }

    public GraphicsObjects getElement(){
        GraphicsObjects list = new GraphicsObjects();

        for(int i = 0; i < m_objectList.size(); i++)
            list.add(m_objectList.get(i));

        return list;
    }

    public String toString() {
        String str = "group[[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);

            if (!(element instanceof Group)) {
                if (!str.equals("group[[")) {
                    str += ", ";
                }
                str += element.toString();

            }
        }
        str += "],[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);

            if (element instanceof Group) {

                str += element.toString();
            }
        }
        return str + "]]";
    }
}
