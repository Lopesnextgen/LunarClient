package com.moonsworth.lunar.files;

import lombok.Generated;

public class Files$Data6 implements Files$Extension {
   private final String field1;
   private final String field2;
   private final String field3;
   private final String field4;

   @Override
   public String method2() {
      return this.field2;
   }

   @Override
   public String method3() {
      return this.field4;
   }

   @Generated
   public Files$Data6(String var1, String var2, String var3, String var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
   }

   @Generated
   @Override
   public String method1() {
      return this.field1;
   }

   @Generated
   public String method4() {
      return this.field2;
   }

   @Generated
   @Override
   public String getNewName() {
      return this.field3;
   }

   @Generated
   public String method5() {
      return this.field4;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Files$Data6 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         String var3 = this.method1();
         String var4 = var2.method1();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            String var5 = this.method4();
            String var6 = var2.method4();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               String var7 = this.getNewName();
               String var8 = var2.getNewName();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  String var9 = this.method5();
                  String var10 = var2.method5();
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
      return var1 instanceof Files$Data6;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      String var3 = this.method1();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      String var4 = this.method4();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      String var5 = this.getNewName();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      String var6 = this.method5();
      return var2 * 59 + (var6 == null ? 43 : var6.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "Match.MethodMatch(oldName="
         + this.method1()
         + ", oldSignature="
         + this.method4()
         + ", newName="
         + this.getNewName()
         + ", newSignature="
         + this.method5()
         + ")";
   }
}
