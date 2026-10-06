package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

@FunctionalInterface
public interface MapRenderCallback {
   void run(float value1, float value2, MarkerModel<?> markers3);
}
