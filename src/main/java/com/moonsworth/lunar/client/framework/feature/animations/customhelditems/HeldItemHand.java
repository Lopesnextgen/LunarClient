package com.moonsworth.lunar.client.framework.feature.animations.customhelditems;

import lombok.Generated;

enum HeldItemHand {
   LEFT("leftHand", false),
   RIGHT("rightHand", true);

   public final String id;
   public final boolean enabledByDefault;

   public HeldItemHand opposite() {
      return this == LEFT ? RIGHT : LEFT;
   }

   @Generated
   HeldItemHand(String text, boolean flag) {
      this.id = text;
      this.enabledByDefault = flag;
   }
}
