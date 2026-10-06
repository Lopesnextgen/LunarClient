package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.BlendFunctionBridge;
import com.moonsworth.lunar.bridge.ShaderDefinesBridge;
import com.moonsworth.lunar.bridge.ShaderUniformDeclaration;
import com.moonsworth.lunar.bridge.PolygonMode;
import com.moonsworth.lunar.bridge.DepthTestFunction;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.RenderPipelineBridge;
import com.moonsworth.lunar.bridge.VertexFormatBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import lombok.Generated;
import net.minecraft.client.shader.ShaderLoader.ShaderType;
import org.jetbrains.annotations.Nullable;

public class ShaderBridgeImpl implements RenderPipelineBridge {
   private final ResourceLocationBridge field1;
   private final ResourceLocationBridge field2;
   private final ResourceLocationBridge field3;
   private final VertexFormatBridge field4;
   private final DrawMode field5;
   private final ShaderDefinesBridge field6;
   private final List<String> field7;
   private final List<ShaderUniformDeclaration> field8;
   private final DepthTestFunction field9;
   private final PolygonMode field10;
   private final boolean field11;
   private final boolean field12;
   private final boolean field13;
   private final boolean field14;
   @Nullable
   private final BlendFunctionBridge field15;
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

   public ShaderDefinesBridge bridge$shaderDefines() {
      return this.field6;
   }

   public List<String> bridge$samplers() {
      return this.field7;
   }

   public List<ShaderUniformDeclaration> bridge$uniforms() {
      return this.field8;
   }

   @Nullable
   public BlendFunctionBridge bridge$blendFunction() {
      return this.field15;
   }

   public DepthTestFunction bridge$depthTestFunction() {
      return this.field9;
   }

   public PolygonMode bridge$polygonMode() {
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

   public VertexFormatBridge bridge$vertexFormat() {
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
      if (Ref.MC_VERSION >= 1) {
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
   public ShaderBridgeImpl(
      ResourceLocationBridge horsestats141,
      ResourceLocationBridge horsestats142,
      ResourceLocationBridge horsestats143,
      VertexFormatBridge bridge_634,
      DrawMode bridgetype_65,
      ShaderDefinesBridge bridge2_26,
      List<String> list7,
      List<ShaderUniformDeclaration> list8,
      DepthTestFunction bridgetype_129,
      PolygonMode bridgetype310,
      boolean flag11,
      boolean flag12,
      boolean flag13,
      boolean flag14,
      @Nullable BlendFunctionBridge bridge2_1515,
      boolean flag16,
      @Nullable ResourceLocationBridge horsestats1417,
      float value18,
      float value19
   ) {
      this.field1 = horsestats141;
      this.field2 = horsestats142;
      this.field3 = horsestats143;
      this.field4 = bridge_634;
      this.field5 = bridgetype_65;
      this.field6 = bridge2_26;
      this.field7 = list7;
      this.field8 = list8;
      this.field9 = bridgetype_129;
      this.field10 = bridgetype310;
      this.field11 = flag11;
      this.field12 = flag12;
      this.field13 = flag13;
      this.field14 = flag14;
      this.field15 = bridge2_1515;
      this.field16 = flag16;
      this.field17 = horsestats1417;
      this.field18 = value18;
      this.field19 = value19;
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
