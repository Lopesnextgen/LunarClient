package com.moonsworth.lunar.client.replay.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.replay.project.PathUtils;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;

public class RewindPackListProvider extends com.moonsworth.lunar.client.driver.core.gui.GuiIterator implements DriverGuiExtension {
   private final File field6 = new File(Ref.method3().bridge$getMcDataDir(), "resourcepacks");
   private long field7 = 0L;
   private JsonArray field8 = new JsonArray();

   public RewindPackListProvider() {
   }

   public JsonElement provide() {
      return this.method128();
   }

   public JsonElement method128() {
      if (System.currentTimeMillis() - this.field7 < 2000L) {
         return this.field8;
      }

      this.field7 = System.currentTimeMillis();
      JsonArray array1 = new JsonArray();
      this.method2(this.field6, array1);
      File file2 = new File(Ref.method3().bridge$getMcDataDir(), "assets");

      for (File file4 : Ref.method3().bridge$getAllBuiltInPacksFiles()) {
         JsonObject json5 = new JsonObject();
         String text6 = file4.getName();
         String text7 = Ref.method3().bridge$getBuiltInPackName(new File(file4.getName()).getName());
         if (text7 != null) {
            text6 = Ref.method4().method67().method2("rewind", text7, new Object[0]);
         }

         json5.addProperty("name", text6);
         json5.addProperty("path", PathUtils.method2(file4, file2));
         json5.addProperty("type", "pack");
         array1.add(json5);
      }

      return this.field8 = array1;
   }

   private void method2(File file1, JsonArray array2) {
      File[] items3 = file1.listFiles();
      if (items3 != null) {
         for (File file7 : items3) {
            JsonObject json8 = new JsonObject();
            json8.addProperty("name", file7.getName());
            if (file7.isDirectory() && !new File(file7, "pack.mcmeta").exists()) {
               JsonArray array9 = new JsonArray();
               json8.addProperty("type", "folder");
               json8.add("packs", array9);
               array2.add(json8);
               this.method2(file7, array9);
            } else if (!file7.isDirectory() && file7.getName().endsWith(".zip") || file7.isDirectory() && new File(file7, "pack.mcmeta").exists()) {
               json8.addProperty("path", PathUtils.method2(file7, this.field6));
               json8.addProperty("type", "pack");
               array2.add(json8);
            }
         }
      }
   }
}
