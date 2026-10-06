package com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeResolver;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.mod.render.crosshair.CrosshairStyle;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public class Crosshairelytra3 {
   private final CrosshairStyle field1;
   private final Crosshairelytra3.Data field2;
   private final Crosshairelytra3.Data field3;
   private volatile BufferedImage field4 = null;

   protected Crosshairelytra3(CrosshairStyle var1, String var2) {
      this.field1 = var1;
      this.field2 = new Crosshairelytra3.Data(var2, this::method2, false);
      this.field3 = new Crosshairelytra3.Data(var2 + "_outline", this::method3, true);
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

   protected void method1(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5) {
      ResourceLocationBridge var6 = this.field2.method3().orElse(null);
      if (var6 != null) {
         int var7 = this.field1.method35().method12().size();
         RenderTypeResolver var8 = var5.method12() ? LunarRenderTypes.field12 : LunarRenderTypes.field33;
         var1.push();
         var1.method38(var2, var3, 0.0F);
         float var9 = (var7 + 1) / 16.0F;
         if (var9 > 1.0F) {
            var9 = 1.0F + 1.0F / var9;
         }

         var4 /= Math.max(1.0F, var9);
         var1.scale(var4, var4, 1.0F);
         float var10 = (int)Math.floor(var7 / 2.0F);
         float var11 = (int)Math.ceil(var7 / 2.0F);
         var1.method9(
            var8.get(var6),
            var6,
            -var10,
            -var10,
            var11 + var10,
            var11 + var10,
            var5x -> var5x.method2(-var10, var11, 0.0)
               .method10(0.0F, 1.0F)
               .method9(var5.method1(var2 - var10, var3 + var11))
               .method16()
               .method2(var11, var11, 0.0)
               .method10(1.0F, 1.0F)
               .method9(var5.method1(var2 + var11, var3 + var11))
               .method16()
               .method2(var11, -var10, 0.0)
               .method10(1.0F, 0.0F)
               .method9(var5.method1(var2 + var11, var3 - var11))
               .method16()
               .method2(-var10, -var10, 0.0)
               .method10(0.0F, 0.0F)
               .method9(var5.method1(var2 - var11, var3 - var11))
               .method16()
         );
         Optional var12 = this.field3.method3();
         Crosshairelytra var13 = this.field1.method17();
         if (var13 != null && var12.isPresent()) {
            float var14 = (int)Math.floor((var7 + 2) / 2.0F);
            float var15 = (int)Math.ceil((var7 + 2) / 2.0F);
            var1.method9(
               LunarRenderTypes.field33.get((ResourceLocationBridge)var12.get()),
               (ResourceLocationBridge)var12.get(),
               -var14,
               -var14,
               var15 + var14,
               var15 + var14,
               var5x -> var5x.method2(-var14, var15, 0.0)
                  .method10(0.0F, 1.0F)
                  .method9(var13.method1(var2 - var14, var3 + var15))
                  .method16()
                  .method2(var15, var15, 0.0)
                  .method10(1.0F, 1.0F)
                  .method9(var13.method1(var2 + var15, var3 + var15))
                  .method16()
                  .method2(var15, -var14, 0.0)
                  .method10(1.0F, 0.0F)
                  .method9(var13.method1(var2 + var15, var3 - var15))
                  .method16()
                  .method2(-var14, -var14, 0.0)
                  .method10(0.0F, 0.0F)
                  .method9(var13.method1(var2 - var15, var3 - var15))
                  .method16()
            );
         }

         var1.pop();
      }
   }

   private BufferedImage method2() {
      int var1 = this.field1.method35().method12().size();
      BufferedImage var2 = new BufferedImage(var1, var1, 2);
      boolean[] var3 = this.field1.method35().method13();

      for (int var4 = 0; var4 < var1 * var1; var4++) {
         if (var3[var4]) {
            var2.setRGB(var4 % var2.getWidth(), var4 / var2.getHeight(), -1);
         }
      }

      this.field4 = var2;
      return var2;
   }

   private BufferedImage method3() {
      int var1 = this.field1.method35().method12().size();
      byte var2 = 25;
      int var3 = (var1 + 2) * var2;
      int var4 = var1 * var2;
      float var5 = this.field1.method19();
      BufferedImage var6 = this.field4;
      if (var6 == null) {
         var6 = this.method2();
      }

      this.field4 = null;
      int var7 = var6.getWidth();
      int var8 = var6.getHeight();
      int var9 = (int)Math.ceil((1.0F - var5) * var2);
      int var10 = var2 + (int)Math.floor(var5 * var2);
      BufferedImage var11 = new BufferedImage(var3, var3, 2);
      Graphics2D var12 = (Graphics2D)var11.getGraphics();
      var12.drawImage(var6, var9, var9, var4 + var9, var4 + var9, 0, 0, var7, var8, null);
      var12.drawImage(var6, var9, var10, var4 + var9, var4 + var10, 0, 0, var7, var8, null);
      var12.drawImage(var6, var10, var9, var4 + var10, var4 + var9, 0, 0, var7, var8, null);
      var12.drawImage(var6, var10, var10, var4 + var10, var4 + var10, 0, 0, var7, var8, null);
      var12.drawImage(var6, var2, var9, var4 + var2, var4 + var9, 0, 0, var7, var8, null);
      var12.drawImage(var6, var2, var10, var4 + var2, var4 + var10, 0, 0, var7, var8, null);
      var12.drawImage(var6, var9, var2, var4 + var9, var4 + var2, 0, 0, var7, var8, null);
      var12.drawImage(var6, var10, var2, var4 + var10, var4 + var2, 0, 0, var7, var8, null);
      var12.dispose();
      BufferedImage var13 = new BufferedImage(var3, var3, 2);
      var12 = (Graphics2D)var13.getGraphics();
      var12.drawImage(var6, var2, var2, var4 + var2, var4 + var2, 0, 0, var7, var8, null);
      var12.dispose();

      for (int var14 = 0; var14 < var13.getWidth(); var14++) {
         for (int var15 = 0; var15 < var13.getHeight(); var15++) {
            if (var13.getRGB(var14, var15) == -1) {
               var11.setRGB(var14, var15, 0);
            }
         }
      }

      return var11;
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

      private Data(String var1, Supplier<BufferedImage> var2, boolean var3) {
         var1 = var1.toLowerCase(Locale.ROOT);
         this.field1 = ResourceLocationBridge.create("lunar", "custom_crosshair_" + var1);
         this.field2 = var2;
         this.field3 = var3;
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
            ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(this.field1);
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
                  ThreadModuleDump37.method6().execute(() -> {
                     this.image = this.field2.get();
                     ThreadModuleDump37.method7(this::update);
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
                  ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(this.field1);
                  this.loaded = false;
               }

               ThreadModuleDump63.method3().bridge$getTextureManager().bridge$loadTexture(this.field1, Bridge.method8().method22(this.image));
               this.loaded = true;
            } finally {
               this.image = null;
               this.field4 = false;
            }
         }
      }
   }
}
