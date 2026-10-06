package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;

public class SandKeystrokeRenderer implements KeystrokeRenderer {
   private final BitSet field1 = new BitSet(64);
   private long field2 = System.nanoTime();
   private int count = 0;

   public SandKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixinCore9, float value, int value2, MixinHelper_4 mixinhelper_44) {
      long number5 = System.nanoTime();

      while (number5 - this.field2 > 10000000L) {
         this.method3();
         if (this.count < 64) {
            int number7 = ThreadLocalRandom.current().nextInt(2) + 3;
            if (this.field1.get(getIndex(number7, 7))) {
               do {
                  number7 = ThreadLocalRandom.current().nextInt(8);
               } while (this.field1.get(getIndex(number7, 7)));
            }

            this.field1.set(getIndex(number7, 7));
            this.count++;
         }

         this.field2 += 10000000L;
      }

      mixinhelper_44.push();
      mixinhelper_44.scale(mixinCore9.getWidth() / 8.0F, mixinCore9.getHeight() / 8.0F, 1.0F);
      mixinhelper_44.method42(180.0F);
      mixinhelper_44.method38(-8.0F, -8.0F, 0.0F);
      this.method2(mixinhelper_44, value2);
      mixinhelper_44.pop();
   }

   private void method2(MixinHelper_4 mixinhelper_41, int value) {
      for (int index3 = 0; index3 < 8; index3++) {
         for (int index4 = 0; index4 < 8; index4++) {
            if (this.field1.get(getIndex(index3, index4))) {
               this.method2(mixinhelper_41, index3, index4, 1.0F, 1.0F, value);
            }
         }
      }
   }

   private void method3() {
      for (int index1 = 0; index1 < 8; index1++) {
         for (int index2 = 0; index2 < 8; index2++) {
            boolean flag3 = this.field1.get(getIndex(index1, index2));
            if (flag3) {
               boolean flag4 = index2 == 0;
               if (!flag4) {
                  boolean flag5 = this.field1.get(getIndex(index1, index2 - 1));
                  if (!flag5) {
                     this.field1.set(getIndex(index1, index2 - 1));
                     this.field1.clear(getIndex(index1, index2));
                  } else {
                     if (index1 > 0) {
                        boolean flag6 = this.field1.get(getIndex(index1 - 1, index2 - 1));
                        if (!flag6) {
                           this.field1.set(getIndex(index1 - 1, index2 - 1));
                           this.field1.clear(getIndex(index1, index2));
                           continue;
                        }
                     }

                     if (index1 < 7) {
                        boolean flag7 = this.field1.get(getIndex(index1 + 1, index2 - 1));
                        if (!flag7) {
                           this.field1.set(getIndex(index1 + 1, index2 - 1));
                           this.field1.clear(getIndex(index1, index2));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static int getIndex(int value, int value2) {
      return value2 * 8 + value;
   }
}
