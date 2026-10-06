package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.RenderStateShardBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Shader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class ShaderRenderStateShard implements RenderStateShardBridge {
   public final Shader field1;
   private int field2 = 0;
   private final boolean[] field3;

   public ShaderRenderStateShard(ShaderRenderState wrapper_91) {
      this.field1 = (Shader)wrapper_91;
      int index2 = Ref.MC_VERSION == 0
         ? this.field1.getShaderManager().samplerNames$v1_7.size()
         : this.field1.getShaderManager().samplerNames.size();
      this.field3 = new boolean[index2];
   }

   public static ShaderRenderStateShard method1(Shader shader0) {
      return new ShaderRenderStateShard((ShaderRenderState)shader0);
   }

   public void bridge$setupState() {
      this.field2 = LegacyRenderTypeFactory.field9.field9;

      for (int index1 = 0; index1 < this.field3.length; index1++) {
         int index2 = ShaderRenderState.method1(index1);
         this.field3[index1] = LegacyRenderTypeFactory.field9.field8[index2];
      }

      ((ShaderRenderState)this.field1).lunar$setupState();
   }

   public void bridge$clearState() {
      ((ShaderRenderState)this.field1).lunar$clearState();

      for (int index1 = this.field3.length - 1; index1 >= 0; index1--) {
         int number2 = ShaderRenderState.method1(index1);
         this.method2(number2, this.field3[index1]);
      }

      this.method2(this.field2, null);
   }

   private void method2(int number1, Boolean flag2) {
      if (LegacyRenderTypeFactory.field9.field9 != number1) {
         if (Ref.MC_VERSION >= 1) {
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + number1);
         } else {
            GL13.glActiveTexture(OpenGlHelper.defaultTexUnit + number1);
         }
      }

      if (flag2 != null) {
         if (flag2 != LegacyRenderTypeFactory.field9.method3()) {
            if (flag2) {
               if (Ref.MC_VERSION >= 1) {
                  GlStateManager.enableTexture2D();
               } else {
                  GL11.glEnable(3553);
               }
            } else if (Ref.MC_VERSION >= 1) {
               GlStateManager.disableTexture2D();
            } else {
               GL11.glDisable(3553);
            }
         }
      }
   }
}
