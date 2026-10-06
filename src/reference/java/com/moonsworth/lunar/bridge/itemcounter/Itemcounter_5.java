package com.moonsworth.lunar.bridge.itemcounter;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 16)
public interface Itemcounter_5 {
   ResourceLocationBridge bridge$layerAssetId(boolean var1, ItemStackBridge var2);

   boolean bridge$isDecal();
}
