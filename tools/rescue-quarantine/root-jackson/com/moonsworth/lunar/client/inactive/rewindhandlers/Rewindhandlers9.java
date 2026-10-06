package com.moonsworth.lunar.client.inactive.rewindhandlers;

import com.moonsworth.lunar.Annotation6;
import com.moonsworth.lunar.Annotation8;
import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper52_5;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper62_5;
import com.moonsworth.lunar.MixinHelper72_4;
import com.moonsworth.lunar.MixinHelper73_3;
import java.io.IOException;

@Annotation6(IHRORRHHCRCIHHRRIHRHICCCRRCRRI = Rewindhandlers9.Data2.class)
@Annotation8(IHRORRHHCRCIHHRRIHRHICCCRRCRRI = Rewindhandlers9.Data.class)
public class Rewindhandlers9 {
   public double[] field1;
   public Rewindhandlers8 field2;
   public boolean field3;

   static class Data extends MixinHelper62_5<Rewindhandlers9> {
      public void method1(Rewindhandlers9 var1, MixinHelper52_5 var2, MixinHelper72_4 var3) {
         if (var1.field1 != null) {
            var2.method89(var1.field1);
         } else if (var1.field2 != null) {
            var2.method89(var1.field2);
         } else {
            var2.method81();
         }
      }
   }

   static class Data2 extends MixinHelper102_4<Rewindhandlers9> {
      public Rewindhandlers9 method1(MixinHelper53 var1, MixinHelper73_3 var2) {
         Rewindhandlers9 var3 = new Rewindhandlers9();
         switch (var1.method46()) {
            case VALUE_NULL:
               break;
            case START_ARRAY:
               var3.field1 = var1.method94(double[].class);
               var3.field3 = true;
               break;
            case START_OBJECT:
               var3.field2 = var1.method94(Rewindhandlers8.class);
               var3.field3 = false;
               break;
            default:
               throw new IOException("Cannot deserialize UvUnion");
         }

         return var3;
      }
   }
}
