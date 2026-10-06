package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.cosmetics.SprayManager;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import lombok.Generated;

public class SprayEntry implements JsonProviderLegacy {
   private final int field1;
   private final String field2;
   private final ResourceLocationBridge field3;
   private final int field4;
   private final float field5;
   private final float field6;
   private final float field7;
   private final float field8;
   private final boolean field9;
   private final boolean field10;
   private final boolean field11;
   private final int field12;

   public SprayEntry(
      int var1, String var2, ResourceLocationBridge var3, int var4, float value, float value2, float value3, float value4, boolean flag, boolean flag2, boolean flag3, int value5
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = ResourceLocationBridge.create("lunar-jit", var3.bridge$getPath());
      this.field4 = var4;
      this.field5 = value;
      this.field6 = value2;
      this.field7 = value3;
      this.field8 = value4;
      this.field9 = flag;
      this.field10 = flag2;
      this.field11 = flag3;
      this.field12 = value5;
   }

   public boolean method2() {
      return this.field5 <= 1.0F && this.field6 <= 1.0F;
   }

   public boolean method3() {
      return this.field7 != 1.0F && this.field8 != 0.0F;
   }

   @Override
   public String toString() {
      return "SprayEntry{id=" + this.field1 + ", name=" + this.field2 + "}";
   }

   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", this.field1);
      var1.addProperty("name", this.field2);
      var1.addProperty("texture", this.field3.bridge$getPath());
      var1.addProperty("width", this.field5);
      var1.addProperty("height", this.field6);
      var1.addProperty("offsetX", this.field7);
      var1.addProperty("offsetY", this.field8);
      var1.addProperty("particleColor", this.field4);
      var1.addProperty("animated", this.field10);
      var1.addProperty("emissive", this.field9);
      var1.addProperty("canCover", this.field11);
      var1.addProperty("duration", this.field12);
      SprayManager var2 = ThreadModuleDump63.method4().method46();
      List var3 = var2.method44();
      boolean var4 = var3 != null && var3.contains(this.field1) && ThreadModuleDump63.method4().method54().method9();
      var1.addProperty("isFreeLunarPlus", var4);
      return var1;
   }

   @Generated
   public int getId() {
      return this.field1;
   }

   @Generated
   public String getName() {
      return this.field2;
   }

   @Generated
   public ResourceLocationBridge method4() {
      return this.field3;
   }

   @Generated
   public int method5() {
      return this.field4;
   }

   @Generated
   public float getWidth() {
      return this.field5;
   }

   @Generated
   public float getHeight() {
      return this.field6;
   }

   @Generated
   public float method6() {
      return this.field7;
   }

   @Generated
   public float method7() {
      return this.field8;
   }

   @Generated
   public boolean method8() {
      return this.field9;
   }

   @Generated
   public boolean method9() {
      return this.field10;
   }

   @Generated
   public boolean method10() {
      return this.field11;
   }

   @Generated
   public int getDuration() {
      return this.field12;
   }
}
