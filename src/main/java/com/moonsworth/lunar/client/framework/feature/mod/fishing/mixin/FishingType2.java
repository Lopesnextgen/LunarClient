package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import lombok.Generated;

public enum FishingType2 {
   GOLD("Gold", "skins/119ab896bb481ef96081065c7e3df8a04885b76b"),
   DIAMOND("Diamond", "skins/19d0bd2954efc6d7a291b04a286b19553c85aff5"),
   EMERALD("Emerald", "skins/68cce765761625513b8276bad8f1dd737cf80b5f"),
   LAPIS("Lapis", "skins/b5493f3915934034e1a34af62030aefd5bed9645");

   private final String id;
   private final String skinPath;

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public String getSkinPath() {
      return this.skinPath;
   }

   @Generated
   FishingType2(String text, String text2) {
      this.id = text;
      this.skinPath = text2;
   }
}
