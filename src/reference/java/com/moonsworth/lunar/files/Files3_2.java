package com.moonsworth.lunar.files;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

public class Files3_2 {
   public static final int field1 = 7;
   public static final Duration field2 = Duration.ofMillis(180000L);
   public static final Duration field3 = field2;
   public static final HttpClient field4 = HttpClient.newBuilder()
      .connectTimeout(field2)
      .executor(Executors.newSingleThreadExecutor())
      .followRedirects(Redirect.NORMAL)
      .build();

   public static boolean method1(Path var0) {
      try {
         if (java.nio.file.Files.notExists(var0)) {
            return true;
         }

         String var1 = var0.toString();
         if (!var1.endsWith(".zip") && !var1.endsWith(".jar")) {
            return true;
         }

         try {
            new ZipFile(var0.toFile()).close();
            return false;
         } catch (Exception var3) {
            java.nio.file.Files.delete(var0);
            return true;
         }
      } catch (Throwable var4) {
         throw var4;
      }
   }

   @Nullable
   public static String method2(String var0) {
      try {
         byte[] var1 = method3(var0);
         return var1 == null ? null : new String(var1, StandardCharsets.UTF_8);
      } catch (Throwable var2) {
         throw var2;
      }
   }

   public static byte[] method3(String var0) {
      try {
         return method4(new URL(var0), 0, null);
      } catch (MalformedURLException var2) {
         throw new FilesException("Bad URL", var2);
      }
   }

   public static byte[] method4(URL var0, int var1, @Nullable Exception var2) {
      if (var1 >= 7) {
         throw new FilesException("Maxed out retries in downloading " + var0, var2);
      }

      try {
         HttpRequest var3 = HttpRequest.newBuilder(var0.toURI()).timeout(field3).build();
         HttpResponse var4 = field4.send(var3, BodyHandlers.ofByteArray());
         int var5 = var4.statusCode();
         byte[] var6 = (byte[])var4.body();
         if (var5 >= 200 && var5 < 300 && var6 != null && var6.length != 0) {
            return var6;
         } else {
            FilesException var7 = new FilesException("Failed to download " + var0 + ": " + var5 + " body: " + (var6 == null ? "null" : new String(var6)), var2);
            if (var5 != 404 && var1 + 1 < 7) {
               return method4(var0, var1 + 1, var7);
            } else {
               throw var7;
            }
         }
      } catch (IOException var8) {
         return method4(var0, var1 + 1, var8);
      } catch (URISyntaxException | InterruptedException var9) {
         throw new FilesException("Failed request to " + var0, var9);
      }
   }

   public static void method5(List<String> var0, String var1, Predicate<byte[]> var2) {
      int var3 = 0;
      ArrayList var4 = new ArrayList();

      for (String var6 : var0) {
         try {
            var4.add(HttpRequest.newBuilder(new URI(var6 + var1)).timeout(field3).build());
         } catch (URISyntaxException var13) {
            throw new RuntimeException(var13);
         }
      }

      BodyHandler var15 = BodyHandlers.ofByteArray();
      ArrayList var16 = new ArrayList();

      do {
         for (HttpRequest var8 : var4) {
            int var9 = var3;

            do {
               try {
                  HttpResponse var10 = field4.send(var8, var15);
                  int var11 = var10.statusCode();
                  if (var11 == 404) {
                     var16.add(var8.uri() + " - Skipping, HTTP 404");
                     break;
                  }

                  byte[] var12 = (byte[])var10.body();
                  if (var11 >= 200 && var11 < 300 && var12 != null && var12.length != 0) {
                     if (var2.test(var12)) {
                        return;
                     }

                     var16.add(var8.uri() + " - Byte predicate failed");
                  } else {
                     var16.add(var8.uri() + " - HTTP " + var11);
                  }
               } catch (IOException | InterruptedException var14) {
                  var16.add(var8.uri() + " - Exception: " + var14);
               }
            } while (var9++ <= 7);
         }
      } while (var3++ <= 7);

      throw new IllegalStateException("Unable to download path: " + var1 + ". Attempt failure reasons: " + var16);
   }

   public static long copy(InputStream var0, OutputStream var1) {
      byte[] var2 = new byte[4096];
      long var3 = 0L;

      while (true) {
         int var5 = var0.read(var2);
         if (var5 == -1) {
            return var3;
         }

         var1.write(var2, 0, var5);
         var3 += var5;
      }
   }

   public static byte[] toByteArray(InputStream var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      copy(var0, var1);
      var0.close();
      return var1.toByteArray();
   }
}
