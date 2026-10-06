package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.bridge.ColorChannelOrder;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump59;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.render.texture.Alert4;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

class ElytraTextureMetadata extends Alert4 {
   public ElytraTextureMetadata(ElytraTextureLoaderLegacy var1, Bridge11_2 var2, ResourceLocationBridge var3, ColorChannelOrder var4, BridgeType2_5 var5) {
      super(var2, var3, var4, var5, ElytraTextureLoaderLegacy.method3(var1));
      this.field6 = var1;
   }

   protected BufferedImage method3(BufferedImage var1, BridgeType2_5 var2) {
      BufferedImage var3 = new BufferedImage(var1.getWidth(), var1.getHeight(), 2);

      try {
         boolean var4 = false;
         BufferedImage var5 = null;
         if (this.field6.field18 != null) {
            FileInputStream var6 = new FileInputStream(this.field6.field18);
            var5 = ThreadModuleDump59.method1(var6, null);
         } else if (ThreadModuleDump63.MC_VERSION >= 28 && this.field6.field19 != null) {
            var5 = Bridge.method16().method1(this.field6.field19);
            var4 = true;
         }

         if (var5 == null) {
            return var3;
         }

         for (int var10 = 0; var10 < var1.getWidth(); var10++) {
            for (int var7 = 0; var7 < var1.getHeight(); var7++) {
               int var8 = var1.getRGB(var10, var7);
               var3.setRGB(var10, var7, var8);
            }
         }

         for (int var11 = 0; var11 < 64; var11++) {
            for (int var16 = 0; var16 < 64; var16++) {
               var3.setRGB(var11, var16, 0);
            }
         }

         if (var5.getHeight() == 64) {
            for (int var12 = 0; var12 < var5.getWidth(); var12++) {
               for (int var17 = 0; var17 < var5.getHeight(); var17++) {
                  if ((var17 >= 8 || var12 >= 8) && (var12 < 56 || var17 < 16 || var17 >= 48)) {
                     int var21 = var5.getRGB(var12, var17);
                     var3.setRGB(var12, var17, var4 ? ThreadModuleDump23.method17(var21) : var21);
                  }
               }
            }
         } else if (var5.getHeight() == 32) {
            for (int var13 = 0; var13 < var5.getWidth(); var13++) {
               for (int var18 = 0; var18 < var5.getHeight(); var18++) {
                  if ((var18 >= 16 || var13 < 32) && (var18 >= 8 || var13 >= 8) && var13 < 56) {
                     int var22 = var5.getRGB(var13, var18);
                     var3.setRGB(var13, var18, var4 ? ThreadModuleDump23.method17(var22) : var22);
                  }
               }
            }

            for (int var14 = 0; var14 < 16; var14++) {
               for (int var19 = 16; var19 < 32; var19++) {
                  int var23 = var5.getRGB(var14, var19);
                  var3.setRGB(var14 + 16, var19 + 32, var4 ? ThreadModuleDump23.method17(var23) : var23);
               }
            }

            for (int var15 = 40; var15 < 56; var15++) {
               for (int var20 = 16; var20 < 32; var20++) {
                  int var24 = var5.getRGB(var15, var20);
                  var3.setRGB(var15 - 8, var20 + 32, var4 ? ThreadModuleDump23.method17(var24) : var24);
               }
            }
         }

         this.field6.field20 = var3;
         return this.field6.field20;
      } catch (IOException var9) {
         throw new RuntimeException(var9);
      }
   }
}
