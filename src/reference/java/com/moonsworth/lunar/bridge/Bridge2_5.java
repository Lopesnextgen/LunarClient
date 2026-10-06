package com.moonsworth.lunar.bridge;

import java.nio.ByteBuffer;

@com.moonsworth.lunar.ichor.Annotation2(min = 6)
public interface Bridge2_5 {
   void bridge$draw(MixinHelper_21 var1, RenderLayerBridge var2);

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default void bridge$drawWithoutFog(MixinHelper_21 var1, RenderLayerBridge var2) {
      this.bridge$draw(var1, var2);
   }

   default int method1() {
      throw new AbstractMethodErrorImpl();
   }

   Bridge_35 bridge$getVertexBuffer();

   Bridge_63 bridge$getVertexFormat();

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   DrawMode bridge$getVertexFormatMode();

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   Bridge$Type2 bridge$getIndexType();

   default void bridge$bind() {
   }

   default void bridge$unbind() {
   }

   void bridge$close();

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default void bridge$uploadWithBuffers(Object var1, ByteBuffer var2, ByteBuffer byteBuffer, RenderLayerBridge renderLayerBridge) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default void bridge$updateIndexBuffer(ByteBuffer var1) {
      throw new AbstractMethodErrorImpl();
   }
}
