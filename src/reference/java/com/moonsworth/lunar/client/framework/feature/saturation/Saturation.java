package com.moonsworth.lunar.client.framework.feature.saturation;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import org.jspecify.annotations.Nullable;

class Saturation {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("textures/gui/icons.png");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("textures/gui/sprites/hud/food_full.png");
   private static final ResourceLocationBridge field3 = ResourceLocationBridge.create("textures/gui/sprites/hud/food_half.png");
   private static final ResourceLocationBridge field4 = ResourceLocationBridge.create("textures/gui/sprites/hud/food_empty.png");
   private static final ResourceLocationBridge field5 = ResourceLocationBridge.create("textures/gui/sprites/hud/food_full_hunger.png");
   private static final ResourceLocationBridge field6 = ResourceLocationBridge.create("textures/gui/sprites/hud/food_half_hunger.png");
   private static final ResourceLocationBridge field7 = ResourceLocationBridge.create("textures/gui/sprites/hud/food_empty_hunger.png");
   public static final int field8 = 9;
   public static final int field9 = 8;

   Saturation() {
   }

   public static Saturation.Data method1(boolean flag0) {
      return method9() ? method7(flag0 ? field5 : field2) : method8(flag0 ? 88 : 52);
   }

   public static Saturation.Data method2(boolean flag0) {
      return method9() ? method7(flag0 ? field6 : field3) : method8(flag0 ? 97 : 61);
   }

   public static Saturation.Data method3(boolean flag0) {
      return method9() ? method7(flag0 ? field7 : field4) : method8(flag0 ? 133 : 16);
   }

   public static Saturation.Data method4(boolean flag0) {
      return method9() ? method7(flag0 ? field7 : field4) : method8(43);
   }

   public static Saturation.Data method5(boolean flag0, boolean flag1) {
      if (method9()) {
         return flag1 ? method2(flag0) : method1(flag0);
      } else {
         return method8(flag0 ? 133 : 25);
      }
   }

   public static Saturation.@Nullable Data method6(boolean flag0, boolean flag1) {
      return method9() ? null : method8((flag0 ? 106 : 70) + (flag1 ? 9 : 0));
   }

   private static Saturation.Data method7(ResourceLocationBridge horsestats140) {
      return new Saturation.Data(horsestats140, 0, 0, 9.0F);
   }

   private static Saturation.Data method8(int number0) {
      return new Saturation.Data(field1, number0, 27, 256.0F);
   }

   private static boolean method9() {
      return Ref.MC_VERSION >= 19;
   }

   public class Data {
      private final ResourceLocationBridge field1;
      private final int field2;
      private final int v;
      private final float size;

      public Data(ResourceLocationBridge horsestats141, int number2, int number3, float value4) {
         this.field1 = horsestats141;
         this.field2 = number2;
         this.v = number3;
         this.size = value4;
      }

      public ResourceLocationBridge method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public float method3() {
         return this.size;
      }
   }
}
