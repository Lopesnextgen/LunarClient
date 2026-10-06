package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.KeyEventBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import lombok.Generated;

abstract class GuiModule {
   protected int activeTicks = 0;

   public abstract boolean method1(GuiScreenBridge bridge5extension61);

   public abstract void method2(GuiScreenBridge bridge5extension61, MixinHelper_4 mixinhelper_42, int number3, int number4, float value5);

   public boolean method3(GuiScreenBridge bridge5extension61, int number2, int number3, int number4) {
      return false;
   }

   public boolean method4(GuiScreenBridge bridge5extension61, int number2, int number3, int number4) {
      return false;
   }

   public boolean method5(GuiScreenBridge bridge5extension61, double value) {
      return false;
   }

   public boolean method6(GuiScreenBridge bridge5extension61, int number2, int number3, int number4, long value) {
      return false;
   }

   public void method7() {
      this.activeTicks++;
   }

   public void method8() {
   }

   public void onOpen() {
   }

   public void onClose() {
   }

   public boolean method10(GuiScreenBridge bridge5extension61, KeyEventBridge bridge_72) {
      return false;
   }

   protected String method11(GuiScreenBridge bridge5extension61) {
      return bridge5extension61 instanceof GuiContainerBridge bridge5extension_32 ? TextBridge.getTextContent(TextBridge.asAdventure(bridge5extension_32.bridge$title())) : null;
   }

   @Generated
   protected GuiModule() {
   }
}
