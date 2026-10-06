package com.moonsworth.lunar.client.cosmetics;

import com.google.common.hash.Hashing;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;

public class VanillaCapeManager implements LoadableHandler {
   private final Map<String, Component> field1 = new HashMap<>();

   @Override
   public void init() {
      new Thread(
            () -> {
               Path var1 = ThreadModuleDump48.field12.resolve("vanilla_capes.json");
               if (var1.toFile().exists()) {
                  try {
                     String var2 = Files.readString(var1);

                     for (JsonElement var5 : (JsonArray)ThreadModuleDump48.field22.fromJson(var2, JsonArray.class)) {
                        if (var5.isJsonObject()) {
                           JsonObject var6 = var5.getAsJsonObject();
                           this.field1
                              .put(
                                 var6.get("hash").getAsString(),
                                 method2(var6.get("name").getAsString(), VanillaCapeManager.Type.valueOf(var6.get("rarity").getAsString()))
                              );
                        }
                     }
                  } catch (Exception var7) {
                     Inventorymod2.method5(var7, "Loading Vanilla Cape Data");
                  }
               }
            }
         )
         .start();
   }

   @Override
   public void close() {
   }

   public Optional<Component> method1(ResourceLocationBridge var1) {
      if (this.field1.isEmpty()) {
         return Optional.empty();
      }

      String[] var2 = var1.bridge$getPath().split("/");
      if (var2.length < 2) {
         return Optional.empty();
      }

      String var3 = var2[1];
      if (ThreadModuleDump63.MC_VERSION <= 5) {
         var3 = Hashing.sha1().hashUnencodedChars(var3).toString();
      }

      return Optional.ofNullable(this.field1.get(var3));
   }

   private static Component method2(String text2, VanillaCapeManager.Type var1) {
      TextComponent var2 = Component.text(text2);
      switch (var1) {
         case UNCOMMON:
            var2 = (TextComponent)var2.color(TextColor.color(98, 214, 90));
            break;
         case RARE:
            var2 = (TextComponent)var2.color(TextColor.color(56, 194, 224));
            break;
         case EPIC:
            var2 = (TextComponent)var2.color(TextColor.color(179, 0, 224));
            break;
         case LEGENDARY:
            var2 = (TextComponent)var2.color(TextColor.color(255, 187, 0));
            break;
         case MOJANG:
            var2 = (TextComponent)var2.color(TextColor.color(224, 41, 43));
      }

      return var2;
   }

   private enum Type {
      COMMON,
      UNCOMMON,
      RARE,
      EPIC,
      LEGENDARY,
      MOJANG;
   }
}
