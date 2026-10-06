package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class HighlightIterator extends LunarEvent {
   private final ResourceLocationBridge skinLocation;

   public BridgeExtension method1() {
      WorldBridgeExtension itemcounter6extension1 = Ref.method8();
      if (itemcounter6extension1 == null) {
         return null;
      }

      for (BridgeExtension bridgeextension3 : itemcounter6extension1.bridge$getEntities()) {
         if (bridgeextension3 instanceof Bridge5_11 bridge5_114 && bridge5_114.bridge$getLocationSkin().equals(this.skinLocation)) {
            return bridge5_114;
         }
      }

      return null;
   }

   @Generated
   public ResourceLocationBridge getSkinLocation() {
      return this.skinLocation;
   }

   @Generated
   public HighlightIterator(ResourceLocationBridge horsestats141) {
      this.skinLocation = horsestats141;
   }
}
