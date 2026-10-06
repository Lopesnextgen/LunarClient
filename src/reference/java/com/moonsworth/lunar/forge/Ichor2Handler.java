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

public class Ichor2Handler implements IchorInjection {
   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.INIT};
   }

   @Annotation8(method1 = true, OORRORHHCIICCICOOOIOOHRRHCOIHI = trueormInvoker.RemapTransformResult method2(URLClassLoader var1) {
      try {
         MappingSet var2 = var1.method1().method10(Files.Data2.field4).orElseThrow(() -> new FatalIchorError("Can't find mappings"));
         return new RemapTransformInvoker.RemapTransformResult(var2, Ichor6Iterator.field1::contains);
      } catch (Throwable var3) {
         throw var3;
      }
   }
}
