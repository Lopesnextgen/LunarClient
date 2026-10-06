package com.moonsworth.lunar.client.cosmetics.skin;

import com.moonsworth.lunar.bridge.ModelRendererBridge;
import java.util.function.Supplier;
import lombok.Generated;

final class SkinPartEntry {
   private final com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 field1;
   private final boolean field2;
   private final OverlayGarment field3;
   private final BodyPartSpec field4;
   private final Supplier<ModelRendererBridge> field5;
   private final Supplier<Boolean> field6;

   @Generated
   public SkinPartEntry(
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg31,
      boolean flag,
      OverlayGarment pkg3$type3,
      BodyPartSpec pkg3$type24,
      Supplier<ModelRendererBridge> supplier5,
      Supplier<Boolean> supplier6
   ) {
      this.field1 = pkg31;
      this.field2 = flag;
      this.field3 = pkg3$type3;
      this.field4 = pkg3$type24;
      this.field5 = supplier5;
      this.field6 = supplier6;
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
   public OverlayGarment method3() {
      return this.field3;
   }

   @Generated
   public BodyPartSpec method4() {
      return this.field4;
   }

   @Generated
   public Supplier<ModelRendererBridge> method5() {
      return this.field5;
   }

   @Generated
   public Supplier<Boolean> method6() {
      return this.field6;
   }

   @Generated
   @Override
   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof SkinPartEntry pkg3$data2)) {
         return false;
      } else {
         if (this.method2() != pkg3$data2.method2()) {
            return false;
         }

         com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg33 = this.method1();
         com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg34 = pkg3$data2.method1();
         if (pkg33 == null ? pkg34 == null : pkg33.equals(pkg34)) {
            OverlayGarment pkg3$type5 = this.method3();
            OverlayGarment pkg3$type6 = pkg3$data2.method3();
            if (pkg3$type5 == null ? pkg3$type6 == null : pkg3$type5.equals(pkg3$type6)) {
               BodyPartSpec pkg3$type27 = this.method4();
               BodyPartSpec pkg3$type28 = pkg3$data2.method4();
               if (pkg3$type27 == null ? pkg3$type28 == null : pkg3$type27.equals(pkg3$type28)) {
                  Supplier supplier9 = this.method5();
                  Supplier supplier10 = pkg3$data2.method5();
                  if (supplier9 == null ? supplier10 == null : supplier9.equals(supplier10)) {
                     Supplier supplier11 = this.method6();
                     Supplier supplier12 = pkg3$data2.method6();
                     return supplier11 == null ? supplier12 == null : supplier11.equals(supplier12);
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
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.method2() ? 79 : 97);
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg33 = this.method1();
      number2 = number2 * 59 + (pkg33 == null ? 43 : pkg33.hashCode());
      OverlayGarment pkg3$type4 = this.method3();
      number2 = number2 * 59 + (pkg3$type4 == null ? 43 : pkg3$type4.hashCode());
      BodyPartSpec pkg3$type25 = this.method4();
      number2 = number2 * 59 + (pkg3$type25 == null ? 43 : pkg3$type25.hashCode());
      Supplier supplier6 = this.method5();
      number2 = number2 * 59 + (supplier6 == null ? 43 : supplier6.hashCode());
      Supplier supplier7 = this.method6();
      return number2 * 59 + (supplier7 == null ? 43 : supplier7.hashCode());
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
