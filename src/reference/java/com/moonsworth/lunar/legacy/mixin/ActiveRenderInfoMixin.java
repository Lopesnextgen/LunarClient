package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ActiveRenderInfo.class)
public abstract class ActiveRenderInfoMixin {
   public ActiveRenderInfoMixin() {
   }

   @ModifyVariable(method = "updateRenderInfo", at = @At("HEAD"), index = 1, argsOnly = true)
   private static boolean lunar$rewindActiveRenderInfo$back(boolean flag0) {
      return Ref.method4().method40().method85().method17(arg0x -> !arg0x.method45().method15().isFixedToPlayer()) ? false : flag0;
   }

   @WrapOperation(
      method = "updateRenderInfo(Lnet/minecraft/entity/player/EntityPlayer;Z)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;rotationPitch:F"),
      expect = 0,
      require = 0
   )
   private static float lunar$rewindActiveRenderInfo$pitch(EntityPlayer player0, Operation<Float> operation1) {
      return lunar$rewindPitch(player0, operation1);
   }

   @WrapOperation(
      method = "updateRenderInfo(Lnet/minecraft/entity/Entity;Z)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;rotationPitch:F"),
      expect = 0,
      require = 0
   )
   @Dynamic
   private static float lunar$rewindActiveRenderInfo$pitch(Entity entity0, Operation<Float> operation1) {
      return lunar$rewindPitch(entity0, operation1);
   }

   @Unique
   private static float lunar$rewindPitch(Entity entity0, Operation<Float> operation1) {
      if (Ref.method4().method40().method85().method17(arg0x -> arg0x.method45().method19() || !arg0x.method45().method15().isFixedToPlayer())) {
         return Ref.MC_VERSION <= 0
            ? Minecraft.getMinecraft().renderViewEntity$v1_7.rotationPitch
            : Minecraft.getMinecraft().renderViewEntity.rotationPitch;
      } else {
         return (Float)operation1.call(new Object[]{entity0});
      }
   }

   @WrapOperation(
      method = "updateRenderInfo(Lnet/minecraft/entity/player/EntityPlayer;Z)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;rotationYaw:F"),
      expect = 0,
      require = 0
   )
   private static float lunar$rewindActiveRenderInfo$yaw(EntityPlayer player0, Operation<Float> operation1) {
      return lunar$rewindYaw(player0, operation1);
   }

   @WrapOperation(
      method = "updateRenderInfo(Lnet/minecraft/entity/Entity;Z)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;rotationYaw:F"),
      expect = 0,
      require = 0
   )
   @Dynamic
   private static float lunar$rewindActiveRenderInfo$yaw(Entity entity0, Operation<Float> operation1) {
      return lunar$rewindYaw(entity0, operation1);
   }

   @Unique
   private static float lunar$rewindYaw(Entity entity0, Operation<Float> operation1) {
      if (Ref.method4().method40().method85().method17(arg0x -> arg0x.method45().method19() || !arg0x.method45().method15().isFixedToPlayer())) {
         return Ref.MC_VERSION <= 0
            ? Minecraft.getMinecraft().renderViewEntity$v1_7.rotationYaw
            : Minecraft.getMinecraft().renderViewEntity.rotationYaw;
      } else {
         return (Float)operation1.call(new Object[]{entity0});
      }
   }
}
