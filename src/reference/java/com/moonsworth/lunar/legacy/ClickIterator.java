package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge4$Data;
import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.render.texture.TexturePathResolver;
import com.moonsworth.lunar.client.render.texture.ModelTextureUpdater;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Locale;
import javax.imageio.ImageIO;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class ClickIterator extends ModelTextureUpdater {
   private final TextureMap field1;

   @Override
   protected boolean method5(String var1) {
      Client var2 = ThreadModuleDump63.method4();
      if (var2 != null && var2.method40().method84() != null) {
         TextureAtlasSprite var3 = this.field1.getAtlasSprite(var1);
         if (var3 != null && var3 != this.field1.missingImage) {
            TexturePathResolver var4 = var2.method40().method84().method71();
            boolean var5 = var4.method6(var1);
            boolean var6 = false;
            Iterator var7 = this.method5((Bridge4_8)var3, var1).iterator();

            while (true) {
               String var8;
               TextureAtlasSprite var9;
               Bridge4_8 var10;
               Bridge4$Data var11;
               while (true) {
                  if (!var7.hasNext()) {
                     return var6;
                  }

                  var8 = (String)var7.next();
                  var9 = this.field1.getAtlasSprite(var8);
                  if (var9 != null && var9 != this.field1.missingImage) {
                     var10 = (Bridge4_8)var9;
                     var11 = var10.bridge$getSourceImage();
                     if (var5) {
                        break;
                     }

                     if (var11 != null) {
                        var10.bridge$setSourceImage(null);
                        break;
                     }
                  }
               }

               BufferedImage var12;
               if (var11 == null) {
                  try {
                     IResourceManager var13 = Minecraft.getMinecraft().getResourceManager();
                     IResource var14 = var13.getResource(this.method3(var9));
                     BufferedImage var15 = ThreadModuleDump63.MC_VERSION < 1
                        ? ImageIO.read(var14.getInputStream())
                        : TextureUtil.readBufferedImage(var14.getInputStream());
                     var12 = new BufferedImage(var15.getWidth(), var15.getHeight(), 2);
                     Graphics2D var16 = var12.createGraphics();
                     var16.setComposite(AlphaComposite.Src);
                     var16.drawImage(var15, 0, 0, null);
                     var16.dispose();
                  } catch (Throwable var18) {
                     if (!var8.equals(OverlayMod.BARRIER_OUTLINE_TEXTURE.toString())) {
                        Slayer.method9(var18, "[TextureUpdater] Error while reading %s", var8);
                     }

                     var12 = new BufferedImage(var9.width, var9.height, 2);
                  }

                  int var19 = var12.getWidth();
                  int var20 = var12.getHeight();
                  int[] var21 = var12.getRGB(0, 0, var19, var20, null, 0, var19);
                  var10.bridge$setSourceImage(new Bridge4$Data(var21, var19, var20));
               } else {
                  var12 = new BufferedImage(var11.method2(), var11.method3(), 2);
                  var12.setRGB(0, 0, var12.getWidth(), var12.getHeight(), var11.method1(), 0, var12.getWidth());
               }

               if (var5) {
                  MixinHelper4.method6(var12, var1, var8);
               }

               try {
                  this.method2(var9, var12);
                  if (ThreadModuleDump63.MC_VERSION >= 1) {
                     GlStateManager.bindTexture(this.field1.getGlTextureId());
                  } else {
                     GL11.glBindTexture(3553, this.field1.getGlTextureId());
                  }

                  TextureUtil.uploadTextureMipmap(
                     var9.getFrameTextureData(0), var9.getIconWidth(), var9.getIconHeight(), var9.getOriginX(), var9.getOriginY(), false, false
                  );
                  Bridge.method5().ifPresent(var1x -> var1x.updateMultiTextureSprite(var10));
                  var6 = true;
               } catch (IOException var17) {
                  throw new RuntimeException(var17);
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void method2(TextureAtlasSprite var1, BufferedImage var2) {
      AnimationMetadataSection var3 = var1.animationMetadata;
      var1.resetSprite();
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         var1.interpolatedFrameData = null;
      }

      if (ThreadModuleDump63.MC_VERSION > 1) {
         var1.loadSpriteFrames$v1_12(new ClickIterator.Data(this.method3(var1), var2, var3), 1 + this.field1.mipmapLevels);
      } else {
         BufferedImage[] var4 = new BufferedImage[1 + this.field1.mipmapLevels];
         var4[0] = var2;
         if (ThreadModuleDump63.MC_VERSION == 1) {
            var1.loadSprite(var4, var3);
         } else {
            var1.loadSprite(var4, var3, Minecraft.getMinecraft().gameSettings.anisotropicFiltering$v1_7 > 1.0F);
         }
      }

      var1.generateMipmaps(this.field1.mipmapLevels);
   }

   private ResourceLocation method3(TextureAtlasSprite var1) {
      return this.method4(var1, 0);
   }

   private ResourceLocation method4(TextureAtlasSprite var1, int var2) {
      ResourceLocation var3 = new ResourceLocation(var1.getIconName());
      String var4 = ThreadModuleDump63.MC_VERSION < 5 ? var3.getResourceDomain() : var3.getNamespace$v1_12();
      String var5 = ThreadModuleDump63.MC_VERSION < 5 ? var3.getResourcePath() : var3.getPath();
      String var6 = var5.toLowerCase(Locale.ROOT);
      if (var6.startsWith("mcpatcher/") || var6.startsWith("optifine/")) {
         return new ResourceLocation(var3 + ".png");
      } else {
         return var2 == 0
            ? new ResourceLocation(var4, String.format("%s/%s%s", this.field1.basePath, var5, ".png"))
            : new ResourceLocation(var4, String.format("%s/mipmaps/%s.%d%s", this.field1.basePath, var5, var2, ".png"));
      }
   }

   @Generated
   public ClickIterator(TextureMap var1) {
      this.field1 = var1;
   }

   private static class Data implements IResource {
      private final ResourceLocation field1;
      private final BufferedImage field2;
      private final AnimationMetadataSection field3;

      public InputStream getInputStream() {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();

         try {
            ImageIO.write(this.field2, "PNG", var1);
         } catch (IOException var3) {
            throw new RuntimeException(var3);
         }

         return new ByteArrayInputStream(var1.toByteArray());
      }

      public boolean hasMetadata() {
         return true;
      }

      public IMetadataSection getMetadata(String var1) {
         return "animation".equals(var1) ? this.field3 : null;
      }

      public ResourceLocation getResourceLocation() {
         return this.field1;
      }

      @Nullable
      public <T extends IMetadataSection> T getMetadata(String var1) {
         return (T)("animation".equals(var1) ? this.field3 : null);
      }

      public String getResourcePackName() {
         return "";
      }

      @Generated
      public Data(ResourceLocation var1, BufferedImage var2, AnimationMetadataSection var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }
   }
}
