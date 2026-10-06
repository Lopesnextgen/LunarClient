package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;

public class Fishing6 {
   private final ToggleOption field1;
   private final ToggleOption field2;
   private final MultiSelectOption field3;

   public Fishing6(ToggleOption var1, ToggleOption option, MultiSelectOption option2) {
      this.field1 = var1;
      this.field2 = option;
      this.field3 = option2;
   }

   public boolean isEnabled() {
      return this.method3().get();
   }

   public boolean method1() {
      return this.method4().get();
   }

   public boolean method2(Gui2Extension var1) {
      return this.method5().contains(var1.getId());
   }

   public ToggleOption method3() {
      return this.field1;
   }

   public ToggleOption method4() {
      return this.field2;
   }

   public MultiSelectOption method5() {
      return this.field3;
   }
}
