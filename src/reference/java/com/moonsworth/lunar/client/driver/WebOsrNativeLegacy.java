package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

public enum WebOsrNativeLegacy {
   ULTRALIGHT("UltralightCore", "WebCore", "Ultralight"),
   COMMON("WebOSR-Binding");

   private final String[] natives;

   WebOsrNativeLegacy(String... var3) {
      this.natives = var3;
   }

   public void load() {
      tryLoadNatives(this.natives);
   }

   public static void loadWebOSR() {
      if (ThreadModuleDumpType2.isWindows()) {
         System.loadLibrary("vcruntime140");
         System.loadLibrary("vcruntime140_1");
         System.loadLibrary("msvcp140");
         System.loadLibrary("msvcp140_atomic_wait");
         System.loadLibrary("msvcp140_codecvt_ids");
         System.loadLibrary("msvcp140_1");
         System.loadLibrary("msvcp140_2");
      } else if (ThreadModuleDumpType2.isLinux() && Client.method29().isPresent()) {
         for (File var3 : Objects.requireNonNull(((File)Client.method29().get()).listFiles())) {
            if (var3.getName().endsWith("*.ttf")) {
               try {
                  Files.copy(var3.toPath(), new File("/usr/local/share/fonts/", var3.getName()).toPath());
               } catch (Exception var5) {
                  var5.printStackTrace();
               }
            }
         }
      }

      ULTRALIGHT.load();
      COMMON.load();
   }

   private static void tryLoadNatives(String... items) {
      for (String var4 : items) {
         try {
            if (!ThreadModuleDumpType2.isWindows()) {
               if (var4.endsWith("-0")) {
                  var4 = var4.replace("-0", "");
               }

               if (var4.startsWith("lib")) {
                  var4 = var4.substring(3);
               }
            }

            Slayer.method4("WebOSR", "Loading native: " + var4, new Object[0]);
            System.loadLibrary(var4);
         } catch (UnsatisfiedLinkError var6) {
            var6.printStackTrace();
            Slayer.method6("WebOSR", "Failed to load library " + var4, new Object[0]);
         }
      }
   }
}
