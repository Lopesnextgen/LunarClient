package com.moonsworth.lunar.client.render.font;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public class TextRenderCache extends Object2ObjectOpenHashMap<TextRenderCache.CacheKey, CachedTextMesh> {
   private TextRenderCache.CacheKey field1 = new TextRenderCache.CacheKey(null, 0, false);

   public TextRenderCache() {
   }

   public CachedTextMesh method1(String text1, int number2, boolean flag3) {
      this.field1.string = text1;
      this.field1.color = number2;
      this.field1.shadow = flag3;
      return (CachedTextMesh)this.get(this.field1);
   }

   public static class CacheKey {
      private String string;
      private int color;
      private boolean shadow;

      public CacheKey(String text1, int number2, boolean flag3) {
         this.string = text1;
         this.color = number2;
         this.shadow = flag3;
      }

      @Override
      public boolean equals(Object object) {
         if (this == object) {
            return true;
         } else if (object != null && this.getClass() == object.getClass()) {
            TextRenderCache.CacheKey data2 = (TextRenderCache.CacheKey)object;
            return this.color == data2.color && this.shadow == data2.shadow && this.string.equals(data2.string);
         } else {
            return false;
         }
      }

      @Override
      public int hashCode() {
         int number1 = this.string.hashCode();
         number1 = 31 * number1 + this.color;
         return 31 * number1 + (this.shadow ? 1 : 0);
      }
   }
}
