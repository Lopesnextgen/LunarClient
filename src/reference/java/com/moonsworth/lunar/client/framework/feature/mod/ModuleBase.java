package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import lombok.Generated;

public abstract class ModuleBase extends GuiModule {
   public abstract boolean method2(GuiScreenBridge bridge5extension61);

   @Override
   public boolean method1(GuiScreenBridge bridge5extension61) {
      return this.method2(bridge5extension61);
   }

   public boolean method3() {
      return true;
   }

   public boolean method4() {
      return true;
   }

   public boolean method5() {
      return false;
   }

   protected void method6(MixinHelper_4 mixinhelper_41) {
      mixinhelper_41.method1(0, 0, LcuiScreen.method151().getScaledWidth(), LcuiScreen.method151().getScaledHeight(), -804253680);
   }

   @Generated
   protected ModuleBase() {
   }
}
