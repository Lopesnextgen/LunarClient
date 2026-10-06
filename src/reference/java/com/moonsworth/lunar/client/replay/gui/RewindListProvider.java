package com.moonsworth.lunar.client.replay.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.replay.project.ReplayMetadata;
import com.moonsworth.lunar.client.replay.project.PathUtils;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
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

public class RewindListProvider extends com.moonsworth.lunar.client.driver.core.gui.GuiIterator {
   private final ExecutorService field6 = Executors.newSingleThreadExecutor(new DefaultThreadFactory("lunar-rewind-list-provider-thread", true));
   private final IOFileFilter field7 = new IOFileFilter() {
      public boolean accept(File file1) {
         return file1.isFile() && file1.getName().endsWith(".rewind")
            || file1.isFile() && file1.getName().equals("project.json") && !file1.getParentFile().getName().equals("backups");
      }

      public boolean accept(File file1, String text2) {
         return false;
      }
   };
   private boolean loaded = false;

   public RewindListProvider() {
   }

   public void method2() {
      this.field6
         .submit(
            () -> {
               com.moonsworth.lunar.client.replay.project.RewindPaths.field7.mkdirs();
               com.moonsworth.lunar.client.replay.project.RewindPaths.field10.mkdirs();
               File file1 = new File(com.moonsworth.lunar.client.replay.project.RewindPaths.field10, Bridge.getMinecraftVersion().method45());
               List list2 = Arrays.asList(
                  com.moonsworth.lunar.client.replay.project.RewindPaths.field8,
                  file1,
                  com.moonsworth.lunar.client.replay.project.RewindPaths.field10,
                  com.moonsworth.lunar.client.replay.project.RewindPaths.field7
               );
               int number3 = Ref.method3().bridge$getProtocolVersion();
               JsonArray array4 = new JsonArray();

               for (int index5 = 0; index5 < list2.size(); index5++) {
                  File file6 = (File)list2.get(index5);
                  if (file6.isDirectory()) {
                     Collection list7 = FileUtils.listFiles(file6, this.field7, TrueFileFilter.TRUE);
                     File[] items8 = list7.stream().sorted(Comparator.comparingLong(File::lastModified).reversed()).toArray(File[]::new);

                     for (File file12 : items8) {
                        boolean flag13 = false;

                        for (int index14 = 0; index14 < index5; index14++) {
                           File file15 = (File)list2.get(index14);
                           if (file12.toPath().startsWith(file15.toPath())) {
                              flag13 = true;
                              break;
                           }
                        }

                        if (!flag13) {
                           this.method3(file12, number3, array4);
                        }
                     }

                     if (!this.loaded) {
                        this.method3("entries", this.method2(array4));
                     }
                  }
               }

               this.loaded = true;
               Ref.method3().bridge$submit(() -> this.method3("entries", this.method2(array4)));
            }
         );
   }

   private JsonArray method2(JsonArray array1) {
      ArrayList list2 = new ArrayList();

      for (int index3 = 0; index3 < array1.size(); index3++) {
         list2.add(array1.get(index3).getAsJsonObject());
      }

      list2.sort((arg0, arg1x) -> {
         long number2x = arg0.get("lastModifiedValue").getAsLong();
         long number4 = arg1x.get("lastModifiedValue").getAsLong();
         return Long.compare(number4, number2x);
      });
      JsonArray array6 = new JsonArray();

      for (JsonObject json5 : list2) {
         array6.add(json5);
      }

      return array6;
   }

   private void method3(File file1, int number2, JsonArray array3) {
      JsonObject json4 = new JsonObject();
      JsonArray array5 = new JsonArray();
      JsonArray array6 = new JsonArray();
      if (file1.getName().equals("project.json")) {
         file1 = file1.getParentFile();
         File file8 = new File(file1, "project.json");
         if (!file8.isFile()) {
            return;
         }

         ReplayMetadata rewind4_27;
         try (FileInputStream stream9 = new FileInputStream(file8)) {
            rewind4_27 = (ReplayMetadata)LunarConstants.field22.fromJson(new InputStreamReader(stream9, StandardCharsets.UTF_8), ReplayMetadata.class);
         } catch (Exception exception17) {
            return;
         }

         if (rewind4_27 == null) {
            return;
         }

         json4.addProperty("minecraftVersion", rewind4_27.method1());
         json4.addProperty("duration", "");
         json4.addProperty("compatible", number2 == rewind4_27.method2());
         json4.addProperty("type", "PROJECT");
         JsonObject json22 = new JsonObject();
         if (rewind4_27.method3() != null) {
            for (Entry entry11 : rewind4_27.method3().entrySet()) {
               json22.addProperty((String)entry11.getKey(), (String)entry11.getValue());
            }
         }

         json4.add("mods", json22);
         File file24 = new File(new File(file1, "thumbnails"), "project.png");
         if (file24.isFile()) {
            array5.add(PathUtils.method2(file24, com.moonsworth.lunar.client.replay.project.RewindPaths.field10));
         }
      } else {
         try {
            ZipFile zipfile19 = new ZipFile(file1, StandardCharsets.UTF_8);
            Rewind2 rewind221 = (Rewind2)LunarConstants.field22
               .fromJson(new InputStreamReader(zipfile19.getInputStream(zipfile19.getEntry("metadata.json")), StandardCharsets.UTF_8), Rewind2.class);
            zipfile19.close();
            json4.addProperty("minecraftVersion", rewind221.method4());
            json4.addProperty("duration", TimeFormatting.method2(rewind221.method1()));
            json4.addProperty("compatible", number2 == rewind221.method5());
            json4.addProperty("type", rewind221.isShadow() ? "SHADOW" : "REWIND");
            JsonObject json23 = new JsonObject();

            for (Entry entry28 : rewind221.method11().entrySet()) {
               json23.addProperty((String)entry28.getKey(), (String)entry28.getValue());
            }

            json4.add("mods", json23);

            for (Entry entry29 : rewind221.method9().entrySet()) {
               JsonObject json12 = new JsonObject();
               json12.addProperty("id", (String)entry29.getKey());
               json12.addProperty("name", (String)entry29.getValue());
               array6.add(json12);
            }

            File file27 = new File(new File(file1.getParentFile(), ".thumbnails"), rewind221.getId().toString());
            if (file27.isDirectory()) {
               for (File file14 : Objects.requireNonNull(file27.listFiles())) {
                  array5.add(PathUtils.method2(file14, com.moonsworth.lunar.client.replay.project.RewindPaths.field7));
               }
            }
         } catch (Exception exception18) {
            return;
         }
      }

      json4.add("thumbnails", array5);
      json4.add("locations", array6);
      json4.addProperty("date", TimeFormatting.method4(file1.lastModified()));
      json4.addProperty("lastModified", TimeFormatting.method3(file1.lastModified()));
      json4.addProperty("lastModifiedValue", file1.lastModified());
      String text20 = file1.getName();
      if (text20.endsWith(".rewind")) {
         text20 = text20.substring(0, file1.getName().length() - ".rewind".length());
      }

      json4.addProperty("name", text20);
      json4.addProperty("file", file1.getAbsolutePath());
      array3.add(json4);
   }
}
