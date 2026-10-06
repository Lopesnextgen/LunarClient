package com.moonsworth.lunar.client.util.alert;

import com.moonsworth.lunar.bridge.GlslUniformType;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.shader.ShaderCatalog;
import com.moonsworth.lunar.client.render.shader.ShaderKey;
import com.moonsworth.lunar.client.render.shader.ShaderUniform;
import com.moonsworth.lunar.client.render.shader.ShaderResource;
import com.moonsworth.lunar.client.render.shader.GlslBuiltin;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.function.BiConsumer;
import lombok.Generated;

public final class Alert6 {
   private final ResourceLocationBridge texture;
   private final ShaderKey fragmentShader;
   private final ShaderKey vertexShader;
   private final List<GlslBuiltin> uniforms;
   private final List<ShaderResource> samplers;
   private final boolean renderOnTick;
   private final int renderWidth;
   private final int renderHeight;
   private String vertexSource;
   private String fragmentSource;

   public boolean isPerPlayer() {
      if (this.getPipeline().method3()) {
         return true;
      }

      for (GlslBuiltin var2 : this.uniforms) {
         if (var2.isDifferentPerPlayer()) {
            return true;
         }
      }

      return false;
   }

   public ShaderUniform getPipeline() {
      if (this.vertexShader.equals(ShaderCatalog.field5)) {
         return ShaderUniform.field3;
      } else {
         return this.vertexShader.equals(ShaderCatalog.field4) ? ShaderUniform.field2 : ShaderUniform.field1;
      }
   }

   public static void declareUniforms(ShaderUniform colorsaturation4, BiConsumer<String, GlslUniformType> var1) {
      if (colorsaturation4.method3()) {
         if (ThreadModuleDump63.MC_VERSION >= 6) {
            var1.accept("LunarModelViewMat", GlslUniformType.MATRIX4X4);
            var1.accept("LunarNormalMat", GlslUniformType.MATRIX4X4);
            if (colorsaturation4 == ShaderUniform.field3) {
               var1.accept("LunarProjectionMat", GlslUniformType.MATRIX4X4);
            }
         } else {
            var1.accept("LunarLegacyUISize", GlslUniformType.VEC2);
         }
      }
   }

   @Generated
   public Alert6(
      ResourceLocationBridge var1,
      ShaderKey var2,
      ShaderKey var3,
      List<GlslBuiltin> var4,
      List<ShaderResource> var5,
      boolean var6,
      int var7,
      int var8
   ) {
      this.texture = var1;
      this.fragmentShader = var2;
      this.vertexShader = var3;
      this.uniforms = var4;
      this.samplers = var5;
      this.renderOnTick = var6;
      this.renderWidth = var7;
      this.renderHeight = var8;
   }

   @Generated
   public ResourceLocationBridge getTexture() {
      return this.texture;
   }

   @Generated
   public ShaderKey getFragmentShader() {
      return this.fragmentShader;
   }

   @Generated
   public ShaderKey getVertexShader() {
      return this.vertexShader;
   }

   @Generated
   public List<GlslBuiltin> getUniforms() {
      return this.uniforms;
   }

   @Generated
   public List<ShaderResource> getSamplers() {
      return this.samplers;
   }

   @Generated
   public boolean isRenderOnTick() {
      return this.renderOnTick;
   }

   @Generated
   public int getRenderWidth() {
      return this.renderWidth;
   }

   @Generated
   public int getRenderHeight() {
      return this.renderHeight;
   }

   @Generated
   public String getVertexSource() {
      return this.vertexSource;
   }

   @Generated
   public String getFragmentSource() {
      return this.fragmentSource;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Alert6 var2)) {
         return false;
      } else {
         if (this.isRenderOnTick() != var2.method9()) {
            return false;
         }

         if (this.getRenderWidth() != var2.method10()) {
            return false;
         }

         if (this.getRenderHeight() != var2.method11()) {
            return false;
         }

         ResourceLocationBridge var3 = this.getTexture();
         ResourceLocationBridge var4 = var2.method4();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            ShaderKey var5 = this.getFragmentShader();
            ShaderKey var6 = var2.method5();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               ShaderKey var7 = this.getVertexShader();
               ShaderKey var8 = var2.method6();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  List var9 = this.getUniforms();
                  List var10 = var2.method7();
                  if (var9 == null ? var10 == null : var9.equals(var10)) {
                     List var11 = this.getSamplers();
                     List var12 = var2.method8();
                     if (var11 == null ? var12 == null : var11.equals(var12)) {
                        String var13 = this.getVertexSource();
                        String var14 = var2.method12();
                        if (var13 == null ? var14 == null : var13.equals(var14)) {
                           String var15 = this.getFragmentSource();
                           String var16 = var2.method13();
                           return var15 == null ? var16 == null : var15.equals(var16);
                        } else {
                           return false;
                        }
                     } else {
                        return false;
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + (this.isRenderOnTick() ? 79 : 97);
      var2 = var2 * 59 + this.getRenderWidth();
      var2 = var2 * 59 + this.getRenderHeight();
      ResourceLocationBridge var3 = this.getTexture();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      ShaderKey var4 = this.getFragmentShader();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      ShaderKey var5 = this.getVertexShader();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      List var6 = this.getUniforms();
      var2 = var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      List var7 = this.getSamplers();
      var2 = var2 * 59 + (var7 == null ? 43 : var7.hashCode());
      String var8 = this.getVertexSource();
      var2 = var2 * 59 + (var8 == null ? 43 : var8.hashCode());
      String var9 = this.getFragmentSource();
      return var2 * 59 + (var9 == null ? 43 : var9.hashCode());
   }

   @Generated
   public Alert6 setVertexSource(String var1) {
      this.vertexSource = var1;
      return this;
   }

   @Generated
   public Alert6 setFragmentSource(String var1) {
      this.fragmentSource = var1;
      return this;
   }
}
