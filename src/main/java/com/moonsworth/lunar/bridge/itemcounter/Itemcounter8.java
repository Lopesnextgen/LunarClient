package com.moonsworth.lunar.bridge.itemcounter;

import java.io.File;
import lombok.Generated;

public class Itemcounter8 {
   private String path;
   private String name;
   private String version;
   private File field1;

   @Generated
   public String getPath() {
      return this.path;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getVersion() {
      return this.version;
   }

   @Generated
   public File method1() {
      return this.field1;
   }

   @Generated
   public void setPath(String var1) {
      this.path = var1;
   }

   @Generated
   public void setName(String var1) {
      this.name = var1;
   }

   @Generated
   public void setVersion(String var1) {
      this.version = var1;
   }

   @Generated
   public void method3(File var1) {
      this.field1 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Itemcounter8 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         String var3 = this.getPath();
         String var4 = var2.getPath();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            String var5 = this.getName();
            String var6 = var2.getName();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               String var7 = this.getVersion();
               String var8 = var2.getVersion();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  File var9 = this.method1();
                  File var10 = var2.method1();
                  return var9 == null ? var10 == null : var9.equals(var10);
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
      return var1 instanceof Itemcounter8;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      String var3 = this.getPath();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      String var4 = this.getName();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      String var5 = this.getVersion();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      File var6 = this.method1();
      return var2 * 59 + (var6 == null ? 43 : var6.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "LevelData(path=" + this.getPath() + ", name=" + this.getName() + ", version=" + this.getVersion() + ", icon=" + this.method1() + ")";
   }

   @Generated
   public Itemcounter8(String var1, String var2, String var3, File var4) {
      this.path = var1;
      this.name = var2;
      this.version = var3;
      this.field1 = var4;
   }
}
