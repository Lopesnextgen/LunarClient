package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.screen.EventScreenClose;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import java.util.Optional;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

public class GuiScreenImpl extends GuiScreen implements Bridge5Extension62 {
   private final Bridge7_8 field1;

   public void bridge$drawScreen(AbstractRenderContext bridgeextension_91, int number2, int number3, float value4) {
      this.drawScreen(number2, number3, value4);
   }

   public void bridge$setWorldAndResolution(int number1, int number2) {
      this.setWorldAndResolution(Minecraft.theMinecraft, number1, number2);
   }

   public void bridge$updateScreen() {
      this.updateScreen();
   }

   public int bridge$getWidth() {
      return this.width;
   }

   public int bridge$getHeight() {
      return this.height;
   }

   public boolean bridge$hasTextFieldFocused() {
      return this.field1 instanceof LcuiScreen bridge7iterator1 && bridge7iterator1.method143();
   }

   public void drawScreen(int number1, int number2, float value3) {
      this.field1.method1(new com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge(AbstractRenderContext.method32()), new Data4(number1, number2), value3);
   }

   public void setWorldAndResolution(Minecraft minecraft1, int number2, int number3) {
      super.setWorldAndResolution(minecraft1, number2, number3);
      this.field1.method2((MinecraftBridge)minecraft1, new Data4(number2, number3));
   }

   public void mouseClicked(int number1, int number2, int number3) {
      this.field1.method3(new Data4(number1, number2), number3);
   }

   public void mouseReleased(int number1, int number2, int number3) {
      this.field1.method4(new Data4(number1, number2), number3);
   }

   public void onGuiClosed() {
      this.field1.onGuiClosed();
   }

   public void updateScreen() {
      this.field1.updateScreen();
   }

   public void keyTyped(char character1, int number2) {
      if (number2 == 1) {
         EventScreenClose highlightimpl113 = (EventScreenClose)LunarEventBus.method29().method12(EventScreenClose.class, () -> new EventScreenClose(this.field1));
         if ((highlightimpl113 == null || !highlightimpl113.isCancelled()) && this.field1.shouldCloseOnEsc()) {
            super.keyTyped(character1, number2);
         }
      } else {
         super.keyTyped(character1, number2);
      }

      this.field1.method5(character1, com.moonsworth.lunar.legacy.KeyboardBridgeImpl.method7(number2));
   }

   public void handleMouseInput() {
      super.handleMouseInput();
      int number1 = Mouse.getEventX() * this.width / this.mc.displayWidth;
      int number2 = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
      this.field1.method6(Mouse.getEventDWheel(), new Data4(number1, number2));
   }

   public Optional<String> method1() {
      return this.field1.method8();
   }

   public boolean doesGuiPauseGame() {
      return this.field1.doesGuiPauseGame();
   }

   public void bridge$mouseClicked(int number1, int number2, int number3) {
      this.mouseClicked(number1, number2, number3);
   }

   public void bridge$mouseReleased(int number1, int number2, int number3) {
      this.mouseReleased(number1, number2, number3);
   }

   public void bridge$mouseClickMove(int number1, int number2, int number3, long number4, double value6, double value8) {
      this.mouseClickMove(number1, number2, number3, number4);
   }

   public boolean bridge$isShiftKeyDown() {
      return isShiftKeyDown();
   }

   public boolean bridge$isCtrlKeyDown() {
      return isCtrlKeyDown();
   }

   public void bridge$mouseScrolled(int number1, int number2, double value3, double value5) {
      Bridge.method20().method2(number1, number2, (int)value5);
      this.handleMouseInput();
   }

   public void bridge$keyTyped(char character1, int number2, int number3) {
      this.keyTyped(character1, number2);
   }

   public boolean bridge$allowUserInput() {
      return this.allowUserInput;
   }

   public void bridge$setAllowUserInput(boolean flag1) {
      this.allowUserInput = flag1;
   }

   @Generated
   public Bridge7_8 method2() {
      return this.field1;
   }

   @Generated
   public GuiScreenImpl(Bridge7_8 bridge7_81) {
      this.field1 = bridge7_81;
   }
}
