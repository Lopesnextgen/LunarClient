package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public interface Bridge_45 {
   ResourceLocationBridge bridge$location();

   ResourceLocationBridge bridge$vertexShader();

   ResourceLocationBridge bridge$fragmentShader();

   Bridge2_2 bridge$shaderDefines();

   List<String> bridge$samplers();

   List<Bridge3_7> bridge$uniforms();

   @Nullable
   Bridge2_15 bridge$blendFunction();

   DepthTestMode bridge$depthTestFunction();

   PolygonDrawMode bridge$polygonMode();

   boolean bridge$cull();

   boolean bridge$writeColor();

   boolean bridge$writeAlpha();

   boolean bridge$writeDepth();

   Bridge_63 bridge$vertexFormat();

   DrawMode bridge$vertexFormatMode();

   float bridge$getDepthBiasScaleFactor();

   float bridge$getDepthBiasConstant();

   default void bridge$cleanup() {
   }

   default void bridge$cleanShaders() {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8, max = 25)
   default void method1(Supplier<Bridge_44> var1) {
      throw new AbstractMethodErrorImpl("Shader supplier is not available on the current version");
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8, max = 25)
   default void method2(String var1) {
      throw new AbstractMethodErrorImpl("Shader supplier is not available on the current version");
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8, max = 25)
   default Supplier<Bridge_44> method3() {
      throw new AbstractMethodErrorImpl("Shader supplier is not available on the current version");
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 30)
   default void bridge$setLunarUniforms(List<Bridge3_7> var1) {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 30)
   default List<Bridge3_7> bridge$getLunarUniforms() {
      return null;
   }
}
