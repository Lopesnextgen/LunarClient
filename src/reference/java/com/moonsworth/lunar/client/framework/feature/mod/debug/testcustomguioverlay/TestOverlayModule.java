package com.moonsworth.lunar.client.framework.feature.mod.debug.testcustomguioverlay;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.KeyEventBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.GuiRenderer;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 1)
public class TestOverlayModule extends com.moonsworth.lunar.client.framework.feature.mod.OverlayModule {
   public TestOverlayModule() {
   }

   @Override
   public boolean method1(GuiScreenBridge bridge5extension61) {
      return "testoverlay".equals(this.method11(bridge5extension61));
   }

   @Override
   public void method2(GuiScreenBridge bridge5extension61, MixinHelper_4 mixinhelper_42, int number3, int number4, float value) {
      GuiRenderer.field1.method1("TestOverlay", bridge5extension61, mixinhelper_42, number3, number4, false);
      String text6 = GuiRenderer.field1.method45("TestOverlay-Textinput", 10, 10, 100, 10, "Text box", true);
      if (GuiRenderer.field1.method35("Text: " + text6, 10, 30, 100, 16) != -1) {
         System.out.println("Text button was clicked with '" + text6 + "' as input!");
      }

      GuiRenderer.field1.end();
   }

   @Override
   public boolean method3(GuiScreenBridge bridge5extension61, int number2, int number3, int number4) {
      return GuiRenderer.field1.method27("TestOverlay", number2, number3, number4);
   }

   @Override
   public boolean method4(GuiScreenBridge bridge5extension61, int number2, int number3, int number4) {
      return GuiRenderer.field1.method28("TestOverlay", number2, number3, number4);
   }

   @Override
   public boolean method10(GuiScreenBridge bridge5extension61, KeyEventBridge bridge_72) {
      return GuiRenderer.field1.method29("TestOverlay", bridge_72);
   }

   @Override
   public void onOpen() {
      System.out.println("Opened TestOverlay");
   }

   @Override
   public void onClose() {
      System.out.println("Closed TestOverlay");
      GuiRenderer.field1.method5("TestOverlay");
   }
}
