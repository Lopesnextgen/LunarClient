package com.moonsworth.lunar.files.mixin;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Generated;

public class ResolvedVersion {
   private transient VersionInfo field1 = null;
   @SerializedName("id")
   private String id;
   @SerializedName("javaVersion")
   private JsonObject javaVersion;
   @SerializedName("assetIndex")
   private AssetIndex assetIndex;
   @SerializedName("mainClass")
   private String mainClass;
   @SerializedName("type")
   private String type;
   @SerializedName("time")
   private String time;
   @SerializedName("releaseTime")
   private String releaseTime;
   @SerializedName("downloads")
   private Map<String, ResolvedVersion.DownloadInfo> downloads;

   @Generated
   public ResolvedVersion() {
   }

   @Generated
   public VersionInfo method1() {
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
   public AssetIndex getAssetIndex() {
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
   public Map<String, ResolvedVersion.DownloadInfo> method7() {
      return this.downloads;
   }

   @Generated
   public void method8(VersionInfo files31) {
      this.field1 = files31;
   }

   @Generated
   public void setId(String text1) {
      this.id = text1;
   }

   @Generated
   public void setJavaVersion(JsonObject json1) {
      this.javaVersion = json1;
   }

   @Generated
   public void setAssetIndex(AssetIndex files21) {
      this.assetIndex = files21;
   }

   @Generated
   public void setMainClass(String text1) {
      this.mainClass = text1;
   }

   @Generated
   public void setType(String text1) {
      this.type = text1;
   }

   @Generated
   public void setTime(String text1) {
      this.time = text1;
   }

   @Generated
   public void setReleaseTime(String text1) {
      this.releaseTime = text1;
   }

   @Generated
   public void method14(Map<String, ResolvedVersion.DownloadInfo> map) {
      this.downloads = map;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof ResolvedVersion files2)) {
         return false;
      } else {
         if (!files2.canEqual(this)) {
            return false;
         }

         String text3 = this.getId();
         String text4 = files2.getId();
         if (text3 == null ? text4 == null : text3.equals(text4)) {
            JsonObject json5 = this.method2();
            JsonObject json6 = files2.method2();
            if (json5 == null ? json6 == null : json5.equals(json6)) {
               AssetIndex files27 = this.getAssetIndex();
               AssetIndex files28 = files2.method3();
               if (files27 == null ? files28 == null : files27.equals(files28)) {
                  String text9 = this.getMainClass();
                  String text10 = files2.getMainClass();
                  if (text9 == null ? text10 == null : text9.equals(text10)) {
                     String text11 = this.getType();
                     String text12 = files2.getType();
                     if (text11 == null ? text12 == null : text11.equals(text12)) {
                        String text13 = this.getTime();
                        String text14 = files2.method5();
                        if (text13 == null ? text14 == null : text13.equals(text14)) {
                           String text15 = this.getReleaseTime();
                           String text16 = files2.method6();
                           if (text15 == null ? text16 == null : text15.equals(text16)) {
                              Map map17 = this.method7();
                              Map map18 = files2.method7();
                              return map17 == null ? map18 == null : map17.equals(map18);
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
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof ResolvedVersion;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      String text3 = this.getId();
      number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
      JsonObject json4 = this.method2();
      number2 = number2 * 59 + (json4 == null ? 43 : json4.hashCode());
      AssetIndex files25 = this.getAssetIndex();
      number2 = number2 * 59 + (files25 == null ? 43 : files25.hashCode());
      String text6 = this.getMainClass();
      number2 = number2 * 59 + (text6 == null ? 43 : text6.hashCode());
      String text7 = this.getType();
      number2 = number2 * 59 + (text7 == null ? 43 : text7.hashCode());
      String text8 = this.getTime();
      number2 = number2 * 59 + (text8 == null ? 43 : text8.hashCode());
      String text9 = this.getReleaseTime();
      number2 = number2 * 59 + (text9 == null ? 43 : text9.hashCode());
      Map map10 = this.method7();
      return number2 * 59 + (map10 == null ? 43 : map10.hashCode());
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

   public static final class DownloadInfo {
      @SerializedName("sha1")
      private String field1;
      @SerializedName("url")
      private String url;

      @Generated
      public DownloadInfo() {
      }

      @Generated
      public String method1() {
         return this.field1;
      }

      @Generated
      public String getUrl() {
         return this.url;
      }

      @Generated
      public void method2(String text1) {
         this.field1 = text1;
      }

      @Generated
      public void setUrl(String text1) {
         this.url = text1;
      }

      @Generated
      @Override
      public boolean equals(Object obj1) {
         if (obj1 == this) {
            return true;
         } else if (!(obj1 instanceof ResolvedVersion.DownloadInfo data2)) {
            return false;
         } else {
            String text3 = this.method1();
            String text4 = data2.method1();
            if (text3 == null ? text4 == null : text3.equals(text4)) {
               String text5 = this.getUrl();
               String text6 = data2.getUrl();
               return text5 == null ? text6 == null : text5.equals(text6);
            } else {
               return false;
            }
         }
      }

      @Generated
      @Override
      public int hashCode() {
         byte number1 = 59;
         int number2 = 1;
         String text3 = this.method1();
         number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
         String text4 = this.getUrl();
         return number2 * 59 + (text4 == null ? 43 : text4.hashCode());
      }

      @Generated
      @Override
      public String toString() {
         return "ResolvedVersion.DownloadInfo(sha1=" + this.method1() + ", url=" + this.getUrl() + ")";
      }
   }
}
