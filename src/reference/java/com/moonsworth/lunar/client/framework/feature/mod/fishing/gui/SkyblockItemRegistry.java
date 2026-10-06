package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.google.gson.JsonObject;
import com.lunarclient.items.ItemsResponse;
import com.lunarclient.items.item.Item;
import com.lunarclient.items.item.skin.Skin;
import com.lunarclient.minecraft.hypixel.skyblock.SkyBlockItemsUtil;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.text.UuidUtils;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lombok.Generated;

public final class SkyblockItemRegistry {
   private static final Pattern field1 = Pattern.compile("%%(\\w+)%%");
   public static ItemsResponse field2;
   public static Item[] items = new Item[0];
   public static final Map<String, Item> field3 = new HashMap<>();
   public static final Map<String, String> field4 = new HashMap<>();
   public static final Map<String, String> field5 = new HashMap<>();

   public static void onEnable() {
      if (field2 == null || !field2.success()) {
         BackgroundExecutor.method4(() -> {
            field2 = SkyBlockItemsUtil.getItemsSync();
            if (field2 != null && field2.success()) {
               items = field2.items();

               for (Item item3 : items) {
                  field3.put(item3.id(), item3);
                  String text4 = field1.matcher(item3.name()).replaceAll(arg0 -> {
                     try {
                        String text1 = arg0.group(1).toUpperCase(Locale.ROOT);
                        return ChatFormatting.valueOf(text1).toString();
                     } catch (IllegalArgumentException illegalargumentexception2) {
                        return arg0.group();
                     }
                  });
                  field4.put(item3.id(), text4);
                  field5.put(text4, item3.id());
               }

               Map map5 = Ref.method4().method40().method82().method15().method37();
               if (map5 != null) {
                  for (Entry entry7 : map5.entrySet()) {
                     String text8 = (String)entry7.getKey();
                     String text9 = (String)entry7.getValue();
                     field4.put(text8, text9);
                     field5.put(text9, text8);
                  }
               }
            }
         });
      }
   }

   @Nullable
   public static ItemStackBridge method1(String text0) {
      Item item1 = field3.get(text0);
      return item1 != null ? method2(item1, text0) : null;
   }

   @Nullable
   public static ItemStackBridge method2(Item item0, String text1) {
      Skin skin2 = item0.skin();
      if (skin2 == null) {
         return null;
      }

      String text3 = skin2.value();
      String text4 = new String(Base64.getDecoder().decode(text3), StandardCharsets.UTF_8);
      JsonObject json5 = (JsonObject)LunarConstants.field22.fromJson(text4, JsonObject.class);
      String text6 = UuidUtils.method1(json5.get("profileId").getAsString());
      ItemStackBridge bridgeextension_47 = SkyblockItemUtil.method12(text6, text3, skin2.signature());
      bridgeextension_47.bridge$setStackDisplayName(TextBridge.asAdventure(field4.get(text1)));
      return bridgeextension_47;
   }

   @Nullable
   public static ItemStackBridge method3(String text0) {
      Item item1 = field3.get(text0);
      if (item1 == null) {
         return null;
      }

      String text2 = item1.itemModel();
      String text3 = text2 != null && !text2.startsWith("hypixel_skyblock:") ? text2 : item1.material();
      Map map4 = Ref.method4().method40().method82().method15().method38();
      if (map4 != null) {
         String text5 = text3 + ":" + item1.durability();
         text3 = map4.getOrDefault(text5, map4.getOrDefault(text3, text3));
      }

      if (text3.equals("SKULL_ITEM") && item1.durability() == 3) {
         ItemStackBridge bridgeextension_49 = method2(item1, text0);
         if (bridgeextension_49 != null) {
            return bridgeextension_49;
         }
      }

      ItemBridge bridge6_410 = Bridge.method28().method22(text3.toLowerCase(Locale.ROOT));
      if (bridge6_410 == null) {
         return null;
      }

      ItemStackBridge bridgeextension_46 = Bridge.method8().method38(bridge6_410);
      bridgeextension_46.bridge$setFoil(item1.glowing());
      if (item1.color() != null) {
         String[] items7 = item1.color().split(",");
         if (items7.length >= 3) {
            int number8 = ColorUtils.method12(
               NumberUtils.method3(items7[0]), NumberUtils.method3(items7[1]), NumberUtils.method3(items7[2])
            );
            bridgeextension_46.bridge$setDyedColor(number8);
         }
      }

      if (text2 != null && text2.startsWith("hypixel_skyblock:")) {
         bridgeextension_46.bridge$setItemModel(ResourceLocationBridge.create(text2));
      }

      return bridgeextension_46;
   }

   @Generated
   private SkyblockItemRegistry() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
