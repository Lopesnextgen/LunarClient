package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui3;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers4;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class RewindhandlersNameplate2 {
   private static final String field1 = "https://ffmpeg.lunarclientcdn.com/%s/ffmpeg-%s-%s.zip";
   private static final String field2 = "7.1.1";
   private final Gui3 field3;

   public RewindhandlersNameplate2(Gui3 var1) {
      this.field3 = var1;
   }

   public void method1(RewindHandlers var1, Path var2) {
      if (this.field3 != null) {
         this.field3.onStart();
      }

      Path var3 = null;
      HttpURLConnection var4 = null;

      label242: {
         try {
            String var5 = this.method2();
            String var6 = this.method3();
            if ("unknown".equals(var5) || "unknown".equals(var6)) {
               this.method6("Unsupported OS or architecture: " + System.getProperty("os.name") + "/" + System.getProperty("os.arch"), null);
               return;
            }

            String var7 = String.format("https://ffmpeg.lunarclientcdn.com/%s/ffmpeg-%s-%s.zip", "7.1.1", var5, var6);
            URL var8 = new URL(var7);
            var4 = (HttpURLConnection)var8.openConnection();
            var4.setRequestMethod("GET");
            var4.setConnectTimeout(15000);
            var4.setReadTimeout(300000);
            int var9 = var4.getResponseCode();
            if (var9 == 200) {
               long var10 = var4.getContentLengthLong();
               var3 = Files.createTempFile("ffmpeg-download-", ".zip");
               BufferedInputStream var12 = new BufferedInputStream(var4.getInputStream());

               try (OutputStream var13 = Files.newOutputStream(var3)) {
                  byte[] var14 = new byte[8192];
                  long var16 = 0L;

                  int var15;
                  while ((var15 = var12.read(var14)) != -1) {
                     var13.write(var14, 0, var15);
                     var16 += var15;
                     if (this.field3 != null) {
                        float var18 = var10 > 0L ? (float)(var16 * 100L) / (float)var10 : -1.0F;
                        this.field3.method1(var16, var10, var18);
                     }
                  }
               } catch (Throwable var36) {
                  try {
                     var12.close();
                  } catch (Throwable var33) {
                     var36.addSuppressed(var33);
                  }

                  throw var36;
               }

               var12.close();
               this.method7("Extracting...");
               this.method4(var3, var2);
               if (!ThreadModuleDumpType2.isWindows()) {
                  this.method5(var2);
               }
               break label242;
            }

            this.method6("Server returned HTTP " + var9 + " " + var4.getResponseMessage() + " for URL: " + var7, null);
         } catch (Exception var37) {
            this.method6("Error during download/extraction: " + var37.getMessage(), var37);
            break label242;
         } finally {
            if (var4 != null) {
               var4.disconnect();
            }

            if (var3 != null) {
               try {
                  Files.deleteIfExists(var3);
               } catch (IOException var32) {
                  Inventorymod2.method5(var32, "FFmpegDownloader");
               }
            }

            if (this.field3 != null) {
               this.field3.method2();
            }
         }

         return;
      }

      RewindHandlers4 var39 = var1.method57();
      var39.method26().method8().method1();
   }

   private String method2() {
      if (ThreadModuleDumpType2.isMacos()) {
         return "macos";
      } else if (ThreadModuleDumpType2.isWindows()) {
         return "windows";
      } else {
         return ThreadModuleDumpType2.isLinux() ? "linux" : "unknown";
      }
   }

   private String method3() {
      String var1 = System.getProperty("os.arch", "generic").toLowerCase(Locale.ENGLISH);
      if (var1.contains("amd64") || var1.contains("x86_64")) {
         return "x64";
      } else if (var1.contains("aarch64") || var1.contains("arm64")) {
         return "arm64";
      } else {
         return var1.contains("x86") ? "x86" : "unknown";
      }
   }

   private void method4(Path var1, Path var2) {
      Files.createDirectories(var2);

      ZipEntry var6;
      try (
         InputStream var3 = Files.newInputStream(var1);
         BufferedInputStream var4 = new BufferedInputStream(var3);
         ZipInputStream var5 = new ZipInputStream(var4);
      ) {
         for (; (var6 = var5.getNextEntry()) != null; var5.closeEntry()) {
            Path var7 = var2.resolve(var6.getName());
            if (!var7.normalize().startsWith(var2.normalize())) {
               throw new IOException("Bad zip entry: " + var6.getName() + " (Path traversal attempt)");
            }

            if (var6.isDirectory()) {
               Files.createDirectories(var7);
            } else {
               if (var7.getParent() != null) {
                  Files.createDirectories(var7.getParent());
               }

               Files.copy(var5, var7, StandardCopyOption.REPLACE_EXISTING);
            }
         }
      }
   }

   private void method5(Path var1) {
      String var2 = "ffmpeg";
      byte var3 = 5;

      try (Stream var4 = Files.walk(var1, var3)) {
         var4.filter(var0 -> Files.isRegularFile(var0))
            .forEach(
               var2x -> {
                  if (var2x.getFileName().toString().equals(var2)) {
                     if (!Files.isExecutable(var2x)) {
                        try {
                           if (!var2x.toFile().setExecutable(true)) {
                              this.method7(
                                 "Warning: Failed to set executable permission for " + var2x + ". It might already be set or permissions are insufficient."
                              );
                           }
                        } catch (SecurityException var4x) {
                           this.method6("Warning: Could not set executable permission for " + var2x + " due to security manager: " + var4x.getMessage(), var4x);
                        }
                     }
                  }
               }
            );
      }
   }

   private void method6(String var1, Exception var2) {
      if (this.field3 != null) {
         this.field3.method3(var1, var2);
      } else {
         System.err.println("Error: " + var1);
         if (var2 != null) {
            Inventorymod2.method5(var2, "FFmpegDownloader");
         }
      }
   }

   private void method7(String var1) {
      if (this.field3 != null) {
         this.field3.onMessage(var1);
      } else {
         System.out.println("Info: " + var1);
      }
   }
}
