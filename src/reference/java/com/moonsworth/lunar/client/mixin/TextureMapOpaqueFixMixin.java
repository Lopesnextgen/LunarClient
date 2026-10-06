package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.render.texture.OpaqueTextureFix;
import com.moonsworth.lunar.client.util.io.ImageUtils;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
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

@Mixin(TextureMap.class)
public class TextureMapOpaqueFixMixin {
   public TextureMapOpaqueFixMixin() {
   }

   @Inject(
      method = "loadTextureAtlas(Lnet/minecraft/client/resources/IResourceManager;)V",
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Lnet/minecraft/client/renderer/texture/TextureUtil;readBufferedImage(Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;",
         shift = Shift.AFTER,
         by = 1,
         ordinal = 0
      ),
      locals = LocalCapture.CAPTURE_FAILSOFT,
      require = 0,
      expect = 0
   )
   public void onLoadTextureAtlas(
      IResourceManager iresourcemanager1,
      CallbackInfo callback2,
      int number3,
      Stitcher stitcher4,
      int number5,
      int number6,
      Iterator<?> iterator7,
      Entry<?, ?> entry8,
      TextureAtlasSprite textureatlassprite9,
      ResourceLocation location10,
      ResourceLocation location11,
      IResource iresource12,
      BufferedImage[] items13
   ) {
      try {
         try {
            ResourcePackRepository resourcepackrepository14 = Minecraft.getMinecraft().getResourcePackRepository();
            IResourcePack iresourcepack15 = resourcepackrepository14.getResourcePackInstance() == null ? resourcepackrepository14.rprDefaultResourcePack : resourcepackrepository14.getResourcePackInstance();
            InputStream input16 = iresourcepack15.getInputStream(location11);
            if (input16 != null) {
               BufferedImage bufferedimage17 = ImageUtils.method1(input16, null);
               if (!OpaqueTextureFix.method1(bufferedimage17) && OpaqueTextureFix.method1(items13[0])) {
                  OpaqueTextureFix.method2(items13[0]);
               }
            }
         } catch (FileNotFoundException filenotfoundexception18) {
            filenotfoundexception18.printStackTrace();
         }
      } catch (Throwable exception19) {
         throw exception19;
      }
   }
}
