package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.MixinHelper6$Data6;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.FishingType;
import javax.annotation.Nullable;
import lombok.Generated;

public class Fishing2 {
   private final Vector3iBridge field1;
   @Nullable
   private final FishingType field2;
   private final String field3;

   public Fishing2(Vector3iBridge var1, @Nullable FishingType var2) {
      this.field1 = var1;
      this.field2 = var2;
      MixinHelper6$Data6 var3 = new MixinHelper6$Data6();
      this.field3 = var3.method3(this);
   }

   public Fishing2(Vector3iBridge var1, @Nullable FishingType var2, String var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public double method1(Fishing2 var1) {
      return Math.sqrt(this.field1.method2(var1.field1.bridge$getX(), var1.field1.bridge$getY(), var1.field1.bridge$getZ()));
   }

   public JsonObject method2() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("uuid", this.field3);
      String var2 = this.field2 == null ? "null" : this.field2.getId();
      var1.addProperty("resource", var2);
      JsonObject var3 = new JsonObject();
      var3.addProperty("x", this.field1.bridge$getX());
      var3.addProperty("y", this.field1.bridge$getY());
      var3.addProperty("z", this.field1.bridge$getZ());
      var1.add("position", var3);
      return var1;
   }

   public static Fishing2 method3(JsonObject var0) {
      try {
         String var1 = var0.get("resource").getAsString();
         FishingType var2 = FishingType.fromId(var1);
         String var3 = var0.get("uuid").getAsString();
         JsonObject var4 = var0.getAsJsonObject("position");
         int var5 = var4.get("x").getAsInt();
         int var6 = var4.get("y").getAsInt();
         int var7 = var4.get("z").getAsInt();
         Horsestats20Extension2 var8 = Bridge.method8().method4(var5, var6, var7);
         return new Fishing2(var8, var2, var3);
      } catch (Exception var9) {
         return null;
      }
   }

   @Generated
   public Vector3iBridge method4() {
      return this.field1;
   }

   @Nullable
   @Generated
   public FishingType method5() {
      return this.field2;
   }

   @Generated
   public String getUuid() {
      return this.field3;
   }

   public static class Data2 {
      private float field1;
      private Fishing2 field2;

      @Generated
      public void method1(float var1) {
         this.field1 = var1;
      }

      @Generated
      public void method2(Fishing2 var1) {
         this.field2 = var1;
      }

      @Generated
      public float method3() {
         return this.field1;
      }

      @Generated
      public Fishing2 method4() {
         return this.field2;
      }

      @Generated
      public Data2(float var1, Fishing2 var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }
}
