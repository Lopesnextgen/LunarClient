package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Framework11Handler;
import com.moonsworth.lunar.client.framework.listener.Nameplate;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface Framework11 {
   static Framework11Handler method1(Framework7Extension framework7, Nameplate var1) {
      return new Framework11Handler(var1, framework7);
   }

   boolean method2();

   @Internal
   void method3(boolean var1);
}
