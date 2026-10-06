package com.moonsworth.lunar.files.mixin;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.files.MappingDownloader;
import lombok.Generated;

public class VersionInfo {
   private transient VersionInfo field1 = null;
   @SerializedName("id")
   private String id;
   @SerializedName("type")
   private String type;
   @SerializedName("url")
   private String url;
   @SerializedName("time")
   private String time;
   @SerializedName("releaseTime")
   private String releaseTime;

   @Override
   public String toString() {
      String text1 = this.field1 == null ? "none" : this.field1.id;
      return "VersionInfo{" + this.id + ", " + this.type + ", parent=" + text1 + "}";
   }

   public ResolvedVersion method1() {
      ResolvedVersion files1 = (ResolvedVersion)com.moonsworth.lunar.files.MappingsGson.field1.fromJson(MappingDownloader.method2(this.url), ResolvedVersion.class);
      files1.method8(this.field1);
      return files1;
   }

   public boolean method2() {
      return this.id.compareTo("26") >= 0;
   }

   @Generated
   public VersionInfo() {
   }

   @Generated
   public VersionInfo method3() {
      return this.field1;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public String getType() {
      return this.type;
   }

   @Generated
   public String getUrl() {
      return this.url;
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
   public void setId(String text1) {
      this.id = text1;
   }

   @Generated
   public void setType(String text1) {
      this.type = text1;
   }

   @Generated
   public void setUrl(String text1) {
      this.url = text1;
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
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof VersionInfo files32)) {
         return false;
      } else {
         if (!files32.canEqual(this)) {
            return false;
         }

         String text3 = this.getId();
         String text4 = files32.getId();
         if (text3 == null ? text4 == null : text3.equals(text4)) {
            String text5 = this.getType();
            String text6 = files32.getType();
            if (text5 == null ? text6 == null : text5.equals(text6)) {
               String text7 = this.getUrl();
               String text8 = files32.getUrl();
               if (text7 == null ? text8 == null : text7.equals(text8)) {
                  String text9 = this.getTime();
                  String text10 = files32.method4();
                  if (text9 == null ? text10 == null : text9.equals(text10)) {
                     String text11 = this.getReleaseTime();
                     String text12 = files32.method5();
                     return text11 == null ? text12 == null : text11.equals(text12);
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
      return obj1 instanceof VersionInfo;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      String text3 = this.getId();
      number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
      String text4 = this.getType();
      number2 = number2 * 59 + (text4 == null ? 43 : text4.hashCode());
      String text5 = this.getUrl();
      number2 = number2 * 59 + (text5 == null ? 43 : text5.hashCode());
      String text6 = this.getTime();
      number2 = number2 * 59 + (text6 == null ? 43 : text6.hashCode());
      String text7 = this.getReleaseTime();
      return number2 * 59 + (text7 == null ? 43 : text7.hashCode());
   }

   @Generated
   public void method8(VersionInfo files31) {
      this.field1 = files31;
   }
}
