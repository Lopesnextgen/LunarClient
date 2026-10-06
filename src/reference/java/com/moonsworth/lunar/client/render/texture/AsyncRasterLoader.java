package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_9;
import com.moonsworth.lunar.bridge.Bridge7_3;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.ColorChannelOrder;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;
import com.moonsworth.lunar.client.render.font.CachedFont;

public class AsyncRasterLoader {
   private static final AsyncRasterLoader field1 = new AsyncRasterLoader();
   private final ColorModel field2 = ColorModel.getRGBdefault();
   private final int[] field3 = new int[1098304];
   private final WritableRaster field4 = this.method1();
   private final Semaphore field5 = new Semaphore(1);
   private final ConcurrentLinkedQueue<Runnable> field6 = new ConcurrentLinkedQueue<>();

   private WritableRaster method1() {
      int[] var1 = new int[]{16711680, 65280, 255, -16777216};
      DataBufferInt var2 = new DataBufferInt(this.field3, this.field3.length, 0);
      return Raster.createPackedRaster(var2, 1048, 1048, 1048, var1, null);
   }

   public CompletableFuture<Integer> method2(
      Bridge8Extension3 var1, Font var2, boolean var3, boolean var4, CachedFont.Data[] var5, boolean var6, String var7
   ) {
      if (var6) {
         try {
            int var8 = this.method3(var2, var3, var4, var5);
            int var9 = this.method4(var5);
            this.method5(var1, var9, var7);
            return CompletableFuture.completedFuture(var8);
         } catch (Throwable var10) {
            Slayer.error("Failed to load font", var10);
            if (this.field5.availablePermits() == 0) {
               this.field5.release(1);
            }

            return CompletableFuture.completedFuture(0);
         }
      } else {
         return CompletableFuture.<Integer>supplyAsync(() -> this.method3(var2, var3, var4, var5), ThreadModuleDump37.method6()).thenApplyAsync(var4x -> {
            int var5x = this.method4(var5);
            this.method5(var1, var5x, var7);
            return (Integer)var4x;
         }, this.executor()).exceptionally(var1x -> {
            Slayer.error("Failed to load font", var1x);
            if (this.field5.availablePermits() == 0) {
               this.field5.release(1);
            }

            return 0;
         });
      }
   }

   public int method3(Font var1, boolean var2, boolean var3, CachedFont.Data[] var4) {
      if (Bridge.method42().method1()) {
         while (!this.field5.tryAcquire(1)) {
            Runnable var5 = this.field6.poll();
            if (var5 != null) {
               var5.run();
            }
         }
      } else {
         this.field5.acquireUninterruptibly(1);
      }

      return this.method6(var1, var2, var3, var4);
   }

   public int method4(CachedFont.Data[] var1) {
      int var2 = method7(var1);
      float var3 = var2;

      for (CachedFont.Data var7 : var1) {
         var7.field3 = var7.field1 / 1048.0F;
         var7.field5 = (var7.field1 + var7.width) / 1048.0F;
         var7.field4 = var7.field2 / var3;
         var7.field6 = (var7.field2 + var7.height) / var3;
      }

      return var2;
   }

   public void method5(Bridge8Extension3 var1, int var2, String var3) {
      Bridge7_3 var4 = Bridge.method55();
      Bridge6_9 var5 = new Bridge6_9(false, false, null, null, 1048, var2, var2, ColorChannelOrder.ARGB, this.field3, var3);
      var4.method1(var5, var1);
      this.field5.release(1);
   }

   public Executor executor() {
      return var1 -> {
         this.field6.add(var1);
         ThreadModuleDump37.method7(() -> {
            Runnable var1x = this.field6.poll();
            if (var1x != null) {
               var1x.run();
            }
         });
      };
   }

   protected int method6(Font var1, boolean var2, boolean var3, CachedFont.Data[] var4) {
      short var5 = 1048;
      BufferedImage var6 = new BufferedImage(this.field2, this.field4, false, new Hashtable());
      Graphics2D var7 = (Graphics2D)var6.getGraphics();
      var7.setFont(var1);
      Arrays.fill(this.field3, 0);
      var7.setColor(Color.WHITE);
      var7.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, var3 ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
      var7.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, var2 ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
      var7.setRenderingHint(RenderingHints.KEY_ANTIALIASING, var2 ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
      FontMetrics var8 = var7.getFontMetrics();
      int var9 = 0;
      int var10 = 0;
      int var11 = 1;
      int var12 = -1;

      for (int var13 = 0; var13 < var4.length; var13++) {
         char var14 = (char)var13;
         CachedFont.Data var15 = new CachedFont.Data();
         Rectangle2D var16 = var8.getStringBounds(String.valueOf(var14), var7);
         var15.width = var16.getBounds().width + 8;
         var15.height = var16.getBounds().height;
         if (var10 + var15.width >= var5) {
            var10 = 0;
            var11 += var9;
            var9 = 0;
         }

         if (var15.height > var9) {
            var9 = var15.height;
         }

         var15.field1 = var10;
         var15.field2 = var11;
         if (var15.height > var12) {
            var12 = var15.height;
         }

         var4[var13] = var15;
         var7.drawString(String.valueOf(var14), var10 + 2, var11 + var8.getAscent());
         var10 += var15.width;
      }

      return var12;
   }

   public static int method7(CachedFont.Data[] var0) {
      CachedFont.Data var1 = var0[var0.length - 1];
      return var1.field2 + var1.height;
   }

   public static AsyncRasterLoader method8() {
      return field1;
   }
}
