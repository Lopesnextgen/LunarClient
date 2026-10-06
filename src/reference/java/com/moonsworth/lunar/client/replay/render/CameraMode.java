package com.moonsworth.lunar.client.replay.render;

import lombok.Generated;

public enum CameraMode implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
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
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   CameraMode(String text3) {
      this.id = text3;
   }
}
