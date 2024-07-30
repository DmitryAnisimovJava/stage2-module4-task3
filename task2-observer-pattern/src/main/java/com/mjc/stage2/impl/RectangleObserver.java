package com.mjc.stage2.impl;

import com.mjc.stage2.Observer;
import com.mjc.stage2.entity.Rectangle;
import com.mjc.stage2.entity.RectangleValues;
import com.mjc.stage2.event.RectangleEvent;
import com.mjc.stage2.warehouse.RectangleWarehouse;

public class RectangleObserver implements Observer {

    private static final RectangleWarehouse RECTANGLE_WAREHOUSE = RectangleWarehouse.getInstance();
    @Override
    public void handleEvent(RectangleEvent event) {
        Rectangle source = event.getSource();
        double sideA = source.getSideA();
        double sideB = source.getSideB();
        RectangleValues rectangleValues = new RectangleValues(sideA * sideB, 2 * sideA + 2 * sideB);
        RECTANGLE_WAREHOUSE.put(source.getId(), rectangleValues);
    }
}
