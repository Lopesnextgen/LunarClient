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

@Annotation6(IHRORRHHCRCIHHRRIHRHICCCRRCRRI = Rewindhandlers11.Data2.class)
@Annotation8(IHRORRHHCRCIHHRRIHRHICCCRRCRRI = Rewindhandlers11.Data.class)
public class Rewindhandlers11 {
   public double[][][] field1;
   public RewindhandlersType field2;

   static class Data extends MixinHelper62_5<Rewindhandlers11> {
      public void method1(Rewindhandlers11 var1, MixinHelper52_5 var2, MixinHelper72_4 var3) {
         if (var1.field1 != null) {
            var2.method89(var1.field1);
         } else if (var1.field2 != null) {
            var2.method89(var1.field2);
         } else {
            throw new IOException("PolysUnion must not be null");
         }
      }
   }

   static class Data2 extends MixinHelper102_4<Rewindhandlers11> {
      public Rewindhandlers11 method1(MixinHelper53 var1, MixinHelper73_3 var2) {
         Rewindhandlers11 var3 = new Rewindhandlers11();
         switch (var1.method46()) {
            case VALUE_STRING:
               String var4 = var1.method94(String.class);

               try {
                  var3.field2 = RewindhandlersType.forValue(var4);
               } catch (Exception var6) {
               }
               break;
            case START_ARRAY:
               var3.field1 = var1.method94(double[][][].class);
               break;
            default:
               throw new IOException("Cannot deserialize PolysUnion");
         }

         return var3;
      }
   }
}
