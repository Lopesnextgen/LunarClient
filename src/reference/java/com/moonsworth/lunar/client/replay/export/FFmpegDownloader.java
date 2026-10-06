package com.moonsworth.lunar.client.replay.export;

import com.moonsworth.lunar.client.replay.gui.ProgressListener;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRenderQueue;
import com.moonsworth.lunar.client.framework.OperatingSystem;
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

public class FFmpegDownloader {
   private static final String field1 = "https://ffmpeg.lunarclientcdn.com/%s/ffmpeg-%s-%s.zip";
   private static final String field2 = "7.1.1";
   private final ProgressListener field3;

   public FFmpegDownloader(ProgressListener gui31) {
      this.field3 = gui31;
   }

   public void method1(RewindHandlers rewindhandlers1, Path path2) {
      if (this.field3 != null) {
         this.field3.onStart();
      }

      Path path3 = null;
      HttpURLConnection httpurlconnection4 = null;

      label242: {
         try {
            String text5 = this.method2();
            String text6 = this.method3();
            if ("unknown".equals(text5) || "unknown".equals(text6)) {
               this.method6("Unsupported OS or architecture: " + System.getProperty("os.name") + "/" + System.getProperty("os.arch"), null);
               return;
            }

            String text7 = String.format("https://ffmpeg.lunarclientcdn.com/%s/ffmpeg-%s-%s.zip", "7.1.1", text5, text6);
            URL url8 = new URL(text7);
            httpurlconnection4 = (HttpURLConnection)url8.openConnection();
            httpurlconnection4.setRequestMethod("GET");
            httpurlconnection4.setConnectTimeout(15000);
            httpurlconnection4.setReadTimeout(300000);
            int number9 = httpurlconnection4.getResponseCode();
            if (number9 == 200) {
               long number10 = httpurlconnection4.getContentLengthLong();
               path3 = Files.createTempFile("ffmpeg-download-", ".zip");
               BufferedInputStream bufferedinputstream12 = new BufferedInputStream(httpurlconnection4.getInputStream());

               try (OutputStream output13 = Files.newOutputStream(path3)) {
                  byte[] items14 = new byte[8192];
                  long number16 = 0L;

                  int number15;
                  while ((number15 = bufferedinputstream12.read(items14)) != -1) {
                     output13.write(items14, 0, number15);
                     number16 += number15;
                     if (this.field3 != null) {
                        float value18 = number10 > 0L ? (float)(number16 * 100L) / (float)number10 : -1.0F;
                        this.field3.method1(number16, number10, value18);
                     }
                  }
               } catch (Throwable exception36) {
                  try {
                     bufferedinputstream12.close();
                  } catch (Throwable exception33) {
                     exception36.addSuppressed(exception33);
                  }

                  throw exception36;
               }

               bufferedinputstream12.close();
               this.method7("Extracting...");
               this.method4(path3, path2);
               if (!OperatingSystem.isWindows()) {
                  this.method5(path2);
               }
               break label242;
            }

            this.method6("Server returned HTTP " + number9 + " " + httpurlconnection4.getResponseMessage() + " for URL: " + text7, null);
         } catch (Exception exception37) {
            this.method6("Error during download/extraction: " + exception37.getMessage(), exception37);
            break label242;
         } finally {
            if (httpurlconnection4 != null) {
               httpurlconnection4.disconnect();
            }

            if (path3 != null) {
               try {
                  Files.deleteIfExists(path3);
               } catch (IOException exception32) {
                  CrashReporter.method5(exception32, "FFmpegDownloader");
               }
            }

            if (this.field3 != null) {
               this.field3.method2();
            }
         }

         return;
      }

      RewindRenderQueue rewindhandlers439 = rewindhandlers1.method57();
      rewindhandlers439.method26().method8().method1();
   }

   private String method2() {
      if (OperatingSystem.isMacos()) {
         return "macos";
      } else if (OperatingSystem.isWindows()) {
         return "windows";
      } else {
         return OperatingSystem.isLinux() ? "linux" : "unknown";
      }
   }

   private String method3() {
      String text1 = System.getProperty("os.arch", "generic").toLowerCase(Locale.ENGLISH);
      if (text1.contains("amd64") || text1.contains("x86_64")) {
         return "x64";
      } else if (text1.contains("aarch64") || text1.contains("arm64")) {
         return "arm64";
      } else {
         return text1.contains("x86") ? "x86" : "unknown";
      }
   }

   private void method4(Path path1, Path path2) {
      Files.createDirectories(path2);

      ZipEntry zipentry6;
      try (
         InputStream input3 = Files.newInputStream(path1);
         BufferedInputStream bufferedinputstream4 = new BufferedInputStream(input3);
         ZipInputStream zipinputstream5 = new ZipInputStream(bufferedinputstream4);
      ) {
         for (; (zipentry6 = zipinputstream5.getNextEntry()) != null; zipinputstream5.closeEntry()) {
            Path path7 = path2.resolve(zipentry6.getName());
            if (!path7.normalize().startsWith(path2.normalize())) {
               throw new IOException("Bad zip entry: " + zipentry6.getName() + " (Path traversal attempt)");
            }

            if (zipentry6.isDirectory()) {
               Files.createDirectories(path7);
            } else {
               if (path7.getParent() != null) {
                  Files.createDirectories(path7.getParent());
               }

               Files.copy(zipinputstream5, path7, StandardCopyOption.REPLACE_EXISTING);
            }
         }
      }
   }

   private void method5(Path path1) {
      String text2 = "ffmpeg";
      byte number3 = 5;

      try (Stream stream4 = Files.walk(path1, number3)) {
         stream4.filter(arg0 -> Files.isRegularFile(arg0))
            .forEach(
               arg2x -> {
                  if (arg2x.getFileName().toString().equals(text2)) {
                     if (!Files.isExecutable(arg2x)) {
                        try {
                           if (!arg2x.toFile().setExecutable(true)) {
                              this.method7(
                                 "Warning: Failed to set executable permission for " + arg2x + ". It might already be set or permissions are insufficient."
                              );
                           }
                        } catch (SecurityException securityexception4x) {
                           this.method6("Warning: Could not set executable permission for " + arg2x + " due to security manager: " + securityexception4x.getMessage(), securityexception4x);
                        }
                     }
                  }
               }
            );
      }
   }

   private void method6(String text1, Exception exception2) {
      if (this.field3 != null) {
         this.field3.method3(text1, exception2);
      } else {
         System.err.println("Error: " + text1);
         if (exception2 != null) {
            CrashReporter.method5(exception2, "FFmpegDownloader");
         }
      }
   }

   private void method7(String text1) {
      if (this.field3 != null) {
         this.field3.onMessage(text1);
      } else {
         System.out.println("Info: " + text1);
      }
   }
}
