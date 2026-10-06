package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.google.gson.JsonObject;
import com.lunarclient.items.ItemsResponse;
import com.lunarclient.items.item.Item;
import com.lunarclient.items.item.skin.Skin;
import com.lunarclient.minecraft.hypixel.skyblock.SkyBlockItemsUtil;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump36;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump40;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lombok.Generated;

public final class Gui2 {
   private static final Pattern field1 = Pattern.compile("%%(\\w+)%%");
   public static ItemsResponse field2;
   public static Item[] items = new Item[0];
   public static final Map<String, Item> field3 = new HashMap<>();
   public static final Map<String, String> field4 = new HashMap<>();
   public static final Map<String, String> field5 = new HashMap<>();

   public static void onEnable() {
      if (field2 == null || !field2.success()) {
         ThreadModuleDump37.method4(() -> {
            field2 = SkyBlockItemsUtil.getItemsSync();
            if (field2 != null && field2.success()) {
               items = field2.items();

               for (Item var3 : items) {
                  field3.put(var3.id(), var3);
                  String var4 = field1.matcher(var3.name()).replaceAll(var0 -> {
                     try {
                        String var1 = var0.group(1).toUpperCase(Locale.ROOT);
                        return AdventureChatFormatting.valueOf(var1).toString();
                     } catch (IllegalArgumentException var2) {
                        return var0.group();
                     }
                  });
                  field4.put(var3.id(), var4);
                  field5.put(var4, var3.id());
               }

               Map var5 = ThreadModuleDump63.method4().method40().method82().method15().method37();
               if (var5 != null) {
                  for (Entry var7 : var5.entrySet()) {
                     String var8 = (String)var7.getKey();
                     String var9 = (String)var7.getValue();
                     field4.put(var8, var9);
                     field5.put(var9, var8);
                  }
               }
            }
         });
      }
   }

   @Nullable
   public static ItemStackBridge method1(String var0) {
      Item var1 = field3.get(var0);
      return var1 != null ? method2(var1, var0) : null;
   }

   @Nullable
   public static ItemStackBridge method2(Item var0, String var1) {
      Skin var2 = var0.skin();
      if (var2 == null) {
         return null;
      }

      String var3 = var2.value();
      String var4 = new String(Base64.getDecoder().decode(var3), StandardCharsets.UTF_8);
      JsonObject var5 = (JsonObject)ThreadModuleDump48.field22.fromJson(var4, JsonObject.class);
      String var6 = ThreadModuleDump36.method1(var5.get("profileId").getAsString());
      ItemStackBridge var7 = Gui3.method12(var6, var3, var2.signature());
      var7.bridge$setStackDisplayName(AdventureTextBridge.asAdventure(field4.get(var1)));
      return var7;
   }

   @Nullable
   public static ItemStackBridge method3(String var0) {
      Item var1 = field3.get(var0);
      if (var1 == null) {
         return null;
      }

      String var2 = var1.itemModel();
      String var3 = var2 != null && !var2.startsWith("hypixel_skyblock:") ? var2 : var1.material();
      Map var4 = ThreadModuleDump63.method4().method40().method82().method15().method38();
      if (var4 != null) {
         String var5 = var3 + ":" + var1.durability();
         var3 = var4.getOrDefault(var5, var4.getOrDefault(var3, var3));
      }

      if (var3.equals("SKULL_ITEM") && var1.durability() == 3) {
         ItemStackBridge var9 = method2(var1, var0);
         if (var9 != null) {
            return var9;
         }
      }

      Bridge6_4 var10 = Bridge.method28().method22(var3.toLowerCase(Locale.ROOT));
      if (var10 == null) {
         return null;
      }

      ItemStackBridge var6 = Bridge.method8().method38(var10);
      var6.bridge$setFoil(var1.glowing());
      if (var1.color() != null) {
         String[] var7 = var1.color().split(",");
         if (var7.length >= 3) {
            int var8 = ThreadModuleDump23.method12(
               ThreadModuleDump40.method3(var7[0]), ThreadModuleDump40.method3(var7[1]), ThreadModuleDump40.method3(var7[2])
            );
            var6.bridge$setDyedColor(var8);
         }
      }

      if (var2 != null && var2.startsWith("hypixel_skyblock:")) {
         var6.bridge$setItemModel(ResourceLocationBridge.create(var2));
      }

      return var6;
   }

   @Generated
   private Gui2() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
