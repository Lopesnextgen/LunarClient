package com.moonsworth.lunar.files.mixin;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.files.Files3_2;
import lombok.Generated;

public class Files3 {
   private transient Files3 field1 = null;
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
      String var1 = this.field1 == null ? "none" : this.field1.id;
      return "VersionInfo{" + this.id + ", " + this.type + ", parent=" + var1 + "}";
   }

   public Files method1() {
      Files var1 = (Files)com.moonsworth.lunar.files.Files.field1.fromJson(Files3_2.method2(this.url), Files.class);
      var1.method8(this.field1);
      return var1;
   }

   public boolean method2() {
      return this.id.compareTo("26") >= 0;
   }

   @Generated
   public Files3 method3() {
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
   public void setId(String var1) {
      this.id = var1;
   }

   @Generated
   public void setType(String var1) {
      this.type = var1;
   }

   @Generated
   public void setUrl(String var1) {
      this.url = var1;
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
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Files3 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         String var3 = this.getId();
         String var4 = var2.getId();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            String var5 = this.getType();
            String var6 = var2.getType();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               String var7 = this.getUrl();
               String var8 = var2.getUrl();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  String var9 = this.getTime();
                  String var10 = var2.method4();
                  if (var9 == null ? var10 == null : var9.equals(var10)) {
                     String var11 = this.getReleaseTime();
                     String var12 = var2.method5();
                     return var11 == null ? var12 == null : var11.equals(var12);
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
      return var1 instanceof Files3;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      String var3 = this.getId();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      String var4 = this.getType();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      String var5 = this.getUrl();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      String var6 = this.getTime();
      var2 = var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      String var7 = this.getReleaseTime();
      return var2 * 59 + (var7 == null ? 43 : var7.hashCode());
   }

   @Generated
   public void method8(Files3 var1) {
      this.field1 = var1;
   }
}
