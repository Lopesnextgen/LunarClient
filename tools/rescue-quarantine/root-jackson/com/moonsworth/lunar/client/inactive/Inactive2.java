package com.moonsworth.lunar.client.inactive;

import com.moonsworth.lunar.Annotation27;
import lombok.Generated;

public class Inactive2 {
   private String field1;
   private Boolean field2;
   private Long field3;

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Inactive2 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         Boolean var3 = this.method3();
         Boolean var4 = var2.method3();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            Long var5 = this.method5();
            Long var6 = var2.method5();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               String var7 = this.method1();
               String var8 = var2.method1();
               return var7 == null ? var8 == null : var7.equals(var8);
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
      return var1 instanceof Inactive2;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      Boolean var3 = this.method3();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Long var4 = this.method5();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      String var5 = this.method1();
      return var2 * 59 + (var5 == null ? 43 : var5.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "AnimationState(animation=" + this.method1() + ", isDefault=" + this.method3() + ", timer=" + this.method5() + ")";
   }

   @Annotation27("animation")
   @Generated
   public String method1() {
      return this.field1;
   }

   @Annotation27("animation")
   @Generated
   public void method2(String var1) {
      this.field1 = var1;
   }

   @Annotation27("is_default")
   @Generated
   public Boolean method3() {
      return this.field2;
   }

   @Annotation27("is_default")
   @Generated
   public void method4(Boolean var1) {
      this.field2 = var1;
   }

   @Annotation27("timer")
   @Generated
   public Long method5() {
      return this.field3;
   }

   @Annotation27("timer")
   @Generated
   public void method6(Long var1) {
      this.field3 = var1;
   }
}
