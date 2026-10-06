package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump50;
import com.moonsworth.lunar.client.util.ThreadModuleDump59;
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
public class TextureMapMixin3 {
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
      IResourceManager var1,
      CallbackInfo var2,
      int var3,
      Stitcher var4,
      int var5,
      int var6,
      Iterator<?> var7,
      Entry<?, ?> var8,
      TextureAtlasSprite var9,
      ResourceLocation var10,
      ResourceLocation var11,
      IResource var12,
      BufferedImage[] var13
   ) {
      try {
         try {
            ResourcePackRepository var14 = Minecraft.getMinecraft().getResourcePackRepository();
            IResourcePack var15 = var14.getResourcePackInstance() == null ? var14.rprDefaultResourcePack : var14.getResourcePackInstance();
            InputStream var16 = var15.getInputStream(var11);
            if (var16 != null) {
               BufferedImage var17 = ThreadModuleDump59.method1(var16, null);
               if (!ThreadModuleDump50.method1(var17) && ThreadModuleDump50.method1(var13[0])) {
                  ThreadModuleDump50.method2(var13[0]);
               }
            }
         } catch (FileNotFoundException var18) {
            var18.printStackTrace();
         }
      } catch (Throwable var19) {
         throw var19;
      }
   }
}
