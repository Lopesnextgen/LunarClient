package com.moonsworth.lunar.files.mixin;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class AssetIndex {
   @SerializedName("id")
   private String id;
   @SerializedName("sha1")
   private String sha1;
   @SerializedName("url")
   private String url;

   @Generated
   public AssetIndex() {
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public String getSha1() {
      return this.sha1;
   }

   @Generated
   public String getUrl() {
      return this.url;
   }

   @Generated
   public void setId(String text1) {
      this.id = text1;
   }

   @Generated
   public void setSha1(String text1) {
      this.sha1 = text1;
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
      } else if (!(obj1 instanceof AssetIndex files22)) {
         return false;
      } else {
         if (!files22.canEqual(this)) {
            return false;
         }

         String text3 = this.getId();
         String text4 = files22.getId();
         if (text3 == null ? text4 == null : text3.equals(text4)) {
            String text5 = this.getSha1();
            String text6 = files22.method1();
            if (text5 == null ? text6 == null : text5.equals(text6)) {
               String text7 = this.getUrl();
               String text8 = files22.getUrl();
               return text7 == null ? text8 == null : text7.equals(text8);
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
      return obj1 instanceof AssetIndex;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      String text3 = this.getId();
      number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
      String text4 = this.getSha1();
      number2 = number2 * 59 + (text4 == null ? 43 : text4.hashCode());
      String text5 = this.getUrl();
      return number2 * 59 + (text5 == null ? 43 : text5.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "AssetIndex(id=" + this.getId() + ", sha1=" + this.getSha1() + ", url=" + this.getUrl() + ")";
   }
}
