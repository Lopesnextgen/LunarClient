package com.moonsworth.lunar.bridge;

import java.util.Locale;

public enum MixinHelper2$Type5 {
   NONE,
   THIRD_PERSON_LEFT_HAND,
   THIRD_PERSON_RIGHT_HAND,
   FIRST_PERSON_LEFT_HAND,
   FIRST_PERSON_RIGHT_HAND,
   HEAD,
   GUI,
   GROUND,
   FIXED;

   public String getModernName() {
      return this.name().toUpperCase(Locale.ROOT);
   }

   public int legacyIndex() {
      return switch (this) {
         case NONE -> 0;
         case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND -> 1;
         case FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> 2;
         default -> this.ordinal() - 2;
      };
   }
}
