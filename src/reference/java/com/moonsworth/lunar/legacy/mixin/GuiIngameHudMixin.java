package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType3;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.PlayerStatsRenderEvent;
import com.moonsworth.lunar.client.event.OutcomeEvent;
import com.moonsworth.lunar.client.event.render.RenderScaleEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.InventoryScreenRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.BossBarRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.CrosshairRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ChatRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.HotbarRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ArmorRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.hud.tab.Tab;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.render.armorstatus.ArmorstatusBars;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.mod.render.potioneffects.PotionEffects;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.TextColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiBossOverlay;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.settings.KeyBinding_v1_7;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GuiIngame.class, priority = 100)
public abstract class GuiIngameHudMixin {
   @Final
   @Shadow
   public Minecraft mc;
   @Final
   @Shadow
   public GuiNewChat persistantChatGUI;
   @Shadow
   public int lastPlayerHealth;
   @Unique
   private boolean lunar$foodRendered;

   @WrapOperation(
      method = {"renderPumpkinBlur$v1_7", "renderPumpkinOverlay$v1_8"},
      at = {
            @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V", ordinal = 0),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V", ordinal = 0)
      }
   )
   private void lunar$renderPumpkinOverlay(float var1, float var2, float var3, float var4, Operation<Void> var5) {
      OverlayMod var6 = ThreadModuleDump63.method4().method40().method84();
      if (var6.isEnabled()) {
         var4 = var6.getPumpkinOverlayOpacity().get();
      }

      var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Annotation2(max = 0)
   @Inject(method = "renderBossHealth$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$renderBossHealth$v1_7(CallbackInfo var1) {
      BossBarRenderEvent var2 = ClientEventBus.method29().method12(BossBarRenderEvent.class, BossBarRenderEvent::new);
      if (var2 != null && var2.isCancelled()) {
         var1.cancel();
      }
   }

   @Inject(method = {"renderScoreboard$v1_7", "renderScoreboard$v1_8"}, at = @At("HEAD"), cancellable = true)
   private void lunar$renderScoreboard$v1_7(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method53().method17())) {
         var1.cancel();
      }

      if (ThreadModuleDump63.method4().method40().method15().isEnabled()) {
         var1.cancel();
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "renderGameOverlay$v1_7", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_7;getIsKeyPressed()Z"))
   public boolean lunar$cancelTabKeybindAndRender(KeyBinding_v1_7 var1) {
      if (var1 != this.mc.gameSettings.keyBindPlayerList$v1_7) {
         return var1.getIsKeyPressed();
      }

      Rewind var2 = ThreadModuleDump63.method4().method40().method85();
      if (var2.method19()) {
         if (var2.method35().method42().get().method15().getOrDefault(GuiType3.TAB, false)) {
            this.lunar$handlePlayerListRendering();
         }

         return false;
      } else {
         Tab var3 = ThreadModuleDump63.method4().method40().method48();
         if (var3.isEnabled() ? !var3.isActive() : !var1.getIsKeyPressed()) {
            return var1.getIsKeyPressed();
         }

         this.lunar$handlePlayerListRendering();
         return false;
      }
   }

   @Unique
   private void lunar$runEvent2D() {
      ScaledResolution var1 = this.lunar$newScaledResolution();
      BridgeExtension3_5 var2 = AbstractRenderContext.method32();
      if (!Minecraft.getMinecraft().gameSettings.showDebugInfo) {
         ClientEventBus.method29()
            .method12(
               HudBaseRenderEvent.class,
               () -> new HudBaseRenderEvent(var2, new LegacyGuiGraphicsBridge(var2), new MarkerModel.Data4(var1.getScaledWidth_double(), var1.getScaledHeight_double()))
            );
      }

      var2.push();
      float var3 = LcuiScreen.getScale();
      var2.method29(var3, var3);
      ClientEventBus.method29()
         .method12(
            EventRenderHudLegacy.class,
            () -> new EventRenderHudLegacy(var2, new LegacyGuiGraphicsBridge(var2), new MarkerModel.Data2(var1.getScaledWidth_double() / var3, var1.getScaledHeight_double() / var3))
         );
      var2.pop();
   }

   @Annotation2(max = 0)
   @Inject(
      method = "renderGameOverlay$v1_7(FZII)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiIngame;overlayMessageTime:I", opcode = 180, ordinal = 0),
      require = 1
   )
   private void lunar$Event2D(float var1, boolean var2, int var3, int var4, CallbackInfo var5) {
      this.lunar$runEvent2D();
   }

   @Annotation2(min = 1)
   @Inject(
      method = "renderGameOverlay$v1_8(F)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiIngame;overlayMessageTime:I", opcode = 180, ordinal = 0),
      require = 1
   )
   private void lunar$Event2D(float var1, CallbackInfo var2) {
      this.lunar$runEvent2D();
   }

   @ModifyExpressionValue(
      method = {"renderGameOverlay$v1_7(FZII)V", "renderGameOverlay$v1_8(F)V"},
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiIngame;overlayMessageTime:I", opcode = 180, ordinal = 1)
   )
   private int lunar$renderActionBar(int var1) {
      return ThreadModuleDump63.method4().method40().method90().isEnabled() ? 0 : var1;
   }

   @Annotation2(1)
   @Inject(method = "renderTooltip$v1_8", at = @At("HEAD"))
   private void lunar$depthOnTooltipRender(CallbackInfo var1) {
      GlStateManager.enableDepth();
   }

   @Annotation2(min = 5)
   @Inject(method = "renderHotbar$v1_12", at = @At("HEAD"))
   private void lunar$depthOnHotbarRender(CallbackInfo var1) {
      GlStateManager.enableDepth();
   }

   @Annotation2(1)
   @Inject(method = "renderTooltip$v1_8", at = @At("HEAD"), cancellable = true)
   public void lunar$renderHotbar(ScaledResolution var1, float var2, CallbackInfo var3) {
      HotbarRenderEvent var4 = ClientEventBus.method29().method12(HotbarRenderEvent.class, HotbarRenderEvent::new);
      if (var4 != null && var4.method1() == OutcomeEvent.Type.DENY) {
         var3.cancel();
      }
   }

   @Annotation2(min = 1)
   @Redirect(method = "renderGameOverlay$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawChat(I)V"))
   private void lunar$textCheckEvent(GuiNewChat var1, int var2) {
      ChatRenderEvent var3 = ClientEventBus.method29().method12(ChatRenderEvent.class, () -> new ChatRenderEvent(var2));
      if (var3 == null || var3.method1() != OutcomeEvent.Type.DENY) {
         this.persistantChatGUI.drawChat(var2);
      }
   }

   @Annotation2(max = 0)
   @Redirect(method = "renderGameOverlay$v1_7", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;drawTexturedModalRect(IIIIII)V"))
   public void lunar$crosshairCheckEvent(GuiIngame var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      if (var6 == 16 && var7 == 16) {
         CrosshairRenderEvent var9 = ClientEventBus.method29().method12(CrosshairRenderEvent.class, CrosshairRenderEvent::new);
         if (var9 == null || var9.method1() != OutcomeEvent.Type.DENY) {
            GL11.glEnable(3008);
            var1.drawTexturedModalRect(var2, var3, var4, var5, var6, var7);
         }
      } else if (var6 == 10 && var7 == 8) {
         int var8 = (var5 - 176) / 8;
         if (var8 != 5) {
            var1.drawTexturedModalRect(var2, var3, var4, var5, var6, var7);
         }
      } else {
         var1.drawTexturedModalRect(var2, var3, var4, var5, var6, var7);
      }
   }

   @Annotation2(1)
   @Inject(method = "showCrosshair$v1_8", at = @At("HEAD"), cancellable = true)
   public void lunar$crosshairCheckEvent(CallbackInfoReturnable<Boolean> var1) {
      CrosshairRenderEvent var2 = ClientEventBus.method29().method12(CrosshairRenderEvent.class, CrosshairRenderEvent::new);
      if (var2 != null) {
         switch (var2.method1()) {
            case ALLOW:
               var1.setReturnValue(true);
               break;
            case DENY:
               var1.setReturnValue(false);
         }
      }
   }

   @Annotation2(min = 5)
   @Redirect(method = "renderAttackIndicator$v1_12", at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;thirdPersonView:I"))
   public int impl$renderAttackIndicator(GameSettings var1) {
      CrosshairRenderEvent var2 = ClientEventBus.method29().method12(CrosshairRenderEvent.class, CrosshairRenderEvent::new);
      if (var2 != null && var2.method1() == OutcomeEvent.Type.DENY) {
         if (!FramebufferCaptureTask.method6()
            && this.mc.gameSettings.attackIndicator$v1_12 == 1
            && (var1.thirdPersonView == 0 || ThreadModuleDump63.method4().method40().method33().method13().get())) {
            RewindHandlers var3 = ThreadModuleDump63.method4().method40().method85().method35();
            if (var3 != null && !var3.method53().method14()) {
               return 1;
            }

            if (ThreadModuleDump63.method4().method40().method34().method13()) {
               return 1;
            }

            float var4 = this.mc.thePlayer.getCooledAttackStrength$v1_12(0.0F);
            boolean var5 = false;
            if (this.mc.pointedEntity != null && this.mc.pointedEntity instanceof EntityLivingBase && var4 >= 1.0F) {
               var5 = this.mc.thePlayer.getCooldownPeriod$v1_12() > 5.0F;
               var5 &= ((EntityLivingBase)this.mc.pointedEntity).isEntityAlive();
            }

            int var6 = LcuiScreen.method151().getScaledWidth();
            int var7 = LcuiScreen.method151().getScaledHeight();
            int var8 = var7 / 2 - 7 + 16;
            int var9 = var6 / 2 - 8;
            if (var5) {
               ((GuiIngame)this).drawTexturedModalRect(var9, var8, 68, 94, 16, 16);
            } else if (var4 < 1.0F) {
               int var10 = (int)(var4 * 17.0F);
               ((GuiIngame)this).drawTexturedModalRect(var9, var8, 36, 94, 16, 4);
               ((GuiIngame)this).drawTexturedModalRect(var9, var8, 52, 94, var10, 4);
            }
         }

         return 1;
      } else {
         return ThreadModuleDump63.method4().method40().method33().method13().get() ? 0 : var1.thirdPersonView;
      }
   }

   @Annotation2(min = 5)
   @Inject(
      method = "renderAttackIndicator$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;getCooledAttackStrength$v1_12(F)F"),
      cancellable = true
   )
   private void lunar$onRenderAttackIndicator(float var1, ScaledResolution var2, CallbackInfo var3) {
      if (ThreadModuleDump63.method4().method40().method34().method13()) {
         var3.cancel();
      }
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "renderHotbar$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;getCooledAttackStrength$v1_12(F)F")
   )
   private float lunar$onRenderAttackIndicatorHotbar(EntityPlayerSP var1, float var2, Operation<Float> var3) {
      return ThreadModuleDump63.method4().method40().method34().method13() ? 1.0F : (Float)var3.call(new Object[]{var1, var2});
   }

   @Annotation2(min = 1)
   @Redirect(
      method = "renderPlayerStats$v1_8(Lnet/minecraft/client/gui/ScaledResolution;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/storage/WorldInfo;isHardcoreModeEnabled()Z")
   )
   public boolean renderPlayerStats_isHardcoreModeEnabled(WorldInfo var1) {
      return var1.isHardcoreModeEnabled() || ThreadModuleDump63.method4().method40().method67().method13();
   }

   @Annotation2(max = 0)
   @Inject(method = "renderIcons$v1_7", at = @At("TAIL"))
   private void lunar$renderHealthBarEvent(int var1, int var2, CallbackInfo var3) {
      this.lunar$renderHealthBarEvent();
   }

   @Annotation2(min = 1)
   @Inject(method = "renderPlayerStats$v1_8", at = @At("TAIL"))
   private void lunar$renderHealthBarEvent(ScaledResolution var1, CallbackInfo var2) {
      this.lunar$renderHealthBarEvent();
   }

   @Unique
   private void lunar$renderHealthBarEvent() {
      boolean var1 = this.lunar$foodRendered;
      this.lunar$foodRendered = false;
      if (!ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method45().method15().isFixedToPlayer())) {
         ClientEventBus.method29().method12(PlayerStatsRenderEvent.class, () -> {
            ScaledResolution var2 = this.lunar$newScaledResolution();
            BridgeExtension3_5 var3 = AbstractRenderContext.method32();
            return new PlayerStatsRenderEvent(var3, new LegacyGuiGraphicsBridge(var3), new MarkerModel.Data4(var2.getScaledWidth_double(), var2.getScaledHeight_double()), var1);
         });
      }
   }

   @Unique
   private ScaledResolution lunar$newScaledResolution() {
      Minecraft var1 = Minecraft.getMinecraft();
      ScaledResolution var2;
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         var2 = new ScaledResolution(var1);
      } else {
         var2 = new ScaledResolution(var1, var1.displayWidth, var1.displayHeight);
      }

      return var2;
   }

   @Annotation2(5)
   @ModifyVariable(method = "renderPlayerStats$v1_8", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
   private boolean lunar$renderPlayerStats(boolean var1, @Local(ordinal = 0) int var2) {
      return !ThreadModuleDump63.method4().method40().method98().method19().method13() ? var1 : var1 && var2 < this.lastPlayerHealth;
   }

   @Annotation2(min = 1)
   @ModifyVariable(method = "renderPlayerStats$v1_8", at = @At(value = "STORE", ordinal = 0), ordinal = 1)
   private int lunar$renderPlayerStats(int var1) {
      boolean var2 = ThreadModuleDump63.MC_VERSION == 5
         ? ThreadModuleDump63.method4().method40().method98().method19().method13()
         : ThreadModuleDump63.method4().method40().method98().method16().method13();
      return var2 ? 0 : var1;
   }

   @Annotation2(min = 5)
   @WrapWithCondition(method = "renderGameOverlay$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiBossOverlay;renderBossHealth()V"))
   private boolean lunar$renderBossHealth$v1_12(GuiBossOverlay var1) {
      BossBarRenderEvent var2 = ClientEventBus.method29().method12(BossBarRenderEvent.class, BossBarRenderEvent::new);
      return var2 == null || !var2.isCancelled();
   }

   @Annotation2(1)
   @WrapWithCondition(method = "renderGameOverlay$v1_8", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;renderBossHealth()V"))
   private boolean lunar$renderBossHealth$v1_8(GuiIngame var1) {
      BossBarRenderEvent var2 = ClientEventBus.method29().method12(BossBarRenderEvent.class, BossBarRenderEvent::new);
      return var2 == null || !var2.isCancelled();
   }

   @Annotation2(min = 5)
   @Inject(method = "renderPotionEffects$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$disablePotionEffectRendering(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method23().isEnabled() && ThreadModuleDump63.method4().method40().method23().method21().get()) {
         var1.cancel();
      }
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "renderPotionEffects$v1_12",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;drawTexturedModalRect(IIIIII)V", ordinal = 2),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;drawTexturedModalRect(IIIIII)V", ordinal = 3)
      }
   )
   private void lunar$effectDurationBarHook$v1_12(
      GuiIngame var1, int var2, int var3, int var4, int var5, int var6, int var7, Operation<Void> var8, @Local PotionEffect var9
   ) {
      var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
      PotionEffects var10 = ThreadModuleDump63.method4().method40().method23();
      if (var10.isEffectBarEnabledOnVanillaHud()) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         var10.renderWithContext(AbstractRenderContext.method32(), (Fog)var9, var2 - 1, var3 - 1, 20.0F, 20.0F);
      }
   }

   @Annotation2(min = 1)
   @Redirect(
      method = "renderGameOverlay$v1_8(F)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiIngame;titlesTimer:I", opcode = 180, ordinal = 0)
   )
   private int lunar$renderGameOverlay$title(GuiIngame var1) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method53().method15())) {
         return 0;
      } else {
         return ThreadModuleDump63.method4().method40().method35().method14() ? 0 : var1.titlesTimer;
      }
   }

   @Annotation2(min = 1)
   @Redirect(method = "renderGameOverlay$v1_8(F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_8;isKeyDown()Z"))
   private boolean lunar$isTabKeyDown(KeyBinding var1) {
      if (var1 == Minecraft.getMinecraft().gameSettings.keyBindPlayerList
         && ThreadModuleDump63.method3().bridge$getCurrentScreen() != null
         && DriverViewportLegacy.method50().method64() == DriverOverlayRegistryLegacy.field2) {
         return false;
      }

      Rewind var2 = ThreadModuleDump63.method4().method40().method85();
      if (var2.method19()) {
         return var2.method35().method42().get().method15().getOrDefault(GuiType3.TAB, false);
      }

      Tab var3 = ThreadModuleDump63.method4().method40().method48();
      return var3.isEnabled() ? var3.isActive() : var1.isKeyDown();
   }

   @Annotation2(max = 0)
   @Inject(method = "renderVignette$v1_7", at = @At("HEAD"), cancellable = true)
   public void lunar$disableVignetteWhenUsingCache$v1_7(float var1, int var2, int var3, CallbackInfo var4) {
      if (FramebufferCaptureTask.method6()) {
         var4.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "renderVignette$v1_8", at = @At("HEAD"), cancellable = true)
   public void lunar$disableVignetteWhenUsingCache$v1_8(float var1, ScaledResolution var2, CallbackInfo var3) {
      if (FramebufferCaptureTask.method6()) {
         var3.cancel();
      }
   }

   @Annotation2(max = 0)
   @Unique
   private void lunar$handlePlayerListRendering() {
      ScoreObjective var1 = this.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(0);
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null) {
         BridgeExtension3_5 var3 = AbstractRenderContext.method32();
         Client var4 = ThreadModuleDump63.method4();
         Tab var5 = var4.method40().method48();
         if (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer$v1_7.sendQueue.playerInfoList$v1_7.size() > 1 || var1 != null) {
            this.mc.mcProfiler.startSection("playerList");
            NetHandlerPlayClient var6 = this.mc.thePlayer$v1_7.sendQueue;
            List var7 = var6.playerInfoList$v1_7;
            if (ThreadModuleDump63.method4().method40().method48().method14()) {
               UUID var8 = Minecraft.getMinecraft().thePlayer$v1_7.getUniqueID();
               Bridge2_33 var9 = var7.stream()
                  .map(var0 -> (Bridge2_33)var0)
                  .filter(var1x -> var1x.bridge$getGameProfile() != null && var1x.bridge$getGameProfile().getId().equals(var8))
                  .findFirst()
                  .orElse(null);
               if (var9 != null) {
                  var7.remove((GuiPlayerInfo)var9);
                  var7.add(0, (GuiPlayerInfo)var9);
               }
            }

            this.lunar$queueTabLogoUpdates(var7);
            int var34 = var6.currentServerMaxPlayers;
            int var35 = var34;
            ScaledResolution var11 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);

            int var10;
            for (var10 = 1; var35 > 20; var35 = (var34 + var10 - 1) / var10) {
               var10++;
            }

            int var12 = 300 / var10;
            if (var12 > 150) {
               var12 = 150;
            }

            int var13 = (var11.getScaledWidth() - var10 * var12) / 2;
            byte var14 = 15;
            Gui.drawRect(var13 - 1, var14 - 1, var13 + var12 * var10, var14 + 9 * var35, var5.isEnabled() ? var5.getBackgroundColor().method14(0.0F) : Integer.MIN_VALUE);

            for (int var15 = 0; var15 < var34; var15++) {
               int var16 = var13 + var15 % var10 * var12;
               int var17 = var14 + var15 / var10 * 9;
               int var18 = 553648127;
               if (var5.isEnabled()) {
                  var18 = var5.getRowsColor().method14(var15);
                  if (var7 != null && var5.getPingRow().get() && var15 < var7.size()) {
                     GuiPlayerInfo var19 = (GuiPlayerInfo)var7.get(var15);
                     if (var19 != null) {
                        var18 = var5.getPingColor(var19.responseTime);
                        var18 = ThreadModuleDump23.method18(var18, 0.1254902F);
                     }
                  }
               }

               Gui.drawRect(var16, var17, var16 + var12 - 1, var17 + 8, var18);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glEnable(3008);
               if (var15 < var7.size()) {
                  GuiPlayerInfo var37 = (GuiPlayerInfo)var7.get(var15);
                  Bridge2_33 var20 = (Bridge2_33)var37;
                  ScorePlayerTeam var21 = this.mc.theWorld.getScoreboard().getPlayersTeam(var37.name);
                  boolean var22 = this.mc.fontRendererObj.getStringWidth(var37.name) == 0;
                  String var23 = ScorePlayerTeam.formatPlayerName(var21, var22 ? "" : var37.name);
                  boolean var24 = var20.bridge$getGameProfile() != null && var2.bridge$getUniqueID() == var20.bridge$getGameProfile().getId();
                  boolean var25 = false;
                  UUID var26 = var24 ? var20.bridge$getGameProfile().getId() : null;
                  float var27 = 0.0F;
                  if (ThreadModuleDump63.method4().method41().method6().method41().get() && var20.bridge$getGameProfile() != null && var26 != null) {
                     var25 = var5.renderLeftIcon(var3.method42(), var16, var17, var26);
                  }

                  int var28 = 16777215;
                  boolean var29 = var5.isEnabled() ? var5.getNameShadow().get() : true;
                  if (var24 && var5.isEnabled() && var5.getHighlightOwnName().get()) {
                     Component var30 = AdventureTextBridge.asAdventure((Bridge2_42)(new ChatComponentText(var23)))
                        .replaceText(
                           (TextReplacementConfig)TextReplacementConfig.builder()
                              .match(var2.bridge$getName())
                              .replacement(var2x -> ((Builder)var2x.color(TextColor.color(var5.getNameColor().method14(0.0F)))).content(var2.bridge$getName()))
                              .build()
                        );
                     var27 = ThreadModuleDump63.method10().bridge$drawString(var3, var30, var25 ? var16 + 9 : var16, var17, var28, var29);
                  } else {
                     var27 = this.mc.fontRendererObj.drawString(var23, var25 ? var16 + 9 : var16, var17, var28, var5.isEnabled() && var5.getNameShadow().get());
                  }

                  if (var26 != null) {
                     var5.renderRightIcon(var3.method42(), var27, var17, var26);
                  }

                  if (var1 != null) {
                     int var39 = var16 + this.mc.fontRendererObj.getStringWidth(var23) + 5;
                     int var31 = var16 + var12 - 12 - 5;
                     if (var31 - var39 > 5) {
                        Score var32 = var1.getScoreboard().getValueFromObjective(var37.name, var1);
                        String var33 = "" + AdventureChatFormatting.YELLOW + var32.getScorePoints();
                        this.mc
                           .fontRendererObj
                           .drawString(var33, var31 - this.mc.fontRendererObj.getStringWidth(var33), var17, 16777215, var5.isEnabled() && var5.getNameShadow().get());
                     }
                  }

                  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                  if (!var5.shouldHidePing(var37.responseTime) && !var5.renderPingNumber(var3, var37.responseTime, var16 + var12 - 2, var17)) {
                     this.mc.getTextureManager().bindTexture(Gui.icons);
                     byte var40 = 0;
                     byte var41;
                     if (var37.responseTime < -1) {
                        var41 = 5;
                     } else if (var37.responseTime < 150) {
                        var41 = 0;
                     } else if (var37.responseTime < 300) {
                        var41 = 1;
                     } else if (var37.responseTime < 600) {
                        var41 = 2;
                     } else if (var37.responseTime < 1000) {
                        var41 = 3;
                     } else {
                        var41 = 4;
                     }

                     ((Gui)this).zLevel += 100.0F;
                     ((Gui)this).drawTexturedModalRect(var16 + var12 - 12, var17, 0 + var40 * 10, 176 + var41 * 8, 10, 8);
                     ((Gui)this).zLevel -= 100.0F;
                  }
               }
            }
         }
      }
   }

   @ModifyVariable(method = {"setRecordPlaying$v1_7", "setOverlayMessage$v1_12(Ljava/lang/String;Z)V"}, at = @At("HEAD"), argsOnly = true)
   private String lunar$modifyActionBarMessage(String var1, @Share("event") LocalRef<com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy> var2) {
      com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy var3 = ClientEventBus.method29()
         .method12(
            com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data2.class,
            () -> new com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data2(AdventureTextBridge.asAdventure(var1))
         );
      if (var3 != null) {
         var2.set(var3);
         if (var3.isCancelled()) {
            return null;
         } else {
            return !var3.isChanged() ? var1 : AdventureTextBridge.asLegacyString(var3.method2());
         }
      } else {
         return var1;
      }
   }

   @Inject(method = {"setRecordPlaying$v1_7", "setOverlayMessage$v1_12(Ljava/lang/String;Z)V"}, at = @At("HEAD"), cancellable = true)
   private void lunar$handleCancellation(
      String var1, boolean var2, CallbackInfo var3, @Share("event") LocalRef<com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy> var4
   ) {
      com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy var5 = (com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy)var4.get();
      if (var5 != null && var5.isCancelled()) {
         var3.cancel();
      }
   }

   @Inject(method = {"renderInventorySlot$v1_7", "renderTooltip$v1_8", "renderHotbar$v1_12"}, at = @At("HEAD"), cancellable = true)
   private void lunar$disableRenderHotbarWhenUsingCache(CallbackInfo var1) {
      if (FramebufferCaptureTask.method6()) {
         var1.cancel();
      }
   }

   @Annotation2(min = 1)
   @ModifyExpressionValue(
      method = "renderPlayerStats$v1_8(Lnet/minecraft/client/gui/ScaledResolution;)V",
      slice = @Slice(from = @At(value = "INVOKE_STRING", target = "startSection(Ljava/lang/String;)V", args = "ldc=armor")),
      at = @At(value = "CONSTANT", args = "intValue=10", ordinal = 0)
   )
   private int lunar$modifyArmorVisual(int var1) {
      ArmorRenderEvent var2 = ClientEventBus.method29().method12(ArmorRenderEvent.class, ArmorRenderEvent::new);
      return var2 != null && var2.isCancelled() ? 0 : var1;
   }

   @Annotation2(max = 1)
   @WrapOperation(
      method = {"renderIcons$v1_7", "renderPlayerStats$v1_8"},
      slice = @Slice(
         from = @At(value = "INVOKE_STRING", target = "startSection(Ljava/lang/String;)V", args = "ldc=armor"),
         to = @At(value = "INVOKE_STRING", target = "endStartSection(Ljava/lang/String;)V", args = "ldc=health")
      ),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;drawTexturedModalRect(IIIIII)V")
   )
   private void lunar$drawArmorBarIcons$v1_7(
      GuiIngame var1, int var2, int var3, int var4, int var5, int var6, int var7, Operation<Void> var8, @Local(index = 22) int var9
   ) {
      if (!this.lunar$drawArmorBarIcon(var9, var2, var3)) {
         var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
      }
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "renderPlayerStats$v1_8",
      slice = @Slice(
         from = @At(value = "INVOKE_STRING", target = "startSection(Ljava/lang/String;)V", args = "ldc=armor"),
         to = @At(value = "INVOKE_STRING", target = "endStartSection(Ljava/lang/String;)V", args = "ldc=health")
      ),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;drawTexturedModalRect(IIIIII)V")
   )
   private void lunar$drawArmorBarIcons$v1_12(
      GuiIngame var1, int var2, int var3, int var4, int var5, int var6, int var7, Operation<Void> var8, @Local(index = 21) int var9
   ) {
      if (!this.lunar$drawArmorBarIcon(var9, var2, var3)) {
         var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
      }
   }

   @Unique
   private boolean lunar$drawArmorBarIcon(int var1, int var2, int var3) {
      ArmorstatusBars var4 = ArmorstatusBars.method14();
      return var4 != null && var4.method2(new LegacyGuiGraphicsBridge(AbstractRenderContext.method32()), var1, var2, var3);
   }

   @Annotation2(max = 0)
   @Inject(method = "renderIcons$v1_7", at = @At(value = "INVOKE_STRING", target = "endStartSection(Ljava/lang/String;)V", args = "ldc=health"))
   private void lunar$restoreArmorBarsState$v1_7(int var1, int var2, CallbackInfo var3) {
      this.lunar$restoreArmorBarsState();
   }

   @Annotation2(min = 1)
   @Inject(
      method = "renderPlayerStats$v1_8(Lnet/minecraft/client/gui/ScaledResolution;)V",
      at = @At(value = "INVOKE_STRING", target = "endStartSection(Ljava/lang/String;)V", args = "ldc=health")
   )
   private void lunar$restoreArmorBarsState$v1_8(ScaledResolution var1, CallbackInfo var2) {
      this.lunar$restoreArmorBarsState();
   }

   @Unique
   private void lunar$restoreArmorBarsState() {
      if (ArmorstatusBars.method14() != null) {
         RenderSystemBridge var1 = Bridge.method42();
         var1.method34();
         var1.method36();
         this.mc.getTextureManager().bindTexture(Gui.icons);
      }
   }

   @Annotation2(min = 1)
   @ModifyExpressionValue(
      method = "renderPlayerStats$v1_8(Lnet/minecraft/client/gui/ScaledResolution;)V",
      slice = @Slice(from = @At(value = "INVOKE_STRING", target = "endStartSection(Ljava/lang/String;)V", args = "ldc=food")),
      at = @At(value = "CONSTANT", args = "intValue=10", ordinal = 0)
   )
   private int lunar$modifyHungerVisual(int var1) {
      com.moonsworth.lunar.client.event.mixin.highlight.VanillaHudRenderEvent var2 = ClientEventBus.method29()
         .method12(
            com.moonsworth.lunar.client.event.mixin.highlight.VanillaHudRenderEvent.class,
            com.moonsworth.lunar.client.event.mixin.highlight.VanillaHudRenderEvent::new
         );
      if (var2 != null && var2.isCancelled()) {
         return 0;
      }

      this.lunar$foodRendered = true;
      return var1;
   }

   @Annotation2(max = 0)
   @Inject(
      method = "renderIcons$v1_7",
      at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", args = "ldc=food")
   )
   private void lunar$trackFoodRendered$v1_7(int var1, int var2, CallbackInfo var3) {
      this.lunar$foodRendered = true;
   }

   @WrapOperation(
      method = {"renderGameOverlay$v1_7", "renderGameOverlay$v1_8"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;gameIsSurvivalOrAdventure()Z")
   )
   private boolean lunar$cancelRenderExperienceBar(PlayerControllerMP var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method45().method15().isFixedToPlayer())
         ? false
         : (Boolean)var2.call(new Object[]{var1});
   }

   @Inject(method = {"renderGameOverlay$v1_7", "renderGameOverlay$v1_8"}, at = @At("HEAD"), cancellable = true)
   private void lunar$rewindHideGUI(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method19() && ThreadModuleDump63.method3().bridge$getGameSettings().bridge$isHideGui()) {
         var1.cancel();
      }
   }

   @Inject(method = {"renderGameOverlay$v1_7", "renderGameOverlay$v1_8"}, at = @At("HEAD"), cancellable = true)
   private void lunar$preventHud(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method56().method25()) {
         var1.cancel();
      }
   }

   @Unique
   private void lunar$queueTabLogoUpdates(List<GuiPlayerInfo> var1) {
   }

   @Annotation2(min = 1)
   @WrapMethod(method = {"renderTooltip$v1_8", "renderHotbar$v1_12"})
   private void lunar$renderHotbar(ScaledResolution var1, float var2, Operation<Void> var3) {
      MutableBoolean var4 = new MutableBoolean();
      ScaledResolution var5 = this.lunar$scaleHotbar$pre(var1, var4);
      if (!FramebufferCaptureTask.method6() && !this.lunar$hotbarHidden()) {
         ClientEventBus.method29().method16(InventoryScreenRenderEvent.HotbarPreEvent.class, InventoryScreenRenderEvent.HotbarPostEvent.class, var2x -> {
            BridgeExtension3_5 var3x = AbstractRenderContext.method9(var2);
            LegacyGuiGraphicsBridge var4x = new LegacyGuiGraphicsBridge(var3x);
            int var5x = var5.scaledWidth / 2 - 91;
            int var6 = var5.scaledHeight - 22;
            return var2x.wrapEvents(() -> new InventoryScreenRenderEvent.HotbarPreEvent(var4x, var5x, var6), () -> new InventoryScreenRenderEvent.HotbarPostEvent(var4x, var5x, var6));
         }, () -> {
            var3.call(new Object[]{var5, var2});
            Bridge.method42().method34();
         });
      } else {
         var3.call(new Object[]{var5, var2});
      }

      if (var4.getValue()) {
         this.lunar$scaleHotbar$post();
      }
   }

   @Inject(
      method = {
            "renderInventorySlot$v1_7",
            "renderTooltip$v1_8",
            "renderHotbar$v1_12",
            "renderIcons$v1_7",
            "renderPlayerStats$v1_8",
            "renderExpBar$v1_8",
            "renderHorseJumpBar$v1_8",
            "renderSelectedItem$v1_8",
            "renderMountHealth$v1_12"
      },
      at = @At("HEAD"),
      cancellable = true
   )
   private void lunar$rewindDisableHotbar(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method53().method21())) {
         var1.cancel();
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "renderPotionEffects$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindDisablePotionEffects(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method53().method25())) {
         var1.cancel();
      }
   }

   @WrapWithCondition(
      method = {"renderGameOverlay$v1_7", "renderGameOverlay$v1_8"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;III)I", ordinal = 0),
      slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=overlayMessage"))
   )
   private boolean lunar$rewindDisableOverlayMessage(FontRenderer var1, String var2, int var3, int var4, int var5) {
      return !ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method53().method22());
   }

   @Inject(method = {"renderVignette$v1_7", "renderVignette$v1_8"}, at = @At("HEAD"), cancellable = true)
   private void lunar$rewindDisableVignette(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method53().method23())) {
         var1.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(
      method = {"renderTooltip$v1_8", "renderHotbar$v1_12"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderHelper;enableGUIStandardItemLighting()V")
   )
   private void lunar$renderHotBarBeforeItem$v1_8(ScaledResolution var1, float var2, CallbackInfo var3) {
      ClientEventBus.method29().method12(InventoryScreenRenderEvent.HotbarItemsEvent.class, () -> {
         BridgeExtension3_5 var2x = AbstractRenderContext.method9(var2);
         LegacyGuiGraphicsBridge var3x = new LegacyGuiGraphicsBridge(var2x);
         int var4 = var1.scaledWidth / 2 - 91;
         int var5 = var1.scaledHeight - 22;
         return new InventoryScreenRenderEvent.HotbarItemsEvent(var3x, var4, var5);
      });
   }

   @Annotation2(0)
   @Inject(
      method = "renderGameOverlay$v1_7(FZII)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
         ordinal = 0
      )
   )
   private void lunar$renderHotbarPre$v1_7(float var1, boolean var2, int var3, int var4, CallbackInfo var5, @Local ScaledResolution var6) {
      if (!this.lunar$hotbarHidden()) {
         ClientEventBus.method29()
            .method12(
               InventoryScreenRenderEvent.HotbarPreEvent.class,
               () -> new InventoryScreenRenderEvent.HotbarPreEvent(new LegacyGuiGraphicsBridge(AbstractRenderContext.method9(var1)), var6.scaledWidth / 2 - 91, var6.scaledHeight - 22)
            );
      }
   }

   @Annotation2(0)
   @Inject(
      method = "renderGameOverlay$v1_7(FZII)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderHelper;enableGUIStandardItemLighting()V")
   )
   private void lunar$renderHotbarBeforeItems$v1_7(float var1, boolean var2, int var3, int var4, CallbackInfo var5, @Local ScaledResolution var6) {
      if (!this.lunar$hotbarHidden()) {
         ClientEventBus.method29()
            .method12(
               InventoryScreenRenderEvent.HotbarItemsEvent.class,
               () -> new InventoryScreenRenderEvent.HotbarItemsEvent(new LegacyGuiGraphicsBridge(AbstractRenderContext.method9(var1)), var6.scaledWidth / 2 - 91, var6.scaledHeight - 22)
            );
      }
   }

   @Annotation2(0)
   @Inject(
      method = "renderGameOverlay$v1_7(FZII)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderHelper;disableStandardItemLighting()V", shift = Shift.AFTER)
   )
   private void lunar$renderHotbarPost$v1_7(float var1, boolean var2, int var3, int var4, CallbackInfo var5, @Local ScaledResolution var6) {
      if (!FramebufferCaptureTask.method1() && !this.lunar$hotbarHidden()) {
         BridgeExtension3_5 var7 = AbstractRenderContext.method9(var1);
         ClientEventBus.method29()
            .method12(InventoryScreenRenderEvent.HotbarPostEvent.class, () -> new InventoryScreenRenderEvent.HotbarPostEvent(new LegacyGuiGraphicsBridge(var7), var6.scaledWidth / 2 - 91, var6.scaledHeight - 22));
         var7.method33();
      }
   }

   @Unique
   private boolean lunar$hotbarHidden() {
      return ThreadModuleDump63.method4().method40().method85().method17(var0 -> !var0.method53().method21());
   }

   @Annotation2(min = 1)
   @ModifyConstant(method = "renderScoreboard$v1_8", constant = @Constant(intValue = 553648127))
   private int lunar$fixScoreboardAlpha$v1_8(int var1) {
      return -1;
   }

   @Annotation2(0)
   @ModifyConstant(method = "renderScoreboard$v1_7", constant = @Constant(intValue = 553648127))
   private int lunar$fixScoreboardAlpha$v1_7(int var1) {
      return -1;
   }

   @Annotation2(max = 0)
   @Redirect(method = "renderGameOverlay$v1_7", at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;showDebugInfo:Z"))
   private boolean lunar$modifyDebugRenderer(GameSettings var1) {
      return ThreadModuleDump63.method4().method40().method95().isEnabled() ? false : var1.showDebugInfo;
   }

   @Annotation2(min = 1)
   @ModifyVariable(
      method = {"renderHorseJumpBar$v1_8", "renderExpBar$v1_8", "renderSelectedItem$v1_8", "renderPlayerStats$v1_8", "renderMountHealth$v1_12"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private ScaledResolution lunar$scaleHotbar$pre$resolution(ScaledResolution var1, @Share("scaled") LocalBooleanRef var2) {
      MutableBoolean var3 = new MutableBoolean();
      var1 = this.lunar$scaleHotbar$pre(var1, var3);
      var2.set(var3.getValue());
      return var1;
   }

   @Annotation2(min = 1)
   @WrapOperation(
      method = "renderGameOverlay$v1_8",
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
      ScaledResolution var5 = this.lunar$newScaledResolution();
      var5.scaledWidth = Math.round(var5.scaledWidth / var4);
      var5.scaledHeight = Math.round(var5.scaledHeight / var4);
      LcuiScreen.method150(
         new ThreadModuleDump71(
            var5.scaleFactor, var5.scaledWidth * var5.scaleFactor, var5.scaledHeight * var5.scaleFactor, var5.scaledWidth, var5.scaledHeight
         )
      );
      return var5;
   }

   @Annotation2(min = 1)
   @ModifyVariable(method = {"renderHorseJumpBar$v1_8", "renderExpBar$v1_8"}, at = @At("HEAD"), argsOnly = true)
   private int lunar$scaleHotbar$pre$x(int var1) {
      RenderScaleEvent var2 = ClientEventBus.method29().method12(RenderScaleEvent.class, RenderScaleEvent::new);
      float var3 = var2 == null ? 1.0F : var2.getScale();
      return var3 == 1.0F ? var1 : Math.round((var1 + 91) / var3) - 91;
   }

   @Annotation2(min = 1)
   @Inject(
      method = {"renderHorseJumpBar$v1_8", "renderExpBar$v1_8", "renderSelectedItem$v1_8", "renderPlayerStats$v1_8", "renderMountHealth$v1_12"},
      at = @At("RETURN")
   )
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

   @Annotation2(min = 1)
   @WrapMethod(method = {"renderTooltip$v1_8", "renderHotbar$v1_12"})
   private void lunar$doTint(ScaledResolution var1, float var2, Operation<Void> var3) {
      OverlayMod var4 = ThreadModuleDump63.method4().method40().method84();
      if (var4.isHotbarTintEnabled()) {
         Gui2.method1(var4.getHotbarTint());
         var3.call(new Object[]{var1, var2});
         Gui2.method2();
      } else {
         var3.call(new Object[]{var1, var2});
      }
   }

   @Annotation2(1)
   @WrapMethod(method = "renderHotbarItem$v1_8")
   private void lunar$onHotbarItem$v1_8(int var1, int var2, int var3, float var4, EntityPlayer var5, Operation<Void> var6) {
      Gui2.method3();
      var6.call(new Object[]{var1, var2, var3, var4, var5});
      Gui2.method4();
   }

   @Annotation2(5)
   @WrapMethod(method = "renderHotbarItem$v1_12")
   private void lunar$onHotbarItem$v1_12(int var1, int var2, float var3, EntityPlayer var4, ItemStack var5, Operation<Void> var6) {
      Gui2.method3();
      var6.call(new Object[]{var1, var2, var3, var4, var5});
      Gui2.method4();
   }

   @Annotation2(0)
   @Inject(
      method = "renderGameOverlay$v1_7(FZII)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
         ordinal = 0,
         shift = Shift.AFTER
      )
   )
   private void lunar$beginHotbarTint$v1_7(CallbackInfo var1) {
      OverlayMod var2 = ThreadModuleDump63.method4().method40().method84();
      if (var2.isHotbarTintEnabled()) {
         Gui2.method1(var2.getHotbarTint());
      }
   }

   @Annotation2(0)
   @Inject(
      method = "renderGameOverlay$v1_7(FZII)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
         ordinal = 1
      )
   )
   private void lunar$endHotbarTint$v1_7(CallbackInfo var1) {
      Gui2.method2();
   }

   @Annotation2(0)
   @ModifyExpressionValue(method = "renderGameOverlay$v1_7(FZII)V", at = @At(value = "CONSTANT", args = "intValue=8453920"))
   private int lunar$xpLevelTextColor$v1_7(int var1) {
      return ThreadModuleDump63.method4().method40().method84().method25(var1);
   }

   @Annotation2(min = 1)
   @ModifyExpressionValue(method = "renderExpBar$v1_8", at = @At(value = "CONSTANT", args = "intValue=8453920"))
   private int lunar$xpLevelTextColor$v1_8(int var1) {
      return ThreadModuleDump63.method4().method40().method84().method25(var1);
   }
}
