package com.moonsworth.lunar.files.mixin;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.files.Files3_2;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import lombok.Generated;

public class Files4 {
   public static final String SERIALIZED_NAME_HTTPS_LAUNCHERMETA_MOJANG_COM_MC_GAME_VERSION_MANIFEST_JSON = "https://launchermeta.mojang.com/mc/game/version_manifest.json";
   private static String field2 = null;
   @SerializedName("latest")
   private JsonObject latest;
   @SerializedName("versions")
   private Files3[] versions;

   public static Files4 method1() {
      try {
         if (field2 == null) {
            field2 = Files3_2.method2("https://launchermeta.mojang.com/mc/game/version_manifest.json");
         }

         Files4 var0 = (Files4)com.moonsworth.lunar.files.Files.field1.fromJson(field2, Files4.class);
         method3(var0);
         return var0;
      } catch (Throwable var1) {
         throw var1;
      }
   }

   public static Files4 method2(byte[] var0) {
      return (Files4)com.moonsworth.lunar.files.Files.field1.fromJson(new String(var0, StandardCharsets.UTF_8), Files4.class);
   }

   private static void method3(Files4 var0) {
      Files3[] var1 = var0.versions;
      Arrays.sort(
         var1,
         (var0x, var1x) -> !method4(var0x.getId()) && !method4(var1x.getId())
            ? method5(var0x.getId(), var1x.getId())
            : var0x.method5().compareTo(var1x.method5())
      );

      for (int var2 = 1; var2 < var1.length; var2++) {
         var1[var2].method8(var1[var2 - 1]);
      }
   }

   private static boolean method4(String var0) {
      return var0.startsWith("a") || var0.startsWith("b") || var0.startsWith("1") || !var0.contains(".") && var0.contains("w");
   }

   private static int method5(String var0, String var1) {
      int var2 = var0.indexOf(45);
      int var3 = var1.indexOf(45);
      String[] var4 = (var2 == -1 ? var0 : var0.substring(0, var2)).split("\\.");
      String[] var5 = (var3 == -1 ? var1 : var1.substring(0, var3)).split("\\.");
      String var6 = var2 == -1 ? "" : var0.substring(var2 + 1);
      String var7 = var3 == -1 ? "" : var1.substring(var3 + 1);

      for (int var8 = 0; var8 < Math.max(var4.length, var5.length); var8++) {
         int var9 = var8 < var4.length ? method8(var4[var8]) : 0;
         int var10 = var8 < var5.length ? method8(var5[var8]) : 0;
         if (var9 != var10) {
            return Integer.compare(var9, var10);
         }
      }

      int var11 = method6(var6);
      int var12 = method6(var7);
      return var11 != var12 ? Integer.compare(var11, var12) : Integer.compare(method7(var6), method7(var7));
   }

   private static int method6(String var0) {
      if (var0.startsWith("snapshot-")) {
         return 0;
      } else if (var0.startsWith("pre-")) {
         return 1;
      } else {
         return var0.startsWith("rc-") ? 2 : 3;
      }
   }

   private static int method7(String var0) {
      int var1 = var0.lastIndexOf(45);
      return var1 == -1 ? 0 : method8(var0.substring(var1 + 1));
   }

   private static int method8(String var0) {
      try {
         return Integer.parseInt(var0);
      } catch (NumberFormatException var2) {
         return 0;
      }
   }

   public Optional<Files3> method9(String var1) {
      for (Files3 var5 : this.versions) {
         if (var5.getId().equals(var1)) {
            return Optional.of(var5);
         }
      }

      return Optional.empty();
   }

   @Generated
   public JsonObject getLatest() {
      return this.latest;
   }

   @Generated
   public Files3[] getVersions() {
      return this.versions;
   }

   @Generated
   public void setLatest(JsonObject var1) {
      this.latest = var1;
   }

   @Generated
   public void setVersions(Files3[] var1) {
      this.versions = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Files4 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         JsonObject var3 = this.getLatest();
         JsonObject var4 = var2.method10();
         return (var3 == null ? var4 == null : var3.equals(var4)) ? Arrays.deepEquals(this.getVersions(), var2.method11()) : false;
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Files4;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      JsonObject var3 = this.getLatest();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      return var2 * 59 + Arrays.deepHashCode(this.getVersions());
   }

   @Generated
   @Override
   public String toString() {
      return "VersionManifest(latest=" + this.getLatest() + ", versions=" + Arrays.deepToString(this.getVersions()) + ")";
   }
}
