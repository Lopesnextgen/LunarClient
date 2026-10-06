package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.feature.pkg.Pkg6;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.ModelSkeletonHeadImpl;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer_v1_7;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(max = 0)
@Mixin(TileEntitySkullRenderer_v1_7.class)
public class TileEntitySkullRenderer_v1_7Mixin {
   @Inject(
      method = "func_152674_a",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"),
      cancellable = true
   )
   public void lunar$onTileEntitySkullRender(float var1, float var2, float var3, int var4, float var5, int var6, GameProfile var7, CallbackInfo var8) {
      if (var6 == 3 && var7 != null) {
         Minecraft var9 = Minecraft.getMinecraft();
         Map var10 = var9.getSkinManager().loadSkinFromCache(var7);
         if (var10 != null && var10.containsKey(Type.SKIN)) {
            MinecraftProfileTexture var11 = (MinecraftProfileTexture)var10.get(Type.SKIN);
            ResourceLocation var12 = var9.getSkinManager().loadSkin(var11, Type.SKIN);
            ThreadDownloadImageData var13 = AbstractClientPlayer.getDownloadImageSkin(var12, var7.getName());
            GL11.glEnable(3042);
            GL14.glBlendEquation(32774);
            GL11.glBlendFunc(770, 771);
            ModelSkeletonHeadImpl var14;
            if (var13.textureUploaded && var13.bufferedImage.getHeight() > 32) {
               var14 = ModelSkeletonHeadImpl.field1;
            } else {
               var14 = ModelSkeletonHeadImpl.field2;
            }

            var14.setRotationAngles(0.0F, 0.0F, 0.0F, var5, 0.0F, 0.0625F, null);
            Pkg6.method4(var7, (Bridge2_46)var14.field3, var14);
            var14.render(null, 0.0F, 0.0F, 0.0F, var5, 0.0F, 0.0625F);
            Pkg6.method6(AbstractRenderContext.method32(), var7, var5, 0.0F, var14);
            GL11.glPopMatrix();
            var8.cancel();
         }
      }
   }
}
