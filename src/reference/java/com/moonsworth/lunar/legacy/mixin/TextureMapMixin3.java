package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.render.texture.TexturePathResolver;
import com.moonsworth.lunar.client.util.ThreadModuleDump50;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.ClickIterator;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TextureMap.class)
public abstract class TextureMapMixin3 {
   @Inject(method = "loadTextureAtlas", at = @At("RETURN"))
   private void lunar$onLoadPre(IResourceManager var1, CallbackInfo var2) {
      if (this == Minecraft.getMinecraft().getTextureMapBlocks()) {
         TexturePathResolver.method1(new ClickIterator((TextureMap)this));
      }
   }

   @Annotation2(1)
   @Redirect(
      method = "loadTextureAtlas",
      at = @At(value = "INVOKE", target = "Lnet/optifine/util/TextureUtils;scaleImage(Ljava/awt/image/BufferedImage;I)Ljava/awt/image/BufferedImage;")
   )
   private BufferedImage impl$scaleTexture(BufferedImage var1, int var2) {
      return var1;
   }

   @Annotation2(max = 0)
   @Inject(
      method = "loadTextureAtlas(Lnet/minecraft/client/resources/IResourceManager;)V",
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Ljavax/imageio/ImageIO;read(Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;",
         shift = Shift.AFTER,
         by = 1,
         ordinal = 0
      ),
      locals = LocalCapture.CAPTURE_FAILEXCEPTION
   )
   public void postReadImage(
      IResourceManager var1,
      CallbackInfo var2,
      int var3,
      Stitcher var4,
      int var5,
      Iterator<?> var6,
      TextureAtlasSprite var7,
      Entry<?, ?> var8,
      ResourceLocation var9,
      ResourceLocation var10,
      IResource var11,
      BufferedImage[] var12
   ) {
      this.removeTransparency(var10, var12);
   }

   @Annotation2(1)
   @Inject(
      method = "loadTextureAtlas(Lnet/minecraft/client/resources/IResourceManager;)V",
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Lnet/minecraft/client/renderer/texture/TextureUtil;readBufferedImage(Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;",
         shift = Shift.AFTER,
         by = 1,
         ordinal = 0
      ),
      locals = LocalCapture.CAPTURE_FAILEXCEPTION
   )
   public void onLoadTextureAtlas(
      IResourceManager var1,
      CallbackInfo var2,
      int var3,
      Stitcher var4,
      int var5,
      int var6,
      int var7,
      int var8,
      int var9,
      Iterator var10,
      Entry var11,
      TextureAtlasSprite var12,
      ResourceLocation var13,
      ResourceLocation var14,
      IResource var15,
      BufferedImage[] var16
   ) {
      try {
         this.removeTransparency(var14, var16);
      } catch (Throwable var18) {
         throw var18;
      }
   }

   @Annotation2(max = 1)
   private void removeTransparency(ResourceLocation var1, BufferedImage[] var2) {
      try {
         try {
            ResourcePackRepository var3 = Minecraft.getMinecraft().getResourcePackRepository();
            IResourcePack var4 = var3.getResourcePackInstance() == null ? var3.rprDefaultResourcePack : var3.getResourcePackInstance();
            InputStream var5 = var4.getInputStream(var1);
            if (var5 != null) {
               try {
                  BufferedImage var6 = ImageIO.read(var5);
                  if (!ThreadModuleDump50.method1(var6) && ThreadModuleDump50.method1(var2[0])) {
                     ThreadModuleDump50.method2(var2[0]);
                  }
               } catch (IOException var7) {
                  var7.printStackTrace();
               }
            }
         } catch (FileNotFoundException var8) {
         }
      } catch (Throwable var9) {
         throw var9;
      }
   }
}
