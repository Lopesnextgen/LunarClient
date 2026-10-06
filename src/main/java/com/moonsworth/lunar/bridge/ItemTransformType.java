package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum ItemTransformType {
   NONE(0, "none"),
   THIRD_PERSON_LEFT_HAND(1, "thirdperson_lefthand"),
   THIRD_PERSON_RIGHT_HAND(2, "thirdperson_righthand"),
   FIRST_PERSON_LEFT_HAND(3, "firstperson_lefthand"),
   FIRST_PERSON_RIGHT_HAND(4, "firstperson_righthand"),
   HEAD(5, "head"),
   GUI(6, "gui"),
   GROUND(7, "ground"),
   FIXED(8, "fixed");

   public static ItemTransformType[] VALUES = values();
   private final byte id;
   private final String name;

   ItemTransformType(int value, String text) {
      this.name = text;
      this.id = (byte)value;
   }

   public String getSerializedName() {
      return this.name;
   }

   public boolean firstPerson() {
      return this == FIRST_PERSON_LEFT_HAND || this == FIRST_PERSON_RIGHT_HAND;
   }

   @Generated
   public byte getId() {
      return this.id;
   }
}
