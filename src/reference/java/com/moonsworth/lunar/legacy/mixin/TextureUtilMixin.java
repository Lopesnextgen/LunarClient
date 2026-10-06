package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.util.io.ImageUtils;
import com.moonsworth.lunar.ichor.VersionGate;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextureUtil.class)
public abstract class TextureUtilMixin {
   public TextureUtilMixin() {
   }

   @VersionGate(max = 0)
   @Redirect(method = "readImageData", at = @At(value = "INVOKE", target = "javax/imageio/ImageIO.read (Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;"))
   private static BufferedImage lunar$redirectImageIoToLunar_v1_7(InputStream input0) {
      return lunar$redirectImageIoToLunar(input0);
   }

   @VersionGate(min = 1)
   @Redirect(
      method = "readBufferedImage$v1_8",
      at = @At(value = "INVOKE", target = "javax/imageio/ImageIO.read (Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;")
   )
   private static BufferedImage lunar$redirectImageIoToLunar_v1_8(InputStream input0) {
      return lunar$redirectImageIoToLunar(input0);
   }

   @Unique
   private static BufferedImage lunar$redirectImageIoToLunar(InputStream input0) {
      try {
         return ImageUtils.method1(input0, null);
      } catch (Exception exception2) {
         CrashReporter.method5(exception2, "Reading Image with LunarImageIO");
         throw exception2;
      }
   }
}
