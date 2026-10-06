package com.moonsworth.lunar.client.config.profile.importer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import com.moonsworth.lunar.client.config.profile.ModProfile;
import com.moonsworth.lunar.client.config.profile.ModProfileManager;

public final class BadlionProfileImporter {
   private static final Set<String> CHILD_AWARE_MODS = Set.of("SKYBLOCK", "HYPIXEL_BEDWARS");

   private BadlionProfileImporter() {
   }

   public static ModProfile importProfile(File file0, String text1) {
      String text3 = null;

      JsonObject json2;
      try (ZipFile zipfile4 = new ZipFile(file0)) {
         ZipEntry zipentry5 = zipfile4.getEntry("data.json");
         if (zipentry5 == null) {
            throw new IOException("Not a Badlion profile: missing data.json in " + file0.getName());
         }

         InputStreamReader reader6 = new InputStreamReader(zipfile4.getInputStream(zipentry5), StandardCharsets.UTF_8);

         try {
            json2 = JsonParser.parseReader(reader6).getAsJsonObject();
         } catch (Throwable exception13) {
            try {
               reader6.close();
            } catch (Throwable exception12) {
               exception13.addSuppressed(exception12);
            }

            throw exception13;
         }

         reader6.close();
         ZipEntry zipentry17 = zipfile4.getEntry("info.json");
         if (zipentry17 != null) {
            try (InputStreamReader reader7 = new InputStreamReader(zipfile4.getInputStream(zipentry17), StandardCharsets.UTF_8)) {
               JsonObject json8 = JsonParser.parseReader(reader7).getAsJsonObject();
               if (json8.has("name") && !json8.get("name").isJsonNull()) {
                  text3 = json8.get("name").getAsString();
               }
            }
         }
      }

      String text16 = text1 != null && !text1.isBlank() ? text1 : text3;
      return importProfile(json2, readCanvasCrosshair(file0, json2), text16);
   }

   public static String readProfileName(File file0) {
      try (ZipFile zipfile1 = new ZipFile(file0)) {
         ZipEntry zipentry2 = zipfile1.getEntry("info.json");
         if (zipentry2 == null) {
            return null;
         }

         try (InputStreamReader reader3 = new InputStreamReader(zipfile1.getInputStream(zipentry2), StandardCharsets.UTF_8)) {
            JsonObject json4 = JsonParser.parseReader(reader3).getAsJsonObject();
            return json4.has("name") && !json4.get("name").isJsonNull() ? json4.get("name").getAsString() : null;
         }
      } catch (Exception exception10) {
         return null;
      }
   }

