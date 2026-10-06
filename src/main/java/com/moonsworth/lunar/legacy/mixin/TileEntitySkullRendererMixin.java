package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(TileEntitySkullRenderer.class)
public class TileEntitySkullRendererMixin {
   public TileEntitySkullRendererMixin() {
   }

   @VersionGate(1)
   @Inject(
      method = "renderSkull$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V")
   )
   public void lunar$onTileEntitySkullRender(
      float value, float value2, float value3, EnumFacing facing4, float value4, int value5, GameProfile gameprofile7, int value6, CallbackInfo callback9
   ) {
      try {
         if (value5 == 3 && gameprofile7 != null) {
            Minecraft minecraft10 = Minecraft.getMinecraft();
            Map map11 = minecraft10.getSkinManager().loadSkinFromCache(gameprofile7);
            if (map11 != null && map11.containsKey(Type.SKIN)) {
               GL11.glEnable(3042);
               GL14.glBlendEquation(32774);
               GL11.glBlendFunc(770, 771);
            }
         }
      } catch (IllegalArgumentException illegalargumentexception12) {
      }
   }
}
