package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension;
import com.moonsworth.lunar.bridge.Bridge5Extension4;
import com.moonsworth.lunar.bridge.Bridge5Extension9;
import com.moonsworth.lunar.client.mod.hud.tab.Tab;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import net.kyori.adventure.util.Ticks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.scoreboard.ScoreObjective;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public abstract class GuiIngameBridgeMixin implements Bridge5Extension9 {
   @Final
   @Shadow
   public Minecraft mc;
   @Shadow
   public String overlayMessage;
   @Shadow
   public int overlayMessageTime;
   @Shadow
   public boolean animateOverlayMessageColor;
   @Shadow
   public int titlesTimer;
   @Shadow
   public String displayedTitle;
   @Shadow
   public String displayedSubTitle;
   @Shadow
   public int titleFadeIn;
   @Shadow
   public int titleDisplayTime;
   @Shadow
   public int titleFadeOut;
   @Annotation2(min = 1)
   @Unique
   private float bridge$scale;
   @Annotation2(min = 1)
   @Unique
   private float bridge$interpolationScale;
   @Annotation2(min = 1)
   @Unique
   private float bridge$interpolationRate;

   @Shadow
   public abstract GuiNewChat getChatGUI();

   @Shadow
   public abstract int getUpdateCounter();

   @Shadow
   public abstract void renderGameOverlay(float var1);

   @Shadow
   public abstract void renderGameOverlay(float var1, boolean var2, int var3, int var4);

   @Shadow
   public abstract void displayTitle(String var1, String var2, int var3, int var4, int var5);

   @Shadow
   public abstract GuiPlayerTabOverlay getTabList();

   @Override
   public Bridge5Extension4 bridge$getChatGUI() {
      return (Bridge5Extension4)this.getChatGUI();
   }

   @Override
   public void bridge$renderGameOverlay(float var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.renderGameOverlay(var1);
      } else {
         this.renderGameOverlay(var1, true, 0, 0);
      }
   }

   @Override
   public boolean bridge$showCrosshair() {
      return ThreadModuleDump63.MC_VERSION >= 1 && this.mc.playerController.isSpectator() || this.mc.pointedEntity == null;
   }

   @Annotation2(min = 1)
   @Override
   public void bridge$displayTitle(@Nullable Component var1, @Nullable Component var2, Times var3, float var4, float var5, float var6) {
      if (var1 != null || var2 != null) {
         if (var1 != null) {
            this.displayedTitle = AdventureTextBridge.asLegacyString(var1);
         }

         if (var2 != null) {
            this.displayedSubTitle = AdventureTextBridge.asLegacyString(var2);
         }

         this.titleFadeIn = (int)(var3.fadeIn().toMillis() / 50L);
         this.titleDisplayTime = (int)(var3.stay().toMillis() / 50L);
         this.titleFadeOut = (int)(var3.fadeOut().toMillis() / 50L);
         this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
         this.bridge$scale = var4;
         this.bridge$interpolationScale = var5;
         this.bridge$interpolationRate = var6;
      }
   }

   @Annotation2(min = 1)
   @Override
   public void bridge$clearTitle() {
      this.titleFadeIn = 10;
      this.titleDisplayTime = 70;
      this.titleFadeOut = 20;
      this.titlesTimer = 0;
      this.displayedTitle = null;
      this.displayedSubTitle = null;
      this.bridge$scale = 1.0F;
      this.bridge$interpolationRate = 0.0F;
      this.bridge$interpolationScale = 0.0F;
   }

   @Annotation2(min = 1)
   @Override
   public Title bridge$getTitle() {
      if (this.displayedTitle == null && this.displayedSubTitle == null) {
         return null;
      }

      String var1 = Objects.requireNonNullElse(this.displayedTitle, "");
      String var2 = Objects.requireNonNullElse(this.displayedSubTitle, "");
      return Title.title(
         Component.text(var1),
         Component.text(var2),
         Times.times(Ticks.duration(this.titleFadeIn), Ticks.duration(this.titleDisplayTime), Ticks.duration(this.titleFadeOut))
      );
   }

   @Annotation2(min = 1)
   @Override
   public int bridge$titlesTimer() {
      return this.titlesTimer;
   }

   @Annotation2(min = 1)
   @Override
   public void bridge$interpolateTitle() {
      if (this.bridge$interpolationScale != 0.0F && this.bridge$interpolationRate != 0.0F) {
         if (!(this.bridge$scale > 1.0F) && !(this.bridge$scale < 0.0F)) {
            if (this.bridge$interpolationScale > this.bridge$scale) {
               this.bridge$scale = Math.min(this.bridge$interpolationScale, this.bridge$scale + this.bridge$interpolationRate);
            } else {
               this.bridge$scale = Math.max(this.bridge$interpolationScale, this.bridge$scale - this.bridge$interpolationRate);
            }
         }
      }
   }

   @Annotation2(min = 1)
   @Override
   public float bridge$getTitleScale() {
      return this.bridge$scale;
   }

   @Override
   public Component bridge$getOverlayMessage() {
      return this.overlayMessage == null ? null : Component.text(this.overlayMessage);
   }

   @Override
   public int bridge$getOverlayMessageTime() {
      return this.overlayMessageTime;
   }

   @Override
   public boolean bridge$isAnimateOverlayMessageColor() {
      return this.animateOverlayMessageColor;
   }

   @Override
   public int bridge$getTicks() {
      return this.getUpdateCounter();
   }

   @Override
   public List<Bridge2_33> bridge$getPlayerInfoList() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         EntityPlayerSP var3 = this.mc.thePlayer;
         if (var3 == null) {
            return List.of();
         }

         NetHandlerPlayClient var4 = this.mc.thePlayer.sendQueue;
         return var4 == null ? List.of() : GuiPlayerTabOverlay.field_175252_a.sortedCopy(var4.getPlayerInfoMap());
      } else {
         EntityClientPlayerMP var1 = this.mc.thePlayer$v1_7;
         if (var1 == null) {
            return List.of();
         }

         NetHandlerPlayClient var2 = this.mc.thePlayer$v1_7.sendQueue;
         return var2 == null ? List.of() : var2.playerInfoList$v1_7;
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "updateTick", at = @At("HEAD"))
   private void lunar$interpolateTitle(CallbackInfo var1) {
      this.bridge$interpolateTitle();
   }

   @Annotation2(min = 1)
   @Inject(
      method = "updateTick",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiIngame;displayedSubTitle$v1_8:Ljava/lang/String;", opcode = 181, shift = Shift.AFTER)
   )
   private void lunar$resetScale(CallbackInfo var1) {
      this.bridge$scale = 1.0F;
      this.bridge$interpolationRate = 0.0F;
      this.bridge$interpolationScale = 0.0F;
   }

   @Override
   public boolean bridge$isTabVisible() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return this.getTabList().isBeingRendered;
      }

      Tab var1 = ThreadModuleDump63.method4().method40().method48();
      if (var1.isEnabled() && var1.isActive()) {
         return true;
      }

      ScoreObjective var2 = this.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(0);
      return this.mc.gameSettings.keyBindPlayerList$v1_7.getIsKeyPressed()
         && (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer$v1_7.sendQueue.playerInfoList$v1_7.size() > 1 || var2 != null);
   }

   @Override
   public Bridge5Extension bridge$getTabList() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return (Bridge5Extension)this.getTabList();
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }
}
