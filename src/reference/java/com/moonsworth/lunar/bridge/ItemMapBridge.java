package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.world.MapDataBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import javax.annotation.Nullable;

public interface ItemMapBridge {
   MapDataBridge bridge$getMapData(ItemStackBridge bridgeextension_41, Itemcounter6 itemcounter62);

   @Nullable
   Integer bridge$getMapId(ItemStackBridge bridgeextension_41, Itemcounter6 itemcounter62);
}
