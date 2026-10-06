package com.moonsworth.lunar.client.framework.feature.tiertagger;

import lombok.Generated;

public enum TiertaggerType {
   NA(14244970),
   EU(9040284),
   AS(11501457),
   SA(6147292),
   ME(15061894),
   AU(14003584),
   AF(12409789);

   public final int color;

   public static TiertaggerType fromString(String text) {
      String[] items1 = text.split("_");
      if (items1.length == 1) {
         return valueOf(items1[0].substring(0, 2));
      }

      StringBuilder builder2 = new StringBuilder();

      for (String text6 : items1) {
         builder2.append(text6.charAt(0));
      }

      return valueOf(builder2.toString());
   }

   @Generated
   TiertaggerType(int value) {
      this.color = value;
   }
}
