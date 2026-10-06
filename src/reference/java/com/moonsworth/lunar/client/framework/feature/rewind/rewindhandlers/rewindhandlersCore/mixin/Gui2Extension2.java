package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.mixin;

import lombok.Generated;

public enum Gui2Extension2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   POV("pov"),
   FREE_CAMERA("freeCamera"),
   FOLLOW("follow"),
   FORCE_FIRST_PERSON("forceFirstPerson"),
   FORCE_THIRD_PERSON_BACK("forceThirdPersonBack"),
   FORCE_THIRD_PERSON_FRONT("forceThirdPersonFront");

   private final String id;

   public boolean isFirstPerson() {
      return this == POV || this == FORCE_FIRST_PERSON;
   }

   public boolean isFixedToPlayer() {
      return this != FREE_CAMERA && this != FOLLOW;
   }

   public boolean forceThirdPersonView() {
      return this.isFixedToPlayer() && this != POV;
   }

   public int getThirdPersonView() {
      return switch (this) {
         case FORCE_THIRD_PERSON_BACK -> 1;
         case FORCE_THIRD_PERSON_FRONT -> 2;
         default -> 0;
      };
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension2(String text) {
      this.id = text;
   }
}
