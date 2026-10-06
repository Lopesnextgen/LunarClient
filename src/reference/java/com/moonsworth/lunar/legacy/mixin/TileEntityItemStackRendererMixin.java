package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.pkg.Pkg6;
import com.moonsworth.lunar.client.mod.combat.shields.Shields;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelShield;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Annotation2(min = 1)
@Mixin(TileEntityItemStackRenderer.class)
public class TileEntityItemStackRendererMixin {
   @Annotation2(max = 1)
   @ModifyVariable(
      method = "renderByItem(Lnet/minecraft/item/ItemStack;)V",
      at = @At(
         value = "INVOKE",
         target = "net/minecraft/client/renderer/tileentity/TileEntitySkullRenderer.renderSkull(FFFLnet/minecraft/util/EnumFacing;FILcom/mojang/authlib/GameProfile;I)V"
      ),
      ordinal = 0
   )
   private GameProfile lunar$renderByItem$v1_8(GameProfile var1, ItemStack var2) {
      Pkg6.method2((ItemStackBridge)var2, var1);
      return var1;
   }

   @Annotation2(min = 5)
   @ModifyVariable(
      method = "renderByItem$v1_12(Lnet/minecraft/item/ItemStack;F)V",
      at = @At(
         value = "INVOKE",
         target = "net/minecraft/client/renderer/tileentity/TileEntitySkullRenderer.renderSkull(FFFLnet/minecraft/util/EnumFacing;FILcom/mojang/authlib/GameProfile;IF)V"
      ),
      ordinal = 0
   )
   private GameProfile lunar$renderByItem$v1_12(GameProfile var1, ItemStack var2, float var3) {
      Pkg6.method2((ItemStackBridge)var2, var1);
      return var1;
   }

   @Annotation2(min = 5)
   @WrapOperation(method = "renderByItem$v1_12", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelShield;render()V"))
   private void lunar$shieldHook(ModelShield var1, Operation<Void> var2, ItemStack var3) {
      WorldClient var4 = Minecraft.getMinecraft().theWorld;
      Shields var5 = ThreadModuleDump63.method4().method40().method91();
      if (var4 != null && var5.isEnabled() && var5.method13().get()) {
         for (EntityPlayer var7 : var4.playerEntities) {
            if (var7 != Minecraft.getMinecraft().thePlayer && var3.equals(var7.getHeldItemMainhand$v1_12()) || var3.equals(var7.getHeldItemOffhand$v1_12())) {
               int var8 = var5.method11(var7.getUniqueID());
               if (var8 != -1) {
                  float var9 = (var8 >> 16 & 0xFF) / 255.0F;
                  float var10 = (var8 >> 8 & 0xFF) / 255.0F;
                  float var11 = (var8 & 0xFF) / 255.0F;
                  float var12 = (var8 >> 24 & 0xFF) / 255.0F;
                  GlStateManager.color(var9, var10, var11, var12);
               }
               break;
            }
         }

         var1.render();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      } else {
         var2.call(new Object[]{var1});
      }
   }
}
