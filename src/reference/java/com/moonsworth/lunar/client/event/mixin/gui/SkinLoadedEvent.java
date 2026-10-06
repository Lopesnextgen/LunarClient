package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class SkinLoadedEvent extends Highlight {
   private final ResourceLocationBridge skinLocation;

   public BridgeExtension method1() {
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 == null) {
         return null;
      }

      for (BridgeExtension var3 : var1.bridge$getEntities()) {
         if (var3 instanceof Bridge5_11 var4 && var4.bridge$getLocationSkin().equals(this.skinLocation)) {
            return var4;
         }
      }

      return null;
   }

   @Generated
   public ResourceLocationBridge getSkinLocation() {
      return this.skinLocation;
   }

   @Generated
   public SkinLoadedEvent(ResourceLocationBridge var1) {
      this.skinLocation = var1;
   }
}
