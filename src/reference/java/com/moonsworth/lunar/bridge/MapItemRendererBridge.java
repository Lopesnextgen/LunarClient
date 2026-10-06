package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.world.MapDataBridge;

public interface MapItemRendererBridge {
   void bridge$renderMap(AbstractRenderContext bridgeextension_91, int number2, MapDataBridge itemcounter2_33);

   ResourceLocationBridge bridge$getMapTexture(MapDataBridge itemcounter2_31, int number2);
}
