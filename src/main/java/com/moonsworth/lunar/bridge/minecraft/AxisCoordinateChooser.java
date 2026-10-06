package com.moonsworth.lunar.bridge.minecraft;

@FunctionalInterface
public interface AxisCoordinateChooser {
   double choose(double value1, double value3, double value5);
}
