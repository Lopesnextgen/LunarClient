package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.render.texture.TexturePathResolver;
import com.moonsworth.lunar.client.util.ThreadModuleDump50;
import com.moonsworth.lunar.client.util.ThreadModuleDump59;
import com.moonsworth.lunar.ichor.Annotation_2;
import com.moonsworth.lunar.legacy.ClickIterator;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
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
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Annotation_2(absent = "optifine")
@Mixin(TextureMap.class)
public class TextureMapMixin2 {
   @Inject(method = "loadTextureAtlas", at = @At("RETURN"))
   private void lunar$onLoadPre(IResourceManager var1, CallbackInfo var2) {
      if (this == Minecraft.getMinecraft().getTextureMapBlocks()) {
         TexturePathResolver.method1(new ClickIterator((TextureMap)this));
      }
   }

   @Inject(
      method = "loadTextureAtlas(Lnet/minecraft/client/resources/IResourceManager;)V",
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Ljavax/imageio/ImageIO;read(Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;",
         shift = Shift.AFTER,
         by = 1,
         ordinal = 0
      ),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   public void postReadImage(
      IResourceManager var1,
      CallbackInfo var2,
      int var3,
      Stitcher var4,
      int var5,
      Iterator<?> var6,
      Entry<?, ?> var7,
      ResourceLocation var8,
      TextureAtlasSprite var9,
      ResourceLocation var10,
      IResource var11,
      BufferedImage[] var12
   ) {
      try {
         ResourcePackRepository var13 = Minecraft.getMinecraft().getResourcePackRepository();
         IResourcePack var14 = var13.getResourcePackInstance() == null ? var13.rprDefaultResourcePack : var13.getResourcePackInstance();
         InputStream var15 = var14.getInputStream(var10);
         if (var15 != null) {
            BufferedImage var16 = ThreadModuleDump59.method1(var15, null);
            if (!ThreadModuleDump50.method1(var16) && ThreadModuleDump50.method1(var12[0])) {
               ThreadModuleDump50.method2(var12[0]);
            }
         }
      } catch (Exception var17) {
      }
   }
}
