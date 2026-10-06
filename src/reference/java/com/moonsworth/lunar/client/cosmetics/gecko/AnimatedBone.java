package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.Annotation6;
import com.moonsworth.lunar.Annotation8;
import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper52_5;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper62_5;
import com.moonsworth.lunar.MixinHelper72_4;
import com.moonsworth.lunar.MixinHelper73_3;
import java.io.IOException;
import com.moonsworth.lunar.client.inactive.rewindhandlers.Rewindhandlers10;

@Annotation6(IHRORRHHCRCIHHRRIHRHICCCRRCRRI = AnimatedBone.Data2.class)
@Annotation8(IHRORRHHCRCIHHRRIHRHICCCRRCRRI = AnimatedBone.Data.class)
public class AnimatedBone {
   public Rewindhandlers10 field1;
   public double[] field2;

   static class Data extends MixinHelper62_5<AnimatedBone> {
      public void method1(AnimatedBone var1, MixinHelper52_5 var2, MixinHelper72_4 var3) {
         if (var1.field1 != null) {
            var2.method89(var1.field1);
         } else if (var1.field2 != null) {
            var2.method89(var1.field2);
         } else {
            throw new IOException("LocatorValue must not be null");
         }
      }
   }

   static class Data2 extends MixinHelper102_4<AnimatedBone> {
      public AnimatedBone method1(MixinHelper53 var1, MixinHelper73_3 var2) {
         AnimatedBone var3 = new AnimatedBone();
         switch (var1.method48()) {
            case START_ARRAY:
               var3.field2 = var1.method94(double[].class);
               break;
            case START_OBJECT:
               var3.field1 = var1.method94(Rewindhandlers10.class);
               break;
            default:
               throw new IOException("Cannot deserialize LocatorValue");
         }

         return var3;
      }
   }
}
