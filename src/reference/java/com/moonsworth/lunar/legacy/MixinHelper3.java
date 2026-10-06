package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public final class MixinHelper3 {
   public static void method1(boolean flag) {
      if (ThreadModuleDump63.MC_VERSION <= 1) {
         GL11.glScalef(2.5F, 2.5F, 2.5F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.56F, 0.52F, 0.71999997F);
      } else {
         float var1 = flag ? -1.0F : 1.0F;
         GlStateManager.translate(var1 * -0.56F, 0.52F, 0.72F);
         GlStateManager.scale(2.5F, 2.5F, 2.5F);
      }
   }

   @Generated
   private MixinHelper3() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
