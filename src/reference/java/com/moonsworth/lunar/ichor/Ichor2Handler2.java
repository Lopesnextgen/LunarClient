package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.loader.Ichor4Type;

public class Ichor2Handler2 implements IchorInjection, LunarMappingProviderHook, MixinHelper3_2 {
   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.EXTERNAL_REMAP};
   }

   @Override
   public boolean method1(IchorTransformer.Data3 data) {
      String var2 = data.className();
      return !var2.startsWith("it/unimi/dsi/fastutil/") && !var2.startsWith("com/moonsworth/");
   }
}
