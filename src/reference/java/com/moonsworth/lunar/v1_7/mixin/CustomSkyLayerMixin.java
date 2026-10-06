package com.moonsworth.lunar.v1_7.mixin;

import net.minecraft.client.renderer.Tessellator;
import net.optifine.v1_7.CustomSkyLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CustomSkyLayer.class)
public abstract class CustomSkyLayerMixin {
   @Overwrite
   public void renderSide(Tessellator var1, int var2) {
      double var3 = var2 % 3 / 3.0;
      double var5 = var2 / 3 / 2.0;
      var1.startDrawingQuads();
      var1.addVertexWithUV(-70.0, -70.0, -70.0, var3, var5);
      var1.addVertexWithUV(-70.0, -70.0, 70.0, var3, var5 + 0.5);
      var1.addVertexWithUV(70.0, -70.0, 70.0, var3 + 0.3333333333333333, var5 + 0.5);
      var1.addVertexWithUV(70.0, -70.0, -70.0, var3 + 0.3333333333333333, var5);
      var1.draw();
   }
}
