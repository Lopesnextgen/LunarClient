package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import org.jetbrains.annotations.Nullable;

public abstract class F3DebugEntry {
   private boolean field1 = false;

   protected F3DebugEntry() {
   }

   protected abstract void method1(F3DebugWriter f3display_31, @Nullable F3DisplayModule f3modulechildmod2);

   protected abstract void method2(F3DebugWriter f3display_31, @Nullable F3DisplayModule f3modulechildmod2);

   protected void clear() {
   }
}
