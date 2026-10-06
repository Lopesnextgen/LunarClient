package com.moonsworth.lunar.files;

import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Files$Data3 {
   private final String field1;
   private final String field2;
   private final List<Files$Data5> field3 = new ArrayList<>();
   private final List<Files$Data6> field4 = new ArrayList<>();

   @Generated
   public String method1() {
      return this.field1;
   }

   @Generated
   public String getNewName() {
      return this.field2;
   }

   @Generated
   public List<Files$Data5> method2() {
      return this.field3;
   }

   @Generated
   public List<Files$Data6> method3() {
      return this.field4;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Files$Data3 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         String var3 = this.method1();
         String var4 = var2.method1();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            String var5 = this.getNewName();
            String var6 = var2.getNewName();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               List var7 = this.method2();
               List var8 = var2.method2();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  List var9 = this.method3();
                  List var10 = var2.method3();
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
      return var1 instanceof Files$Data3;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      String var3 = this.method1();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      String var4 = this.getNewName();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      List var5 = this.method2();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      List var6 = this.method3();
      return var2 * 59 + (var6 == null ? 43 : var6.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "Match.ClassMatch(oldName="
         + this.method1()
         + ", newName="
         + this.getNewName()
         + ", fieldMatches="
         + this.method2()
         + ", methodMatches="
         + this.method3()
         + ")";
   }

   @Generated
   public Files$Data3(String var1, String var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
