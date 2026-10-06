package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click12;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.mod.skyblock.chocolatefactory.SkyblockChocolateFactory;
import com.moonsworth.lunar.client.util.ThreadModuleDump11;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;

public class Fishing3 {
   private static final File field1 = new File(ThreadModuleDump48.field25 + File.separator + "skyblock_rabbit_collection.json");
   public static final ResourceLocationBridge field2 = ResourceLocationBridge.create("entity.experience_orb.pickup");
   private final SkyblockChocolateFactory field3;
   private long field4;

   public Fishing3(SkyblockChocolateFactory var1) {
      this.field3 = var1;
   }

   public void method1(Rewindhandlers.Data15 var1) {
      this.field3.method66(new Fishing4());
      if (field1.exists()) {
         try {
            String var2 = var1.method2();
            JsonElement var3 = JsonParser.parseReader(new FileReader(field1));
            if (!var3.isJsonObject()) {
               return;
            }

            JsonObject var4 = var3.getAsJsonObject();
            String var5 = ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId().toString();
            if (!var4.has(var5)) {
               return;
            }

            JsonObject var6 = var4.getAsJsonObject(var5);
            if (!var6.has(var2)) {
               return;
            }

            this.field3.method66((Fishing4)ThreadModuleDump48.field22.fromJson(var6.getAsJsonObject(var2), Fishing4.class));
         } catch (IOException var7) {
            Inventorymod2.method5(var7, "Loading SkyBlock Hoppity Rabbit Collection");
         }
      }
   }

   public void method2(String var1) {
      if (var1 != null) {
         try {
            Object var2;
            if (field1.exists()) {
               try {
                  var2 = JsonParser.parseReader(new FileReader(field1));
               } catch (JsonParseException var12) {
                  var2 = new JsonObject();
               }
            } else {
               var2 = new JsonObject();
            }

            if (!var2.isJsonObject()) {
               return;
            }

            String var3 = ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId().toString();
            JsonObject var4 = var2.getAsJsonObject();
            JsonObject var5 = var4.getAsJsonObject(var3);
            if (var5 == null) {
               var5 = new JsonObject();
            }

            JsonObject var6 = ThreadModuleDump48.field22.toJsonTree(this.field3.method55()).getAsJsonObject();
            var5.add(var1, var6);
            var4.add(var3, var5);

            try (FileWriter var7 = new FileWriter(field1)) {
               ThreadModuleDump48.field22.toJson(var4, var7);
            }
         } catch (IOException var13) {
            Inventorymod2.method5(var13, "Saving SkyBlock Hoppity Rabbit Collection");
         }

         this.field3.method55().setDirty(false);
      }
   }

   public TextComponent method3(long var1, long var3) {
      if (this.field3.method61() <= 0.0) {
         return (TextComponent)Component.text("Never").style(Style.style(NamedTextColor.RED));
      }

      Style var5 = Click12.getStyle(this.field3.method54());
      if (var3 >= var1) {
         return (TextComponent)Component.text("Now").style(var5);
      }

      long var6 = (long)((var1 - var3) / this.field3.method61()) * 1000L;
      return (TextComponent)Component.text(ThreadModuleDump11.formatDuration(var6)).style(var5);
   }

   public TextComponent method4(long var1) {
      long var3 = this.field3.method57() + this.field3.method74();
      return this.method3(var1, var3);
   }

   public void method5(ResourceLocationBridge var1, int var2) {
      long var3 = ThreadModuleDump63.method3().bridge$getSystemTime();
      if (var3 - this.field4 > var2) {
         this.field4 = var3;
         ThreadModuleDump63.method3().bridge$getSoundHandler().method1(var1);
      }
   }
}
