package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiRenameWorld;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenAddServer;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiEditSign;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
public abstract class GuiScreenMixin extends Gui implements GuiScreenBridge {
   @Shadow
   public int width;
   @Shadow
   public int height;
   @Shadow
   public FontRenderer fontRenderer;
   @Shadow
   public Minecraft mc;
   @Shadow
   public boolean allowUserInput;
   @Unique
   private int lunar$inventoryScale = 0;
   @Unique
   private float lunar$inventoryScaleFactor = 1.0F;

   public GuiScreenMixin() {
   }

   @Shadow
   public abstract void drawScreen(int number1, int number2, float value3);

   @Shadow
   public abstract void setWorldAndResolution(Minecraft minecraft1, int number2, int number3);

   @Shadow
   public abstract void updateScreen();

   @Shadow
   public abstract void actionPerformed(GuiButton guibutton1);

   @Shadow
   public abstract void mouseClickMove(int number1, int number2, int number3, long number4);

   @Shadow
   public static boolean isShiftKeyDown() {
      return false;
   }

   @Shadow
   public static boolean isCtrlKeyDown() {
      return false;
   }

   @Shadow
   public abstract void mouseClicked(int number1, int number2, int number3);

   @Shadow
   public abstract void handleMouseInput();

   @Shadow
   public abstract void mouseReleased(int number1, int number2, int number3);

   @Shadow
   public abstract void keyTyped(char character1, int number2);

   @Shadow
   public abstract void initGui();

   @Override
   public void bridge$drawScreen(AbstractRenderContext bridgeextension_91, int number2, int number3, float value4) {
      this.drawScreen(number2, number3, value4);
   }

   @Override
   public void bridge$setWorldAndResolution(int number1, int number2) {
      this.setWorldAndResolution(Minecraft.theMinecraft, number1, number2);
   }

   @Override
   public void bridge$updateScreen() {
      this.updateScreen();
   }

   @Override
   public int bridge$getWidth() {
      return this.width;
   }

   @Override
   public int bridge$getHeight() {
      return this.height;
   }

   @Override
   public boolean bridge$hasTextFieldFocused() {
      if (this instanceof GuiScreenAddServer guiscreenaddserver9) {
         return guiscreenaddserver9.serverNameField.isFocused || guiscreenaddserver9.serverIPField.isFocused;
      } else if (this instanceof GuiCreateWorld guicreateworld8) {
         return guicreateworld8.worldNameField.isFocused;
      } else if (this instanceof GuiRenameWorld guirenameworld7) {
         return guirenameworld7.field_146583_f.isFocused;
      } else if (this instanceof GuiScreenServerList guiscreenserverlist6) {
         return guiscreenserverlist6.field_146302_g.isFocused;
      } else if (this instanceof GuiContainerCreative guicontainercreative5) {
         return guicontainercreative5.searchField.isFocused;
      } else if (this instanceof GuiEditSign || this instanceof GuiScreenBook guiscreenbook1 && guiscreenbook1.bookIsUnsigned) {
         return true;
      } else if (this instanceof GuiCommandBlock guicommandblock4) {
         return guicommandblock4.commandTextField.isFocused;
      } else {
         return this instanceof GuiRepair guirepair3 ? guirepair3.nameField.isFocused : false;
      }
   }

   @Inject(method = {"mouseClicked", "mouseReleased", "mouseClickMove"}, at = @At("HEAD"), cancellable = true, require = 3, expect = 3)
   private void lunar$webosr$disableMouseInputs(CallbackInfo callback1) {
      if (DriverViewportLegacy.method50() != null && DriverViewportLegacy.method50().method40() && this.mc != null && this == this.mc.currentScreen) {
         callback1.cancel();
      }
   }

   @Override
   public void bridge$mouseClicked(int number1, int number2, int number3) {
      this.mouseClicked(number1, number2, number3);
   }

   @Override
   public void bridge$mouseReleased(int number1, int number2, int number3) {
      this.mouseReleased(number1, number2, number3);
   }

   @Override
   public void bridge$mouseClickMove(int number1, int number2, int number3, long number4, double value6, double value8) {
      this.mouseClickMove(number1, number2, number3, number4);
   }

   @Override
   public boolean bridge$isShiftKeyDown() {
      return isShiftKeyDown();
   }

   @Override
   public boolean bridge$isCtrlKeyDown() {
      return isCtrlKeyDown();
   }

   @Override
   public void bridge$mouseScrolled(int number1, int number2, double value3, double value5) {
      Bridge.method20().method2(number1, number2, (int)value5);
   }

   @Override
   public void bridge$keyTyped(char character1, int number2, int number3) {
      this.keyTyped(character1, number2);
   }

   @Override
   public boolean bridge$allowUserInput() {
      return this.allowUserInput;
   }

   @Override
   public void bridge$setAllowUserInput(boolean flag1) {
      this.allowUserInput = flag1;
   }

   @Override
   public int bridge$getInventoryScale() {
      return this.lunar$inventoryScale;
   }

   @Override
   public void bridge$setInventoryScale(int number1) {
      this.lunar$inventoryScale = number1;
   }

   @Override
   public float bridge$getInventoryScaleFactor() {
      return this.lunar$inventoryScaleFactor;
   }

   @Override
   public void bridge$setInventoryScaleFactor(float value1) {
      this.lunar$inventoryScaleFactor = value1;
   }
}
