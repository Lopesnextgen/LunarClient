package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import java.util.EnumMap;
import org.jetbrains.annotations.Nullable;

public class F3display_2 {
   private final EnumMap<Gui2Extension, F3display$Data> field1 = new EnumMap<>(Gui2Extension.class);

   public F3display_2() {
      this.field1.put(Gui2Extension.TARGET_BLOCK, new F3display2_2("Target Block", false));
      this.field1.put(Gui2Extension.TARGET_FLUID, new F3display2_2("Target Fluid", true));
   }

   public void method1(F3display_3 var1, @Nullable F3DisplayModule var2) {
      this.method3(Gui2Extension.TARGET_BLOCK, var1, var2);
   }

   public void method2(F3display_3 var1, @Nullable F3DisplayModule var2) {
      this.method3(Gui2Extension.TARGET_FLUID, var1, var2);
   }

   private void method3(Gui2Extension var1, F3display_3 var2, @Nullable F3DisplayModule var3) {
      F3display$Data var4 = this.field1.get(var1);
      if (var4 != null) {
         if (!var4.field1) {
            var4.method1(var2, var3);
            var4.field1 = true;
         }

         var4.method2(var2, var3);
      }
   }

   public void clear() {
      for (F3display$Data var2 : this.field1.values()) {
         var2.clear();
         var2.field1 = false;
      }
   }
}
