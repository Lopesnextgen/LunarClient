package com.moonsworth.lunar.bridge.slayer;

import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BakedModelExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Optional;

public interface Slayer6 {
   boolean useGlint();

   boolean renderCustomEffect(Bridge5_19 var1, ItemStackBridge var2, BakedModelExtension var3);

   default Optional<BakedModelExtension> getCustomItemModel(ItemStackBridge var1, BakedModelExtension var2, ResourceLocationBridge var3, boolean flag) {
      return Optional.empty();
   }
}
