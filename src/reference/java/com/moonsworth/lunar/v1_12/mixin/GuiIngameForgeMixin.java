package com.moonsworth.lunar.v1_12.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType3;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.PlayerStatsRenderEvent;
import com.moonsworth.lunar.client.event.OutcomeEvent;
import com.moonsworth.lunar.client.event.render.RenderScaleEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.BossBarRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ChatRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ArmorRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderTabListLegacy;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.hud.tab.Tab;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.render.armorstatus.ArmorstatusBars;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.legacy.mixin.GuiIngameBridgeMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiBossOverlay;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiIngameForge.class)
public abstract class GuiIngameForgeMixin extends GuiIngameBridgeMixin {
   @Shadow
   public RenderGameOverlayEvent eventParent;
   @Unique
   private boolean lunar$foodRendered;

   @Inject(
      method = "renderGameOverlay(F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/GuiIngameForge;renderRecordOverlay(IIF)V"),
      require = 1
   )
   private void lunar$renderGameOverlay$forge(float var1, CallbackInfo var2) {
      ScaledResolution var3 = new ScaledResolution(Minecraft.getMinecraft());
      BridgeExtension3_5 var4 = AbstractRenderContext.method32();
      if (!Minecraft.getMinecraft().gameSettings.showDebugInfo) {
         ClientEventBus.method29()
            .method12(
               HudBaseRenderEvent.class,
               () -> new HudBaseRenderEvent(var4, new LegacyGuiGraphicsBridge(var4), new MarkerModel.Data4(var3.getScaledWidth_double(), var3.getScaledHeight_double()))
            );
      }

      var4.push();
      float var5 = LcuiScreen.getScale();
      var4.method29(var5, var5);
      ClientEventBus.method29()
         .method12(
            EventRenderHudLegacy.class,
            () -> new EventRenderHudLegacy(var4, new LegacyGuiGraphicsBridge(var4), new MarkerModel.Data2(var3.getScaledWidth_double() / var5, var3.getScaledHeight_double() / var5))
         );
      var4.pop();
   }

   @Inject(method = "renderGameOverlay(F)V", at = @At("HEAD"), cancellable = true)
   private void lunar$preventHud(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method56().method25()) {
         var1.cancel();
      }
   }

   @Redirect(method = "renderChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawChat(I)V"))
   private void lunar$drawChat(GuiNewChat var1, int var2) {
      ChatRenderEvent var3 = ClientEventBus.method29().method12(ChatRenderEvent.class, () -> new ChatRenderEvent(var2));
      if (var3 == null || var3.method1() != OutcomeEvent.Type.DENY) {
         var1.drawChat(var2);
      }
   }

   @Redirect(method = "renderHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/storage/WorldInfo;isHardcoreModeEnabled()Z"))
   private boolean lunar$renderPlayerStats_isHardcoreModeEnabled(WorldInfo var1) {
      return var1.isHardcoreModeEnabled() || ThreadModuleDump63.method4().method40().method67().method13();
   }

   @Redirect(
      method = "renderPlayerList(II)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;renderPlayerlist(ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreObjective;)V"
      )
   )
   private void lunar$onRenderPlayerList(GuiPlayerTabOverlay var1, int var2, Scoreboard var3, ScoreObjective var4) {
      EventRenderTabListLegacy var5 = ClientEventBus.method29().method12(EventRenderTabListLegacy.class, EventRenderTabListLegacy::new);
      if (var5 == null || !var5.isCancelled()) {
         var1.renderPlayerlist(var2, var3, var4);
      }
   }

   @Redirect(method = "renderTitle", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/GuiIngameForge;titlesTimer:I", opcode = 180, ordinal = 0))
   private int lunar$renderGameOverlay$title(GuiIngameForge var1) {
      return 0;
   }

   @ModifyExpressionValue(method = "renderPlayerList", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
   private boolean lunar$isTabKeyDown(boolean var1) {
      Rewind var2 = ThreadModuleDump63.method4().method40().method85();
      if (var2.method19()) {
         return var2.method35().method42().get().method15().getOrDefault(GuiType3.TAB, false);
      }

      Tab var3 = ThreadModuleDump63.method4().method40().method48();
      return var3.isEnabled() ? var3.isActive() : var1;
   }

   @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
   public void lunar$disableRenderTooltip(ScaledResolution var1, float var2, CallbackInfo var3) {
      if (FramebufferCaptureTask.method6()) {
         var3.cancel();
      } else if (this.eventParent == null) {
         this.eventParent = new RenderGameOverlayEvent(var2, var1);
      }
   }

   @Inject(method = "pre", at = @At("HEAD"), cancellable = true)
   public void lunar$checkValidEvent$pre(ElementType var1, CallbackInfoReturnable<Boolean> var2) {
      if (this.eventParent == null) {
         var2.setReturnValue(false);
      }
   }

   @Inject(method = "post", at = @At("HEAD"), cancellable = true)
   public void lunar$checkValidEvent$post(ElementType var1, CallbackInfo var2) {
      if (this.eventParent == null) {
         var2.cancel();
      }
   }

   @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
   private void lunar$onRenderFood(int var1, int var2, CallbackInfo var3) {
      com.moonsworth.lunar.client.event.mixin.highlight.VanillaHudRenderEvent var4 = ClientEventBus.method29()
         .method12(
            com.moonsworth.lunar.client.event.mixin.highlight.VanillaHudRenderEvent.class,
            com.moonsworth.lunar.client.event.mixin.highlight.VanillaHudRenderEvent::new
         );
      if (var4 != null && var4.isCancelled()) {
         var3.cancel();
      }
   }

   @Inject(method = "renderFood", at = @At("TAIL"))
   private void lunar$trackFoodRendered(int var1, int var2, CallbackInfo var3) {
      this.lunar$foodRendered = true;
   }

   @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
   private void lunar$onDrawArmor(int var1, int var2, CallbackInfo var3) {
      ArmorRenderEvent var4 = ClientEventBus.method29().method12(ArmorRenderEvent.class, ArmorRenderEvent::new);
      if (var4 != null && var4.isCancelled()) {
         var3.cancel();
      }
   }

   @WrapOperation(method = "renderArmor", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/GuiIngameForge;drawTexturedModalRect(IIIIII)V"))
   private void lunar$drawArmorBarIcons(
      GuiIngameForge var1, int var2, int var3, int var4, int var5, int var6, int var7, Operation<Void> var8, @Local(index = 6) int var9
   ) {
      ArmorstatusBars var10 = ArmorstatusBars.method14();
      int var11 = var9 / 2;
      if (var10 == null || !var10.method2(new LegacyGuiGraphicsBridge(AbstractRenderContext.method32()), var11, var2, var3)) {
         var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
      }
   }

   @Inject(method = "renderArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;disableBlend()V"))
   private void lunar$restoreArmorBarsState(int var1, int var2, CallbackInfo var3) {
      if (ArmorstatusBars.method14() != null) {
         RenderSystemBridge var4 = Bridge.method42();
         var4.method34();
         var4.method36();
         this.mc.getTextureManager().bindTexture(Gui.icons);
      }
   }

   @WrapWithCondition(method = "renderBossHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiBossOverlay;renderBossHealth()V"))
   private boolean lunar$onRenderBossHealth(GuiBossOverlay var1) {
      BossBarRenderEvent var2 = ClientEventBus.method29().method12(BossBarRenderEvent.class, BossBarRenderEvent::new);
      return var2 == null || !var2.isCancelled();
   }

   @Inject(method = "renderGameOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/GuiIngameForge;renderSleepFade(II)V"))
   private void lunar$renderHealthBarEvent(float var1, CallbackInfo var2) {
      boolean var3 = this.lunar$foodRendered;
      this.lunar$foodRendered = false;
      if (!ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method45().method15().isFixedToPlayer())) {
         if (this.mc.playerController.shouldDrawHUD() && this.mc.getRenderViewEntity() instanceof EntityPlayer) {
            ClientEventBus.method29()
               .method12(
                  PlayerStatsRenderEvent.class,
                  () -> {
                     ScaledResolution var2x = new ScaledResolution(this.mc);
                     BridgeExtension3_5 var3x = AbstractRenderContext.method32();
                     return new PlayerStatsRenderEvent(
                        var3x, new LegacyGuiGraphicsBridge(var3x), new MarkerModel.Data4(var2x.getScaledWidth_double(), var2x.getScaledHeight_double()), var3
                     );
                  }
               );
         }
      }
   }

   @Inject(
      method = {"renderHealth", "renderArmor", "renderFood", "renderHealthMount", "renderAir", "renderExperience", "renderToolHighlight"},
      at = @At("HEAD"),
      cancellable = true
   )
   private void lunar$rewindDisableHotbar(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method53().method21())) {
         var1.cancel();
      }
   }

   @Inject(method = "renderRecordOverlay", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindDisableOverlayMessage(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method53().method22())) {
         var1.cancel();
      }
   }

   @Inject(method = "renderRecordOverlay", at = @At("HEAD"), cancellable = true)
   private void lunar$actionBarDisableOverlayMessage(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method90().isEnabled()) {
         var1.cancel();
      }
   }

   @Inject(method = "pre", at = @At("HEAD"), cancellable = true)
   private void lunar$renderDebugInfo(ElementType var1, CallbackInfoReturnable<Boolean> var2) {
      if (var1 == ElementType.DEBUG && ThreadModuleDump63.method4().method40().method95().isEnabled()) {
         var2.setReturnValue(true);
      }
   }

   @WrapOperation(
      method = "renderHotbar",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiSpectator;renderTooltip(Lnet/minecraft/client/gui/ScaledResolution;F)V")
   )
   private void lunar$scaleHotbar$spectator(GuiSpectator var1, ScaledResolution var2, float var3, Operation<Void> var4) {
      MutableBoolean var5 = new MutableBoolean();
      var2 = this.lunar$scaleHotbar$pre(var2, var5);
      var4.call(new Object[]{var1, var2, var3});
      if (var5.getValue()) {
         this.lunar$scaleHotbar$post();
      }
   }

   @Unique
   private ScaledResolution lunar$scaleHotbar$pre(ScaledResolution var1, MutableBoolean var2) {
      RenderScaleEvent var3 = ClientEventBus.method29().method12(RenderScaleEvent.class, RenderScaleEvent::new);
      float var4 = var3 == null ? 1.0F : var3.getScale();
      if (var4 == 1.0F) {
         return var1;
      }

      var2.setValue(true);
      Bridge.method42().method4();
      Bridge.method42().bridge$scale(var4, var4, 1.0F);
      ScaledResolution var5 = new ScaledResolution(this.mc);
      var5.scaledWidth = Math.round(var5.scaledWidth / var4);
      var5.scaledHeight = Math.round(var5.scaledHeight / var4);
      LcuiScreen.method150(
         new ThreadModuleDump71(
            var5.scaleFactor, var5.scaledWidth * var5.scaleFactor, var5.scaledHeight * var5.scaleFactor, var5.scaledWidth, var5.scaledHeight
         )
      );
      return var5;
   }

   @ModifyVariable(
      method = {"renderJumpBar", "renderExperience", "renderFood", "renderHealth", "renderAir", "renderHealthMount", "renderArmor"},
      at = @At("HEAD"),
      argsOnly = true,
      ordinal = 0
   )
   private int lunar$scaleHotbar$pre$width(int var1) {
      RenderScaleEvent var2 = ClientEventBus.method29().method12(RenderScaleEvent.class, RenderScaleEvent::new);
      float var3 = var2 == null ? 1.0F : var2.getScale();
      return (int)(var1 / var3);
   }

   @ModifyVariable(
      method = {"renderJumpBar", "renderExperience", "renderFood", "renderHealth", "renderAir", "renderHealthMount", "renderArmor"},
      at = @At("HEAD"),
      argsOnly = true,
      ordinal = 1
   )
   private int lunar$scaleHotbar$pre$height(int var1) {
      RenderScaleEvent var2 = ClientEventBus.method29().method12(RenderScaleEvent.class, RenderScaleEvent::new);
      float var3 = var2 == null ? 1.0F : var2.getScale();
      return (int)(var1 / var3);
   }

   @Inject(
      method = "renderGameOverlay",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;shouldDrawHUD()Z"),
            @At(value = "INVOKE", target = "Lnet/minecraftforge/client/GuiIngameForge;renderSleepFade(II)V", shift = Shift.AFTER)
      }
   )
   private void lunar$scaleHotbar$pre$overlay(CallbackInfo var1, @Share("scaled") LocalBooleanRef var2) {
      MutableBoolean var3 = new MutableBoolean();
      this.lunar$scaleHotbar$pre(new ScaledResolution(this.mc), var3);
      var2.set(var3.getValue());
   }

   @Inject(
      method = "renderGameOverlay",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraftforge/client/GuiIngameForge;renderSleepFade(II)V"),
            @At(value = "INVOKE", target = "Lnet/minecraftforge/client/GuiIngameForge;renderToolHighlight(Lnet/minecraft/client/gui/ScaledResolution;)V")
      }
   )
   private void lunar$scaleHotbar$post$overlay(CallbackInfo var1, @Share("scaled") LocalBooleanRef var2) {
      if (var2.get()) {
         this.lunar$scaleHotbar$post();
      }
   }

   @ModifyVariable(method = "renderToolHighlight", at = @At("HEAD"), argsOnly = true, ordinal = 0)
   @Dynamic
   private ScaledResolution lunar$scaleHotbar$pre$resolution(ScaledResolution var1, @Share("scaled") LocalBooleanRef var2) {
      MutableBoolean var3 = new MutableBoolean();
      var1 = this.lunar$scaleHotbar$pre(var1, var3);
      var2.set(var3.getValue());
      return var1;
   }

   @Inject(method = "renderToolHighlight", at = @At("RETURN"))
   @Dynamic
   private void lunar$scaleHotbar$post$resolution(CallbackInfo var1, @Share("scaled") LocalBooleanRef var2) {
      if (var2.get()) {
         this.lunar$scaleHotbar$post();
      }
   }

   @Unique
   private void lunar$scaleHotbar$post() {
      Bridge.method42().method5();
      LcuiScreen.method150(new ThreadModuleDump71((Bridge5_12)this.mc));
   }
}
