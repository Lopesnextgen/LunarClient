package com.moonsworth.lunar.client.util;

import com.google.common.io.Files;
import com.luciad.imageio.webp.WebP;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import lombok.Generated;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;

public final class ThreadModuleDump59 {
   public static BufferedImage method1(InputStream var0, @Nullable File var1) {
      BufferedImage var2 = null;
      if (var1 != null) {
         if (var0 != null) {
            FileUtils.copyInputStreamToFile(var0, var1);
         }

         if ("webp".equals(Files.getFileExtension(var1.getName()))) {
            ImageReader var3 = null;

            try (ImageInputStream var4 = ImageIO.createImageInputStream(var1)) {
               var3 = WebP.IMAGE_READER.createReaderInstance("webp");
               var3.setInput(var4, true, true);
               var2 = var3.read(0);
            } finally {
               if (var3 != null) {
                  var3.dispose();
               }
            }
         } else {
            var2 = ImageIO.read(var1);
         }
      } else {
         if (ThreadModuleDump63.MC_VERSION >= 15 || var0 instanceof BufferedInputStream || var0 instanceof ImageInputStream || var0 instanceof FileInputStream) {
            ImageInputStream var27 = ImageIO.createImageInputStream(var0);
            if (WebP.IMAGE_READER.canDecodeInput(var27)) {
               ImageReader var28 = null;

               try (var27) {
                  var28 = WebP.IMAGE_READER.createReaderInstance("webp");
                  var28.setInput(var27, true, true);
                  var2 = var28.read(0);
               } finally {
                  if (var28 != null) {
                     var28.dispose();
                  }
               }
            } else {
               var2 = ImageIO.read(var27);
            }
         }

         if (var2 == null) {
            var2 = ImageIO.read(var0);
         }
      }

      return var2;
   }

   public static int[] method2(BufferedImage var0, int var1, int var2, int var3, int var4, int[] var5, int var6, int var7) {
      int var8 = var6;
      int var11 = var0.getRaster().getNumBands();
      int var12 = var0.getRaster().getDataBuffer().getDataType();

      Object var10 = switch (var12) {
         case 0 -> new byte[var11];
         case 1 -> new short[var11];
         default -> throw new IllegalArgumentException("Unknown data buffer type: " + var12);
         case 3 -> new int[var11];
         case 4 -> new float[var11];
         case 5 -> new double[var11];
      };
      if (var5 == null) {
         var5 = new int[var6 + var4 * var7];
      }

      for (int var13 = var2; var13 < var2 + var4; var8 += var7) {
         int var9 = var8;

         for (int var14 = var1; var14 < var1 + var3; var14++) {
            Object var15 = var0.getRaster().getDataElements(var14, var13, var10);
            int var16 = var0.getColorModel().getAlpha(var15) << 24
               | var0.getColorModel().getBlue(var15) << 16
               | var0.getColorModel().getGreen(var15) << 8
               | var0.getColorModel().getRed(var15) << 0;
            var5[var9++] = var16;
         }

         var13++;
      }

      return var5;
   }

   public static BufferedImage method3(BufferedImage var0) {
      int var1 = var0.getWidth();
      int var2 = var0.getHeight();
      ColorModel var3 = var0.getColorModel();
      int[] var4 = new int[var1 * var2];
      if (var3.getNumColorComponents() == 1 && var3.getColorSpace() != null && var3.getColorSpace().getType() == 6) {
         for (int var10 = 0; var10 < var2; var10++) {
            for (int var11 = 0; var11 < var1; var11++) {
               int var12 = var0.getRaster().getSample(var11, var10, 0);
               int var13 = var3.hasAlpha() ? var0.getRaster().getSample(var11, var10, 1) : 255;
               var4[var11 + var10 * var1] = var13 << 24 | var12 << 16 | var12 << 8 | var12;
            }
         }
      } else {
         if (var3.hasAlpha() && var3.isAlphaPremultiplied() && var3 instanceof ComponentColorModel) {
            var0 = new BufferedImage(var3, var0.getRaster(), false, null);
            var3 = var0.getColorModel();
         }

         Object var5 = null;
         int var6 = 0;

         for (int var7 = 0; var7 < var2; var7++) {
            for (int var8 = 0; var8 < var1; var8++) {
               var5 = var0.getRaster().getDataElements(var8, var7, var5);
               if (ThreadModuleDump63.MC_VERSION > 5) {
                  var4[var6++] = var3.getAlpha(var5) << 24 | var3.getBlue(var5) << 16 | var3.getGreen(var5) << 8 | var3.getRed(var5);
               } else {
                  var4[var6++] = var3.getAlpha(var5) << 24 | var3.getRed(var5) << 16 | var3.getGreen(var5) << 8 | var3.getBlue(var5);
               }
            }
         }
      }

      var0 = new BufferedImage(var1, var2, 2);
      var0.setRGB(0, 0, var1, var2, var4, 0, var1);
      return var0;
   }

   @Nullable
   public static BufferedImage method4(ResourceLocationBridge var0) {
      IResourceBridge var1 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var0);
      if (var1 != null) {
         try (InputStream var2 = var1.bridge$getInputStream()) {
            return ImageIO.read(var2);
         } catch (IOException var7) {
         }
      }

      return null;
   }

   @Generated
   private ThreadModuleDump59() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
