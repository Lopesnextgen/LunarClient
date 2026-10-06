package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge11_4;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiDisconnected.class)
public abstract class GuiDisconnectedMixin extends GuiScreen implements GuiScreenBridge, Bridge11_4 {
   @Final
   @Shadow
   public GuiScreen parentScreen;
   @Shadow
   public int textHeight$v1_8;
   @Shadow
   public IChatComponent message$v1_7;
   @Unique
   private static final int lunar$CANCEL_BUTTON_SIZE = 20;
   @Unique
   private static final int lunar$CANCEL_BUTTON_GAP = 4;
   @Unique
   private GuiButton lunar$reconnectButton;
   @Unique
   private GuiButton lunar$cancelButton;
   @Unique
   private boolean lunar$cancelButtonVisible;
   @Unique
   private boolean lunar$reconnectCancelled;

   public GuiDisconnectedMixin() {
   }

   @Inject(method = "initGui", at = @At("TAIL"))
   private void lunar$initGui(CallbackInfo callback1) {
      if (Ref.MC_VERSION != 0
         || this.message$v1_7 == null
         || !this.message$v1_7.getFormattedText().equals("You have died. Game over, man, it's game over!§r")) {
         int number2 = Ref.MC_VERSION >= 1
            ? this.height / 2 + this.textHeight$v1_8 / 2 + this.fontRenderer.FONT_HEIGHT
            : this.height / 4 + 120 + 12;
         List list3 = Ref.MC_VERSION >= 1 ? this.buttonList : this.buttonList$v1_7;
         this.lunar$reconnectButton = new GuiButton(
            1, this.width / 2 - 100, number2 + 25, Ref.method4().method67().method2("gui.components", "reconnect", new Object[0])
         );
         list3.add(this.lunar$reconnectButton);
         this.lunar$cancelButton = new GuiButton(
            2, 0, number2 + 25, 20, 20, Ref.method4().method67().method2("gui.components", "cancelAutoReconnect", new Object[0])
         );
         this.lunar$cancelButton.visible = this.lunar$cancelButtonVisible;
         list3.add(this.lunar$cancelButton);
         this.lunar$placeButtonRow();
      }
   }

   @Unique
   private void lunar$placeButtonRow() {
      if (this.lunar$cancelButton != null && this.lunar$reconnectButton != null) {
         int number1 = this.lunar$reconnectButton.width;
         if (this.lunar$cancelButtonVisible) {
            number1 += 24;
         }

         this.lunar$reconnectButton.xPosition = (this.width - number1) / 2;
         this.lunar$cancelButton.xPosition = this.lunar$reconnectButton.xPosition + this.lunar$reconnectButton.width + 4;
         this.lunar$cancelButton.yPosition = this.lunar$reconnectButton.yPosition;
      }
   }

   @Inject(method = "actionPerformed", at = @At("HEAD"), cancellable = true)
   private void lunar$actionPerformed(GuiButton guibutton1, CallbackInfo callback2) {
      if (guibutton1.id == 0 && this.parentScreen instanceof GuiDisconnected) {
         this.mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
         callback2.cancel();
      } else if (guibutton1.id == 1) {
         this.bridge$reconnect();
      } else if (guibutton1.id == 2) {
         this.lunar$reconnectCancelled = true;
         this.bridge$setCancelButtonVisible(false);
      }
   }

   @Unique
   private ServerData lunar$getServerData() {
      ServerData serverdata1 = this.mc.getCurrentServerData();
      if (serverdata1 == null) {
         serverdata1 = (ServerData)Ref.method3().bridge$lastServerData();
      }

      return serverdata1;
   }

   public boolean bridge$canReconnect() {
      return this.lunar$reconnectButton != null && this.lunar$getServerData() != null;
   }

   public void bridge$reconnect() {
      ServerData serverdata1 = this.lunar$getServerData();
      if (serverdata1 != null) {
         this.mc.displayGuiScreen(new GuiConnecting(this.parentScreen, this.mc, serverdata1));
      }
   }

   public void bridge$setReconnectButtonText(String text1) {
      if (this.lunar$reconnectButton != null) {
         this.lunar$reconnectButton.displayString = text1;
      }
   }

   public void bridge$setCancelButtonVisible(boolean flag1) {
      this.lunar$cancelButtonVisible = flag1;
      if (this.lunar$cancelButton != null) {
         this.lunar$cancelButton.visible = flag1;
      }

      this.lunar$placeButtonRow();
   }

   public boolean bridge$isReconnectCancelled() {
      return this.lunar$reconnectCancelled;
   }

   @Inject(method = "drawScreen", at = @At("HEAD"), cancellable = true)
   private void lunar$drawScreen(int number1, int number2, float value3, CallbackInfo callback4) {
      if (this.fontRenderer == null) {
         callback4.cancel();
      }
   }
}
