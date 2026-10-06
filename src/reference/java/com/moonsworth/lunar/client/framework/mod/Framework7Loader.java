package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import lombok.Generated;

public class Framework7Loader implements JsonPersistable {
   private final String field1;
   private final String field2;
   private final String field3;
   private final List<MixinHelper_15> field4;
   private JsonObject field5;

   public boolean method1() {
      return this.field1 != null && this.field2 != null;
   }

   public void method2(JsonObject var1) {
      this.field5 = var1;
   }

   @Override
   public void load(JsonObject var1) {
      JsonObject var2 = new JsonObject();
      if (var1.isJsonObject() && var1.has("keybinds")) {
         var2 = var1.get("keybinds").getAsJsonObject();
      }

      this.method2(var2);
   }

   @Override
   public void method1(JsonObject var1) {
      String var2 = this.method5();
      if (var2 != null) {
         JsonObject var3 = new JsonObject();

         for (MixinHelper_15 var7 : ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getKeyBindings()) {
            if (var7.bridge$getCategory().equals(var2)) {
               var3.addProperty(var7.bridge$getUntranslatedKeyDescription(), var7.bridge$getKey().name());
            }
         }

         if (!var3.isEmpty()) {
            var1.add("keybinds", var3);
         }

         this.method2(var3);
      }
   }

   @Generated
   public String method4() {
      return this.field1;
   }

   @Generated
   public String method5() {
      return this.field2;
   }

   @Generated
   public String method6() {
      return this.field3;
   }

   @Generated
   public List<MixinHelper_15> method7() {
      return this.field4;
   }

   @Generated
   public JsonObject method8() {
      return this.field5;
   }

   @Generated
   public Framework7Loader(String var1, String var2, String var3, List<MixinHelper_15> var4, JsonObject var5) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
   }
}
