package com.moonsworth.lunar.bridge.optifine;

import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Optional;

public interface CustomItemsBridge {
   boolean useGlint();

   boolean renderCustomEffect(RenderItemBridge bridge5_191, ItemStackBridge bridgeextension_42, BakedModelBridge mixinhelper4_53);

   default Optional<BakedModelBridge> getCustomItemModel(ItemStackBridge bridgeextension_41, BakedModelBridge mixinhelper4_52, ResourceLocationBridge horsestats143, boolean flag) {
      return Optional.empty();
   }
}
