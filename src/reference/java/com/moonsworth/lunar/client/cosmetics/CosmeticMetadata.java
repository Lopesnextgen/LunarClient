package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.protobuf.Struct;
import com.moonsworth.lunar.client.feature.Module2;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.inactive.Inactive5.Type;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import lombok.Generated;

public class CosmeticMetadata implements JsonProviderLegacy {
   private final OwnedCosmetic field1;
   private JsonObject field2;
   private final Module2 field3;

   public CosmeticMetadata(OwnedCosmetic var1, JsonObject var2) {
      this.field1 = var1;
      this.field2 = var2 == null ? new JsonObject() : var2;
      this.field3 = new Module2();
      this.field3.method1(this.field2);
   }

   public CosmeticMetadata(OwnedCosmetic var1, Module2 var2) {
      this.field1 = var1;
      this.field2 = new JsonObject();
      this.field3 = var2;
      this.field3.method2(this.field2);
   }

   public JsonElement provide() {
      JsonObject var1 = (JsonObject)this.field1.provide();
      JsonObject var2 = this.method5() == null ? new JsonObject() : this.method5().deepCopy();
      if (this.field1 instanceof EmoteModel var3) {
         var3.method6().ifPresent(var1x -> var1x.method13().forEach(var1xx -> {
            if (!var2.has(var1xx.getId())) {
               var2.add(var1xx.getId(), Type.getJsonPrimitive(var1xx.getDefaultValue()));
            }
         }));
      }

      var1.add("metadata", var2);
      return var1;
   }

   public Struct method2() {
      this.field3.method2(this.field2);
      return this.field2.isEmpty() ? Struct.newBuilder().build() : ThreadModuleDump66.method21(this.field2);
   }

   public boolean method3() {
      return ThreadModuleDump63.method4().method55().method14(this);
   }

   @Generated
   public OwnedCosmetic method4() {
      return this.field1;
   }

   @Generated
   public JsonObject method5() {
      return this.field2;
   }

   @Generated
   public Module2 method6() {
      return this.field3;
   }

   @Generated
   public void method6(JsonObject var1) {
      this.field2 = var1;
   }
}
