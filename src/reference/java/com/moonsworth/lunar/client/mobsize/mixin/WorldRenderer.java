package com.moonsworth.lunar.client.mobsize.mixin;

import com.moonsworth.lunar.client.util.click.Click2;

public class WorldRenderer extends net.minecraft.client.renderer.WorldRenderer {
   private final net.minecraft.client.renderer.WorldRenderer field1;
   private final Click2 field2;

   public WorldRenderer(net.minecraft.client.renderer.WorldRenderer var1) {
      super(0);
      this.field1 = var1;
      this.field2 = Click2.field1;
   }

   public net.minecraft.client.renderer.WorldRenderer pos(double var1, double var3, double var5) {
      Click2 var7 = this.field2;
      float[] var8 = var7.field2;
      int var9 = var7.size;
      var8[var9] = (float)var1;
      var8[var9 + 1] = (float)var3;
      var8[var9 + 2] = (float)var5;
      var7.size += 3;
      return this;
   }

   public net.minecraft.client.renderer.WorldRenderer tex(double var1, double var3) {
      Click2 var5 = this.field2;
      float[] var6 = var5.field2;
      int var7 = var5.size;
      var6[var7] = (float)var1;
      var6[var7 + 1] = (float)var3;
      var5.size += 2;
      return this;
   }

   public net.minecraft.client.renderer.WorldRenderer color(float var1, float var2, float var3, float var4) {
      int var5 = (int)(var1 * 255.0F) & 0xFF;
      int var6 = ((int)(var2 * 255.0F) & 0xFF) << 8;
      int var7 = ((int)(var3 * 255.0F) & 0xFF) << 16;
      int var8 = ((int)(var4 * 255.0F) & 0xFF) << 24;
      this.field2.field2[this.field2.size++] = Float.intBitsToFloat(var8 | var7 | var6 | var5);
      return this;
   }

   public net.minecraft.client.renderer.WorldRenderer color(int var1, int var2, int var3, int var4) {
      this.field2.field2[this.field2.size++] = Float.intBitsToFloat(var4 << 24 | var3 << 16 | var2 << 8 | var1);
      return this;
   }

   public net.minecraft.client.renderer.WorldRenderer lightmap(int var1, int var2) {
      this.field2.field2[this.field2.size++] = Float.intBitsToFloat(var1 << 16 | var2);
      return this;
   }

   public void endVertex() {
      this.field2.method1(this.vertexFormat.getIntegerSize());
   }

   public void flush() {
      this.field1.begin(this.drawMode, this.vertexFormat);
      this.field1.vertexCount = this.field2.size / this.vertexFormat.getIntegerSize();
      this.field1.growBuffer(0);
      this.field1.byteBuffer.clear();
      this.field1.byteBuffer.asFloatBuffer().put(this.field2.field2, 0, this.field2.size);
      this.field2.size = 0;
   }

   public net.minecraft.client.renderer.WorldRenderer method1() {
      return this.field1;
   }
}
