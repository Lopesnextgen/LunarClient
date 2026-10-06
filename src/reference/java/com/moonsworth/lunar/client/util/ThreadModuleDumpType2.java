package com.moonsworth.lunar.client.util;

import java.util.Locale;
import lombok.Generated;

public enum ThreadModuleDumpType2 {
   MACOS,
   LINUX,
   WINDOWS,
   SOLARIS,
   UNKNOWN;

   private static final ThreadModuleDumpType2 currentOS = getOperatingSystem();
   private static final boolean waylandSession = currentOS == LINUX
      && (System.getenv("WAYLAND_DISPLAY") != null || "wayland".equalsIgnoreCase(System.getenv("XDG_SESSION_TYPE")));
   private static int macOsMajorVersion = -1;

   private static ThreadModuleDumpType2 getOperatingSystem() {
      String var0 = ThreadModuleDump48.field1.toLowerCase(Locale.US);
      if (var0.contains("win")) {
         return WINDOWS;
      } else if (var0.contains("mac")) {
         return MACOS;
      } else if (!var0.contains("linux") && !var0.contains("unix")) {
         return !var0.contains("solaris") && !var0.contains("sunos") ? UNKNOWN : SOLARIS;
      } else {
         return LINUX;
      }
   }

   public static boolean isMacos() {
      return currentOS == MACOS;
   }

   public static boolean isLinux() {
      return currentOS == LINUX;
   }

   public static boolean isWaylandSession() {
      return waylandSession;
   }

   public static boolean isWindows() {
      return currentOS == WINDOWS;
   }

   public static boolean isSolaris() {
      return currentOS == SOLARIS;
   }

   public static boolean isOther() {
      return currentOS == UNKNOWN;
   }

   public static boolean isAppleSilicon() {
      String var0 = System.getProperty("os.arch");
      return isMacos() && (var0.startsWith("armv8") || var0.startsWith("aarch64"));
   }

   @Generated
   public static int getMacOsMajorVersion() {
      return macOsMajorVersion;
   }

   static {
      if (currentOS == MACOS) {
         try {
            String[] var0 = System.getProperty("os.version").split("\\.");
            if (var0.length > 0) {
               macOsMajorVersion = Integer.parseInt(var0[0]);
            }
         } catch (NumberFormatException var1) {
            var1.printStackTrace();
         }
      }
   }
}
