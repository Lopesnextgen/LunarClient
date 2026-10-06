package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.Bridge3Extension;
import com.moonsworth.lunar.bridge.Bridge3Extension2_2;
import com.moonsworth.lunar.bridge.Bridge3Extension3_2;
import com.moonsworth.lunar.bridge.Bridge6_9;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.bridge.ColorChannelOrder;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump59;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.Callable;
import lombok.Generated;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.util.alert.Alert2;

public class Alert4 implements Callable<Optional<Bridge6_9>> {
   protected final Bridge11_2 field1;
   private final ResourceLocationBridge field2;
   private final ColorChannelOrder field3;
   protected BridgeType2_5 field4;
   private final boolean field5;

   public Optional<Bridge6_9> call() {
      InputStream var1 = null;
      IResourceBridge var2 = this.resourceManager.bridge$getResource(this.textureLocation);
      if (var2 == null) {
         Slayer.method5("Failed to find texture: " + this.textureLocation, new Object[0]);
         return Optional.empty();
      }

      try {
         var1 = var2.bridge$getInputStream();
         BufferedImage var3 = ThreadModuleDump59.method1(var1, null);
         if (var3 == null) {
            Slayer.method5("Failed to load texture: " + this.textureLocation, new Object[0]);
            return Optional.empty();
         }

         boolean var4 = false;
         boolean var5 = false;
         Bridge3Extension var6 = null;
         Bridge3Extension2_2 var7 = null;
         int var8 = var3.getHeight();
         if (var2.bridge$hasMetadata()) {
            try {
               Bridge3Extension3_2 var9 = (Bridge3Extension3_2)var2.bridge$getMetadata("texture");
               if (var9 != null) {
                  var4 = var9.bridge$getTextureBlur();
                  var5 = var9.bridge$getTextureClamp();
               }

               var6 = (Bridge3Extension)var2.bridge$getMetadata("animation");
               var7 = (Bridge3Extension2_2)var2.bridge$getMetadata("lunar");
            } catch (RuntimeException var15) {
               var15.printStackTrace();
               Slayer.method6("Texture", "Failed reading metadata of %s: %s", new Object[]{this.textureLocation, var15.getMessage()});
            }
         }

         var3 = this.processImage(var3, this.quality);
         var6 = this.processAnimationMetadata(var6);
         var8 = this.getTextureHeight(var3, var8);
         var3 = Alert2.sliceTexture(var3, this.quality, this.isSlicedCloak, this.getScaleFactor(var3));
         Bridge6_9 var22 = createDecodedTexture(var5, var4, this.format, var3, var6, var7, var8, this.textureLocation.toString());
         return Optional.of(var22);
      } catch (Exception var16) {
         var16.printStackTrace();
         Slayer.method8("Texture", "Unable to load texture %s", new Object[]{this.textureLocation});
         return Optional.empty();
      } finally {
         IOUtils.closeQuietly(var1);
      }
   }

   protected int getScaleFactor(BufferedImage var1) {
      return 8;
   }

   protected BufferedImage processImage(BufferedImage var1, BridgeType2_5 var2) {
      return var1;
   }

   protected Bridge3Extension processAnimationMetadata(Bridge3Extension var1) {
      return var1;
   }

   protected int getTextureHeight(BufferedImage var1, int var2) {
      return var2;
   }

   public static Bridge6_9 createDecodedTexture(
      boolean var0,
      boolean var1,
      ColorChannelOrder var2,
      BufferedImage var3,
      @Nullable Bridge3Extension var4,
      @Nullable Bridge3Extension2_2 var5,
      int var6,
      String var7
   ) {
      int var8 = var3.getWidth();
      int var9 = var3.getHeight();
      int[] var10 = new int[var8 * var9];
      if (var2 == ColorChannelOrder.ABGR) {
         ThreadModuleDump59.method2(var3, 0, 0, var8, var9, var10, 0, var8);
      } else {
         if (var2 != ColorChannelOrder.ARGB) {
            throw new IllegalArgumentException("Unknown color format " + var2);
         }

         var3.getRGB(0, 0, var8, var9, var10, 0, var8);
      }

      return new Bridge6_9(var0, var1, var4, var5, var8, var9, var6, var2, var10, var7);
   }

   @Generated
   public static Alert4.Data builder() {
      return new Alert4.Data();
   }

   @Generated
   public Alert4(Bridge11_2 var1, ResourceLocationBridge var2, ColorChannelOrder var3, BridgeType2_5 var4, boolean var5) {
      this.resourceManager = var1;
      this.textureLocation = var2;
      this.format = var3;
      this.quality = var4;
      this.isSlicedCloak = var5;
   }

   @Generated
   public static class Data {
      @Generated
      private Bridge11_2 field1;
      @Generated
      private ResourceLocationBridge field2;
      @Generated
      private ColorChannelOrder field3;
      @Generated
      private BridgeType2_5 field4;
      @Generated
      private boolean field5;

      @Generated
      Data() {
      }

      @Generated
      public Alert4.Data call(Bridge11_2 var1) {
         this.resourceManager = var1;
         return this;
      }

      @Generated
      public Alert4.Data getScaleFactor(ResourceLocationBridge var1) {
         this.textureLocation = var1;
         return this;
      }

      @Generated
      public Alert4.Data processImage(ColorChannelOrder var1) {
         this.format = var1;
         return this;
      }

      @Generated
      public Alert4.Data processAnimationMetadata(BridgeType2_5 var1) {
         this.quality = var1;
         return this;
      }

      @Generated
      public Alert4.Data getTextureHeight(boolean var1) {
         this.isSlicedCloak = var1;
         return this;
      }

      @Generated
      public Alert4 createDecodedTexture() {
         return new Alert4(this.resourceManager, this.textureLocation, this.format, this.quality, this.isSlicedCloak);
      }

      @Generated
      @Override
      public String toString() {
         return "TextureLoadTask.TextureLoadTaskBuilder(resourceManager="
            + this.resourceManager
            + ", textureLocation="
            + this.textureLocation
            + ", format="
            + this.format
            + ", quality="
            + this.quality
            + ", isSlicedCloak="
            + this.isSlicedCloak
            + ")";
      }
   }
}
