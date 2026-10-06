package com.moonsworth.lunar.client.config.profile.importer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.profile.ModProfileManager;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.NumberRule;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public final class FeatherProfileImporter {
   private FeatherProfileImporter() {
   }

   public static com.moonsworth.lunar.client.config.profile.ModProfile importProfile(File file0, String text1) {
      JsonObject json2;
      try (BufferedReader reader3 = Files.newBufferedReader(file0.toPath(), StandardCharsets.UTF_8)) {
         json2 = JsonParser.parseReader(reader3).getAsJsonObject();
      }

      return importProfile(json2, readAutoText(file0), readSidebar(file0), text1);
   }

   private static JsonArray readAutoText(File file0) {
      JsonElement element1 = readSiblingJson(file0, "autotext.json", "misc" + File.separator + "autotext.json");
      return element1 != null && element1.isJsonArray() ? element1.getAsJsonArray() : null;
   }

   private static JsonObject readSidebar(File file0) {
      JsonElement element1 = readSiblingJson(file0, "sidebar.json");
      return element1 != null && element1.isJsonObject() ? element1.getAsJsonObject() : null;
   }

   private static JsonElement readSiblingJson(File file0, String... items1) {
      File file2 = file0.getParentFile();

      for (int index3 = 0; index3 < 3 && file2 != null; file2 = file2.getParentFile()) {
         for (String text7 : items1) {
            File file8 = new File(file2, text7);
            if (file8.isFile()) {
               try (BufferedReader reader9 = Files.newBufferedReader(file8.toPath(), StandardCharsets.UTF_8)) {
                  return JsonParser.parseReader(reader9);
               } catch (Exception exception14) {
                  LunarLogger.method5("Couldn't read Feather config from %s: %s", new Object[]{file8, exception14.getMessage()});
               }
            }
         }

         index3++;
      }

      return null;
   }

   public static com.moonsworth.lunar.client.config.profile.ModProfile importProfile(JsonObject json0, String text1) {
      return importProfile(json0, null, null, text1);
   }

   public static com.moonsworth.lunar.client.config.profile.ModProfile importProfile(JsonObject json0, JsonArray array1, JsonObject json2, String text3) {
      FeatherConvertedProfile horsestats24 = new FeatherProfileConverter().method3(FeatherProfileConfig.parse(json0), array1, json2);
      clampAllOptions(horsestats24.getMods());
      ModProfileManager fogiterator5 = Ref.method4().method61();
      String text6 = createUniqueName(fogiterator5, text3);
      File file7 = new File(LunarConstants.field25 + File.separator + text6);
      if (!file7.exists() && !file7.mkdirs()) {
         throw new IOException("Could not create profile directory: " + file7);
      }

      writeJson(new File(file7, "mods.json"), horsestats24.getMods());
      writeJson(new File(file7, "general.json"), horsestats24.getGeneral());
      writeJson(new File(file7, "controls.json"), horsestats24.getControls());
      writeJson(new File(file7, "performance.json"), horsestats24.getPerformance());
      com.moonsworth.lunar.client.config.profile.ModProfile horsestats8 = new com.moonsworth.lunar.client.config.profile.ModProfile(text6, text6, false, false, "");
      fogiterator5.addProfile(horsestats8);
      fogiterator5.save();
      LunarLogger.method3(
         "Imported Feather profile '%s' as Lunar profile '%s' (%d mods mapped, %d skipped)",
         new Object[]{text3, text6, horsestats24.getMappedModIds().size(), horsestats24.getSkippedModIds().size()}
      );
      return horsestats8;
   }

   public static void clampAllOptions(JsonObject json0) {
      if (Ref.method4() != null && Ref.method4().method40() != null) {
         for (Framework7Extension framework7extension2 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            JsonElement element3 = json0.get(framework7extension2.getId());
            if (element3 != null && element3.isJsonObject()) {
               clampModOptions(framework7extension2, element3.getAsJsonObject());
            }
         }
      }
   }

   private static void clampModOptions(Framework7Extension framework7extension0, JsonObject json1) {
      OptionContainer framework52 = (OptionContainer)framework7extension0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
      JsonElement element3 = json1.get("options");
      if (framework52 != null && element3 != null && element3.isJsonObject()) {
         clampOptions(framework52, element3.getAsJsonObject());
      }

      ModChildren alertextension4 = (ModChildren)framework7extension0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      if (alertextension4 != null) {
         for (Framework7Extension framework7extension6 : alertextension4.getChildren()) {
            JsonElement element7 = json1.get(framework7extension6.getId());
            if (element7 != null && element7.isJsonObject()) {
               clampModOptions(framework7extension6, element7.getAsJsonObject());
            }
         }
      }
   }

   private static void clampOptions(OptionContainer framework50, JsonObject json1) {
      for (ClientOption lightingextension3 : framework50.method2()) {
         NumberRule nameplate4 = (NumberRule)lightingextension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
         if (nameplate4 != null) {
            JsonElement element5 = json1.get(lightingextension3.getId());
            if (element5 != null && element5.isJsonPrimitive() && element5.getAsJsonPrimitive().isNumber()) {
               double value6 = element5.getAsDouble();
               double value8 = Math.max(nameplate4.getMin().doubleValue(), Math.min(nameplate4.getMax().doubleValue(), value6));
               if (value8 != value6) {
                  if (!(lightingextension3.get() instanceof Float) && !(lightingextension3.get() instanceof Double)) {
                     json1.addProperty(lightingextension3.getId(), Math.round(value8));
                  } else {
                     json1.addProperty(lightingextension3.getId(), value8);
                  }
               }
            }
         }
      }
   }

   private static String createUniqueName(ModProfileManager fogiterator0, String text1) {
      String text2 = text1 != null && !text1.isBlank() ? text1.trim() : "Feather Import";
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
