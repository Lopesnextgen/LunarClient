package com.moonsworth.lunar.client.framework.feature.mod.fishing.nameplate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.skyblock.visitortrackerhud.SkyblockVisitorTrackerHud.Data;
import java.lang.reflect.Type;

public class VisitorTrackerDeserializer implements JsonDeserializer<Data> {
   public VisitorTrackerDeserializer() {
   }

   public Data method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = element1.getAsJsonObject();
      Data data5 = new Data();
      data5.method18(this.method2(json4, "total"));
      data5.method19(this.method2(json4, "accepted"));
      data5.method20(this.method2(json4, "denied"));
      data5.method21(this.method3(json4, "gardenXP"));
      data5.method22(this.method3(json4, "farmingXP"));
      data5.method23(this.method2(json4, "copper"));
      data5.method24(this.method2(json4, "bits"));
      data5.method25(this.method2(json4, "floweringBouquet"));
      data5.method26(this.method2(json4, "greenBandana"));
      data5.method27(this.method2(json4, "overgrownGrass"));
      data5.method28(this.method2(json4, "dedicationFour"));
      data5.method29(this.method2(json4, "musicRune"));
      data5.method30(this.method2(json4, "copperDye"));
      data5.method31(this.method2(json4, "spaceHelmet"));
      if (json4.has("rarities")) {
         JsonObject json6 = json4.get("rarities").getAsJsonObject();
         data5.method4().replaceAll((arg2x, arg3x) -> this.method2(json6, arg2x));
      }

      return data5;
   }

   private int method2(JsonObject json1, String text2) {
      return json1.has(text2) ? json1.get(text2).getAsInt() : 0;
   }

   private long method3(JsonObject json1, String text2) {
      return json1.has(text2) ? json1.get(text2).getAsLong() : 0L;
   }
}
