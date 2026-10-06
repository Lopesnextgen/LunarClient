package com.moonsworth.lunar.client.mod.render.serverholograms;

import lombok.Generated;
import net.kyori.adventure.text.Component;

public final class Serverholograms {
   private final String field1;
   private Component[] field2;
   private final double field3;
   private final double field4;
   private final double field5;
   private final boolean field6;
   private final boolean field7;
   private final boolean field8;
   private final boolean field9;

   public Serverholograms(String text1, Component[] items2, double value3, double value5, double value7, boolean flag9) {
      this(text1, items2, value3, value5, value7, true, false, flag9);
   }

   public Serverholograms(String text1, Component[] items2, double value3, double value5, double value7, boolean flag9, boolean flag10, boolean flag11) {
      this(text1, items2, value3, value5, value7, true, false, true, flag11);
   }

   public Serverholograms(String text1, Component[] items2, double value3, double value5, double value7, boolean flag9, boolean flag10, boolean flag11, boolean flag) {
      this.field1 = text1;
      this.field2 = items2;
      this.field3 = value3;
      this.field4 = value5;
      this.field5 = value7;
      this.field6 = flag9;
      this.field7 = flag10;
      this.field8 = flag11;
      this.field9 = flag;
   }

   @Generated
   public String getId() {
      return this.field1;
   }

   @Generated
   public Component[] method1() {
      return this.field2;
   }

   @Generated
   public double getX() {
      return this.field3;
   }

   @Generated
   public double getY() {
      return this.field4;
   }

   @Generated
   public double getZ() {
      return this.field5;
   }

   @Generated
   public boolean isShowThroughWalls() {
      return this.field6;
   }

   @Generated
   public boolean method2() {
      return this.field7;
   }

   @Generated
   public boolean isBackground() {
      return this.field8;
   }

   @Generated
   public boolean method3() {
      return this.field9;
   }

   @Generated
   public void method4(Component[] items1) {
      this.field2 = items1;
   }
}
