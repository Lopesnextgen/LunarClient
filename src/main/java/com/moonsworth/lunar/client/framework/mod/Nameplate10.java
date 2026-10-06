package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.Framework12;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import lombok.Generated;

public class Nameplate10 implements Framework12, JsonPersistable {
   private float field1;
   private float field2;
   private int field3;
   private boolean reset;

   public void load(JsonObject var1) {
      JsonElement var2 = var1.get("panelIndex");
      if (var2 != null && !var2.isJsonNull()) {
         this.method6(var2.getAsInt());
      } else {
         this.method6(0);
      }
   }

   public void method1(JsonObject var1) {
      int var2 = this.method5();
      if (var2 != 0) {
         var1.addProperty("panelIndex", var2);
      }
   }

   @Generated
   @Override
   public float method1() {
      return this.field1;
   }

   @Generated
   @Override
   public float method3() {
      return this.field2;
   }

   @Generated
   @Override
   public int method5() {
      return this.field3;
   }

   @Generated
   @Override
   public boolean isReset() {
      return this.reset;
   }

   @Generated
   @Override
   public void method2(float var1) {
      this.field1 = var1;
   }

   @Generated
   @Override
   public void method4(float var1) {
      this.field2 = var1;
   }

   @Generated
   @Override
   public void method6(int var1) {
      this.field3 = var1;
   }

   @Generated
   @Override
   public void setReset(boolean var1) {
      this.reset = var1;
   }
}
