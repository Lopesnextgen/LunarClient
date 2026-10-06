package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface Bridge$Extension {
   @Nullable
   ResourceLocationBridge bridge$vertexShader();

   @Nullable
   ResourceLocationBridge bridge$fragmentShader();

   @Nullable
   Bridge2_2 bridge$shaderDefines();

   @Nullable
   List<String> bridge$samplers();

   @Nullable
   List<Bridge3_7> bridge$uniforms();

   @Nullable
   Bridge2_15 bridge$blendFunction();

   @Nullable
   DepthTestMode bridge$depthTestFunction();

   @Nullable
   PolygonDrawMode bridge$polygonMode();

   @Nullable
   Boolean bridge$cull();

   @Nullable
   Boolean bridge$writeColor();

   @Nullable
   Boolean bridge$writeAlpha();

   @Nullable
   Boolean bridge$writeDepth();

   @Nullable
   Bridge_63 bridge$vertexFormat();

   @Nullable
   DrawMode bridge$vertexFormatMode();
}
