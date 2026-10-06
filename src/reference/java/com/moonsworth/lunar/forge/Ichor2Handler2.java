package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.ichor.Annotation8;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.RemapTransformInvoker;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.loader.Ichor4Type;
import org.cadixdev.lorenz.MappingSet;

public class Ichor2Handler2 implements IchorInjection {
   private MappingSet field1 = null;

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.EXTERNAL_REMAP};
   }

   @Annotation8(method5 = true)
   public RemapTransformInvoker.RemapTransformResult method2(URLClassLoader var1) {
      try {
         MappingSet var2 = var1.method1().method10(Files.Data2.field4).orElseThrow(() -> new FatalIchorError("Can't find mappings"));
         this.field1 = var2;
         return new RemapTransformInvoker.RemapTransformResult(MappingSet.create(), var0 -> false);
      } catch (Throwable var3) {
         throw var3;
      }
   }

   @Annotation8(
      OORRORHHCIICCICOOOIOOHRRHCOIHI = true,
      RHRRICIHIHHROIRCHHROOOHHOCHORH = true,
      COCICIRCIOIRRHRHHRHOCOHRHOHCIO = true,
      method3 = true
   ssLoader var1) {
      try {
         MappingSet var2 = var1.method1().method10(Files.Data2.field16).orElseThrow(() -> new FatalIchorError("Can't find mappings"));
         this.field1 = null;
         return new RemapTransformInvoker.RemapTransformResult(var2, var0 -> Ichor6Iterator.field1.contains(var0.replace('/', '.')));
      } catch (Throwable var3) {
         throw var3;
      }
   }
}
