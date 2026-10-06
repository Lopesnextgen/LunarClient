package com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import javax.annotation.Nullable;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public enum CoordinatesType {
   COMBAT(true, Bridge.method28().method49()),
   MINING(true, Bridge.method28().method38()),
   FARMING(true, Bridge.method28().method47()),
   FORAGING(true, Bridge.method28().method50()),
   FISHING(true, Bridge.method28().method2()),
   ENCHANTING(true, Bridge.method28().method51()),
   ALCHEMY(true, Bridge.method28().method52()),
   TAMING(false, Bridge.method28().method53()),
   HUNTING(true, Bridge.method28().method4()),
   CARPENTRY(true, Bridge.method28().method55()),
   SOCIAL(false, Bridge.method28().method29()),
   RUNECRAFTING(false, Bridge.method28().method56());

   private final String text = WordUtils.capitalizeFully(this.name());
   private final boolean trackable;
   private final ItemStackBridge icon;

   CoordinatesType(boolean flag3, ItemBridge bridge6_44) {
      this.trackable = flag3;
      this.icon = Bridge.method8().method38(bridge6_44);
   }

   @Nullable
   public static CoordinatesType of(String text0) {
      try {
         return valueOf(text0);
      } catch (IllegalArgumentException illegalargumentexception5) {
         for (CoordinatesType coordinatestype4 : values()) {
            if (coordinatestype4.getText().equals(text0)) {
               return coordinatestype4;
            }
         }

         return null;
      }
   }

   @Generated
   public String getText() {
      return this.text;
   }

   @Generated
   public boolean isTrackable() {
      return this.trackable;
   }

   @Generated
   public ItemStackBridge getIcon() {
      return this.icon;
   }
}
