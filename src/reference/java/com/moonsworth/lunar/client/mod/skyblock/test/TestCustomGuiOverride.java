package com.moonsworth.lunar.client.mod.skyblock.test;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.GuiModuleManager;
import com.moonsworth.lunar.client.framework.feature.mod.debug.override.ModuleBase;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class TestCustomGuiOverride extends AbstractFeature {
   private final GuiModuleManager field8 = (GuiModuleManager)this.method63(GuiModuleManager.class);

   public TestCustomGuiOverride(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      if (Ref.MC_VERSION >= 1) {
         this.field8
            .method2(new ModuleBase(), (ModLifecycle)this.method14(ModTraits.field12, arg0 -> ModLifecycle.method13()));
      }
   }

   public String getId() {
      return "TEST_CUSTOM_GUI_OVERRIDE";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method11(this);
   }
}
