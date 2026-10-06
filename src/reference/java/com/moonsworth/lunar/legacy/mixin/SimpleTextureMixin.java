package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.io.ImageUtils;
import com.moonsworth.lunar.client.render.texture.DownloadedImageCache;
import com.moonsworth.lunar.client.cosmetics.CloakTextureSlicer;
import com.moonsworth.lunar.ichor.VersionGate;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SimpleTexture.class)
public class SimpleTextureMixin {
   @Final
   @Shadow
   public ResourceLocation textureLocation;

   public SimpleTextureMixin() {
   }

   @VersionGate(max = 0)
   @Redirect(method = "loadTexture", at = @At(value = "INVOKE", target = "Ljavax/imageio/ImageIO;read(Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;"))
   public BufferedImage lunar$sliceCloakTexture_v1_7(InputStream input1) {
      try {
         BufferedImage bufferedimage2 = ImageUtils.method1(input1, null);
         return CloakTextureSlicer.method2((ResourceLocationBridge)this.textureLocation) ? CloakTextureSlicer.method3(bufferedimage2, 8) : bufferedimage2;
      } catch (Throwable exception3) {
         throw exception3;
      }
   }

   @VersionGate(min = 1)
   @Redirect(
      method = "loadTexture",
      at = @At(
         value = "INVOKE",
         target = "net/minecraft/client/renderer/texture/TextureUtil.readBufferedImage (Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;"
      )
   )
   private BufferedImage lunar$sliceCloakTexture_v1_8(InputStream input1) {
      try {
         BufferedImage bufferedimage2 = DownloadedImageCache.method2(input1);
         return CloakTextureSlicer.method2((ResourceLocationBridge)this.textureLocation) ? CloakTextureSlicer.method3(bufferedimage2, 8) : bufferedimage2;
      } catch (Throwable exception3) {
         throw exception3;
      }
   }
}
