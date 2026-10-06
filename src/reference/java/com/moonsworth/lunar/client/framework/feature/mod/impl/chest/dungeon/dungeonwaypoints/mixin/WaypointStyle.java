package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;

public class WaypointStyle {
   private final WaypointRenderMode field1;
   private final WaypointVisibility field2;
   private final int field3;
   private final int field4;
   private final boolean field5;
   private final float field6;
   private final float field7;
   private final float field8;
   private final float field9;
   private final float field10;
   private final float field11;
   public static final int field12 = 1140915968;
   public static final WaypointStyle field13 = method1(1140915968, false);

   public WaypointStyle(
      WaypointRenderMode gui2extension21, WaypointVisibility gui2extension32, int number3, int number4, boolean flag5, float value6, float value7, float value8, float value9, float value10, float value11
   ) {
      this.field1 = gui2extension21;
      this.field2 = gui2extension32;
      this.field3 = number3;
      this.field4 = number4;
      this.field5 = flag5;
      this.field6 = value6;
      this.field7 = value7;
      this.field8 = value8;
      this.field9 = value9;
      this.field10 = value10;
      this.field11 = value11;
   }

   public static WaypointStyle method1(int number0, boolean flag1) {
      return new WaypointStyle(
         WaypointRenderMode.BOTH, WaypointVisibility.BOTH, number0, ColorUtils.method22(number0, 255), flag1, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
      );
   }

   public WaypointStyle method2(float value1, float value2, float value3, float value4, float value5, float value6) {
      return new WaypointStyle(this.field1, this.field2, this.field3, this.field4, this.field5, value1, value2, value3, value4, value5, value6);
   }

   public WaypointStyle method3() {
      float value1 = Math.min(this.field9, 1.0F - this.field6);
      float value2 = Math.min(this.field10, 1.0F - this.field7);
      float value3 = Math.min(this.field11, 1.0F - this.field8);
      return value1 == this.field9 && value2 == this.field10 && value3 == this.field11 ? this : this.method2(this.field6, this.field7, this.field8, value1, value2, value3);
   }

   public boolean method4() {
      return this.field6 != 0.0F || this.field7 != 0.0F || this.field8 != 0.0F;
   }

   public boolean hasSize() {
      return this.field9 != 1.0F || this.field10 != 1.0F || this.field11 != 1.0F;
   }

   public void method5(JsonObject json1) {
      json1.addProperty("renderMode", this.field1.name());
      json1.addProperty("when", this.field2.name());
      json1.addProperty("fillColor", this.field3);
      json1.addProperty("wireframeColor", this.field4);
      json1.addProperty("showThroughWalls", this.field5);
      if (this.method4()) {
         json1.add("offset", method7(this.field6, this.field7, this.field8));
      }

      if (this.hasSize()) {
         json1.add("size", method7(this.field9, this.field10, this.field11));
      }
   }

   public static WaypointStyle method6(JsonObject json0) {
      int number1 = ThreadModuleDump9.getInt(json0, "fillColor", 1140915968);
      float[] items2 = method8(json0, "offset", 0.0F);
      float[] items3 = method8(json0, "size", 1.0F);
      return new WaypointStyle(
         (WaypointRenderMode)ThreadModuleDump9.getEnum(json0, "renderMode", WaypointRenderMode.BOTH),
         (WaypointVisibility)ThreadModuleDump9.getEnum(json0, "when", WaypointVisibility.BOTH),
         number1,
         ThreadModuleDump9.getInt(json0, "wireframeColor", ColorUtils.method22(number1, 255)),
         ThreadModuleDump9.getBoolean(json0, "showThroughWalls", false),
         items2[0],
         items2[1],
         items2[2],
         items3[0],
         items3[1],
         items3[2]
      );
   }

   private static JsonArray method7(float value0, float value1, float value2) {
      JsonArray array3 = new JsonArray();
      array3.add(value0);
      array3.add(value1);
      array3.add(value2);
      return array3;
   }

   private static float[] method8(JsonObject json0, String text1, float value2) {
      float[] items3 = new float[]{value2, value2, value2};
      JsonArray array4 = ThreadModuleDump9.getBoolean2(json0, text1, (JsonArray)null);
      if (array4 != null) {
         for (int index5 = 0; index5 < Math.min(3, array4.size()); index5++) {
            items3[index5] = ThreadModuleDump9.getBoolean49(array4, index5, value2);
         }
      }

      return items3;
   }

   public WaypointRenderMode method9() {
      return this.field1;
   }

   public WaypointVisibility method10() {
      return this.field2;
   }

   public int method11() {
      return this.field3;
   }

   public int method12() {
      return this.field4;
   }

   public boolean method13() {
      return this.field5;
   }

   public float method14() {
      return this.field6;
   }

   public float method15() {
      return this.field7;
   }

   public float method16() {
      return this.field8;
   }

   public float method17() {
      return this.field9;
   }

   public float method18() {
      return this.field10;
   }

   public float method19() {
      return this.field11;
   }
}
