package com.moonsworth.lunar.files.mixin;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Generated;

public class Files {
   private transient Files3 field1 = null;
   @SerializedName("id")
   private String id;
   @SerializedName("javaVersion")
   private JsonObject javaVersion;
   @SerializedName("assetIndex")
   private Files2 assetIndex;
   @SerializedName("mainClass")
   private String mainClass;
   @SerializedName("type")
   private String type;
   @SerializedName("time")
   private String time;
   @SerializedName("releaseTime")
   private String releaseTime;
   @SerializedName("downloads")
   private Map<String, Files.Data> downloads;

   @Generated
   public Files3 method1() {
      return this.field1;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public JsonObject method2() {
      return this.javaVersion;
   }

   @Generated
   public Files2 getAssetIndex() {
      return this.assetIndex;
   }

   @Generated
   public String getMainClass() {
      return this.mainClass;
   }

   @Generated
   public String getType() {
      return this.type;
   }

   @Generated
   public String getTime() {
      return this.time;
   }

   @Generated
   public String getReleaseTime() {
      return this.releaseTime;
   }

   @Generated
   public Map<String, Files.Data> method7() {
      return this.downloads;
   }

   @Generated
   public void method8(Files3 var1) {
      this.field1 = var1;
   }

   @Generated
   public void setId(String var1) {
      this.id = var1;
   }

   @Generated
   public void setJavaVersion(JsonObject var1) {
      this.javaVersion = var1;
   }

   @Generated
   public void setAssetIndex(Files2 var1) {
      this.assetIndex = var1;
   }

   @Generated
   public void setMainClass(String var1) {
      this.mainClass = var1;
   }

   @Generated
   public void setType(String var1) {
      this.type = var1;
   }

   @Generated
   public void setTime(String var1) {
      this.time = var1;
   }

   @Generated
   public void setReleaseTime(String var1) {
      this.releaseTime = var1;
   }

   @Generated
   public void method14(Map<String, Files.Data> var1) {
      this.downloads = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Files var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         String var3 = this.getId();
         String var4 = var2.getId();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            JsonObject var5 = this.method2();
            JsonObject var6 = var2.method2();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               Files2 var7 = this.getAssetIndex();
               Files2 var8 = var2.method3();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  String var9 = this.getMainClass();
                  String var10 = var2.getMainClass();
                  if (var9 == null ? var10 == null : var9.equals(var10)) {
                     String var11 = this.getType();
                     String var12 = var2.getType();
                     if (var11 == null ? var12 == null : var11.equals(var12)) {
                        String var13 = this.getTime();
                        String var14 = var2.method5();
                        if (var13 == null ? var14 == null : var13.equals(var14)) {
                           String var15 = this.getReleaseTime();
                           String var16 = var2.method6();
                           if (var15 == null ? var16 == null : var15.equals(var16)) {
                              Map var17 = this.method7();
                              Map var18 = var2.method7();
                              return var17 == null ? var18 == null : var17.equals(var18);
                           } else {
                              return false;
                           }
                        } else {
                           return false;
                        }
                     } else {
                        return false;
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Files;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      String var3 = this.getId();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      JsonObject var4 = this.method2();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      Files2 var5 = this.getAssetIndex();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      String var6 = this.getMainClass();
      var2 = var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      String var7 = this.getType();
      var2 = var2 * 59 + (var7 == null ? 43 : var7.hashCode());
      String var8 = this.getTime();
      var2 = var2 * 59 + (var8 == null ? 43 : var8.hashCode());
      String var9 = this.getReleaseTime();
      var2 = var2 * 59 + (var9 == null ? 43 : var9.hashCode());
      Map var10 = this.method7();
      return var2 * 59 + (var10 == null ? 43 : var10.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "ResolvedVersion(parent="
         + this.method1()
         + ", id="
         + this.getId()
         + ", javaVersion="
         + this.method2()
         + ", assetIndex="
         + this.getAssetIndex()
         + ", mainClass="
         + this.getMainClass()
         + ", type="
         + this.getType()
         + ", time="
         + this.getTime()
         + ", releaseTime="
         + this.getReleaseTime()
         + ", downloads="
         + this.method7()
         + ")";
   }

   public static final class Data {
      @SerializedName("sha1")
      private String field1;
      @SerializedName("url")
      private String url;

      @Generated
      public String method1() {
         return this.field1;
      }

      @Generated
      public String getUrl() {
         return this.url;
      }

      @Generated
      public void method2(String var1) {
         this.field1 = var1;
      }

      @Generated
      public void setUrl(String var1) {
         this.url = var1;
      }

      @Generated
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof Files.Data var2)) {
            return false;
         } else {
            String var3 = this.method1();
            String var4 = var2.method1();
            if (var3 == null ? var4 == null : var3.equals(var4)) {
               String var5 = this.getUrl();
               String var6 = var2.getUrl();
               return var5 == null ? var6 == null : var5.equals(var6);
            } else {
               return false;
            }
         }
      }

      @Generated
      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         String var3 = this.method1();
         var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
         String var4 = this.getUrl();
         return var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      }

      @Generated
      @Override
      public String toString() {
         return "ResolvedVersion.DownloadInfo(sha1=" + this.method1() + ", url=" + this.getUrl() + ")";
      }
   }
}
