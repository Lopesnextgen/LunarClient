package com.moonsworth.lunar.bridge;

import java.util.List;

@FunctionalInterface
public interface PropertyMapBridge {
   List<PropertyBridge> bridge$get(String text1);
}
