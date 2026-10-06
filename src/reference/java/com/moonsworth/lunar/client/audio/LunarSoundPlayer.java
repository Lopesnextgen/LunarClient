package com.moonsworth.lunar.client.audio;

import com.google.gson.JsonArray;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.SoundOption;
import com.moonsworth.lunar.client.util.io.FileExtensionFilter;
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
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.LunarConstants;

public final class LunarSoundPlayer {
   private static final File field1 = LunarConstants.field6.resolve("sounds").toFile();
   private static final long field2 = 1048576L;
   private static final FileExtensionFilter field3 = FileExtensionFilter.method1("mp3", "wav", "ogg");
   private static final long field4 = 10000L;
   private static final Map<SoundOption, Long> field5 = new WeakHashMap<>();
   private static List<String> field6;
   private static JsonArray field7;

   public static void method1(SoundOption lightingextension49110) {
      method2(lightingextension49110, null);
   }

   public static void method2(SoundOption lightingextension49110, @Nullable Framework7Extension framework7extension1) {
      if (!lightingextension49110.method7() && !(method6(lightingextension49110) <= 0.0F)) {
         if (lightingextension49110.isFile()) {
            method4(lightingextension49110, framework7extension1);
         } else {
            method3(lightingextension49110, framework7extension1);
         }
      }
   }

   private static void method3(SoundOption lightingextension49110, @Nullable Framework7Extension framework7extension1) {
      ResourceLocationBridge horsestats142;
      try {
         horsestats142 = ResourceLocationBridge.create((String)lightingextension49110.get());
      } catch (RuntimeException exception4) {
         method7(lightingextension49110, framework7extension1);
         return;
      }

      if (!Ref.method3().bridge$getSoundHandler().bridge$getAllRegisteredSounds().contains(horsestats142)) {
         method7(lightingextension49110, framework7extension1);
      } else {
         Ref.method3().bridge$getSoundHandler().bridge$playSound(horsestats142, true, method6(lightingextension49110));
      }
   }

   private static void method4(SoundOption lightingextension49110, @Nullable Framework7Extension framework7extension1) {
      File file2 = new File(method9(), lightingextension49110.getFileName());
      if (!method5(file2)) {
         method7(lightingextension49110, framework7extension1);
      } else {
         float value3 = method6(lightingextension49110);
         if (BackgroundExecutor.method18(() -> method4(lightingextension49110, framework7extension1))) {
            try {
               Ref.method3().bridge$getSoundHandler().bridge$playMp3FromURL(file2.toURI().toString(), value3, false, false);
            } catch (Exception exception5) {
               LunarLogger.method7("Could not play Lunar sound!", new Object[]{exception5.getMessage()});
            }
         }
      }
   }

   private static boolean method5(File file0) {
      if (file0.isFile() && field3.accept(file0.getParentFile(), file0.getName()) && method16(file0)) {
         try {
            return method9().getCanonicalFile().equals(file0.getCanonicalFile().getParentFile());
         } catch (IOException exception2) {
            LunarLogger.method7("Could not resolve sound file path!", new Object[]{exception2.getMessage()});
            return false;
         }
      } else {
         return false;
      }
   }

   private static float method6(SoundOption lightingextension49110) {
      float value1 = ((Integer)lightingextension49110.method8().get()).intValue() / 100.0F;
      return value1 * (Float)Ref.method4().method41().method6().method79().get();
   }

   private static void method7(SoundOption lightingextension49110, @Nullable Framework7Extension framework7extension1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         long number3 = System.currentTimeMillis();
         Long number5 = field5.get(lightingextension49110);
         if (number5 == null || number3 - number5 >= 10000L) {
            field5.put(lightingextension49110, number3);
            String text6 = Ref.method4().method67().method2("messages", "soundMissing", new Object[]{lightingextension49110.get(), lightingextension49110.getName()});
            String text7 = method8(framework7extension1);
            if (text7 != null) {
               text6 = text6 + " " + Ref.method4().method67().method2("messages", "soundMissingConfigure", new Object[]{text7});
            }

            bridge5extension_52.method1(Component.text(text6, NamedTextColor.RED));
         }
      }
   }

   private static @Nullable String method8(@Nullable Framework7Extension framework7extension0) {
      if (framework7extension0 == null) {
         return null;
      }

      ModDetails framework81 = (ModDetails)framework7extension0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field13);
      return framework81 == null ? null : framework81.getName();
   }

   public static File method9() {
      if (!field1.exists() && !field1.mkdirs()) {
         LunarLogger.method5("Could not create the Lunar sounds folder!", new Object[0]);
      }

      return field1;
   }

   public static List<String> method10() {
      ArrayList list0 = new ArrayList();
      list0.add("none");
      list0.addAll(method13());
      method14().forEach(arg1 -> list0.add("file:" + arg1));
      return list0;
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
         field6 = Ref.method3()
            .bridge$getSoundHandler()
            .bridge$getAllRegisteredSounds()
            .stream()
            .map(arg0 -> arg0.bridge$getDomain() + ":" + arg0.bridge$getPath())
            .sorted()
            .toList();
      }

      return field6;
   }

   public static List<String> method14() {
      return method15(method9(), field3);
   }

   public static List<String> method15(File file0, FileExtensionFilter filenamefilter1) {
      ArrayList list2 = new ArrayList();
      File[] items3 = file0.listFiles(filenamefilter1);
      if (items3 != null) {
         Arrays.stream(items3).filter(LunarSoundPlayer::method16).map(File::getName).forEach(list2::add);
      }

      return list2;
   }

   public static boolean method16(File file0) {
      try {
         return Files.size(file0.toPath()) <= 1048576L;
      } catch (IOException exception2) {
         LunarLogger.method7("Could not get sound file size!", new Object[]{exception2.getMessage()});
         return false;
      }
   }

   @Generated
   private LunarSoundPlayer() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
