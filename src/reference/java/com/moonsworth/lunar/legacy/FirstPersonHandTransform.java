package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public final class FirstPersonHandTransform {
   public static void method1(boolean flag) {
      if (Ref.MC_VERSION <= 1) {
         GL11.glScalef(2.5F, 2.5F, 2.5F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.56F, 0.52F, 0.71999997F);
      } else {
         float value1 = flag ? -1.0F : 1.0F;
         GlStateManager.translate(value1 * -0.56F, 0.52F, 0.72F);
         GlStateManager.scale(2.5F, 2.5F, 2.5F);
      }
   }

   @Generated
   private FirstPersonHandTransform() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
