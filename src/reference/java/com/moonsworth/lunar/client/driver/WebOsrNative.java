package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.OperatingSystem;
import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

public enum WebOsrNative {
   ULTRALIGHT("UltralightCore", "WebCore", "Ultralight"),
   COMMON("WebOSR-Binding");

   private final String[] natives;

   WebOsrNative(String... items3) {
      this.natives = items3;
   }

   public void load() {
      tryLoadNatives(this.natives);
   }

   public static void loadWebOSR() {
      if (OperatingSystem.isWindows()) {
         System.loadLibrary("vcruntime140");
         System.loadLibrary("vcruntime140_1");
         System.loadLibrary("msvcp140");
         System.loadLibrary("msvcp140_atomic_wait");
         System.loadLibrary("msvcp140_codecvt_ids");
         System.loadLibrary("msvcp140_1");
         System.loadLibrary("msvcp140_2");
      } else if (OperatingSystem.isLinux() && Client.method29().isPresent()) {
         for (File file3 : Objects.requireNonNull(((File)Client.method29().get()).listFiles())) {
            if (file3.getName().endsWith("*.ttf")) {
               try {
                  Files.copy(file3.toPath(), new File("/usr/local/share/fonts/", file3.getName()).toPath());
               } catch (Exception exception5) {
                  exception5.printStackTrace();
               }
            }
         }
      }

      ULTRALIGHT.load();
      COMMON.load();
   }

   private static void tryLoadNatives(String... items0) {
      for (String text4 : items0) {
         try {
            if (!OperatingSystem.isWindows()) {
               if (text4.endsWith("-0")) {
                  text4 = text4.replace("-0", "");
               }

               if (text4.startsWith("lib")) {
                  text4 = text4.substring(3);
               }
            }

            LunarLogger.method4("WebOSR", "Loading native: " + text4, new Object[0]);
            System.loadLibrary(text4);
         } catch (UnsatisfiedLinkError unsatisfiedlinkerror6) {
            unsatisfiedlinkerror6.printStackTrace();
            LunarLogger.method6("WebOSR", "Failed to load library " + text4, new Object[0]);
         }
      }
   }
}
