package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.IResourcePackBridge;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.render.texture.AnimatedSprite;
import com.moonsworth.lunar.client.util.ThreadModuleDump59;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Optional;
import javax.imageio.ImageIO;
import lombok.Generated;
import org.apache.commons.io.IOUtils;

public class ResourcePackSprite extends AnimatedSprite {
   private final ResourceLocationBridge field3;
   private final int field4;
   private final int field5;
   private final boolean field6;
   private final AnimatedSprite.Data3 field7;
   private BufferedImage image;
   private ResourceLocationBridge field8;

   public ResourcePackSprite(boolean var1, ResourceLocationBridge var2, int var3, int var4, boolean var5, AnimatedSprite.Data3 var6, BufferedImage var7) {
      super(var1);
      this.field3 = var2;
      this.field4 = var3;
      this.field5 = var4;
      this.field6 = var5;
      this.field7 = var6;
      this.image = var7;
   }

   public static Optional<AnimatedSprite> method1(IResourcePackBridge var0, String var1, float var2, float var3, float var4, float var5, int var6, int var7) {
      InputStream var8 = null;
      ResourceLocationBridge var9 = ResourceLocationBridge.create(var1);

      try {
         try {
            var8 = var0.bridge$getInputStream(var9);
         } catch (Exception var27) {
            Slayer.method5(
               "Reading pack resource " + var1 + " in pack " + var0.bridge$getPackName() + ", falling back to vanilla (" + var27.getMessage() + ")",
               new Object[0]
            );
         }

         if (var8 == null) {
            return Optional.empty();
         }

         BufferedImage var10 = ImageIO.read(var8);
         if (var10 == null) {
            return Optional.empty();
         }

         boolean var11 = false;
         int var12 = var10.getWidth();
         int var13 = var10.getHeight();
         if (var12 != var13) {
            try (InputStream var14 = var0.bridge$getInputStream(ResourceLocationBridge.create(var9 + ".mcmeta"))) {
               var11 = true;
               var13 = var12;
            } catch (Exception var29) {
            }
         }

         int var35 = (int)Math.floor(var2 * var12);
         int var15 = (int)Math.floor(var3 * var13);
         int var16 = (int)Math.floor(var4 * var12);
         int var17 = (int)Math.floor(var5 * var13);
         var35 = Math.max(0, Math.min(var35, var12 - 1));
         var15 = Math.max(0, Math.min(var15, var13 - 1));
         var16 = Math.max(0, Math.min(var16, var12 - 1)) + 1;
         var17 = Math.max(0, Math.min(var17, var13 - 1)) + 1;
         var12 = var16 - var35;
         var13 = var17 - var15;
         var10 = var10.getSubimage(var35, var15, var12, var13);
         return Optional.of(
            new ResourcePackSprite(
               var0 == ThreadModuleDump63.method3().bridge$getMcDefaultResourcePack(),
               var9,
               var12,
               var13,
               var11,
               new AnimatedSprite.Data3(var6, var7),
               ThreadModuleDump59.method3(var10)
            )
         );
      } catch (Exception var30) {
         Slayer.method9(var30, "Error while reading resource pack for preview %s%s", new Object[]{var0.bridge$getPackName(), var9.toString()});
      } finally {
         IOUtils.closeQuietly(var8);
      }

      return Optional.empty();
   }

   public static Optional<AnimatedSprite> method2(IResourcePackBridge var0, String var1, int var2, int var3) {
      return method1(var0, var1, 0.0F, 0.0F, 1.0F, 1.0F, var2, var3);
   }

   @Override
   public boolean method1() {
      if (this.image != null && this.field8 == null) {
         BufferedImage var1 = this.image;
         this.image = null;
         Bridge8Extension33 var2 = Bridge.method8().method22(var1);
         String var3 = this.field3.bridge$getPath().replaceAll("/", "_");
         this.field8 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getDynamicTextureLocation(var3, var2);
      }

      return this.field8 != null;
   }

   @Override
   public AnimatedSprite.Data3 method2(MixinHelper_4 var1, int var2, int var3, int var4, int var5) {
      if (this.field8 != null) {
         int var6 = Math.min(this.field7.method2(), this.field4);
         int var7 = Math.min(this.field7.method3(), this.field5);
         if (var7 != var5) {
            var3 += Math.round(var5 / 2.0F - var7 / 2.0F);
         }

         if (var6 != var4) {
            var2 += Math.round(var4 / 2.0F - var6 / 2.0F - 0.25F) - 1;
         }

         var1.method24(this.field8, var2, var3, var6, var7, -1);
         return new AnimatedSprite.Data3(var6, var7);
      } else {
         return AnimatedSprite.Data3.field3;
      }
   }

   @Override
   public void destroy() {
      this.image = null;
      ResourceLocationBridge var1 = this.field8;
      this.field8 = null;
      if (var1 != null) {
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(var1);
      }
   }

   @Generated
   public ResourceLocationBridge method6() {
      return this.field3;
   }

   @Generated
   public int getWidth() {
      return this.field4;
   }

   @Generated
   public int getHeight() {
      return this.field5;
   }

   @Generated
   public boolean method7() {
      return this.field6;
   }

   @Generated
   public AnimatedSprite.Data3 method8() {
      return this.field7;
   }
}
