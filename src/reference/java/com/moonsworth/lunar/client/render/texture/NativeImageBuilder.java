package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.BatchingBufferSourceBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.ichor.Annotation2;

public interface NativeImageBuilder {
   NativeImageBuilder method1();

   NativeImageBuilder method2(float var1, float var2, float var3);

   NativeImageBuilder method3(float var1, float var2);

   NativeImageBuilder method4(float var1, float var2, float var3);

   NativeImageBuilder method5();

   NativeImageBuilder method6();

   @Annotation2(max = 5)
   static NativeImageBuilder.Extension method7() {
      return method8(AbstractRenderContext.method32());
   }

   static NativeImageBuilder.Extension method8(AbstractRenderContext var0) {
      return var1 -> new NativeImageBuilder.Data2(var0.method10(var1));
   }

   static NativeImageBuilder.Extension method9(BatchingBufferSourceBridge var0) {
      return var1 -> new NativeImageBuilder.Data((Bridge4_6)var0.bridge$getBuffer(var1).orElseThrow());
   }

   class Data implements NativeImageBuilder {
      private final Bridge4_6 field1;

      private Data(Bridge4_6 var1) {
         this.field1 = var1;
      }

      @Override
      public NativeImageBuilder method1() {
         return this;
      }

      @Override
      public NativeImageBuilder method2(float var1, float var2, float var3) {
         this.field1.bridge$vertex(var1, var2, var3);
         return this;
      }

      @Override
      public NativeImageBuilder method3(float var1, float var2) {
         this.field1.bridge$uv(var1, var2);
         return this;
      }

      @Override
      public NativeImageBuilder method4(float var1, float var2, float var3) {
         this.field1.bridge$normal(var1, var2, var3);
         return this;
      }

      @Override
      public NativeImageBuilder method5() {
         this.field1.bridge$endVertex();
         return this;
      }

      @Override
      public NativeImageBuilder method6() {
         return this;
      }
   }

   class Data2 implements NativeImageBuilder {
      private final Bridge2_32 field1;

      private Data2(Bridge2_32 var1) {
         this.field1 = var1;
      }

      @Override
      public NativeImageBuilder method1() {
         this.field1.method1();
         return this;
      }

      @Override
      public NativeImageBuilder method2(float var1, float var2, float var3) {
         this.field1.method2(var1, var2, var3);
         return this;
      }

      @Override
      public NativeImageBuilder method3(float var1, float var2) {
         this.field1.method10(var1, var2);
         return this;
      }

      @Override
      public NativeImageBuilder method4(float var1, float var2, float var3) {
         this.field1.method14(var1, var2, var3);
         return this;
      }

      @Override
      public NativeImageBuilder method5() {
         this.field1.method16();
         return this;
      }

      @Override
      public NativeImageBuilder method6() {
         this.field1.method17(BufferBuildMode.BATCHED);
         return this;
      }
   }

   @FunctionalInterface
   interface Extension {
      NativeImageBuilder build(RenderLayerBridge var1);
   }
}
