package com.moonsworth.lunar.client.driver.holograms;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import lombok.Generated;

public class HoverModelLegacy {
   private final OwnedCosmetic field1;
   private float field2;
   private boolean field3;

   public void method1() {
      if (!this.field3) {
         this.field3 = true;
         JsonObject var1 = new JsonObject();
         var1.addProperty("id", this.field1.method9());
         DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), "model:hover", var1);
      }
   }

   public void remove() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", 0);
      DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), "model:hover", var1);
   }

   @Generated
   public void method2(float var1) {
      this.field2 = var1;
   }

   @Generated
   public void method3(boolean var1) {
      this.field3 = var1;
   }

   @Generated
   public OwnedCosmetic method4() {
      return this.field1;
   }

   @Generated
   public float method5() {
      return this.field2;
   }

   @Generated
   public boolean method6() {
      return this.field3;
   }

   @Generated
   public HoverModelLegacy(OwnedCosmetic var1) {
      this.field1 = var1;
   }
}
