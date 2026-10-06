package com.moonsworth.lunar.client.framework.feature.pkg;

import com.moonsworth.lunar.bridge.Bridge2_46;
import java.util.function.Supplier;
import lombok.Generated;

final class Pkg3$Data {
   private final com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 field1;
   private final boolean field2;
   private final Pkg3$Type field3;
   private final Pkg3$Type2 field4;
   private final Supplier<Bridge2_46> field5;
   private final Supplier<Boolean> field6;

   @Generated
   public Pkg3$Data(
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var1,
      boolean var2,
      Pkg3$Type var3,
      Pkg3$Type2 var4,
      Supplier<Bridge2_46> var5,
      Supplier<Boolean> var6
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method1() {
      return this.field1;
   }

   @Generated
   public boolean method2() {
      return this.field2;
   }

   @Generated
   public Pkg3$Type method3() {
      return this.field3;
   }

   @Generated
   public Pkg3$Type2 method4() {
      return this.field4;
   }

   @Generated
   public Supplier<Bridge2_46> method5() {
      return this.field5;
   }

   @Generated
   public Supplier<Boolean> method6() {
      return this.field6;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Pkg3$Data var2)) {
         return false;
      } else {
         if (this.method2() != var2.method2()) {
            return false;
         }

         com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var3 = this.method1();
         com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var4 = var2.method1();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            Pkg3$Type var5 = this.method3();
            Pkg3$Type var6 = var2.method3();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               Pkg3$Type2 var7 = this.method4();
               Pkg3$Type2 var8 = var2.method4();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  Supplier var9 = this.method5();
                  Supplier var10 = var2.method5();
                  if (var9 == null ? var10 == null : var9.equals(var10)) {
                     Supplier var11 = this.method6();
                     Supplier var12 = var2.method6();
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
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + (this.method2() ? 79 : 97);
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var3 = this.method1();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Pkg3$Type var4 = this.method3();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      Pkg3$Type2 var5 = this.method4();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      Supplier var6 = this.method5();
      var2 = var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      Supplier var7 = this.method6();
      return var2 * 59 + (var7 == null ? 43 : var7.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "BodyLayerFeatureRenderer.Layer(layer="
         + this.method1()
         + ", mirrored="
         + this.method2()
         + ", part="
         + this.method3()
         + ", shape="
         + this.method4()
         + ", vanillaGetter="
         + this.method5()
         + ", configGetter="
         + this.method6()
         + ")";
   }
}
