package com.moonsworth.lunar.client.framework.feature.mobsize.mixin;

import com.moonsworth.lunar.client.render.pipeline.FloatArrayBuilder;

public class WorldRenderer extends net.minecraft.client.renderer.WorldRenderer {
   private final net.minecraft.client.renderer.WorldRenderer wrapped;
   private final FloatArrayBuilder builder;

   public WorldRenderer(net.minecraft.client.renderer.WorldRenderer worldrenderer1) {
      super(0);
      this.wrapped = worldrenderer1;
      this.builder = FloatArrayBuilder.INSTANCE;
   }

   public net.minecraft.client.renderer.WorldRenderer pos(double value1, double value3, double value5) {
      FloatArrayBuilder click27 = this.builder;
      float[] items8 = click27.array;
      int index9 = click27.size;
      items8[index9] = (float)value1;
      items8[index9 + 1] = (float)value3;
      items8[index9 + 2] = (float)value5;
      click27.size += 3;
      return this;
   }

   public net.minecraft.client.renderer.WorldRenderer tex(double value1, double value3) {
      FloatArrayBuilder click25 = this.builder;
      float[] items6 = click25.array;
      int index7 = click25.size;
      items6[index7] = (float)value1;
      items6[index7 + 1] = (float)value3;
      click25.size += 2;
      return this;
   }

   public net.minecraft.client.renderer.WorldRenderer color(float value1, float value2, float value3, float value4) {
      int number5 = (int)(value1 * 255.0F) & 0xFF;
      int number6 = ((int)(value2 * 255.0F) & 0xFF) << 8;
      int number7 = ((int)(value3 * 255.0F) & 0xFF) << 16;
      int number8 = ((int)(value4 * 255.0F) & 0xFF) << 24;
      this.builder.array[this.builder.size++] = Float.intBitsToFloat(number8 | number7 | number6 | number5);
      return this;
   }

   public net.minecraft.client.renderer.WorldRenderer color(int number1, int number2, int number3, int number4) {
      this.builder.array[this.builder.size++] = Float.intBitsToFloat(number4 << 24 | number3 << 16 | number2 << 8 | number1);
      return this;
   }

   public net.minecraft.client.renderer.WorldRenderer lightmap(int number1, int number2) {
      this.builder.array[this.builder.size++] = Float.intBitsToFloat(number1 << 16 | number2);
      return this;
   }

   public void endVertex() {
      this.builder.ensureCapacity(this.vertexFormat.getIntegerSize());
   }

   public void flush() {
      this.wrapped.begin(this.drawMode, this.vertexFormat);
      this.wrapped.vertexCount = this.builder.size / this.vertexFormat.getIntegerSize();
      this.wrapped.growBuffer(0);
      this.wrapped.byteBuffer.clear();
      this.wrapped.byteBuffer.asFloatBuffer().put(this.builder.array, 0, this.builder.size);
      this.builder.size = 0;
   }

   public net.minecraft.client.renderer.WorldRenderer getWrapped() {
      return this.wrapped;
   }
}
