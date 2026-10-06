package com.moonsworth.lunar.bridge.fog;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

public interface Fog3 {
   Fog2 method1();

   Fog2 method2();

   Fog2 method3();

   Fog2 method4();

   Fog2 method5();

   Fog2 method6();

   Fog2 method7();

   Fog2 method8(int var1);

   @Annotation2(min = 6)
   Fog2 method9(String var1);

   Fog method10(int var1, String var2, int var3, int var4);

   String method11(String var1);

   Component method12(Fog var1);

   String method13(Fog var1);

   boolean method14(ItemStackBridge var1);

   @Unmodifiable List<String> method15();

   @Nullable
   String method16(ItemStackBridge var1);

   @Annotation2(min = 5)
   default boolean method17(ItemStackBridge var1) {
      return false;
   }

   void method18(ItemStackBridge var1, String var2);
}
