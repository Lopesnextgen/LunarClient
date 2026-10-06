package com.moonsworth.lunar.client.driver;

import com.luciad.imageio.webp.WebP;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump55;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.handler.FileSystem;
import com.moonsworth.webosr.handler.FileSystem.Payload;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.apache.commons.io.FilenameUtils;

public class FileSystemIterator implements FileSystem {
   private static final Set<String> field1 = Set.of("lunar", "minecraft");
   private final boolean field2;
   private final Set<File> field3 = new HashSet<>();

   public FileSystemIterator(boolean var1) {
      this.field2 = var1;
      Client.method26().ifPresent(this.field3::add);
      Client.method29().ifPresent(this.field3::add);
      Client.method27().ifPresent(var1x -> this.field3.add(new File(var1x, "/assets/lunar")));
      Client.method28().ifPresent(var1x -> this.field3.add(new File(var1x, "/assets/lunar-jit")));
      this.field3.add(ThreadModuleDump48.field14.toFile());
      this.field3.add(ThreadModuleDump48.field15.toFile());
      this.field3.add(Gui.field10);
      this.field3.add(Gui.field7);
      this.field3.add(ThreadModuleDump48.field16.toFile());
      this.field3.add(ThreadModuleDump48.field17.toFile());
      this.field3.add(new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "resourcepacks"));
      this.field3.add(new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "assets"));
   }

   public Payload onRequest(String var1) {
      try {
         if (var1.endsWith(".imgsrc")) {
            return this.method1(var1);
         }

         File var2 = this.method4(var1);
         if (var2 != null && var2.isFile() && !var1.toLowerCase().endsWith(var2.getName().toLowerCase())) {
            int var3 = var1.toLowerCase().replace("\\", "/").indexOf(var2.getName().toLowerCase()) + var2.getName().length() + 1;
            String var4 = var1.substring(var3);

            try (ZipFile var5 = new ZipFile(var2)) {
               ZipEntry var6 = var5.getEntry(var4);
               if (var6 != null) {
                  return this.method2(var1, var5.getInputStream(var6));
               }
            }
         }

         if (var2 != null && var2.exists()) {
            return this.method2(var1, new FileInputStream(var2));
         }

         IResourceBridge var11 = this.method5(var1);
         if (var11 != null) {
            return this.method2(var1, var11.bridge$getInputStream());
         }
      } catch (IOException var10) {
         var10.printStackTrace();
         throw new RuntimeException(var10);
      }

      Slayer.method6("WebOSR-REQ", "Attempted to load URL that does not exist: " + var1);
      return new Payload(null, 0, "application/octet-stream", 404);
   }

   private Payload method1(String var1) {
      String var2 = var1.replace("file:///", "").replace(".imgsrc", "");
      String var3 = "IMGSRC-V1\n" + var2;
      ByteBuffer var4 = ByteBuffer.allocateDirect(var3.length()).order(ByteOrder.nativeOrder()).put(var3.getBytes());
      return new Payload(var4, var3.length(), "text/plain", 200);
   }

   public Payload method2(String var1, InputStream var2) {
      try (BufferedInputStream var3 = new BufferedInputStream(var2)) {
         String var4 = URLConnection.guessContentTypeFromStream(var3);
         if (var4 == null) {
            var4 = this.method3(var1);
         }

         byte[] var5 = var3.readAllBytes();
         if (this.field2 && Objects.equals(var4, "image/webp")) {
            var4 = "image/png";
            ImageReader var25 = WebP.IMAGE_READER.createReaderInstance("webp");
            ByteArrayOutputStream var8 = new ByteArrayOutputStream();

            BufferedImage var24;
            try (ImageInputStream var9 = ImageIO.createImageInputStream(new ByteArrayInputStream(var5))) {
               var25.setInput(var9, true, true);
               var24 = var25.read(0);
            } finally {
               var25.dispose();
            }

            if (var24 == null) {
               Slayer.method6("WebOSR-REQ", "Could not load image from " + var1);
               return new Payload(null, 0, "application/octet-stream", 500);
            } else {
               ImageIO.write(var24, "PNG", var8);
               ByteBuffer var26 = ByteBuffer.allocateDirect(var8.size()).order(ByteOrder.nativeOrder()).put(var8.toByteArray());
               return new Payload(var26, var8.size(), var4, 200);
            }
         } else {
            ByteBuffer var6 = ByteBuffer.allocateDirect(var5.length).order(ByteOrder.nativeOrder()).put(var5);
            return new Payload(var6, var5.length, var4, 200);
         }
      }
   }

   private String method3(String var1) {
      return ThreadModuleDump55.method1(var1);
   }

   public boolean doesResourceExist(String var1) {
      try {
         return var1.endsWith(".imgsrc") ? true : this.method4(var1) != null || this.method5(var1) != null;
      } catch (IOException var3) {
         var3.printStackTrace();
         throw new RuntimeException(var3);
      }
   }

   private File method4(String var1) {
      var1 = this.method6(new URL(var1).getPath());
      File var2 = new File(var1);
      if (var2.isAbsolute() && var2.exists()) {
         return var2;
      }

      for (File var4 : this.field3) {
         try {
            String var5 = var1.startsWith("/") ? var1.substring(1) : var1;
            File var6 = Files.exists(Path.of(var5)) ? new File(var5) : new File(var4, var1);
            if (var6.exists()) {
               String var7 = FilenameUtils.normalize(var6.getAbsolutePath());
               if (var7.startsWith(var4.getAbsolutePath())) {
                  return var6;
               }
            }

            if (var6.getParentFile().isFile()) {
               int var11 = var5.toLowerCase().indexOf(var6.getParentFile().getName().toLowerCase()) + var6.getParentFile().getName().length();
               File var8 = new File(var4, var5.substring(0, var11));
               if (var8.exists() && var8.isFile()) {
                  return var8;
               }
            }
         } catch (Exception var9) {
            var9.printStackTrace();
         }
      }

      return null;
   }

   private IResourceBridge method5(String var1) {
      String var2 = var1.replace("file:///", "");

      for (String var4 : field1) {
         ResourceLocationBridge var5 = ResourceLocationBridge.create(var4, var2);
         IResourceBridge var6 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(var5);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   private String method6(String var1) {
      String var2 = com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.getUrl().replaceFirst("file:///", "") + "/";
      return var1.equals(var2) ? var1 : var1.replaceFirst(var2, "");
   }
}
