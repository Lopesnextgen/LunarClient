package com.moonsworth.lunar.client.render.texture;

import com.google.common.hash.Hashing;
import com.google.common.net.MediaType;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ThreadDownloadImageDataBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
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
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.LunarConstants;

public final class DownloadedImageCache {
   private static final Path field1 = LunarConstants.field7.resolve("downloaded-images");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "backgrounds/dirt_background.png");

   public static InputStream method1(BufferedImage bufferedimage0) {
      ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
      ImageIO.write(bufferedimage0, "jpeg", bytearrayoutputstream1);
      return new ByteArrayInputStream(bytearrayoutputstream1.toByteArray());
   }

   public static BufferedImage method2(InputStream input0) {
      if (input0 == null) {
         return null;
      }

      BufferedImage bufferedimage1;
      try {
         bufferedimage1 = Bridge.method8().method55(input0);
      } finally {
         IOUtils.closeQuietly(input0);
      }

      return bufferedimage1;
   }

   public static Optional<BufferedImage> method3(String text0) {
      ByteBuf buffer1 = Unpooled.copiedBuffer(text0, Charsets.UTF_8);
      ByteBuf buffer2 = Base64.decode(buffer1);

      try {
         return Optional.ofNullable(method2(new ByteBufInputStream(buffer2)));
      } catch (IOException exception7) {
         exception7.printStackTrace();
      } finally {
         buffer1.release();
         buffer2.release();
      }

      return Optional.empty();
   }

   private static String method4(String text0) {
      return Hashing.md5().hashString(text0, com.google.common.base.Charsets.UTF_8).toString();
   }

   public static void method5(ResourceLocationBridge horsestats140, String text1) {
      Path path2 = field1;
      MediaType mediatype3 = MediaType.PNG;

      try {
         Files.createDirectories(path2);
      } catch (IOException exception7) {
         CrashReporter.method5(exception7, "Loading Downloads");
         return;
      }

      String text4 = method4(text1);
      Path path5 = path2.resolve(text4 + "." + mediatype3.subtype());
      ThreadDownloadImageDataBridge bridge20extension6 = Bridge.method8().method14(path5.toFile(), text1, horsestats140, field2);
      bridge20extension6.setMimeType(MediaType.PNG);
      Ref.method3().bridge$getTextureManager().bridge$loadTexture(horsestats140, bridge20extension6);
   }

   @Generated
   private DownloadedImageCache() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static Path method6() {
      return field1;
   }
}
