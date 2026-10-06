package com.moonsworth.lunar.client.driver.hologram;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public enum HologramSkin {
   SELF("SELF", null),
   ASTRONAUT("ASTRONAUT", "slim"),
   ASTRONAUT_DARK("ASTRONAUT_DARK", "slim"),
   WHITE("WHITE", "default");

   final String id;
   ResourceLocationBridge skinLocation;
   final String skinType;

   HologramSkin(String text, String text2) {
      this.id = text;
      this.skinType = text2;
   }

   public ResourceLocationBridge getSkinLocation() {
      if (this.skinLocation == null && this != SELF) {
         if (Ref.MC_VERSION == 0) {
            this.skinLocation = ResourceLocationBridge.create("lunar", "skins/" + this.id.toLowerCase() + "_1.7.png");
            Ref.method3().bridge$getTextureManager().bridge$bindTexture(this.skinLocation);
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
