package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.apollo.module.limb.ArmorPiece;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.client.network.apollo.LimbApolloHandler;
import com.moonsworth.lunar.client.cosmetics.skin.SkullSkinLayerRenderer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Collection;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@VersionGate(min = 1)
@Mixin(LayerCustomHead.class)
public class LayerCustomHeadMixin {
   public LayerCustomHeadMixin() {
   }

   @Inject(method = "doRenderLayer", at = @At("HEAD"), cancellable = true)
   private void lunar$doRenderLayer$HEAD(
      EntityLivingBase entity1, float value2, float value3, float value4, float value5, float value6, float value7, float value8, CallbackInfo callback9
   ) {
      Optional optional10 = Ref.method4().method84().method3(LimbModule.class);
      if (optional10.isPresent()) {
         Collection list11 = (Collection)((LimbApolloHandler)optional10.get()).method6().get(entity1.getUniqueID());
         if (list11 != null && list11.contains(ArmorPiece.HELMET)) {
            callback9.cancel();
            return;
         }
      }

      if (Ref.method4().method40().method84().method41((EntityLivingBridge)entity1)) {
         callback9.cancel();
      }
   }

   @VersionGate(max = 1)
   @Inject(
      method = "doRenderLayer",
      locals = LocalCapture.CAPTURE_FAILHARD,
      at = @At(
         value = "INVOKE",
         target = "net/minecraft/client/renderer/tileentity/TileEntitySkullRenderer.renderSkull(FFFLnet/minecraft/util/EnumFacing;FILcom/mojang/authlib/GameProfile;I)V"
      )
   )
   private void lunar$doRenderLayer$v1_8(
      EntityLivingBase entity1,
      float value2,
      float value3,
      float value4,
      float value5,
      float value6,
      float value7,
      float value8,
      CallbackInfo callback9,
      ItemStack stack10,
      Item item11,
      Minecraft minecraft12,
      boolean flag13,
      float value14,
      GameProfile gameprofile15
   ) {
      SkullSkinLayerRenderer.method3((EntityLivingBridge)entity1, gameprofile15);
   }

   @VersionGate(min = 5)
   @Inject(
      method = "doRenderLayer",
      locals = LocalCapture.CAPTURE_FAILHARD,
      at = @At(
         value = "INVOKE",
         target = "net/minecraft/client/renderer/tileentity/TileEntitySkullRenderer.renderSkull(FFFLnet/minecraft/util/EnumFacing;FILcom/mojang/authlib/GameProfile;IF)V"
      )
   )
   private void lunar$doRenderLayer$v1_12(
      EntityLivingBase entity1,
      float value2,
      float value3,
      float value4,
      float value5,
      float value6,
      float value7,
      float value8,
      CallbackInfo callback9,
      ItemStack stack10,
      Item item11,
      Minecraft minecraft12,
      boolean flag13,
      float value14,
      GameProfile gameprofile15
   ) {
      SkullSkinLayerRenderer.method3((EntityLivingBridge)entity1, gameprofile15);
   }
}
