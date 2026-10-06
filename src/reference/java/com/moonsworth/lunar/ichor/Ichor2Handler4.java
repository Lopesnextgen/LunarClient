package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.files.Files3;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.io.FileWriter;
import java.io.IOException;
import lombok.Generated;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.srg.xsrg.XSrgWriter;

public class Ichor2Handler4 implements IchorInjection {
   private Files3 field1;

   protected RemapTransformInvoker.RemapTransformResult method1(URLClassLoader var1) {
      IchorPipeline var2 = var1.method1();
      MappingSet var3 = var2.method10(this.field1).orElseThrow(() -> new FatalIchorError("Can't find Lunar mappings"));
      var2.method20(Ichor5Handler.class, var3, var1);
      if (RemapperIterator2.DEBUG) {
         try {
            XSrgWriter var4 = new XSrgWriter(new FileWriter(".ichor/lunar_mappings.xsrg"));

            try {
               var4.write(var3);
            } catch (Throwable var8) {
               try {
                  var4.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }

               throw var8;
            }

            var4.close();
         } catch (IOException var9) {
            throw new RuntimeException(var9);
         }
      }

      return new RemapTransformInvoker.RemapTransformResult(
         var3,
         var0 -> !var0.contains("/")
            || var0.startsWith("net/minecraft/")
            || var0.startsWith("com/mojang/")
            || var0.startsWith("net/optifine/")
            || var0.startsWith("net/minecraftforge/")
            || var0.startsWith("shadersmod/")
      );
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.INITIAL_REMAP};
   }

   @Generated
   public Ichor2Handler4(Files3 var1) {
      this.field1 = var1;
   }

   public static class Data extends Ichor2Handler4 {
      public Data(Files3 var1) {
         super(var1);
      }

      @Annotation8(
         OORRORHHCIICCICOOOIOOHRRHCOIHI = true,
         COCICIRCIOIRRHRHHRHOCOHRHOHCIO = true,
         HIHCIHIOOCIORRCIOROCCCRCOOICCR = true,
         IRIORHHHHHRHOHIRIOIRHCCOIIRCHI = true
      )
      public RemapTransformInvoker.RemapTransformResult method2(URLClassLoader var1) {
         return this.method1(var1);
      }
   }

   public static class LegacyRemapProvider extends Ichor2Handler4 {
      public LegacyRemapProvider(Files3 var1) {
         super(var1);
      }

      @Annotation8(OORRORHHCIICCICOOOIOOHRRHCOIHI = true, COCICIRCIOIRRHRHHRHOCOHRHOHCIO = true, HIHCIHIOOCIORRCIOROCCCRCOOICCR = true)
      public RemapTransformInvoker.RemapTransformResult method2(URLClassLoader var1) {
         return this.method1(var1);
      }
   }
}
