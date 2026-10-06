package com.moonsworth.lunar.client.util;

import com.google.gson.JsonArray;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.SoundOption;
import com.moonsworth.lunar.client.util.highlight.FilenameFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jspecify.annotations.Nullable;

public final class Coordinates {
   private static final File field1 = ThreadModuleDump48.field6.resolve("sounds").toFile();
   private static final long field2 = 1048576L;
   private static final FilenameFilter field3 = FilenameFilter.method1("mp3", "wav", "ogg");
   private static final long field4 = 10000L;
   private static final Map<SoundOption, Long> field5 = new WeakHashMap<>();
   private static List<String> field6;
   private static JsonArray field7;

   public static void method1(SoundOption var0) {
      method2(var0, null);
   }

   public static void method2(SoundOption var0, @Nullable Framework7Extension var1) {
      if (!var0.method7() && !(method6(var0) <= 0.0F)) {
         if (var0.isFile()) {
            method4(var0, var1);
         } else {
            method3(var0, var1);
         }
      }
   }

   private static void method3(SoundOption var0, @Nullable Framework7Extension var1) {
      ResourceLocationBridge var2;
      try {
         var2 = ResourceLocationBridge.create(var0.get());
      } catch (RuntimeException var4) {
         method7(var0, var1);
         return;
      }

      if (!ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$getAllRegisteredSounds().contains(var2)) {
         method7(var0, var1);
      } else {
         ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$playSound(var2, true, method6(var0));
      }
   }

   private static void method4(SoundOption var0, @Nullable Framework7Extension var1) {
      File var2 = new File(method9(), var0.getFileName());
      if (!method5(var2)) {
         method7(var0, var1);
      } else {
         float var3 = method6(var0);
         if (ThreadModuleDump37.method18(() -> method4(var0, var1))) {
            try {
               ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$playMp3FromURL(var2.toURI().toString(), var3, false, false);
            } catch (Exception var5) {
               Slayer.method7("Could not play Lunar sound!", new Object[]{var5.getMessage()});
            }
         }
      }
   }

   private static boolean method5(File var0) {
      if (var0.isFile() && field3.accept(var0.getParentFile(), var0.getName()) && method16(var0)) {
         try {
            return method9().getCanonicalFile().equals(var0.getCanonicalFile().getParentFile());
         } catch (IOException var2) {
            Slayer.method7("Could not resolve sound file path!", new Object[]{var2.getMessage()});
            return false;
         }
      } else {
         return false;
      }
   }

   private static float method6(SoundOption var0) {
      float var1 = var0.method8().get().intValue() / 100.0F;
      return var1 * ThreadModuleDump63.method4().method41().method6().method79().get();
   }

   private static void method7(SoundOption var0, @Nullable Framework7Extension var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null) {
         long var3 = System.currentTimeMillis();
         Long var5 = field5.get(var0);
         if (var5 == null || var3 - var5 >= 10000L) {
            field5.put(var0, var3);
            String var6 = ThreadModuleDump63.method4().method67().method2("messages", "soundMissing", var0.get(), var0.getName());
            String var7 = method8(var1);
            if (var7 != null) {
               var6 = var6 + " " + ThreadModuleDump63.method4().method67().method2("messages", "soundMissingConfigure", var7);
            }

            var2.HRICOROOOCCOCOROCRHHCRRIRCOICO(Component.text(var6, NamedTextColor.RED));
         }
      }
   }

   private static @Nullable String method8(@Nullable Framework7Extension var0) {
      if (var0 == null) {
         return null;
      }

      ModDetails var1 = (ModDetails)var0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      return var1 == null ? null : var1.getName();
   }

   public static File method9() {
      if (!field1.exists() && !field1.mkdirs()) {
         Slayer.method5("Could not create the Lunar sounds folder!", new Object[0]);
      }

      return field1;
   }

   public static List<String> method10() {
      ArrayList var0 = new ArrayList();
      var0.add("none");
      var0.addAll(method13());
      method14().forEach(var1 -> var0.add("file:" + var1));
      return var0;
   }

   public static JsonArray method11() {
      if (field7 == null) {
         field7 = new JsonArray();
         method10().forEach(field7::add);
      }

      return field7;
   }

   public static void method12() {
      field7 = null;
   }

   private static List<String> method13() {
      if (field6 == null) {
         field6 = ThreadModuleDump63.method3()
            .bridge$getSoundHandler()
            .bridge$getAllRegisteredSounds()
            .stream()
            .map(var0 -> var0.bridge$getDomain() + ":" + var0.bridge$getPath())
            .sorted()
            .toList();
      }

      return field6;
   }

   public static List<String> method14() {
      return method15(method9(), field3);
   }

   public static List<String> method15(File var0, FilenameFilter var1) {
      ArrayList var2 = new ArrayList();
      File[] var3 = var0.listFiles(var1);
      if (var3 != null) {
         Arrays.stream(var3).filter(Coordinates::method16).map(File::getName).forEach(var2::add);
      }

      return var2;
   }

   public static boolean method16(File var0) {
      try {
         return Files.size(var0.toPath()) <= 1048576L;
      } catch (IOException var2) {
         Slayer.method7("Could not get sound file size!", new Object[]{var2.getMessage()});
         return false;
      }
   }

   @Generated
   private Coordinates() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
