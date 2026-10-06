package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;

public class Dungeonwaypoints4 {
   private final Gui2Extension2 field1;
   private final Gui2Extension3 field2;
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
   public static final Dungeonwaypoints4 field13 = method1(1140915968, false);

   public Dungeonwaypoints4(
      Gui2Extension2 var1, Gui2Extension3 var2, int var3, int var4, boolean var5, float var6, float var7, float var8, float var9, float var10, float var11
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
      this.field8 = var8;
      this.field9 = var9;
      this.field10 = var10;
      this.field11 = var11;
   }

   public static Dungeonwaypoints4 method1(int var0, boolean var1) {
      return new Dungeonwaypoints4(
         Gui2Extension2.BOTH, Gui2Extension3.BOTH, var0, ThreadModuleDump23.method22(var0, 255), var1, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
      );
   }

   public Dungeonwaypoints4 method2(float var1, float var2, float var3, float var4, float var5, float var6) {
      return new Dungeonwaypoints4(this.field1, this.field2, this.field3, this.field4, this.field5, var1, var2, var3, var4, var5, var6);
   }

   public Dungeonwaypoints4 method3() {
      float var1 = Math.min(this.field9, 1.0F - this.field6);
      float var2 = Math.min(this.field10, 1.0F - this.field7);
      float var3 = Math.min(this.field11, 1.0F - this.field8);
      return var1 == this.field9 && var2 == this.field10 && var3 == this.field11 ? this : this.method2(this.field6, this.field7, this.field8, var1, var2, var3);
   }

   public boolean method4() {
      return this.field6 != 0.0F || this.field7 != 0.0F || this.field8 != 0.0F;
   }

   public boolean hasSize() {
      return this.field9 != 1.0F || this.field10 != 1.0F || this.field11 != 1.0F;
   }

   public void method5(JsonObject var1) {
      var1.addProperty("renderMode", this.field1.name());
      var1.addProperty("when", this.field2.name());
      var1.addProperty("fillColor", this.field3);
      var1.addProperty("wireframeColor", this.field4);
      var1.addProperty("showThroughWalls", this.field5);
      if (this.method4()) {
         var1.add("offset", method7(this.field6, this.field7, this.field8));
      }

      if (this.hasSize()) {
         var1.add("size", method7(this.field9, this.field10, this.field11));
      }
   }

   public static Dungeonwaypoints4 method6(JsonObject var0) {
      int var1 = ThreadModuleDump9.getInt(var0, "fillColor", 1140915968);
      float[] var2 = method8(var0, "offset", 0.0F);
      float[] var3 = method8(var0, "size", 1.0F);
      return new Dungeonwaypoints4(
         ThreadModuleDump9.getEnum(var0, "renderMode", Gui2Extension2.BOTH),
         ThreadModuleDump9.getEnum(var0, "when", Gui2Extension3.BOTH),
         var1,
         ThreadModuleDump9.getInt(var0, "wireframeColor", ThreadModuleDump23.method22(var1, 255)),
         ThreadModuleDump9.getBoolean(var0, "showThroughWalls", false),
         var2[0],
         var2[1],
         var2[2],
         var3[0],
         var3[1],
         var3[2]
      );
   }

   private static JsonArray method7(float var0, float var1, float var2) {
      JsonArray var3 = new JsonArray();
      var3.add(var0);
      var3.add(var1);
      var3.add(var2);
      return var3;
   }

   private static float[] method8(JsonObject var0, String var1, float var2) {
      float[] var3 = new float[]{var2, var2, var2};
      JsonArray var4 = ThreadModuleDump9.getBoolean2(var0, var1, (JsonArray)null);
      if (var4 != null) {
         for (int var5 = 0; var5 < Math.min(3, var4.size()); var5++) {
            var3[var5] = ThreadModuleDump9.getBoolean49(var4, var5, var2);
         }
      }

      return var3;
   }

   public Gui2Extension2 method9() {
      return this.field1;
   }

   public Gui2Extension3 method10() {
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
