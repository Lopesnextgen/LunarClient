package com.moonsworth.lunar.client.config.profile.importer;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.OperatingSystem;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;

public final class ExternalProfileLocator {
   private static Boolean field1;

   public ExternalProfileLocator() {
   }

   public static boolean hasAnyExternalProfiles() {
      if (field1 == null) {
         field1 = getMinecraftDirs().stream().anyMatch(ExternalProfileLocator::isProfileInDir);
      }

      return field1;
   }

   public static boolean isProfileInDir(File file0) {
      return containsFileWithExtension(getBadlionProfileDir(file0), ".zip") || containsFileWithExtension(getFeatherProfileDir(file0), ".json");
   }

   public static boolean isBadlionDetected() {
      return getMinecraftDirs().stream().anyMatch(arg0 -> containsFileWithExtension(getBadlionProfileDir(arg0), ".zip"));
   }

   public static boolean isFeatherDetected() {
      return getMinecraftDirs().stream().anyMatch(arg0 -> containsFileWithExtension(getFeatherProfileDir(arg0), ".json"));
   }

   public static List<File> findBadlionProfiles() {
      return findProfiles(ExternalProfileLocator::getBadlionProfileDir, ".zip");
   }

   public static List<File> findFeatherProfiles() {
      return findProfiles(ExternalProfileLocator::getFeatherProfileDir, ".json");
   }

   private static List<File> findProfiles(Function<File, File> function0, String text1) {
      LinkedHashMap map2 = new LinkedHashMap();

      for (File file4 : getMinecraftDirs()) {
         File[] items5 = ((File)function0.apply(file4)).listFiles((arg1x, arg2x) -> arg2x.toLowerCase(Locale.ROOT).endsWith(text1));
         if (items5 != null) {
            for (File file9 : items5) {
               map2.putIfAbsent(file9.getName().toLowerCase(Locale.ROOT), file9);
            }
         }
      }

      return List.copyOf(map2.values());
   }

   public static File getBadlionProfileDir(File file0) {
      return new File(file0, "BLClient-Mod-Profiles");
   }

   public static File getFeatherProfileDir(File file0) {
      return new File(file0, "feather" + File.separator + "configuration" + File.separator + "profiles");
   }

   private static boolean containsFileWithExtension(File file0, String text1) {
      File[] items2 = file0.listFiles((arg1x, arg2x) -> arg2x.toLowerCase(Locale.ROOT).endsWith(text1));
      return items2 != null && items2.length > 0;
   }

   private static Set<File> getMinecraftDirs() {
      LinkedHashSet set0 = new LinkedHashSet();
      set0.add(Ref.method3().bridge$getMcDataDir().getAbsoluteFile());
      set0.add(getDefaultMinecraftDir().getAbsoluteFile());
      return set0;
   }

   private static File getDefaultMinecraftDir() {
      String text0 = System.getProperty("user.home", ".");
      if (OperatingSystem.isWindows()) {
         String text1 = System.getenv("APPDATA");
         return new File(text1 != null ? text1 : text0, ".minecraft");
      } else {
         return OperatingSystem.isMacos()
            ? new File(text0, "Library" + File.separator + "Application Support" + File.separator + "minecraft")
            : new File(text0, ".minecraft");
      }
   }
}
