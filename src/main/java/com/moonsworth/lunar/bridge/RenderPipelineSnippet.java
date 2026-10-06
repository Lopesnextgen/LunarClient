package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface RenderPipelineSnippet {
   @Nullable
   ResourceLocationBridge bridge$vertexShader();

   @Nullable
   ResourceLocationBridge bridge$fragmentShader();

   @Nullable
   ShaderDefinesBridge bridge$shaderDefines();

   @Nullable
   List<String> bridge$samplers();

   @Nullable
   List<ShaderUniformDeclaration> bridge$uniforms();

   @Nullable
   BlendFunctionBridge bridge$blendFunction();

   @Nullable
   DepthTestFunction bridge$depthTestFunction();

   @Nullable
   PolygonMode bridge$polygonMode();

   @Nullable
   Boolean bridge$cull();

   @Nullable
   Boolean bridge$writeColor();

   @Nullable
   Boolean bridge$writeAlpha();

   @Nullable
   Boolean bridge$writeDepth();

   @Nullable
   VertexFormatBridge bridge$vertexFormat();

   @Nullable
   DrawMode bridge$vertexFormatMode();
}
