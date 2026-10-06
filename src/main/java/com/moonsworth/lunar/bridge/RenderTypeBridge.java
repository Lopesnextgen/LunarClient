package com.moonsworth.lunar.bridge;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface RenderTypeBridge {
   String bridge$getName();

   default boolean bridge$sortOnUpload() {
      throw new AbstractMethodErrorImpl();
   }

   default int bridge$bufferSize() {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.VersionGate(min = 39)
   default void bridge$bufferSize(int value) {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$setupRenderState() {
   }

   default void bridge$clearRenderState() {
   }

   DrawMode bridge$getVertexFormatMode();

   VertexFormatBridge bridge$getVertexFormat();

   default TransparencyType bridge$getTransparencyType() {
      return TransparencyType.GENERAL_TRANSPARENT;
   }

   @NotNull
   RenderPipelineBridge bridge$getRenderPipeline();

   Optional<ShaderManagerBridge> bridge$getShaderUniforms();

   @com.moonsworth.lunar.ichor.VersionGate(min = 29)
   default void bridge$setupVirtualShaderUniforms(Bridge3_3 bridge3_31) {
   }

   default Optional<RenderTypeBridge> bridge$getOutline() {
      return Optional.empty();
   }

   default boolean bridge$isOutline() {
      return false;
   }

   default void bridge$uncache() {
   }
}
