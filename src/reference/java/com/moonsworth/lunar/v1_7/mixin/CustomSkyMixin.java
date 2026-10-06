package com.moonsworth.lunar.v1_7.mixin;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.world.World;
import net.optifine.render.Blender;
import net.optifine.v1_7.CustomSky;
import net.optifine.v1_7.CustomSkyLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CustomSky.class)
public abstract class CustomSkyMixin {
   @Shadow
   public static CustomSkyLayer[][] worldSkyLayers;

   @Overwrite
   public static void renderSky(World var0, TextureManager var1, float var2) {
      if (worldSkyLayers != null) {
         int var3 = var0.provider.dimensionId;
         if (var3 >= 0 && var3 < worldSkyLayers.length) {
            CustomSkyLayer[] var4 = worldSkyLayers[var3];
            if (var4 != null) {
               long var5 = var0.getWorldTime();
               int var7 = (int)(var5 % 24000L);
               float var8 = var0.getCelestialAngle(var2);
               float var9 = var0.getRainStrength(var2);
               float var10 = var0.getWeightedThunderStrength(var2);
               if (var9 > 0.0F) {
                  var10 /= var9;
               }

               for (CustomSkyLayer var14 : var4) {
                  if (var14.isActive(var0, var7)) {
                     var14.render(var0, var7, var8, var9, var10);
                  }
               }

               float var15 = 1.0F - var9;
               Blender.clearBlend(var15);
            }
         }
      }
   }
}
