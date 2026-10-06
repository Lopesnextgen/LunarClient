package com.moonsworth.lunar.files.mixin;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.files.MappingDownloader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import lombok.Generated;

public class VersionManifest {
   public static final String SERIALIZED_NAME_HTTPS_LAUNCHERMETA_MOJANG_COM_MC_GAME_VERSION_MANIFEST_JSON = "https://launchermeta.mojang.com/mc/game/version_manifest.json";
   private static String field2 = null;
   @SerializedName("latest")
   private JsonObject latest;
   @SerializedName("versions")
   private VersionInfo[] versions;

   public static VersionManifest method1() {
      try {
         if (field2 == null) {
            field2 = MappingDownloader.method2("https://launchermeta.mojang.com/mc/game/version_manifest.json");
         }

         VersionManifest files40 = (VersionManifest)com.moonsworth.lunar.files.MappingsGson.field1.fromJson(field2, VersionManifest.class);
         method3(files40);
         return files40;
      } catch (Throwable exception1) {
         throw exception1;
      }
   }

   public static VersionManifest method2(byte[] items0) {
      return (VersionManifest)com.moonsworth.lunar.files.MappingsGson.field1.fromJson(new String(items0, StandardCharsets.UTF_8), VersionManifest.class);
   }

   private static void method3(VersionManifest files40) {
      VersionInfo[] items1 = files40.versions;
      Arrays.sort(
         items1,
         (arg0x, arg1x) -> !method4(arg0x.getId()) && !method4(arg1x.getId())
            ? method5(arg0x.getId(), arg1x.getId())
            : arg0x.method5().compareTo(arg1x.method5())
      );

      for (int index2 = 1; index2 < items1.length; index2++) {
         items1[index2].method8(items1[index2 - 1]);
      }
   }

   private static boolean method4(String text0) {
      return text0.startsWith("a") || text0.startsWith("b") || text0.startsWith("1") || !text0.contains(".") && text0.contains("w");
   }

   private static int method5(String text0, String text1) {
      int index2 = text0.indexOf(45);
      int index3 = text1.indexOf(45);
      String[] items4 = (index2 == -1 ? text0 : text0.substring(0, index2)).split("\\.");
      String[] items5 = (index3 == -1 ? text1 : text1.substring(0, index3)).split("\\.");
      String text6 = index2 == -1 ? "" : text0.substring(index2 + 1);
      String text7 = index3 == -1 ? "" : text1.substring(index3 + 1);

      for (int index8 = 0; index8 < Math.max(items4.length, items5.length); index8++) {
         int number9 = index8 < items4.length ? method8(items4[index8]) : 0;
         int number10 = index8 < items5.length ? method8(items5[index8]) : 0;
         if (number9 != number10) {
            return Integer.compare(number9, number10);
         }
      }

      int number11 = method6(text6);
      int number12 = method6(text7);
      return number11 != number12 ? Integer.compare(number11, number12) : Integer.compare(method7(text6), method7(text7));
   }

   private static int method6(String text0) {
      if (text0.startsWith("snapshot-")) {
         return 0;
      } else if (text0.startsWith("pre-")) {
         return 1;
      } else {
         return text0.startsWith("rc-") ? 2 : 3;
      }
   }

   private static int method7(String text0) {
      int index1 = text0.lastIndexOf(45);
      return index1 == -1 ? 0 : method8(text0.substring(index1 + 1));
   }

   private static int method8(String text0) {
      try {
         return Integer.parseInt(text0);
      } catch (NumberFormatException numberformatexception2) {
         return 0;
      }
   }

   public Optional<VersionInfo> method9(String text1) {
      for (VersionInfo files35 : this.versions) {
         if (files35.getId().equals(text1)) {
            return Optional.of(files35);
         }
      }

      return Optional.empty();
   }

   @Generated
   public VersionManifest() {
   }

   @Generated
   public JsonObject getLatest() {
      return this.latest;
   }

   @Generated
   public VersionInfo[] getVersions() {
      return this.versions;
   }

   @Generated
   public void setLatest(JsonObject json1) {
      this.latest = json1;
   }

   @Generated
   public void setVersions(VersionInfo[] items1) {
      this.versions = items1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof VersionManifest files42)) {
         return false;
      } else {
         if (!files42.canEqual(this)) {
            return false;
         }

         JsonObject json3 = this.getLatest();
         JsonObject json4 = files42.method10();
         return (json3 == null ? json4 == null : json3.equals(json4)) ? Arrays.deepEquals(this.getVersions(), files42.method11()) : false;
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof VersionManifest;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      JsonObject json3 = this.getLatest();
      number2 = number2 * 59 + (json3 == null ? 43 : json3.hashCode());
      return number2 * 59 + Arrays.deepHashCode(this.getVersions());
   }

   @Generated
   @Override
   public String toString() {
      return "VersionManifest(latest=" + this.getLatest() + ", versions=" + Arrays.deepToString(this.getVersions()) + ")";
   }
}
