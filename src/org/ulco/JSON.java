package org.ulco;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JSON {
    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");

        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));
        String s = "org.ulco."+type.replaceFirst(".",(type.charAt(0)+"").toUpperCase());

        /*if (type.compareTo("square") == 0) {
            o = new Square(str);
        } else if (type.compareTo("rectangle") == 0) {
            o = new Rectangle(str);
        } else if (type.compareTo("circle") == 0) {
            o = new Circle(str);
        }*/

        try {

            Class cl = Class.forName(s);
            Class[] types = new Class[]{String.class};
            Constructor ct = cl.getConstructor(types);
            Object test = ct.newInstance(str);
            o=(GraphicsObject)test;

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return o;
    }

    static public Group parseGroup(String json) {
        return new Group(json);
    }

    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}
