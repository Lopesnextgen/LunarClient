package com.moonsworth.lunar.client.util.alert;

import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Alert2 {
   public static final int DEFAULT_SCALE = 8;

   public static BufferedImage sliceTexture(BufferedImage var0, BridgeType2_5 var1, boolean var2, int var3) {
      if (var2) {
         return resizeSliced(var0, var1 == BridgeType2_5.LOW, var3);
      } else {
         return var1 == BridgeType2_5.LOW ? resizeLowQuality(var0, var3) : var0;
      }
   }

   public static boolean isCloakTexture(ResourceLocationBridge var0) {
      Client var1 = Client.method109();
      if (var1 == null) {
         return false;
      }

      CosmeticManager var2 = var1.method53();
      return var2 == null ? false : var2.method70().contains(var0);
   }

   public static BufferedImage sliceCloakTexture(BufferedImage var0, int var1) {
      return resizeSliced(var0, false, var1);
   }

   public static BufferedImage sliceCloakTextureLowQuality(BufferedImage var0, int var1) {
      return resizeSliced(var0, true, var1);
   }

   private static BufferedImage resizeSliced(BufferedImage var0, boolean var1, int var2) {
      float var3 = (float)var0.getWidth() / var0.getHeight();
      if (Math.abs(var3 - 2.0F) > 0.01F) {
         return var1 ? resizeLowQuality(var0, var2) : var0;
      }

      int var4 = var0.getHeight();
      if (var1) {
         var4 = Math.min(var0.getHeight(), 32 * var2);
      }

      int var5 = var4 * 2;
      int var6 = ceilDiv(var4, 17, 32);
      int var7 = ceilDiv(var5, 22, 64);
      BufferedImage var8 = new BufferedImage(var7, var6, 2);
      Graphics var9 = var8.getGraphics();
      var9.drawImage(var0, 0, 0, var5, var4, null);
      var9.dispose();
      return var8;
   }

   private static BufferedImage resizeLowQuality(BufferedImage var0, int var1) {
      if (var0.getWidth() <= 17 * var1) {
         return var0;
      }

      int var2 = 22 * var1;
      int var3 = ceilDiv(var0.getHeight(), var2, var0.getWidth());
      BufferedImage var4 = new BufferedImage(var2, var3, 2);
      Graphics var5 = var4.getGraphics();
      var5.drawImage(var0, 0, 0, var2, var3, null);
      var5.dispose();
      return var4;
   }

   private static int ceilDiv(int var0, int var1, int var2) {
      long var3 = (long)var0 * var1;
      var3 += var2 - 1;
      return (int)(var3 / var2);
   }
}
