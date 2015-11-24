package org.ulco;

import java.util.Vector;

/**
 * Created by mpillier on 24/11/15.
 */
public class Select {

    public static GraphicsObjects select(Point pt, double distance, Layer layer) {
        GraphicsObjects list = new GraphicsObjects();

        for (GraphicsObject object : layer.getM_list()) {
            if (object.isClosed(pt, distance)) {
                list.addAll(object.getElement());
            }
        }
        return list;
    }

    public static GraphicsObjects select(Point pt, double distance, Document document) {
        GraphicsObjects list = new GraphicsObjects();

        for (Layer layer : document.getM_layers()) {
            list.addAll(layer.select(pt, distance));
        }
        return list;
    }

}
