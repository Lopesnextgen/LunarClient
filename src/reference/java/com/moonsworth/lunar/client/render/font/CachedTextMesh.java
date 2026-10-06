package com.moonsworth.lunar.client.render.font;

import java.util.List;

public class CachedTextMesh {
   private final List<TextRenderPass> passes;
   private final float field1;
   private long field2;
   private final boolean field3;

   public CachedTextMesh(List<TextRenderPass> list, float value, long value2, boolean flag) {
      this.passes = list;
      this.field1 = value;
      this.field2 = value2;
      this.field3 = flag;
   }

   public void method1() {
      for (TextRenderPass rewindhandlers22 : this.passes) {
         rewindhandlers22.method1();
      }
   }

   public void delete() {
      for (TextRenderPass rewindhandlers22 : this.passes) {
         rewindhandlers22.delete();
      }
   }

   public float getWidth() {
      return this.field1;
   }

   public long method2() {
      return this.field2;
   }

   public void method3(long value) {
      this.field2 = value;
   }

   public boolean method4() {
      return this.field3;
   }
}
