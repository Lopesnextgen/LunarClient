package com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeLookup;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.mod.render.crosshair.CrosshairStyle;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public class CrosshairTextureRenderer {
   private final CrosshairStyle field1;
   private final CrosshairTextureRenderer.Data field2;
   private final CrosshairTextureRenderer.Data field3;
   private volatile BufferedImage field4 = null;

   protected CrosshairTextureRenderer(CrosshairStyle crosshairchildmod1, String text2) {
      this.field1 = crosshairchildmod1;
      this.field2 = new CrosshairTextureRenderer.Data(text2, this::method2, false);
      this.field3 = new CrosshairTextureRenderer.Data(text2 + "_outline", this::method3, true);
   }

   public void reload() {
      this.field2.reload();
      if (this.field1.method16()) {
         this.field3.reload();
      } else {
         this.field3.method1();
         this.field4 = null;
      }
   }

   protected void reset() {
      this.field2.method1();
      this.field3.method1();
      this.field4 = null;
   }

   protected void method1(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5) {
      ResourceLocationBridge horsestats146 = this.field2.method3().orElse(null);
      if (horsestats146 != null) {
         int number7 = this.field1.method35().method12().size();
         RenderTypeLookup mixinhelper6_38 = crosshairelytra5.method12() ? LunarRenderTypes.field12 : LunarRenderTypes.field33;
         mixinhelper_41.push();
         mixinhelper_41.method38(value2, value3, 0.0F);
         float value9 = (number7 + 1) / 16.0F;
         if (value9 > 1.0F) {
            value9 = 1.0F + 1.0F / value9;
         }

         value4 /= Math.max(1.0F, value9);
         mixinhelper_41.scale(value4, value4, 1.0F);
         float value10 = (int)Math.floor(number7 / 2.0F);
         float value11 = (int)Math.ceil(number7 / 2.0F);
         mixinhelper_41.method9(
            mixinhelper6_38.get(horsestats146),
            horsestats146,
            -value10,
            -value10,
            value11 + value10,
            value11 + value10,
            arg5x -> arg5x.method2(-value10, value11, 0.0)
               .method10(0.0F, 1.0F)
               .method9(crosshairelytra5.method1(value2 - value10, value3 + value11))
               .method16()
               .method2(value11, value11, 0.0)
               .method10(1.0F, 1.0F)
               .method9(crosshairelytra5.method1(value2 + value11, value3 + value11))
               .method16()
               .method2(value11, -value10, 0.0)
               .method10(1.0F, 0.0F)
               .method9(crosshairelytra5.method1(value2 + value11, value3 - value11))
               .method16()
               .method2(-value10, -value10, 0.0)
               .method10(0.0F, 0.0F)
               .method9(crosshairelytra5.method1(value2 - value11, value3 - value11))
               .method16()
         );
         Optional optional12 = this.field3.method3();
         CrosshairShapeDrawer crosshairelytra13 = this.field1.method17();
         if (crosshairelytra13 != null && optional12.isPresent()) {
            float value14 = (int)Math.floor((number7 + 2) / 2.0F);
            float value15 = (int)Math.ceil((number7 + 2) / 2.0F);
            mixinhelper_41.method9(
               LunarRenderTypes.field33.get((ResourceLocationBridge)optional12.get()),
               (ResourceLocationBridge)optional12.get(),
               -value14,
               -value14,
               value15 + value14,
               value15 + value14,
               arg5x -> arg5x.method2(-value14, value15, 0.0)
                  .method10(0.0F, 1.0F)
                  .method9(crosshairelytra13.method1(value2 - value14, value3 + value15))
                  .method16()
                  .method2(value15, value15, 0.0)
                  .method10(1.0F, 1.0F)
                  .method9(crosshairelytra13.method1(value2 + value15, value3 + value15))
                  .method16()
                  .method2(value15, -value14, 0.0)
                  .method10(1.0F, 0.0F)
                  .method9(crosshairelytra13.method1(value2 + value15, value3 - value15))
                  .method16()
                  .method2(-value14, -value14, 0.0)
                  .method10(0.0F, 0.0F)
                  .method9(crosshairelytra13.method1(value2 - value15, value3 - value15))
                  .method16()
            );
         }

         mixinhelper_41.pop();
      }
   }

   private BufferedImage method2() {
      int number1 = this.field1.method35().method12().size();
      BufferedImage bufferedimage2 = new BufferedImage(number1, number1, 2);
      boolean[] items3 = this.field1.method35().method13();

      for (int index4 = 0; index4 < number1 * number1; index4++) {
         if (items3[index4]) {
            bufferedimage2.setRGB(index4 % bufferedimage2.getWidth(), index4 / bufferedimage2.getHeight(), -1);
         }
      }

      this.field4 = bufferedimage2;
      return bufferedimage2;
   }

   private BufferedImage method3() {
      int number1 = this.field1.method35().method12().size();
      byte number2 = 25;
      int number3 = (number1 + 2) * number2;
      int number4 = number1 * number2;
      float value5 = this.field1.method19();
      BufferedImage bufferedimage6 = this.field4;
      if (bufferedimage6 == null) {
         bufferedimage6 = this.method2();
      }

      this.field4 = null;
      int number7 = bufferedimage6.getWidth();
      int number8 = bufferedimage6.getHeight();
      int number9 = (int)Math.ceil((1.0F - value5) * number2);
      int number10 = number2 + (int)Math.floor(value5 * number2);
      BufferedImage bufferedimage11 = new BufferedImage(number3, number3, 2);
      Graphics2D graphics2d12 = (Graphics2D)bufferedimage11.getGraphics();
      graphics2d12.drawImage(bufferedimage6, number9, number9, number4 + number9, number4 + number9, 0, 0, number7, number8, null);
      graphics2d12.drawImage(bufferedimage6, number9, number10, number4 + number9, number4 + number10, 0, 0, number7, number8, null);
      graphics2d12.drawImage(bufferedimage6, number10, number9, number4 + number10, number4 + number9, 0, 0, number7, number8, null);
      graphics2d12.drawImage(bufferedimage6, number10, number10, number4 + number10, number4 + number10, 0, 0, number7, number8, null);
      graphics2d12.drawImage(bufferedimage6, number2, number9, number4 + number2, number4 + number9, 0, 0, number7, number8, null);
      graphics2d12.drawImage(bufferedimage6, number2, number10, number4 + number2, number4 + number10, 0, 0, number7, number8, null);
      graphics2d12.drawImage(bufferedimage6, number9, number2, number4 + number9, number4 + number2, 0, 0, number7, number8, null);
      graphics2d12.drawImage(bufferedimage6, number10, number2, number4 + number10, number4 + number2, 0, 0, number7, number8, null);
      graphics2d12.dispose();
      BufferedImage bufferedimage13 = new BufferedImage(number3, number3, 2);
      graphics2d12 = (Graphics2D)bufferedimage13.getGraphics();
      graphics2d12.drawImage(bufferedimage6, number2, number2, number4 + number2, number4 + number2, 0, 0, number7, number8, null);
      graphics2d12.dispose();

      for (int index14 = 0; index14 < bufferedimage13.getWidth(); index14++) {
         for (int index15 = 0; index15 < bufferedimage13.getHeight(); index15++) {
            if (bufferedimage13.getRGB(index14, index15) == -1) {
               bufferedimage11.setRGB(index14, index15, 0);
            }
         }
      }

      return bufferedimage11;
   }

   private static class Data {
      private final ResourceLocationBridge field1;
      private final Supplier<BufferedImage> field2;
      private final boolean field3;
      private volatile BufferedImage image = null;
      private boolean loaded = false;
      private boolean needsUpdate = true;
      private boolean field4 = false;
      private long field5 = -1L;

      private Data(String text1, Supplier<BufferedImage> supplier2, boolean flag3) {
         text1 = text1.toLowerCase(Locale.ROOT);
         this.field1 = ResourceLocationBridge.create("lunar", "custom_crosshair_" + text1);
         this.field2 = supplier2;
         this.field3 = flag3;
      }

      public void reload() {
         this.field5 = System.currentTimeMillis();
         this.method2();
      }

      public void method1() {
         this.field5 = -1L;
         this.needsUpdate = true;
         this.field4 = false;
         this.image = null;
         if (this.loaded) {
            Ref.method3().bridge$getTextureManager().bridge$deleteTexture(this.field1);
            this.loaded = false;
         }
      }

      private void method2() {
         if (this.field5 != -1L && !this.field4) {
            if (!this.field3 || System.currentTimeMillis() - this.field5 >= 500L) {
               this.field5 = -1L;
               this.needsUpdate = true;
            }
         }
      }

      public Optional<ResourceLocationBridge> method3() {
         return this.method4() ? Optional.of(this.field1) : Optional.empty();
      }

      private boolean method4() {
         if (!this.field4) {
            this.method2();
            if (this.needsUpdate) {
               this.needsUpdate = false;
               this.field4 = true;
               if (this.field3) {
                  BackgroundExecutor.method6().execute(() -> {
                     this.image = this.field2.get();
                     BackgroundExecutor.method7(this::update);
                  });
               } else {
                  this.image = this.field2.get();
                  this.update();
               }
            }
         }

         return this.loaded && !this.field4 && this.field5 == -1L;
      }

      private void update() {
         if (this.field4) {
            try {
               if (this.loaded) {
                  Ref.method3().bridge$getTextureManager().bridge$deleteTexture(this.field1);
                  this.loaded = false;
               }

               Ref.method3().bridge$getTextureManager().bridge$loadTexture(this.field1, Bridge.method8().method22(this.image));
               this.loaded = true;
            } finally {
               this.image = null;
               this.field4 = false;
            }
         }
      }
   }
}
