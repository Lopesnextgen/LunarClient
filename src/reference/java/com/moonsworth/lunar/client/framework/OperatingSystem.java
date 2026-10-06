package com.moonsworth.lunar.client.framework;

import java.util.Locale;
import lombok.Generated;

public enum OperatingSystem {
   MACOS,
   LINUX,
   WINDOWS,
   SOLARIS,
   UNKNOWN;

   private static final OperatingSystem currentOS = getOperatingSystem();
   private static final boolean waylandSession = currentOS == LINUX
      && (System.getenv("WAYLAND_DISPLAY") != null || "wayland".equalsIgnoreCase(System.getenv("XDG_SESSION_TYPE")));
   private static int macOsMajorVersion = -1;

   OperatingSystem() {
   }

   private static OperatingSystem getOperatingSystem() {
      String text0 = LunarConstants.field1.toLowerCase(Locale.US);
      if (text0.contains("win")) {
         return WINDOWS;
      } else if (text0.contains("mac")) {
         return MACOS;
      } else if (!text0.contains("linux") && !text0.contains("unix")) {
         return !text0.contains("solaris") && !text0.contains("sunos") ? UNKNOWN : SOLARIS;
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
      String text0 = System.getProperty("os.arch");
      return isMacos() && (text0.startsWith("armv8") || text0.startsWith("aarch64"));
   }

   @Generated
   public static int getMacOsMajorVersion() {
      return macOsMajorVersion;
   }

   static {
      if (currentOS == MACOS) {
         try {
            String[] items0 = System.getProperty("os.version").split("\\.");
            if (items0.length > 0) {
               macOsMajorVersion = Integer.parseInt(items0[0]);
            }
         } catch (NumberFormatException numberformatexception1) {
            numberformatexception1.printStackTrace();
         }
      }
   }
}
