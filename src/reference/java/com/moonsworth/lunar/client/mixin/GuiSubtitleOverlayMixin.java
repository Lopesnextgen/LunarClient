package com.moonsworth.lunar.client.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.mod.hud.audiosubtitles.AudioSubtitles;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.client.gui.GuiSubtitleOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.GuiSubtitleOverlay.Subtitle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiSubtitleOverlay.class)
public class GuiSubtitleOverlayMixin {
   @Final
   @Shadow
   public List<Subtitle> subtitles;

   @Inject(method = "renderSubtitles", at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;", ordinal = 1), cancellable = true)
   private void lunar$onRender(
      ScaledResolution var1,
      CallbackInfo var2,
      @Local(ordinal = 1) int var3,
      @Local(ordinal = 0) Vec3 var4,
      @Local(ordinal = 1) Vec3 var5,
      @Local(ordinal = 3) Vec3 var6
   ) {
      AudioSubtitles var7 = ThreadModuleDump63.method4().method40().method89();
      if (var7.isEnabled()) {
         var2.cancel();
         GlStateManager.popMatrix();
         var7.method3(this.subtitles, var3, (Vec3Bridge)var4, (Vec3Bridge)var5, (Vec3Bridge)var6);
      }
   }
}
