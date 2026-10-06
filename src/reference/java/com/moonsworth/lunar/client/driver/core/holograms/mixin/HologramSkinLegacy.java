package com.moonsworth.lunar.client.driver.core.holograms.mixin;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public enum HologramSkinLegacy {
   SELF("SELF", null),
   ASTRONAUT("ASTRONAUT", "slim"),
   ASTRONAUT_DARK("ASTRONAUT_DARK", "slim"),
   WHITE("WHITE", "default");

   final String id;
   ResourceLocationBridge skinLocation;
   final String skinType;

   HologramSkinLegacy(String text, String text2) {
      this.id = text;
      this.skinType = text2;
   }

   public ResourceLocationBridge getSkinLocation() {
      if (this.skinLocation == null && this != SELF) {
         if (ThreadModuleDump63.MC_VERSION == 0) {
            this.skinLocation = ResourceLocationBridge.create("lunar", "skins/" + this.id.toLowerCase() + "_1.7.png");
            ThreadModuleDump63.method3().bridge$getTextureManager().bridge$bindTexture(this.skinLocation);
         } else {
            this.skinLocation = ResourceLocationBridge.create("lunar", "skins/" + this.id.toLowerCase() + ".png");
         }
      }

      return this.skinLocation;
   }

   @Override
   public String toString() {
      return this.id;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public String getSkinType() {
      return this.skinType;
   }
}
