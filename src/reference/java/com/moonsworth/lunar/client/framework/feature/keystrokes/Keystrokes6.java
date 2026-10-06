package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;

public class Keystrokes6 implements Keystrokes3 {
   private final BitSet field1 = new BitSet(64);
   private long field2 = System.nanoTime();
   private int count = 0;

   @Override
   public void method1(MixinCore9Extension var1, float var2, int var3, MixinHelper_4 var4) {
      long var5 = System.nanoTime();

      while (var5 - this.field2 > 10000000L) {
         this.method3();
         if (this.count < 64) {
            int var7 = ThreadLocalRandom.current().nextInt(2) + 3;
            if (this.field1.get(getIndex(var7, 7))) {
               do {
                  var7 = ThreadLocalRandom.current().nextInt(8);
               } while (this.field1.get(getIndex(var7, 7)));
            }

            this.field1.set(getIndex(var7, 7));
            this.count++;
         }

         this.field2 += 10000000L;
      }

      var4.push();
      var4.scale(var1.getWidth() / 8.0F, var1.getHeight() / 8.0F, 1.0F);
      var4.method42(180.0F);
      var4.method38(-8.0F, -8.0F, 0.0F);
      this.method2(var4, var3);
      var4.pop();
   }

   private void method2(MixinHelper_4 var1, int var2) {
      for (int var3 = 0; var3 < 8; var3++) {
         for (int var4 = 0; var4 < 8; var4++) {
            if (this.field1.get(getIndex(var3, var4))) {
               this.method2(var1, var3, var4, 1.0F, 1.0F, var2);
            }
         }
      }
   }

   private void method3() {
      for (int var1 = 0; var1 < 8; var1++) {
         for (int var2 = 0; var2 < 8; var2++) {
            boolean var3 = this.field1.get(getIndex(var1, var2));
            if (var3) {
               boolean var4 = var2 == 0;
               if (!var4) {
                  boolean var5 = this.field1.get(getIndex(var1, var2 - 1));
                  if (!var5) {
                     this.field1.set(getIndex(var1, var2 - 1));
                     this.field1.clear(getIndex(var1, var2));
                  } else {
                     if (var1 > 0) {
                        boolean var6 = this.field1.get(getIndex(var1 - 1, var2 - 1));
                        if (!var6) {
                           this.field1.set(getIndex(var1 - 1, var2 - 1));
                           this.field1.clear(getIndex(var1, var2));
                           continue;
                        }
                     }

                     if (var1 < 7) {
                        boolean var7 = this.field1.get(getIndex(var1 + 1, var2 - 1));
                        if (!var7) {
                           this.field1.set(getIndex(var1 + 1, var2 - 1));
                           this.field1.clear(getIndex(var1, var2));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static int getIndex(int value, int var1) {
      return var1 * 8 + value;
   }
}
