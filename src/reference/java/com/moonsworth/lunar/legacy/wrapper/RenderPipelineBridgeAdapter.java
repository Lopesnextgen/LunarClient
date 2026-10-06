package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge2_15;
import com.moonsworth.lunar.bridge.Bridge2_2;
import com.moonsworth.lunar.bridge.Bridge3_7;
import com.moonsworth.lunar.bridge.PolygonDrawMode;
import com.moonsworth.lunar.bridge.DepthTestMode;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.Bridge_45;
import com.moonsworth.lunar.bridge.Bridge_63;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import lombok.Generated;
import net.minecraft.client.shader.ShaderLoader.ShaderType;
import org.jetbrains.annotations.Nullable;

public class RenderPipelineBridgeAdapter implements Bridge_45 {
   private final ResourceLocationBridge field1;
   private final ResourceLocationBridge field2;
   private final ResourceLocationBridge field3;
   private final Bridge_63 field4;
   private final DrawMode field5;
   private final Bridge2_2 field6;
   private final List<String> field7;
   private final List<Bridge3_7> field8;
   private final DepthTestMode field9;
   private final PolygonDrawMode field10;
   private final boolean field11;
   private final boolean field12;
   private final boolean field13;
   private final boolean field14;
   @Nullable
   private final Bridge2_15 field15;
   private final boolean field16;
   @Nullable
   private final ResourceLocationBridge field17;
   private final float field18;
   private final float field19;

   public ResourceLocationBridge bridge$location() {
      return this.field1;
   }

   public ResourceLocationBridge bridge$vertexShader() {
      return this.field3;
   }

   public ResourceLocationBridge bridge$fragmentShader() {
      return this.field2;
   }

   public Bridge2_2 bridge$shaderDefines() {
      return this.field6;
   }

   public List<String> bridge$samplers() {
      return this.field7;
   }

   public List<Bridge3_7> bridge$uniforms() {
      return this.field8;
   }

   @Nullable
   public Bridge2_15 bridge$blendFunction() {
      return this.field15;
   }

   public DepthTestMode bridge$depthTestFunction() {
      return this.field9;
   }

   public PolygonDrawMode bridge$polygonMode() {
      return this.field10;
   }

   public boolean bridge$cull() {
      return this.field11;
   }

   public boolean bridge$writeColor() {
      return this.field12;
   }

   public boolean bridge$writeAlpha() {
      return this.field13;
   }

   public boolean bridge$writeDepth() {
      return this.field14;
   }

   public Bridge_63 bridge$vertexFormat() {
      return this.field4;
   }

   public DrawMode bridge$vertexFormatMode() {
      return this.field5;
   }

   public float bridge$getDepthBiasScaleFactor() {
      return this.field18;
   }

   public float bridge$getDepthBiasConstant() {
      return this.field19;
   }

   public void bridge$cleanShaders() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         ShaderType.VERTEX.loadedShaders.remove(this.field3.bridge$getPath());
         ShaderType.FRAGMENT.loadedShaders.remove(this.field2.bridge$getPath());
      } else {
         ShaderType.VERTEX.loadedShaders$v1_7.remove(this.field3.bridge$getPath());
         ShaderType.FRAGMENT.loadedShaders$v1_7.remove(this.field2.bridge$getPath());
      }

      if (this.field17 != null) {
         LegacyRenderTypeFactory.method3(this.field17);
      }
   }

   @Generated
   public RenderPipelineBridgeAdapter(
      ResourceLocationBridge var1,
      ResourceLocationBridge var2,
      ResourceLocationBridge var3,
      Bridge_63 var4,
      DrawMode var5,
      Bridge2_2 var6,
      List<String> var7,
      List<Bridge3_7> var8,
      DepthTestMode var9,
      PolygonDrawMode var10,
      boolean var11,
      boolean var12,
      boolean var13,
      boolean var14,
      @Nullable Bridge2_15 var15,
      boolean var16,
      @Nullable ResourceLocationBridge var17,
      float var18,
      float var19
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
      this.field8 = var8;
      this.field9 = var9;
      this.field10 = var10;
      this.field11 = var11;
      this.field12 = var12;
      this.field13 = var13;
      this.field14 = var14;
      this.field15 = var15;
      this.field16 = var16;
      this.field17 = var17;
      this.field18 = var18;
      this.field19 = var19;
   }

   @Generated
   public boolean method1() {
      return this.field16;
   }

   @Nullable
   @Generated
   public ResourceLocationBridge method2() {
      return this.field17;
   }
}
