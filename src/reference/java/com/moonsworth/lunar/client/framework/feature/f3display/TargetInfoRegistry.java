package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import java.util.EnumMap;
import org.jetbrains.annotations.Nullable;

public class TargetInfoRegistry {
   private final EnumMap<F3DebugLine, F3DebugEntry> field1 = new EnumMap<>(F3DebugLine.class);

   public TargetInfoRegistry() {
      this.field1.put(F3DebugLine.TARGET_BLOCK, new TargetInfoProvider("Target Block", false));
      this.field1.put(F3DebugLine.TARGET_FLUID, new TargetInfoProvider("Target Fluid", true));
   }

   public void method1(F3DebugWriter f3display_31, @Nullable F3DisplayModule f3modulechildmod2) {
      this.method3(F3DebugLine.TARGET_BLOCK, f3display_31, f3modulechildmod2);
   }

   public void method2(F3DebugWriter f3display_31, @Nullable F3DisplayModule f3modulechildmod2) {
      this.method3(F3DebugLine.TARGET_FLUID, f3display_31, f3modulechildmod2);
   }

   private void method3(F3DebugLine gui2extension1, F3DebugWriter f3display_32, @Nullable F3DisplayModule f3modulechildmod3) {
      F3DebugEntry f3display$data4 = this.field1.get(gui2extension1);
      if (f3display$data4 != null) {
         if (!f3display$data4.field1) {
            f3display$data4.method1(f3display_32, f3modulechildmod3);
            f3display$data4.field1 = true;
         }

         f3display$data4.method2(f3display_32, f3modulechildmod3);
      }
   }

   public void clear() {
      for (F3DebugEntry f3display$data2 : this.field1.values()) {
         f3display$data2.clear();
         f3display$data2.field1 = false;
      }
   }
}
