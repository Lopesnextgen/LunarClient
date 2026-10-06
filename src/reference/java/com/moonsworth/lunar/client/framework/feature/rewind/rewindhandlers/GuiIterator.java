package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind4_2;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui5;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2;
import com.moonsworth.lunar.client.util.ThreadModuleDump11;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class GuiIterator extends com.moonsworth.lunar.client.driver.core.gui.GuiIterator {
   private final ExecutorService scanExecutor = Executors.newSingleThreadExecutor(new DefaultThreadFactory("lunar-rewind-list-provider-thread", true));
   private final IOFileFilter fileFilter = new IOFileFilter() {
      public boolean accept(File var1) {
         return var1.isFile() && var1.getName().endsWith(".rewind")
            || var1.isFile() && var1.getName().equals("project.json") && !var1.getParentFile().getName().equals("backups");
      }

      public boolean accept(File var1, String var2) {
         return false;
      }
   };
   private boolean loaded = false;

   public void refresh() {
      this.scanExecutor
         .submit(
            () -> {
               com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field7.mkdirs();
               com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field10.mkdirs();
               File var1 = new File(com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field10, Bridge.getMinecraftVersion().method45());
               List var2 = Arrays.asList(
                  com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field8,
                  var1,
                  com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field10,
                  com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field7
               );
               int var3 = ThreadModuleDump63.method3().bridge$getProtocolVersion();
               JsonArray var4 = new JsonArray();

               for (int var5 = 0; var5 < var2.size(); var5++) {
                  File var6 = (File)var2.get(var5);
                  if (var6.isDirectory()) {
                     Collection var7 = FileUtils.listFiles(var6, this.fileFilter, TrueFileFilter.TRUE);
                     File[] var8 = var7.stream().sorted(Comparator.comparingLong(File::lastModified).reversed()).toArray(File[]::new);

                     for (File var12 : var8) {
                        boolean var13 = false;

                        for (int var14 = 0; var14 < var5; var14++) {
                           File var15 = (File)var2.get(var14);
                           if (var12.toPath().startsWith(var15.toPath())) {
                              var13 = true;
                              break;
                           }
                        }

                        if (!var13) {
                           this.addEntry(var12, var3, var4);
                        }
                     }

                     if (!this.loaded) {
                        this.addEntry("entries", this.refresh(var4));
                     }
                  }
               }

               this.loaded = true;
               ThreadModuleDump63.method3().bridge$submit(() -> this.addEntry("entries", this.refresh(var4)));
            }
         );
   }

   private JsonArray refresh(JsonArray var1) {
      ArrayList var2 = new ArrayList();

      for (int var3 = 0; var3 < var1.size(); var3++) {
         var2.add(var1.get(var3).getAsJsonObject());
      }

      var2.sort((var0, var1x) -> {
         long var2x = var0.get("lastModifiedValue").getAsLong();
         long var4 = var1x.get("lastModifiedValue").getAsLong();
         return Long.compare(var4, var2x);
      });
      JsonArray var6 = new JsonArray();

      for (JsonObject var5 : var2) {
         var6.add(var5);
      }

      return var6;
   }

   private void addEntry(File var1, int var2, JsonArray var3) {
      JsonObject var4 = new JsonObject();
      JsonArray var5 = new JsonArray();
      JsonArray var6 = new JsonArray();
      if (var1.getName().equals("project.json")) {
         var1 = var1.getParentFile();
         File var8 = new File(var1, "project.json");
         if (!var8.isFile()) {
            return;
         }

         Rewind4_2 var7;
         try (FileInputStream var9 = new FileInputStream(var8)) {
            var7 = (Rewind4_2)ThreadModuleDump48.field22.fromJson(new InputStreamReader(var9, StandardCharsets.UTF_8), Rewind4_2.class);
         } catch (Exception var17) {
            return;
         }

         if (var7 == null) {
            return;
         }

         var4.addProperty("minecraftVersion", var7.method1());
         var4.addProperty("duration", "");
         var4.addProperty("compatible", var2 == var7.method2());
         var4.addProperty("type", "PROJECT");
         JsonObject var22 = new JsonObject();
         if (var7.method3() != null) {
            for (Entry var11 : var7.method3().entrySet()) {
               var22.addProperty((String)var11.getKey(), (String)var11.getValue());
            }
         }

         var4.add("mods", var22);
         File var24 = new File(new File(var1, "thumbnails"), "project.png");
         if (var24.isFile()) {
            var5.add(Gui5.method2(var24, com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field10));
         }
      } else {
         try {
            ZipFile var19 = new ZipFile(var1, StandardCharsets.UTF_8);
            Rewind2 var21 = (Rewind2)ThreadModuleDump48.field22
               .fromJson(new InputStreamReader(var19.getInputStream(var19.getEntry("metadata.json")), StandardCharsets.UTF_8), Rewind2.class);
            var19.close();
            var4.addProperty("minecraftVersion", var21.method4());
            var4.addProperty("duration", ThreadModuleDump11.formatClock(var21.method1()));
            var4.addProperty("compatible", var2 == var21.method5());
            var4.addProperty("type", var21.isShadow() ? "SHADOW" : "REWIND");
            JsonObject var23 = new JsonObject();

            for (Entry var28 : var21.method11().entrySet()) {
               var23.addProperty((String)var28.getKey(), (String)var28.getValue());
            }

            var4.add("mods", var23);

            for (Entry var29 : var21.method9().entrySet()) {
               JsonObject var12 = new JsonObject();
               var12.addProperty("id", (String)var29.getKey());
               var12.addProperty("name", (String)var29.getValue());
               var6.add(var12);
            }

            File var27 = new File(new File(var1.getParentFile(), ".thumbnails"), var21.getId().toString());
            if (var27.isDirectory()) {
               for (File var14 : Objects.requireNonNull(var27.listFiles())) {
                  var5.add(Gui5.method2(var14, com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui.field7));
               }
            }
         } catch (Exception var18) {
            return;
         }
      }

      var4.add("thumbnails", var5);
      var4.add("locations", var6);
      var4.addProperty("date", ThreadModuleDump11.formatDateTime(var1.lastModified()));
      var4.addProperty("lastModified", ThreadModuleDump11.formatTimeAgo(var1.lastModified()));
      var4.addProperty("lastModifiedValue", var1.lastModified());
      String var20 = var1.getName();
      if (var20.endsWith(".rewind")) {
         var20 = var20.substring(0, var1.getName().length() - ".rewind".length());
      }

      var4.addProperty("name", var20);
      var4.addProperty("file", var1.getAbsolutePath());
      var3.add(var4);
   }
}
