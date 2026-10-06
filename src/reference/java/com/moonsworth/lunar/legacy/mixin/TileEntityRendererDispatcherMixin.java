package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.moonsworth.lunar.bridge.tileentity.BlockEntityBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.replay.render.WorldRenderHandler;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.mod.render.staffxray.StaffXray;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntityRendererDispatcher.class)
public abstract class TileEntityRendererDispatcherMixin {
   public TileEntityRendererDispatcherMixin() {
   }

   @VersionGate(min = 1)
   @Inject(method = {"render$v1_12(Lnet/minecraft/tileentity/TileEntity;FI)V", "renderTileEntity$v1_8"}, at = @At("HEAD"), cancellable = true)
   public void lunar$renderTileEntity$v1_8(TileEntity tileentity1, float value2, int number3, CallbackInfo callback4) {
      this.lunar$renderTileEntity(tileentity1, callback4);
   }

   @VersionGate(max = 0)
   @Inject(method = "renderTileEntity$v1_7", at = @At("HEAD"), cancellable = true)
   public void lunar$renderTileEntity$v1_7(TileEntity tileentity1, float value2, CallbackInfo callback3) {
      this.lunar$renderTileEntity(tileentity1, callback3);
   }

   @Unique
   private void lunar$renderTileEntity(TileEntity tileentity1, CallbackInfo callback2) {
      RewindMod rewind3 = Ref.method4().method40().method85();
      if (rewind3.method19()) {
         WorldRenderHandler rewindhandlers3impl64 = rewind3.method35().method52();
         if ((Boolean)rewindhandlers3impl64.method14().get() && !(Boolean)rewindhandlers3impl64.method16().get()) {
            callback2.cancel();
         }

         if ((Boolean)rewindhandlers3impl64.method15().get() && (Boolean)rewindhandlers3impl64.method28().get()) {
            callback2.cancel();
         }
      }

      StaffXray staffxray6 = Ref.method4().method44().method10();
      if (staffxray6.isEnabled() && staffxray6.method16() && !staffxray6.method14().contains(tileentity1.getBlockType())) {
         callback2.cancel();
      }

      BlockEntityBridge hitcolorextension5 = (BlockEntityBridge)tileentity1;
      if (!Client.method109().method41().method7().method4(hitcolorextension5, hitcolorextension5.bridge$getBlockType())) {
         callback2.cancel();
      }
   }

   @VersionGate(max = 0)
   @ModifyExpressionValue(
      method = "renderTileEntity$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getLightBrightnessForSkyBlocks$v1_7(IIII)I")
   )
   private int lunar$xrayTileEntityBrightness$v1_7(int number1) {
      return this.apollo$isXrayActive() ? 15728880 : number1;
   }

   @VersionGate(min = 1)
   @ModifyExpressionValue(
      method = {"render$v1_12(Lnet/minecraft/tileentity/TileEntity;FI)V", "renderTileEntity$v1_8"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getCombinedLight$v1_8(Lnet/minecraft/util/math/BlockPos;I)I")
   )
   private int lunar$xrayTileEntityBrightness$v1_8(int number1) {
      return this.apollo$isXrayActive() ? 15728880 : number1;
   }

   @Unique
   private boolean apollo$isXrayActive() {
      StaffXray staffxray1 = Ref.method4().method44().method10();
      return staffxray1.isEnabled() && staffxray1.method16();
   }
}
