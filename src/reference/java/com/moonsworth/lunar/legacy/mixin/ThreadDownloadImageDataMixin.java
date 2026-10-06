package com.moonsworth.lunar.legacy.mixin;

import com.google.common.net.MediaType;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.bridge.BufferedImageBridge;
import com.moonsworth.lunar.bridge.ThreadDownloadImageDataBridge;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.io.ImageUtils;
import com.moonsworth.lunar.ichor.VersionGate;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThreadDownloadImageData.class)
public abstract class ThreadDownloadImageDataMixin implements ThreadDownloadImageDataBridge {
   @Shadow
   public boolean textureUploaded;
   @Final
   @Shadow
   public String imageUrl;
   @Final
   @Shadow
   public IImageBuffer imageBuffer;
   @Shadow
   public BufferedImage bufferedImage;
   @Final
   @Shadow
   public File cacheFile;
   private static final ThreadFactory textureThreadFactory = new ThreadFactoryBuilder().setNameFormat("Texture Downloader #%d").setDaemon(true).build();
   private static final ExecutorService textureThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), textureThreadFactory);
   @Unique
   private CompletableFuture<AutoCloseableExtension> requestFuture;
   @Nullable
   private MediaType mimeType = null;

   public ThreadDownloadImageDataMixin() {
   }

   @Shadow
   public abstract void setBufferedImage(BufferedImage bufferedimage1);

   @Redirect(
      method = "loadTexture(Lnet/minecraft/client/resources/IResourceManager;)V",
      at = @At(value = "INVOKE", target = "Ljavax/imageio/ImageIO;read(Ljava/io/File;)Ljava/awt/image/BufferedImage;")
   )
   public BufferedImage impl$ImageIOread(File file1) {
      try {
         return ImageUtils.method1(null, file1);
      } catch (Throwable exception3) {
         throw exception3;
      }
   }

   public void setMimeType(@Nullable MediaType mediatype1) {
      this.mimeType = mediatype1;
   }

   @Overwrite
   @VersionGate(max = 0)
   public void func_152433_a$v1_7() {
      this.impl$loadTextureFromServer();
   }

   @Overwrite
   @VersionGate(min = 1)
   public void loadTextureFromServer() {
      this.impl$loadTextureFromServer();
   }

   public void impl$loadTextureFromServer() {
      textureThreadPool.submit(() -> {
         HttpURLConnection httpurlconnection1 = null;
         ThreadDownloadImageData.logger.debug("Downloading http texture from {} to {}", this.imageUrl, this.cacheFile);

         try {
            httpurlconnection1 = (HttpURLConnection)new URL(this.imageUrl).openConnection(Minecraft.getMinecraft().getProxy());
            if (this.imageUrl.contains(ServiceEndpoints.field3)) {
               httpurlconnection1.setRequestProperty("Referer", "LunarClient-Java");
            }

            if (this.mimeType != null) {
               httpurlconnection1.setRequestProperty("Accept", this.mimeType.toString());
            }

            try (InputStream input2 = httpurlconnection1.getInputStream()) {
               BufferedImage bufferedimage3 = ImageUtils.method1(input2, this.cacheFile);
               if (this.imageBuffer != null) {
                  bufferedimage3 = this.imageBuffer.parseUserSkin(bufferedimage3);
               }

               this.setBufferedImage(bufferedimage3);
            }
         } catch (Exception exception27) {
            if (!(exception27 instanceof FileNotFoundException)) {
               ThreadDownloadImageData.logger.error("Couldn't download http texture: " + exception27.getClass().getName() + ": " + exception27.getMessage());
            }
         } finally {
            this.bridge$setImageFound(this.bufferedImage != null);
            if (httpurlconnection1 != null && httpurlconnection1.getErrorStream() != null) {
               try (InputStream input6 = httpurlconnection1.getErrorStream()) {
                  while (input6.skip(2048L) > 0L) {
                  }
               } catch (Exception exception25) {
               }
            }
         }
      });
   }

   @Inject(method = "setBufferedImage", at = @At("HEAD"))
   private void impl$setBufferedImage(@Nullable BufferedImage bufferedimage1, CallbackInfo callback2) {
      if (bufferedimage1 != null) {
         if (this.requestFuture != null) {
            this.requestFuture.complete(new BufferedImageBridge(bufferedimage1));
         }
      }
   }

   public CompletableFuture<AutoCloseableExtension> bridge$requestContent() {
      if (this.bufferedImage != null) {
         return CompletableFuture.completedFuture(new BufferedImageBridge(this.bufferedImage));
      }

      if (this.requestFuture == null) {
         this.requestFuture = new CompletableFuture<>();
      }

      return this.requestFuture;
   }

   public File bridge$getFile() {
      return this.cacheFile;
   }
}
