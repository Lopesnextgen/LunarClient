package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;

@FunctionalInterface
public interface MarkerPredicate {
   boolean accept(Data2 data21, int number2);
}
