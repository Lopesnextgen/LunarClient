package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorLoader;

public class ForgeHookLoader implements IchorLoader {
   public ForgeHookLoader() {
   }

   public void loadIchor(IchorTransformer ichorTransformer) {
      ichorTransformer.method1(new Ichor2Handler2());
      ichorTransformer.method1(new Ichor2Handler());
      ichorTransformer.method1(new ForgeTransformerHook());
   }
}
