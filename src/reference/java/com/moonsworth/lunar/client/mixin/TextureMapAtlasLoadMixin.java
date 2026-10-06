package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.render.texture.TexturePathResolver;
import com.moonsworth.lunar.client.render.texture.OpaqueTextureFix;
import com.moonsworth.lunar.client.util.io.ImageUtils;
import com.moonsworth.lunar.ichor.MixinCondition;
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

@MixinCondition(absent = "optifine")
@Mixin(TextureMap.class)
public class TextureMapAtlasLoadMixin {
   public TextureMapAtlasLoadMixin() {
   }

   @Inject(method = "loadTextureAtlas", at = @At("RETURN"))
   private void lunar$onLoadPre(IResourceManager iresourcemanager1, CallbackInfo callback2) {
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
      IResourceManager iresourcemanager1,
      CallbackInfo callback2,
      int number3,
      Stitcher stitcher4,
      int number5,
      Iterator<?> iterator6,
      Entry<?, ?> entry7,
      ResourceLocation location8,
      TextureAtlasSprite textureatlassprite9,
      ResourceLocation location10,
      IResource iresource11,
      BufferedImage[] items12
   ) {
      try {
         ResourcePackRepository resourcepackrepository13 = Minecraft.getMinecraft().getResourcePackRepository();
         IResourcePack iresourcepack14 = resourcepackrepository13.getResourcePackInstance() == null ? resourcepackrepository13.rprDefaultResourcePack : resourcepackrepository13.getResourcePackInstance();
         InputStream input15 = iresourcepack14.getInputStream(location10);
         if (input15 != null) {
            BufferedImage bufferedimage16 = ImageUtils.method1(input15, null);
            if (!OpaqueTextureFix.method1(bufferedimage16) && OpaqueTextureFix.method1(items12[0])) {
               OpaqueTextureFix.method2(items12[0]);
            }
         }
      } catch (Exception exception17) {
      }
   }
}
