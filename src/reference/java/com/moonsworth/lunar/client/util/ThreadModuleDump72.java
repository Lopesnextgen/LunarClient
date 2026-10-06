package com.moonsworth.lunar.client.util;

import com.google.common.hash.Hashing;
import com.google.common.net.MediaType;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge20Extension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import javax.imageio.ImageIO;
import lombok.Generated;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public final class ThreadModuleDump72 {
   private static final Path field1 = ThreadModuleDump48.field7.resolve("downloaded-images");
   private static final ResourceLocationBridge DEFAULT_TEXTURE = ResourceLocationBridge.create("lunar", "backgrounds/dirt_background.png");

   public static InputStream imageToJpegStream(BufferedImage var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      ImageIO.write(var0, "jpeg", var1);
      return new ByteArrayInputStream(var1.toByteArray());
   }

   public static BufferedImage readImage(InputStream var0) {
      if (var0 == null) {
         return null;
      }

      BufferedImage var1;
      try {
         var1 = Bridge.method8().method55(var0);
      } finally {
         IOUtils.closeQuietly(var0);
      }

      return var1;
   }

   public static Optional<BufferedImage> decodeBase64Image(String var0) {
      ByteBuf var1 = Unpooled.copiedBuffer(var0, Charsets.UTF_8);
      ByteBuf var2 = Base64.decode(var1);

      try {
         return Optional.ofNullable(readImage(new ByteBufInputStream(var2)));
      } catch (IOException var7) {
         var7.printStackTrace();
      } finally {
         var1.release();
         var2.release();
      }

      return Optional.empty();
   }

   private static String md5Hash(String var0) {
      return Hashing.md5().hashString(var0, com.google.common.base.Charsets.UTF_8).toString();
   }

   public static void loadTexture(ResourceLocationBridge var0, String var1) {
      Path var2 = field1;
      MediaType var3 = MediaType.PNG;

      try {
         Files.createDirectories(var2);
      } catch (IOException var7) {
         Inventorymod2.method5(var7, "Loading Downloads");
         return;
      }

      String var4 = md5Hash(var1);
      Path var5 = var2.resolve(var4 + "." + var3.subtype());
      Bridge20Extension var6 = Bridge.method8().method14(var5.toFile(), var1, var0, field2);
      var6.setMimeType(MediaType.PNG);
      ThreadModuleDump63.method3().bridge$getTextureManager().bridge$loadTexture(var0, var6);
   }

   @Generated
   private ThreadModuleDump72() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static Path getCacheDir() {
      return field1;
   }
}
