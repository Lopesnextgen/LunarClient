package com.moonsworth.lunar.client.framework.feature.pkg;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(max = 5)
public class Pkg_2 {
   public static boolean field1 = false;
   private static final Pkg2_2 field2 = new Pkg2_2();
   private static final Pkg3_2 field3 = new Pkg3_2();

   public static void method1(Bridge5_11 var0, BridgeExtension2_7 var1, float var2) {
      field2.method5(AbstractRenderContext.method32(), var0, var1, 0.0F, 0.0F, 0.0F, var0.method2(), 0.0F, 0.0F, var2);
      field3.method5(AbstractRenderContext.method32(), var0, var1, 0.0F, 0.0F, 0.0F, var0.method2(), 0.0F, 0.0F, var2);
   }
}
