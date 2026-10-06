package com.moonsworth.lunar.client.framework.feature.animations.customhelditems;

import lombok.Generated;

enum CustomhelditemsType {
   LEFT("leftHand", false),
   RIGHT("rightHand", true);

   public final String id;
   public final boolean enabledByDefault;

   public CustomhelditemsType opposite() {
      return this == LEFT ? RIGHT : LEFT;
   }

   @Generated
   CustomhelditemsType(String text, boolean flag) {
      this.id = text;
      this.enabledByDefault = flag;
   }
}
