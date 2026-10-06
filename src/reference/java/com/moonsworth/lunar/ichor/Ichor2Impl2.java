package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.loader.Ichor4Type;
import org.cadixdev.lorenz.MappingSet;

public class Ichor2Impl2 extends com.moonsworth.lunar.ichor.util.Ichor2Handler {
   public Ichor2Impl2() {
      super("com/moonsworth/lunar/");
   }

   @Annotation8(
      OORRORHHCIICCICOOOIOOHRRHCOIHI = true,
      RHRRICIHIHHROIRCHHROOOHHOCHORH = true,
      COCICIRCIOIRRHRHHRHOCOHRHOHCIO = true,
      HCCCHRRORIIOCCRHHROOOHORCHOICC = false,
      HIHCIHIOOCIORRCIOROCCCRCOOICCR = true
   )
   public RemapTransformInvoker.RemapTransformResult method1(URLClassLoader var1) {
      IchorPipeline var2 = var1.method1();
      MappingSet var3 = var2.method10(Files.Data2.field14).orElseThrow(() -> new FatalIchorError("Can't find Lunar to Intermediary mappings"));
      var2.method20(Ichor5Handler.class, var3, var1);
      return new RemapTransformInvoker.RemapTransformResult(
         var3, var0 -> !var0.startsWith("com/moonsworth/lunar/client/") && (!var0.startsWith("com/moonsworth/lunar/bridge/") || var0.contains("bridge/v"))
      );
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.EXTERNAL_REMAP};
   }
}
