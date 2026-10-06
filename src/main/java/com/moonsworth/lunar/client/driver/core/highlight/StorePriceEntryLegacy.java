package com.moonsworth.lunar.client.driver.core.highlight;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import java.util.UUID;

public class StorePriceEntryLegacy implements JsonProviderLegacy {
   private final UUID field1;
   private final int field2;
   private final String field3;
   private final Integer[] field4;
   private final StorePriceLegacy field5;
   private final String field6;
   private final int field7;

   public StorePriceEntryLegacy(UUID var1, int var2, String var3, Integer[] var4, StorePriceLegacy var5, String var6, int var7) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
   }

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", this.field1.toString());
      var1.addProperty("name", this.field3);
      var1.addProperty("currency", this.field6);
      var1.addProperty("fractionDigits", this.field7);
      JsonObject var2 = new JsonObject();
      if (this.field5.method1() != null) {
         var2.addProperty("coins", this.field5.method1());
      }

      var2.addProperty("value", this.field5.value());
      if (this.field5.method2() != null && this.field5.method2() != this.field5.value()) {
         var2.addProperty("original", this.field5.method2());
      }

      var1.add("value", var2);
      JsonArray var3 = new JsonArray();
      if (this.field4 != null) {
         Integer[] var4 = this.field4;
         int var5 = var4.length;

         for (int var6 = 0; var6 < var5; var6++) {
            int var7 = var4[var6];
            JsonObject var8 = new JsonObject();
            var8.addProperty("id", var7);
            var3.add(var8);
         }
      }

      var1.add("cosmetics", var3);
      return var1;
   }

   public UUID id() {
      return this.field1;
   }

   public int method2() {
      return this.field2;
   }

   public String name() {
      return this.field3;
   }

   public Integer[] method3() {
      return this.field4;
   }

   public StorePriceLegacy method4() {
      return this.field5;
   }

   public String method5() {
      return this.field6;
   }

   public int method6() {
      return this.field7;
   }
}
