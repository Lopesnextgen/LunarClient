package com.moonsworth.lunar.bridge;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface RenderLayerBridge {
   String bridge$getName();

   default boolean bridge$sortOnUpload() {
      throw new AbstractMethodErrorImpl();
   }

   default int bridge$bufferSize() {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 39)
   default void bridge$bufferSize(int var1) {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$setupRenderState() {
   }

   default void bridge$clearRenderState() {
   }

   DrawMode bridge$getVertexFormatMode();

   Bridge_63 bridge$getVertexFormat();

   default TransparencyMode bridge$getTransparencyType() {
      return TransparencyMode.GENERAL_TRANSPARENT;
   }

   @NotNull
   Bridge_45 bridge$getRenderPipeline();

   Optional<Bridge6_8> bridge$getShaderUniforms();

   @com.moonsworth.lunar.ichor.Annotation2(min = 29)
   default void bridge$setupVirtualShaderUniforms(Bridge3_3 var1) {
   }

   default Optional<RenderLayerBridge> bridge$getOutline() {
      return Optional.empty();
   }

   default boolean bridge$isOutline() {
      return false;
   }

   default void bridge$uncache() {
   }
}
