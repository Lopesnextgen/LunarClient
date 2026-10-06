package com.moonsworth.lunar.client.framework.feature.saturation;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.io.ImageUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.BitSet;
import org.jspecify.annotations.Nullable;

class SaturationRenderer {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "saturation_outline");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "saturation_outline_hunger");
   private static final int field3 = 4;
   private SaturationRenderer.@Nullable OutlineTexture field4;
   private SaturationRenderer.@Nullable OutlineTexture field5;

   SaturationRenderer() {
   }

   public void reset() {
      if (this.field4 != null) {
         this.field4.delete();
         this.field4 = null;
      }

      if (this.field5 != null) {
         this.field5.delete();
         this.field5 = null;
      }
   }

   public void method1(MixinHelper_4 mixinhelper_41, boolean flag2, float value3, float value4, float value5, int number6) {
      if (!(value5 <= 0.0F)) {
         SaturationRenderer.OutlineTexture data27 = this.method2(flag2);
         if (!data27.method4()) {
            int number8 = data27.method5();
            float value9 = 9.0F / number8;
            mixinhelper_41.push();
            mixinhelper_41.method39(value3, value4);
            mixinhelper_41.method40(value9, value9);
            mixinhelper_41.method25(data27.method3(), 0.0F, 0.0F, SaturationRenderer.OutlineTexture.method2(value5) * number8, 0.0F, number8, number8, data27.method1(), number8, number6);
            mixinhelper_41.pop();
         }
      }
   }

   private SaturationRenderer.OutlineTexture method2(boolean flag1) {
      if (flag1) {
         if (this.field5 == null) {
            this.field5 = this.method3(true);
         }

         return this.field5;
      } else {
         if (this.field4 == null) {
            this.field4 = this.method3(false);
         }

         return this.field4;
      }
   }

   private SaturationRenderer.OutlineTexture method3(boolean flag1) {
      ResourceLocationBridge horsestats142 = flag1 ? field2 : field1;
      SaturationRenderer.Data data3 = this.method8(flag1);
      ArrayList list4 = new ArrayList();

      for (int index5 = 0; index5 < data3.method3(); index5++) {
         for (int index6 = 0; index6 < data3.method3(); index6++) {
            if (this.method5(data3, index6, index5)) {
               list4.add(new SaturationRenderer.OutlinePoint(index6, index5));
            }
         }
      }

      if (list4.isEmpty()) {
         return new SaturationRenderer.OutlineTexture(horsestats142, true, data3.method3(), data3.method5());
      }

      int number11 = data3.method3();
      BufferedImage bufferedimage12 = new BufferedImage(number11 * 4, number11, 2);

      for (SaturationRenderer.OutlinePoint data38 : list4) {
         int number9 = this.method6(data38, number11);

         for (int index10 = this.method7(data38, number11); index10 < 4; index10++) {
            bufferedimage12.setRGB(index10 * number11 + data38.x(), data38.y(), number9);
         }
      }

      Bridge8Extension33 bridge8extension3313 = Bridge.method8().method22(bufferedimage12);
      Ref.method3().bridge$getTextureManager().bridge$loadTexture(horsestats142, bridge8extension3313);
      return new SaturationRenderer.OutlineTexture(horsestats142, false, data3.method3(), data3.method5());
   }

   public int method4(boolean flag1) {
      return this.method2(flag1).method6();
   }

   private boolean method5(SaturationRenderer.Data data1, int number2, int number3) {
      if (!data1.method1(number2, number3)) {
         return false;
      }

      int number4 = data1.method4();

      for (int index5 = -number4; index5 <= number4; index5++) {
         for (int index6 = -number4; index6 <= number4; index6++) {
            if (index6 * index6 + index5 * index5 <= number4 * number4 && !data1.method1(number2 + index6, number3 + index5)) {
               return true;
            }
         }
      }

      return false;
   }

   private int method6(SaturationRenderer.OutlinePoint data31, int number2) {
      float value3 = data31.y() + 0.25F * (data31.x() - number2 / 2.0F);
      int number4 = Math.round(255.0F - 115.0F * ClampUtils.clamp(value3 / number2, 0.0F, 1.0F));
      return ColorUtils.method10(number4, number4, number4, 255);
   }

   private int method7(SaturationRenderer.OutlinePoint data31, int number2) {
      float value3 = (number2 - data31.x() - 0.25F * (data31.y() - number2 / 2.0F)) / number2;
      return ClampUtils.clamp((int)Math.ceil(value3 * 4.0F) - 1, 0, 3);
   }

   private SaturationRenderer.Data method8(boolean flag1) {
      Saturation.Data data2 = Saturation.method1(flag1);
      BufferedImage bufferedimage3 = ImageUtils.method4(data2.method1());
      Saturation.Data data4 = Saturation.method3(flag1);
      BufferedImage bufferedimage5 = ImageUtils.method4(data4.method1());
      int number6 = Math.max(this.method9(bufferedimage3, data2), this.method9(bufferedimage5, data4));
      BitSet bitset7 = new BitSet(number6 * number6);
      int number8 = this.method10(bitset7, bufferedimage5, data4, number6);
      int number9 = this.method10(bitset7, bufferedimage3, data2, number6);
      int number10 = (int)Math.ceil(number6 / 9.0F);
      int number11 = ColorUtils.method35(number8, number9, 0.5F);
      return new SaturationRenderer.Data(bitset7, number6, number10, number11);
   }

   private int method9(@Nullable BufferedImage bufferedimage1, Saturation.Data data2) {
      if (bufferedimage1 == null) {
         return 9;
      }

      float value3 = 9.0F * (bufferedimage1.getWidth() / data2.method3());
      return ClampUtils.clamp(Math.round(value3), 9, 36);
   }

   private int method10(BitSet bitset1, BufferedImage bufferedimage2, Saturation.Data data3, int index4) {
      if (bufferedimage2 == null) {
         return -1;
      }

      float value5 = bufferedimage2.getWidth() / data3.method3();
      float value6 = 9.0F * value5;
      float value7 = data3.method2() * value5;
      float value8 = data3.v() * value5;
      int number9 = 0;
      int number10 = 0;
      int number11 = 0;
      int index12 = 0;

      for (int index13 = 0; index13 < index4; index13++) {
         for (int index14 = 0; index14 < index4; index14++) {
            int number15 = (int)(value7 + (index14 + 0.5F) * value6 / index4);
            int number16 = (int)(value8 + (index13 + 0.5F) * value6 / index4);
            if (number15 < bufferedimage2.getWidth() && number16 < bufferedimage2.getHeight()) {
               int number17 = bufferedimage2.getRGB(number15, number16);
               if (ColorUtils.method4(number17) >= 128) {
                  bitset1.set(index14 + index13 * index4);
                  number9 += ColorUtils.method1(number17);
                  number10 += ColorUtils.method2(number17);
                  number11 += ColorUtils.method3(number17);
                  index12++;
               }
            }
         }
      }

      return index12 == 0 ? -1 : ColorUtils.method10(255 - number9 / index12, 255 - number10 / index12, 255 - number11 / index12, 255);
   }

   private class Data {
      private final BitSet field1;
      private final int field2;
      private final int field3;
      private final int field4;

      private Data(BitSet bitset1, int number2, int number3, int number4) {
         this.field1 = bitset1;
         this.field2 = number2;
         this.field3 = number3;
         this.field4 = number4;
      }

      private boolean method1(int index1, int index2) {
         return index1 >= 0 && index2 >= 0 && index1 < this.field2 && index2 < this.field2 && this.field1.get(index1 + index2 * this.field2);
      }

      public BitSet method2() {
         return this.field1;
      }

      public int method3() {
         return this.field2;
      }

      public int method4() {
         return this.field3;
      }

      public int method5() {
         return this.field4;
      }
   }

   private class OutlineTexture {
      private final ResourceLocationBridge field1;
      private final boolean field2;
      private final int resolution;
      private final int field3;

      private OutlineTexture(ResourceLocationBridge horsestats141, boolean flag2, int number3, int number4) {
         this.field1 = horsestats141;
         this.field2 = flag2;
         this.resolution = number3;
         this.field3 = number4;
      }

      private int method1() {
         return this.resolution * 4;
      }

      private void delete() {
         if (!this.field2) {
            Ref.method3().bridge$getTextureManager().bridge$deleteTexture(this.field1);
         }
      }

      private static int method2(float value0) {
         return value0 >= 1.0F ? 3 : ClampUtils.clamp((int)Math.ceil(value0 * 4.0F) - 1, 0, 2);
      }

      public ResourceLocationBridge method3() {
         return this.field1;
      }

      public boolean method4() {
         return this.field2;
      }

      public int method5() {
         return this.resolution;
      }

      public int method6() {
         return this.field3;
      }
   }

   private class OutlinePoint {
      private final int field1;
      private final int field2;

      private OutlinePoint(int number1, int number2) {
         this.field1 = number1;
         this.field2 = number2;
      }

      public int x() {
         return this.field1;
      }

      public int y() {
         return this.field2;
      }
   }
}