   private static BadlionProfileConverter.CanvasCrosshair readCanvasCrosshair(File file0, JsonObject json1) {
      JsonElement element2 = json1.get("crosshair");
      if (element2 != null && element2.isJsonObject()) {
         JsonObject json3 = element2.getAsJsonObject();
         if (json3.has("mode")
            && "CANVAS".equals(json3.get("mode").getAsString())
            && json3.has("currentCanvasCrosshair")
            && !json3.get("currentCanvasCrosshair").isJsonNull()) {
            String text4 = json3.get("currentCanvasCrosshair").getAsString();
            String text5 = text4.startsWith("crosshair-canvas-") ? text4.substring("crosshair-canvas-".length()) : text4;
            File file6 = new File(file0.getParentFile(), "crosshairs" + File.separator + text5);
            if (!file6.isFile()) {
               return null;
            }

            try {
               BufferedImage bufferedimage7 = ImageIO.read(file6);
               if (bufferedimage7 != null && bufferedimage7.getWidth() == bufferedimage7.getHeight() && bufferedimage7.getWidth() >= 2 && bufferedimage7.getWidth() <= 64) {
                  int[] items8 = new int[bufferedimage7.getWidth() * bufferedimage7.getHeight()];
                  bufferedimage7.getRGB(0, 0, bufferedimage7.getWidth(), bufferedimage7.getHeight(), items8, 0, bufferedimage7.getWidth());
                  return new BadlionProfileConverter.CanvasCrosshair(bufferedimage7.getWidth(), items8);
               } else {
                  return null;
               }
            } catch (Exception exception9) {
               LunarLogger.method5("Couldn't read Badlion canvas crosshair from %s: %s", new Object[]{file6, exception9.getMessage()});
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public static ModProfile importProfile(JsonObject json0, String text1) {
      return importProfile(json0, null, text1);
   }

   public static ModProfile importProfile(JsonObject json0, BadlionProfileConverter.CanvasCrosshair data31, String text2) {
      ConvertedProfile horsestats53 = new BadlionProfileConverter().method2(BadlionProfileConfig.parse(json0), data31);
      com.moonsworth.lunar.client.config.profile.importer.FeatherProfileImporter.method7(horsestats53.getMods());
      disableMissingChildren(horsestats53.getMods(), getAllMods());
      ModProfileManager fogiterator4 = Ref.method4().method61();
      String text5 = createUniqueName(fogiterator4, text2);
      File file6 = new File(LunarConstants.field25 + File.separator + text5);
      if (!file6.exists() && !file6.mkdirs()) {
         throw new IOException("Could not create profile directory: " + file6);
      }

      writeJson(new File(file6, "mods.json"), horsestats53.getMods());
      writeJson(new File(file6, "general.json"), horsestats53.getGeneral());
      writeJson(new File(file6, "controls.json"), horsestats53.getControls());
      writeJson(new File(file6, "performance.json"), horsestats53.getPerformance());
      ModProfile horsestats7 = new ModProfile(text5, text5, false, false, "");
      fogiterator4.addProfile(horsestats7);
      fogiterator4.save();
      LunarLogger.method3(
         "Imported Badlion profile as Lunar profile '%s' (%d mods mapped, %d skipped)", new Object[]{text5, horsestats53.getMappedModIds().size(), horsestats53.getSkippedModIds().size()}
      );
      return horsestats7;
   }

   private static Iterable<? extends Framework7Extension> getAllMods() {
      return Ref.method4() != null && Ref.method4().method40() != null
         ? Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()
         : List.of();
   }

   public static void disableMissingChildren(JsonObject json0, Iterable<? extends Framework7Extension> list1) {
      for (Framework7Extension framework7extension3 : list1) {
         if (field1.contains(framework7extension3.getId())) {
            JsonElement element4 = json0.get(framework7extension3.getId());
            ModChildren alertextension5 = (ModChildren)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
            if (element4 != null && element4.isJsonObject() && alertextension5 != null) {
               JsonObject json6 = element4.getAsJsonObject();

               for (Framework7Extension framework7extension8 : alertextension5.getChildren()) {
                  if (!json6.has(framework7extension8.getId())) {
                     JsonObject json9 = new JsonObject();
                     json9.addProperty("enabled", false);
                     json6.add(framework7extension8.getId(), json9);
                  }
               }
            }
         }
      }
   }

   private static String createUniqueName(ModProfileManager fogiterator0, String text1) {
      String text2 = text1 != null && !text1.isBlank() ? text1.trim() : "Badlion Import";
      String text3 = text2;
      int index4 = 2;

      while (isNameTaken(fogiterator0, text3)) {
         text3 = text2 + " (" + index4++ + ")";
      }

      return text3;
   }

   private static boolean isNameTaken(ModProfileManager fogiterator0, String text1) {
      return fogiterator0.getModProfiles().stream().anyMatch(arg1x -> arg1x.getName().equalsIgnoreCase(text1))
         || new File(LunarConstants.field25 + File.separator + text1).exists();
   }

   private static void writeJson(File file0, JsonObject json1) {
      try (BufferedWriter writer2 = Files.newBufferedWriter(file0.toPath(), StandardCharsets.UTF_8)) {
         LunarConstants.field22.toJson(json1, writer2);
      }
   }
}
