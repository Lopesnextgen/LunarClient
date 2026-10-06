package com.moonsworth.lunar.client.framework.feature.mod.fishing.nameplate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.skyblock.visitortrackerhud.SkyblockVisitorTrackerHud.Data;
import java.lang.reflect.Type;

public class JsonDeserializerImpl implements JsonDeserializer<Data> {
   public Data method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      Data var5 = new Data();
      var5.method18(this.method2(var4, "total"));
      var5.method19(this.method2(var4, "accepted"));
      var5.method20(this.method2(var4, "denied"));
      var5.method21(this.method3(var4, "gardenXP"));
      var5.method22(this.method3(var4, "farmingXP"));
      var5.method23(this.method2(var4, "copper"));
      var5.method24(this.method2(var4, "bits"));
      var5.method25(this.method2(var4, "floweringBouquet"));
      var5.method26(this.method2(var4, "greenBandana"));
      var5.method27(this.method2(var4, "overgrownGrass"));
      var5.method28(this.method2(var4, "dedicationFour"));
      var5.method29(this.method2(var4, "musicRune"));
      var5.method30(this.method2(var4, "copperDye"));
      var5.method31(this.method2(var4, "spaceHelmet"));
      if (var4.has("rarities")) {
         JsonObject var6 = var4.get("rarities").getAsJsonObject();
         var5.method4().replaceAll((var2x, var3x) -> this.method2(var6, var2x));
      }

      return var5;
   }

   private int method2(JsonObject var1, String var2) {
      return var1.has(var2) ? var1.get(var2).getAsInt() : 0;
   }

   private long method3(JsonObject var1, String var2) {
      return var1.has(var2) ? var1.get(var2).getAsLong() : 0L;
   }
}
