package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.render.texture.TexturePathResolver;
import com.moonsworth.lunar.client.render.texture.OpaqueTextureFix;
import com.moonsworth.lunar.ichor.VersionGate;
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
public abstract class TextureMapTransparencyMixin {
   public TextureMapTransparencyMixin() {
   }

   @Inject(method = "loadTextureAtlas", at = @At("RETURN"))
   private void lunar$onLoadPre(IResourceManager iresourcemanager1, CallbackInfo callback2) {
      if (this == Minecraft.getMinecraft().getTextureMapBlocks()) {
         TexturePathResolver.method1(new ClickIterator((TextureMap)this));
      }
   }

   @VersionGate(1)
   @Redirect(
      method = "loadTextureAtlas",
      at = @At(value = "INVOKE", target = "Lnet/optifine/util/TextureUtils;scaleImage(Ljava/awt/image/BufferedImage;I)Ljava/awt/image/BufferedImage;")
   )
   private BufferedImage impl$scaleTexture(BufferedImage bufferedimage1, int number2) {
      return bufferedimage1;
   }

   @VersionGate(max = 0)
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
      IResourceManager iresourcemanager1,
      CallbackInfo callback2,
      int number3,
      Stitcher stitcher4,
      int number5,
      Iterator<?> iterator6,
      TextureAtlasSprite textureatlassprite7,
      Entry<?, ?> entry8,
      ResourceLocation location9,
      ResourceLocation location10,
      IResource iresource11,
      BufferedImage[] items12
   ) {
      this.removeTransparency(location10, items12);
   }

   @VersionGate(1)
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
      IResourceManager iresourcemanager1,
      CallbackInfo callback2,
      int number3,
      Stitcher stitcher4,
      int number5,
      int number6,
      int number7,
      int number8,
      int number9,
      Iterator iterator10,
      Entry entry11,
      TextureAtlasSprite textureatlassprite12,
      ResourceLocation location13,
      ResourceLocation location14,
      IResource iresource15,
      BufferedImage[] items16
   ) {
      try {
         this.removeTransparency(location14, items16);
      } catch (Throwable exception18) {
         throw exception18;
      }
   }

   @VersionGate(max = 1)
   private void removeTransparency(ResourceLocation location1, BufferedImage[] items2) {
      try {
         try {
            ResourcePackRepository resourcepackrepository3 = Minecraft.getMinecraft().getResourcePackRepository();
            IResourcePack iresourcepack4 = resourcepackrepository3.getResourcePackInstance() == null ? resourcepackrepository3.rprDefaultResourcePack : resourcepackrepository3.getResourcePackInstance();
            InputStream input5 = iresourcepack4.getInputStream(location1);
            if (input5 != null) {
               try {
                  BufferedImage bufferedimage6 = ImageIO.read(input5);
                  if (!OpaqueTextureFix.method1(bufferedimage6) && OpaqueTextureFix.method1(items2[0])) {
                     OpaqueTextureFix.method2(items2[0]);
                  }
               } catch (IOException exception7) {
                  exception7.printStackTrace();
               }
            }
         } catch (FileNotFoundException filenotfoundexception8) {
         }
      } catch (Throwable exception9) {
         throw exception9;
      }
   }
}
