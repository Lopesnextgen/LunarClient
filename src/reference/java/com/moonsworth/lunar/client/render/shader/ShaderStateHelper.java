package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.Bridge5_13;
import com.moonsworth.lunar.bridge.ShaderManagerBridge;
import com.moonsworth.lunar.bridge.GlMatrixMode;
import com.moonsworth.lunar.bridge.optifine.ShadersBridge;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.render.shader.ShaderUniform;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.ScreenProjection;
import com.moonsworth.lunar.client.render.shader.ShaderDefinition;
import java.util.function.Consumer;
import lombok.Generated;
import org.joml.Matrix4f;
import com.moonsworth.lunar.client.cosmetics.gecko.VertexBuilder;

public class ShaderStateHelper {
   private static Matrix4f field1 = new Matrix4f();
   public static Matrix4f field2 = new Matrix4f();
   private boolean field3 = false;
   private Consumer<VertexBuilder> field4 = null;

   public ShaderStateHelper() {
   }

   public void method1() {
      this.field3 = false;
      if (method6() && Bridge.getMinecraftVersion().method21()) {
         float value1 = Fov3.method32();
         float value2 = Fov3.method33();
         RenderSystemBridge bridge123 = Bridge.method42();
         bridge123.method24(GlMatrixMode.GL_PROJECTION.getId());
         bridge123.method4();
         bridge123.bridge$scale(value1, -value2, 1.0F);
         bridge123.bridge$translate(0.5F, -0.5F, 0.0F);
         bridge123.method24(GlMatrixMode.GL_MODELVIEW.getId());
         this.field3 = true;
      }
   }

   public void method2(ShaderManagerBridge bridge6_81, ShaderDefinition alert62) {
      if (Ref.MC_VERSION >= 6) {
         Bridge5_13 bridge5_133 = bridge6_81.bridge$getShaderUniform("LunarModelViewMat");
         Bridge5_13 bridge5_134 = bridge6_81.bridge$getShaderUniform("LunarNormalMat");
         if (bridge5_133 != null || bridge5_134 != null) {
            this.method3(bridge5_133, bridge5_134);
         }

         if (alert62.method2() == ShaderUniform.field3) {
            Bridge5_13 bridge5_135 = bridge6_81.bridge$getShaderUniform("LunarProjectionMat");
            if (bridge5_135 != null) {
               if (method6()) {
                  float value6 = Fov3.method32();
                  float value7 = Fov3.method33();
                  Matrix4f matrix4f8 = new Matrix4f(ScreenProjection.projectionMatrix);
                  matrix4f8.scale(value6, -value7, 1.0F);
                  matrix4f8.translate(0.5F, -0.5F, 0.0F);
                  bridge5_135.method1(matrix4f8);
               } else {
                  bridge5_135.method1(ScreenProjection.worldProjectionMatrix);
               }
            }
         }
      } else {
         Bridge5_13 bridge5_139 = bridge6_81.bridge$getShaderUniform("LunarLegacyUISize");
         if (bridge5_139 != null) {
            if (method6()) {
               bridge5_139.bridge$set(Fov3.method32(), Fov3.method33());
            } else {
               bridge5_139.bridge$set(-1.0F, -1.0F);
            }
         }
      }
   }

   private void method3(Bridge5_13 bridge5_131, Bridge5_13 bridge5_132) {
      Matrix4f matrix4f3 = new Matrix4f();
      Matrix4f matrix4f4 = new Matrix4f();
      if (method6()) {
         float value5 = Fov3.method32();
         float value6 = Fov3.method33();
         matrix4f3 = new Matrix4f(field2);
         matrix4f3.scale(1.0F / value5, 1.0F / value6, 0.0F);
         matrix4f3.m30(matrix4f3.m30() * 1.0F / value5 - 0.5F);
         matrix4f3.m31(matrix4f3.m31() * 1.0F / value6 - 0.5F);
         matrix4f3.m11(-matrix4f3.m11());
         matrix4f3.m31(-matrix4f3.m31());
      } else if (this.method5()) {
         matrix4f3.set(field1);
         matrix4f3.normal(matrix4f4);
      }

      if (bridge5_131 != null) {
         bridge5_131.method1(matrix4f3);
      }

      if (bridge5_132 != null) {
         bridge5_132.method1(matrix4f4);
      }
   }

   public void method4() {
      if (this.field3) {
         RenderSystemBridge bridge121 = Bridge.method42();
         bridge121.method24(GlMatrixMode.GL_PROJECTION.getId());
         bridge121.method5();
         bridge121.method24(GlMatrixMode.GL_MODELVIEW.getId());
      }
   }

   private boolean method5() {
      return Ref.MC_VERSION > 21;
   }

   public static boolean method6() {
      return Fov3.method28();
   }

   public static boolean method7() {
      return Bridge.method5().map(arg0 -> {
         ShadersBridge slayer31 = arg0.getShaders();
         String text2 = slayer31.getShaderPack();
         return text2 != null && !slayer31.getPackNone().equals(text2);
      }).orElse(false);
   }

   public static boolean method8() {
      return ExternalLinkRegistry.method2(Fishing2Extension.class)
         .map(arg0 -> arg0.lunar$areShadersEnabledInConfig() && !"(off)".equals(arg0.lunar$getShaderPack()))
         .orElse(false);
   }

   public static void method9(Matrix4f matrix4f0) {
      field1 = matrix4f0;
   }

   @Generated
   public void method10(Consumer<VertexBuilder> consumer1) {
      this.field4 = consumer1;
   }

   @Generated
   public Consumer<VertexBuilder> method11() {
      return this.field4;
   }
}
