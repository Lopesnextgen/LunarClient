package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.RenderStateLifecycleBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Shader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class Bridge19Handler implements RenderStateLifecycleBridge {
   public final Shader field1;
   private int field2 = 0;
   private final boolean[] field3;

   public Bridge19Handler(ShaderStateLifecycle var1) {
      this.field1 = (Shader)var1;
      int var2 = ThreadModuleDump63.MC_VERSION == 0
         ? this.field1.getShaderManager().samplerNames$v1_7.size()
         : this.field1.getShaderManager().samplerNames.size();
      this.field3 = new boolean[var2];
   }

   public static Bridge19Handler method1(Shader var0) {
      return new Bridge19Handler((ShaderStateLifecycle)var0);
   }

   public void bridge$setupState() {
      this.field2 = LegacyRenderTypeFactory.field9.field9;

      for (int var1 = 0; var1 < this.field3.length; var1++) {
         int var2 = ShaderStateLifecycle.method1(var1);
         this.field3[var1] = LegacyRenderTypeFactory.field9.field8[var2];
      }

      ((ShaderStateLifecycle)this.field1).lunar$setupState();
   }

   public void bridge$clearState() {
      ((ShaderStateLifecycle)this.field1).lunar$clearState();

      for (int var1 = this.field3.length - 1; var1 >= 0; var1--) {
         int var2 = ShaderStateLifecycle.method1(var1);
         this.method2(var2, this.field3[var1]);
      }

      this.method2(this.field2, null);
   }

   private void method2(int var1, Boolean var2) {
      if (LegacyRenderTypeFactory.field9.field9 != var1) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + var1);
         } else {
            GL13.glActiveTexture(OpenGlHelper.defaultTexUnit + var1);
         }
      }

      if (var2 != null) {
         if (var2 != LegacyRenderTypeFactory.field9.method3()) {
            if (var2) {
               if (ThreadModuleDump63.MC_VERSION >= 1) {
                  GlStateManager.enableTexture2D();
               } else {
                  GL11.glEnable(3553);
               }
            } else if (ThreadModuleDump63.MC_VERSION >= 1) {
               GlStateManager.disableTexture2D();
            } else {
               GL11.glDisable(3553);
            }
         }
      }
   }
}
