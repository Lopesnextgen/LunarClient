package com.moonsworth.lunar.client.framework.feature.worldeditcui.mixin;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.mod.render.worldeditcui.WorldeditCui;

public interface Worldeditcui {
   void method1(AbstractRenderContext bridgeextension_91, WorldeditCui worldeditcui2);

   default double method2(double value, double value2) {
      return value <= -0.0 ? value - value2 : value + value2;
   }
}
