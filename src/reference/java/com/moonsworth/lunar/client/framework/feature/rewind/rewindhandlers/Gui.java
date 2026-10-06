package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui5;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;

public class Gui extends com.moonsworth.lunar.client.driver.core.gui.GuiIterator implements DriverGuiExtensionLegacy {
   private final File field6 = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "resourcepacks");
   private long field7 = 0L;
   private JsonArray field8 = new JsonArray();

   public JsonElement provide() {
      return this.method128();
   }

   public JsonElement method128() {
      if (System.currentTimeMillis() - this.field7 < 2000L) {
         return this.field8;
      }

      this.field7 = System.currentTimeMillis();
      JsonArray var1 = new JsonArray();
      this.method2(this.field6, var1);
      File var2 = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "assets");

      for (File var4 : ThreadModuleDump63.method3().bridge$getAllBuiltInPacksFiles()) {
         JsonObject var5 = new JsonObject();
         String var6 = var4.getName();
         String var7 = ThreadModuleDump63.method3().bridge$getBuiltInPackName(new File(var4.getName()).getName());
         if (var7 != null) {
            var6 = ThreadModuleDump63.method4().method67().method2("rewind", var7, new Object[0]);
         }

         var5.addProperty("name", var6);
         var5.addProperty("path", Gui5.method2(var4, var2));
         var5.addProperty("type", "pack");
         var1.add(var5);
      }

      return this.field8 = var1;
   }

   private void method2(File var1, JsonArray var2) {
      File[] var3 = var1.listFiles();
      if (var3 != null) {
         for (File var7 : var3) {
            JsonObject var8 = new JsonObject();
            var8.addProperty("name", var7.getName());
            if (var7.isDirectory() && !new File(var7, "pack.mcmeta").exists()) {
               JsonArray var9 = new JsonArray();
               var8.addProperty("type", "folder");
               var8.add("packs", var9);
               var2.add(var8);
               this.method2(var7, var9);
            } else if (!var7.isDirectory() && var7.getName().endsWith(".zip") || var7.isDirectory() && new File(var7, "pack.mcmeta").exists()) {
               var8.addProperty("path", Gui5.method2(var7, this.field6));
               var8.addProperty("type", "pack");
               var2.add(var8);
            }
         }
      }
   }
}
