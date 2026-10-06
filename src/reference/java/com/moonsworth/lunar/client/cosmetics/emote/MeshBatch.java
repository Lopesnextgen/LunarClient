package com.moonsworth.lunar.client.cosmetics.emote;
import com.moonsworth.lunar.client.cosmetics.gecko.VertexSink;

public abstract class MeshBatch implements VertexSink {
   private static final int[] field1 = new int[]{0, 3, 1, 2};
   private final float[][] field2 = new float[4][8];
   private boolean field3 = false;
   private boolean field4 = false;
   private boolean field5 = false;
   private int index = 0;

   public void method1(float var1, float var2) {
      this.field3 = true;
      this.field2[this.index][0] = var1;
      this.field2[this.index][1] = var2;
      this.method4();
   }

   public void method2(float var1, float var2, float var3) {
      this.field4 = true;
      this.field2[this.index][2] = var1;
      this.field2[this.index][3] = var2;
      this.field2[this.index][4] = var3;
      this.method4();
   }

   public void method3(float var1, float var2, float var3) {
      this.field5 = true;
      this.field2[this.index][5] = var1;
      this.field2[this.index][6] = var2;
      this.field2[this.index][7] = var3;
      this.method4();
   }

   public void method4() {
      if (this.field4 && this.field3 && this.field5) {
         this.field4 = false;
         this.field3 = false;
         this.field5 = false;
         this.index++;
         if (this.index >= 4) {
            this.index = 0;

            for (int var4 : field1) {
               this.method5(
                  this.field2[var4][0],
                  this.field2[var4][1],
                  this.field2[var4][2],
                  this.field2[var4][3],
                  this.field2[var4][4],
                  this.field2[var4][5],
                  this.field2[var4][6],
                  this.field2[var4][7]
               );
            }

            this.COCHRCOHCHCOHOOIRRROOOOCCCORHR();
         }
      }
   }

   public static MeshBatch method5(final VertexSink var0) {
      return new MeshBatch() {
         @Override
         public void method1(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
            var0.method1(var1, var2, var3, var4, var5, var6, var7, var8);
         }

         @Override
         public void method2() {
            var0.method2();
         }
      };
   }
}
