package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public interface RenderPipelineBridge {
   ResourceLocationBridge bridge$location();

   ResourceLocationBridge bridge$vertexShader();

   ResourceLocationBridge bridge$fragmentShader();

   ShaderDefinesBridge bridge$shaderDefines();

   List<String> bridge$samplers();

   List<ShaderUniformDeclaration> bridge$uniforms();

   @Nullable
   BlendFunctionBridge bridge$blendFunction();

   DepthTestFunction bridge$depthTestFunction();

   PolygonMode bridge$polygonMode();

   boolean bridge$cull();

   boolean bridge$writeColor();

   boolean bridge$writeAlpha();

   boolean bridge$writeDepth();

   VertexFormatBridge bridge$vertexFormat();

   DrawMode bridge$vertexFormatMode();

   float bridge$getDepthBiasScaleFactor();

   float bridge$getDepthBiasConstant();

   default void bridge$cleanup() {
   }

   default void bridge$cleanShaders() {
   }

   @VersionGate(min = 8, max = 25)
   default void method1(Supplier<ShaderInstanceBridge> supplier1) {
      throw new AbstractMethodErrorImpl("Shader supplier is not available on the current version");
   }

   @VersionGate(min = 8, max = 25)
   default void method2(String text) {
      throw new AbstractMethodErrorImpl("Shader supplier is not available on the current version");
   }

   @VersionGate(min = 8, max = 25)
   default Supplier<ShaderInstanceBridge> method3() {
      throw new AbstractMethodErrorImpl("Shader supplier is not available on the current version");
   }

   @VersionGate(min = 30)
   default void bridge$setLunarUniforms(List<ShaderUniformDeclaration> list) {
   }

   @VersionGate(min = 30)
   default List<ShaderUniformDeclaration> bridge$getLunarUniforms() {
      return null;
   }
}
