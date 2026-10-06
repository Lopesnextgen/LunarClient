package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ItemShopPricesDeserializer implements JsonDeserializer<ItemShopPrices> {
   public ItemShopPricesDeserializer() {
   }

   public ItemShopPrices method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = element1.getAsJsonObject();
      JsonObject json5 = json4.getAsJsonObject("skyMart");
      Map map6 = this.method2(json4.getAsJsonObject("bits"));
      Map map7 = this.method2(json5.getAsJsonObject("farmingEssentials"));
      Map map8 = this.method2(json5.getAsJsonObject("farmingTools"));
      Map map9 = this.method2(json5.getAsJsonObject("barnSkins"));
      Map map10 = this.method2(json5.getAsJsonObject("greenhouseSkins"));
      Map map11 = this.method2(json5.getAsJsonObject("pests"));
      Map map12 = this.method2(json4.getAsJsonObject("chocolate"));
      return new ItemShopPrices(map6, map7, map8, map9, map10, map11, map12);
   }

   private Map<String, ShopItem> method2(JsonObject json1) {
      HashMap map2 = new HashMap();

      for (Entry entry4 : json1.entrySet()) {
         String text5 = (String)entry4.getKey();
         JsonObject json6 = ((JsonElement)entry4.getValue()).getAsJsonObject();

         ShopItemCategory gui3$type7;
         try {
            gui3$type7 = ShopItemCategory.valueOf(json6.get("type").getAsString());
         } catch (IllegalArgumentException illegalargumentexception13) {
            gui3$type7 = ShopItemCategory.ITEM;
         }

         ItemSkin gui_38 = null;
         if (json6.has("skin")) {
            JsonObject json9 = json6.getAsJsonObject("skin");
            gui_38 = new ItemSkin(json9.get("id").getAsString(), json9.get("texture").getAsString(), json9.get("signature").getAsString());
         }

         ArrayList list14 = new ArrayList();
         if (json6.has("requiredItems")) {
            for (JsonElement element11 : json6.getAsJsonArray("requiredItems")) {
               JsonObject json12 = element11.getAsJsonObject();
               list14.add(new RequiredItem(json12.get("id").getAsString(), json12.has("amount") ? json12.get("amount").getAsInt() : 1));
            }
         }

         ShopItem gui3_215 = new ShopItem(
            text5,
            json6.has("skyblockId") ? json6.get("skyblockId").getAsString() : text5,
            json6.get("price").getAsDouble(),
            gui3$type7,
            json6.has("displayName") ? json6.get("displayName").getAsString() : null,
            this.method3(json6),
            gui_38,
            list14
         );
         map2.put(text5, gui3_215);
      }

      return map2;
   }

   private String method3(JsonObject json1) {
      return json1.has("itemModern") ? json1.get("itemModern").getAsString() : null;
   }
}
